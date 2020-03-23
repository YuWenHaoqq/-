package priv.wenhao.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.pojo.dto.SchoolLeaveHistoryDto;
import priv.wenhao.base.pojo.dto.SchoolSignHistoryDto;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;
import priv.wenhao.base.pojo.dto.UnsignHistoryDto;
import priv.wenhao.base.pojo.query.BaseQuery;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.UUIDUtil;
import priv.wenhao.dormitory.mapper.SchoolLeaveHistoryMapper;
import priv.wenhao.dormitory.mapper.SchoolSignHistoryMapper;
import priv.wenhao.dormitory.mapper.SchoolStudentMapper;
import priv.wenhao.dormitory.mapper.UnsignHistoryMapper;
import priv.wenhao.dormitory.pojo.query.LeaveQuery;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.vo.LeaveMessageVo;
import priv.wenhao.dormitory.pojo.vo.SignMessageVo;
import priv.wenhao.dormitory.pojo.vo.UnsignMessageVo;
import priv.wenhao.dormitory.pojo.vo.UserVo;
import priv.wenhao.dormitory.service.StudentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: StudentServiceImpl
 * Author: yuWenHao
 * Date: 2019/12/19
 */

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private SchoolStudentMapper schoolStudentMapper;

	@Autowired
	private SchoolSignHistoryMapper schoolSignHistoryMapper;

	@Autowired
	private SchoolLeaveHistoryMapper schoolLeaveHistoryMapper;

	@Autowired
	private UnsignHistoryMapper unsignHistoryMapper;

	@Resource(name = "firstTemplate")
	private RedisCommands<String, String> firstTemplate;
//	@Autowired
//	private RedisCommands<String,String> testTemplate;

	/***
	 * Description:学生端登录
	 * param:[loginQuery, request, resultVo]
	 * return:void
	 * Author:yu wenhao
	 * date:2019/12/20
	 */
	@Override
	public void login(LoginQuery loginQuery, HttpServletRequest request, ResultVo resultVo) {
//		testTemplate.set

		QueryWrapper<SchoolStudentDto> wrapper = new QueryWrapper<SchoolStudentDto>()
				.eq("stu_id", loginQuery.getAccount())
				.eq("stu_password", loginQuery.getPassword())
				.eq("is_deleted", 0);
		List<SchoolStudentDto> list = schoolStudentMapper.selectList(wrapper);
		if (list.size() == 1) {
			UserVo userVo = new UserVo();
			userVo.setStuId(list.get(0).getStuId());
//			删除原本的token 因为设置时间后不能直接使用set进行更改
			if (firstTemplate.exists(userVo.getStuId())!=0){
				firstTemplate.del(userVo.getStuId());
			}
			//			生成token
			userVo.setToken(UUIDUtil.getUUID32());
			userVo.setUserName(list.get(0).getStuName());
//			userVo.setRsa(new String(Base64.getEncoder().encode(RsaUtil.publicKey.getEncoded())));
			resultVo.setMessage("登录成功");
			resultVo.setData(userVo);
//			将token添加到redis缓存
			SetArgs setArgs = SetArgs.Builder.nx().ex(60 * 60);
			firstTemplate.set(userVo.getStuId(), userVo.getToken(), setArgs);
		} else {
			resultVo.setCode(3);
			resultVo.setMessage("账号或密码错误");
		}
	}

	/***
	* ClassName:StudentServiceImpl
	* Description:学生签到
	* param:[stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2019/12/31
	*/
	@Override
	public void signIn(String stuId, ResultVo resultVo) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		QueryWrapper<SchoolSignHistoryDto> queryWrapper=new QueryWrapper<SchoolSignHistoryDto>()
				.eq("pk_stu_id",stuId)
				.eq("date_format(sign_history_create,'%Y-%m-%d')",dateString)
				.eq("is_deleted",0);
		List isSign=schoolSignHistoryMapper.selectList(queryWrapper);
		if (isSign.size()!=0){
			resultVo.setCode(2);
			resultVo.setMessage("亲,今天已经签到过了");
			return;
		}

		List<SchoolSignHistoryDto> list = schoolSignHistoryMapper.getSignMessageByStuId(stuId);
		if (list.size() == 0) {
			resultVo.setCode(-1);
			resultVo.setMessage("学生信息缺失,请联系教师添加");
			return;
		} else if (list.size() > 1) {
			resultVo.setCode(-1);
			resultVo.setMessage("学生信息异常,请联系教师");
			return;
		}
