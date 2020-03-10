package priv.wenhao.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.pojo.dto.SchoolSignHistoryDto;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.UUIDUtil;
import priv.wenhao.dormitory.mapper.SchoolSignHistoryMapper;
import priv.wenhao.dormitory.mapper.SchoolStudentMapper;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.vo.UserVo;
import priv.wenhao.dormitory.service.StudentService;

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
	private RedisCommands<String, String> firstTemplate;

	/***
	 * Description:学生端登录
	 * param:[loginQuery, request, resultVo]
	 * return:void
	 * Author:yu wenhao
	 * date:2019/12/20
	 */
	@Override
	public void login(LoginQuery loginQuery, HttpServletRequest request, ResultVo resultVo) {
		QueryWrapper<SchoolStudentDto> wrapper = new QueryWrapper<SchoolStudentDto>()
				.eq("stu_id", loginQuery.getAccount())
				.eq("stu_password", loginQuery.getPassword())
				.eq("is_deleted", 0);
		List<SchoolStudentDto> list = schoolStudentMapper.selectList(wrapper);
		if (list.size() == 1) {
			UserVo userVo = new UserVo();
			userVo.setStuId(list.get(0).getStuId());
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
//		String startTime = sdf.format(startTime);
		for (SchoolSignHistoryDto schoolSignHistoryDto:isSign){
//			arrayList.add(sdf.format(schoolSignHistoryDto.getCreateTime()));
//			System.out.println("=="+sdf.format(schoolSignHistoryDto.getCreateTime()));
//			System.out.println(schoolSignHistoryDto.getCreateTime().getMonth()+"-"+schoolSignHistoryDto.getCreateTime().getDate());
			arrayList.add((schoolSignHistoryDto.getCreateTime().getMonth()+1)+"-"+schoolSignHistoryDto.getCreateTime().getDate());

		}
		resultVo.setCode(0);
		resultVo.setMessage("查询成功");
		resultVo.setData(arrayList);
	}
}
