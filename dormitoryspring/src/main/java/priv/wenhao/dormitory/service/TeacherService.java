package priv.wenhao.dormitory.service;

import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.query.UpdateLeaveQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: TeacherService
 * Description:
 * Author: yuWenHao
 * Date: 2020/3/9
 */

public interface TeacherService {
	/***
	* ClassName:TeacherService
	* Description: 教师登录
	* param:[loginQuery, request, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/9
	*/
	public void login(LoginQuery loginQuery, HttpServletRequest request, ResultVo resultVo);

	/***
	* ClassName:TeacherService
	* Description: 获得学生请假
	* param:[]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	public void leaveByTea(String teaId,ResultVo resultVo);
	/***
	* ClassName:TeacherService
	* Description: 修改请假接口
	* param:[list]
	* return:void
	* Author:yu wenhao
	* date:2020/3/19
	*/
	public void updateLeave(UpdateLeaveQuery updateLeaveQuery,ResultVo resultVo);
}
