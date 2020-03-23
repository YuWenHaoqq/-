package priv.wenhao.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.pojo.dto.*;
import priv.wenhao.base.pojo.query.BaseQuery;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.UUIDUtil;
import priv.wenhao.dormitory.mapper.*;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.query.NoticeFormQuery;
import priv.wenhao.dormitory.pojo.query.UpdateLeaveQuery;
import priv.wenhao.dormitory.pojo.vo.LeaveMessageVo;
import priv.wenhao.dormitory.pojo.vo.SignMessageVo;
import priv.wenhao.dormitory.pojo.vo.UnsignMessageVo;
import priv.wenhao.dormitory.pojo.vo.UserVo;
import priv.wenhao.dormitory.service.TeacherService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * ClassName: TeacherServiceImpl
 * Description:
 * Author: yuWenHao
 * Date: 2020/3/9
 */

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private SchoolTeacherMapper schoolTeacherMapper;

	@Autowired
	private SchoolLeaveHistoryMapper schoolLeaveHistoryMapper;

	@Autowired
	private SchoolNoticeMapper schoolNoticeMapper;

	@Autowired
	private SchoolSignHistoryMapper schoolSignHistoryMapper;

	@Autowired
	private UnsignHistoryMapper unsignHistoryMapper;

	@Resource(name = "thirdTemplate")
	private RedisCommands<String, String> thirdTemplate;

	/***
	* ClassName:TeacherServiceImpl
	* Description: 教师登录
	* param:[loginQuery, request, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@Override
	public void login(LoginQuery loginQuery, HttpServletRequest request, ResultVo resultVo) {
		QueryWrapper<SchoolTeacherDto> wrapper = new QueryWrapper<SchoolTeacherDto>()
				.eq("teacher_id", loginQuery.getAccount())
				.eq("teacher_password", loginQuery.getPassword())
				.eq("is_deleted", 0);
		List<SchoolTeacherDto> list = schoolTeacherMapper.selectList(wrapper);
		if (list.size() == 1) {
			UserVo userVo = new UserVo();
			userVo.setStuId(list.get(0).getTeacherId());
			//			删除原本的token 因为设置时间后不能直接使用set进行更改
			if (thirdTemplate.exists(userVo.getStuId())!=0){
				thirdTemplate.del(userVo.getStuId());
			}
			//			生成token
			userVo.setToken(UUIDUtil.getUUID32());
			userVo.setUserName(list.get(0).getTeacherName());
//			userVo.setRsa(new String(Base64.getEncoder().encode(RsaUtil.publicKey.getEncoded())));
			resultVo.setMessage("登录成功");
			resultVo.setData(userVo);
//			将token添加到redis缓存
			SetArgs setArgs = SetArgs.Builder.nx().ex(60 * 60);
			thirdTemplate.set(userVo.getStuId(), userVo.getToken(), setArgs);
		} else {
			resultVo.setCode(3);
			resultVo.setMessage("账号或密码错误");
		}

	}

	/**
	* ClassName:TeacherServiceImpl
	* Description: 教师获得学生请假未审批的记录
	* param:[baseQuery, teaId]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@Override
	public void leaveByTea( String teaId,ResultVo resultVo) {
//		PageHelper.startPage(baseQuery.getPage(),baseQuery.getSize());
		QueryWrapper<SchoolLeaveHistoryDto> queryWrapper=new QueryWrapper<SchoolLeaveHistoryDto>()
				.eq("pk_teacher_id",teaId)
				.eq("is_success",0)
				.eq("is_deleted",0);
		List<SchoolLeaveHistoryDto> list=schoolLeaveHistoryMapper.selectList(queryWrapper);
//		取消了分页显示
//		PageInfo<SchoolLeaveHistoryDto> pageInfo=new PageInfo<>(list);
//		ApprovalLeaveVo approvalLeaveVo=new ApprovalLeaveVo();
//		approvalLeaveVo.setTotal((int)pageInfo.getTotal());
//		approvalLeaveVo.setList(list);
		resultVo.setMessage("查询成功");
		resultVo.setData(list);
	}

	/***
	* ClassName:TeacherServiceImpl
	* Description: 修改请假记录
	* param:[updateLeaveQuery, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/19
	*/
	@Override
	public void updateLeave(UpdateLeaveQuery updateLeaveQuery,ResultVo resultVo) {
		int rows=
		schoolLeaveHistoryMapper.updateLeave(updateLeaveQuery.getHistoryId(),updateLeaveQuery.getType());
		if (rows==updateLeaveQuery.getHistoryId().size()){
			resultVo.setMessage("审批成功");
		}else{
			resultVo.setCode(1);
			resultVo.setMessage("总共"+updateLeaveQuery.getHistoryId().size()+"条,"+"已成功"+rows+"条");
		}


	}

	/***
	* ClassName:TeacherServiceImpl
	* Description: 教師推送公告
	* param:[resultVo, noticeFormQuery]
	* return:void
	* Author:yu wenhao
	* date:2020/3/21
	*/
	@Override
	public void pushNotice(ResultVo resultVo, NoticeFormQuery noticeFormQuery) throws Exception {
		SchoolNoticeDto schoolNoticeDto=noticeFormQuery.getDto();
//		schoolNoticeDto.setTeaId();
		schoolNoticeDto.setReleaseTime(new Date());
		schoolNoticeDto.setDeleted(0);
		Integer row=
		schoolNoticeMapper.insert(schoolNoticeDto);
		if (row==1){
			resultVo.setMessage("推送成功");
		}else{
			resultVo.setMessage("推送失败,清尝试重新推送");
			resultVo.setData(noticeFormQuery);
		}
	}

	/***
	* ClassName:TeacherServiceImpl
	* Description: 教师查询请假记录
	* param:[resultVo, baseQuery]
	* return:void
	* Author:yu wenhao
	* date:2020/3/23
	*/
	@Override
	public void getLeaveByTeacher(ResultVo resultVo, BaseQuery baseQuery) {
		PageHelper.startPage(baseQuery.getPage(),baseQuery.getSize());
		QueryWrapper<SchoolLeaveHistoryDto> queryWrapper=new QueryWrapper<SchoolLeaveHistoryDto>()
				.eq("pk_teacher_id",baseQuery.getTeaId())
				.like(StringUtils.isNotBlank(baseQuery.getStuId()),"pk_stu_name",baseQuery.getStuId())
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

	/***
	* ClassName:TeacherServiceImpl
	* Description: 教师查询签到记录
	* param:[resultVo, baseQuery]
	* return:void
	* Author:yu wenhao
	* date:2020/3/23
	*/
	@Override
	public void getSignByTeacher(ResultVo resultVo, BaseQuery baseQuery) {
		QueryWrapper<SchoolSignHistoryDto> queryWrapper=new QueryWrapper<SchoolSignHistoryDto>()
				.eq("pk_teacher_id",baseQuery.getTeaId())
				.like(StringUtils.isNotBlank(baseQuery.getStuId()),"stu_name",baseQuery.getStuId())
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
	* ClassName:TeacherServiceImpl
	* Description: 教师查询未签到记录
	* param:[resultVo, baseQuery]
	* return:void
	* Author:yu wenhao
	* date:2020/3/23
	*/
	@Override
	public void getUnsignByTeacher(ResultVo resultVo, BaseQuery baseQuery) {
		PageHelper.startPage(baseQuery.getPage(),baseQuery.getSize());
		QueryWrapper<UnsignHistoryDto> queryWrapper=new QueryWrapper<UnsignHistoryDto>()
				.eq("pk_teacher_id",baseQuery.getTeaId())
				.like(StringUtils.isNotBlank(baseQuery.getStuId()),"stu_name",baseQuery.getStuId())
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
}