//		System.out.println(list.get(0).getContinueSignMonth());
		list.get(0).setStuId(stuId);
		list.get(0).setCreateTime(new Date());
		list.get(0).setDeleted(0);
		int row = schoolSignHistoryMapper.insert(list.get(0));
		if (row == 1) {
			resultVo.setMessage("签到成功");
		} else {
			resultVo.setCode(-1);
			resultVo.setMessage("签到异常");
		}
	}

	/***
	* ClassName:StudentServiceImpl
	* Description: 获得本月的所有签到
	* param:[stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@Override
	public void signMonth(String stuId, ResultVo resultVo) {
//		获得本月月份并查找签到的数据
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String dateString = formatter.format(currentTime);
		QueryWrapper<SchoolSignHistoryDto> queryWrapper=new QueryWrapper<SchoolSignHistoryDto>()
				.eq("pk_stu_id",stuId)
				.eq("date_format(sign_history_create,'%Y-%m')",dateString)
				.eq("is_deleted",0);
		List<SchoolSignHistoryDto> isSign=schoolSignHistoryMapper.selectList(queryWrapper);
//		将查到的数据的签到日期正例成list传给前端
		ArrayList<String> arrayList=new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		for (SchoolSignHistoryDto schoolSignHistoryDto:isSign){
			arrayList.add((schoolSignHistoryDto.getCreateTime().getMonth()+1)+"-"+schoolSignHistoryDto.getCreateTime().getDate());
		}
		resultVo.setCode(0);
		resultVo.setMessage("查询成功");
		resultVo.setData(arrayList);
	}

	/***
	* ClassName:StudentServiceImpl
	* Description: 提交请假
	* param:[leaveQuery, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@Override
	public void stuLeave(LeaveQuery leaveQuery, ResultVo resultVo) {
//		获得学生的老师教工号
		QueryWrapper<SchoolStudentDto> stuQuery=new QueryWrapper<SchoolStudentDto>()
				.eq("stu_id",leaveQuery.getStuNumber())
				.eq("is_deleted",0);
		List<SchoolStudentDto> stuList=schoolStudentMapper.selectList(stuQuery);
		if (stuList.size()!=1){
			resultVo.setCode(1);
			resultVo.setMessage("数据库数据错误,请老师查看");
			return;
		}
//		向请假表中新增的记录
		SchoolLeaveHistoryDto schoolLeaveHistoryDto=new SchoolLeaveHistoryDto();
		schoolLeaveHistoryDto.setStuId(leaveQuery.getStuNumber());
		schoolLeaveHistoryDto.setStuName(stuList.get(0).getStuName());
		schoolLeaveHistoryDto.setTeaId(stuList.get(0).getTeacherId());
		schoolLeaveHistoryDto.setSuccess(0);
		schoolLeaveHistoryDto.setCreateTime(new Date());
		schoolLeaveHistoryDto.setStartTime(leaveQuery.getLeaveDate().get(0));
		schoolLeaveHistoryDto.setEndTime(leaveQuery.getLeaveDate().get(1));
		schoolLeaveHistoryDto.setLeaveTime(leaveQuery.getLeaveTime());
		schoolLeaveHistoryDto.setLeaveType(leaveQuery.getLeaveType());
		schoolLeaveHistoryDto.setLeaveDes(leaveQuery.getLeaveDes());
		schoolLeaveHistoryDto.setDeleted(0);

		int row= schoolLeaveHistoryMapper.insert(schoolLeaveHistoryDto);
		if (row==0){
			resultVo.setCode(1);
			resultVo.setMessage("请求失败,请重新填写");
		}else if(row==1){
			resultVo.setCode(0);
			resultVo.setMessage("请假提交成功,等待审批");
		}else{
			resultVo.setCode(2);
			resultVo.setMessage("未知异常,请尝试重新填写");
		}


	}

	/***
	* ClassName:StudentServiceImpl
	* Description: 获得自己的签到记录
	* param:[baseQuery, stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@Override
	public void getSignMessage(BaseQuery baseQuery, String stuId, ResultVo resultVo) {
		QueryWrapper<SchoolSignHistoryDto> queryWrapper=new QueryWrapper<SchoolSignHistoryDto>()
				.eq("pk_stu_id",stuId)
				.eq("is_deleted",0)
				.orderByDesc("sign_history_id");
		PageHelper.startPage(baseQuery.getPage(),baseQuery.getSize());
		List<SchoolSignHistoryDto>list=schoolSignHistoryMapper.selectList(queryWrapper);
		PageInfo<SchoolSignHistoryDto> pageInfo=new PageInfo<>(list);
		resultVo.setMessage("查询成功");
		SignMessageVo signMessageVo=new SignMessageVo();
		signMessageVo.setTotal((int)pageInfo.getTotal());
		signMessageVo.setList(list);
		resultVo.setData(signMessageVo);

	}

	/***
	* ClassName:StudentServiceImpl
	* Description: 获得自己未签到的记录
	* param:[baseQuery, stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@Override
	public void getUnsignMessage(BaseQuery baseQuery, String stuId, ResultVo resultVo) {
		PageHelper.startPage(baseQuery.getPage(),baseQuery.getSize());
		QueryWrapper<UnsignHistoryDto> queryWrapper=new QueryWrapper<UnsignHistoryDto>()
				.eq("pk_stu_id",stuId)
				.eq("is_deleted",0)
				.orderByDesc("unsign_history_id");
		List<UnsignHistoryDto> list=
		unsignHistoryMapper.selectList(queryWrapper);
		PageInfo<UnsignHistoryDto> pageInfo=new PageInfo<>(list);
		UnsignMessageVo unsignMessageVo=new UnsignMessageVo();
		unsignMessageVo.setTotal((int)pageInfo.getTotal());
		unsignMessageVo.setList(list);
		resultVo.setMessage("查询成功");
		resultVo.setData(unsignMessageVo);
	}

	/***
	* ClassName:StudentServiceImpl
	* Description:获得请假记录
	* param:[baseQuery, stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/23
	*/
	@Override
	public void getLeave(BaseQuery baseQuery, String stuId, ResultVo resultVo) {
		PageHelper.startPage(baseQuery.getPage(),baseQuery.getSize());
		QueryWrapper<SchoolLeaveHistoryDto> queryWrapper=new QueryWrapper<SchoolLeaveHistoryDto>()
				.eq("pk_stu_id",stuId)
				.eq("is_deleted",0)
				.orderByDesc("leave_history_id");
		List<SchoolLeaveHistoryDto> list=schoolLeaveHistoryMapper.selectList(queryWrapper);
		PageInfo<SchoolLeaveHistoryDto> pageInfo=new PageInfo<>(list);
		LeaveMessageVo leaveMessageVo=new LeaveMessageVo();
		leaveMessageVo.setTotal((int)pageInfo.getTotal());
		leaveMessageVo.setList(list);
		resultVo.setMessage("查询成功");
		resultVo.setData(leaveMessageVo);

	}
}
