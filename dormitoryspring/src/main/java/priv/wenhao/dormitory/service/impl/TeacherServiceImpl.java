package priv.wenhao.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.pojo.dto.SchoolLeaveHistoryDto;
import priv.wenhao.base.pojo.dto.SchoolTeacherDto;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.UUIDUtil;
import priv.wenhao.dormitory.mapper.SchoolLeaveHistoryMapper;
import priv.wenhao.dormitory.mapper.SchoolTeacherMapper;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.query.UpdateLeaveQuery;
import priv.wenhao.dormitory.pojo.vo.UserVo;
import priv.wenhao.dormitory.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
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
}
