package priv.wenhao.dormitory.service;

import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.LeaveQuery;
import priv.wenhao.dormitory.pojo.query.LoginQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: StudentService
 * Author: yuWenHao
 * Date: 2019/12/19
 */

public interface StudentService {
	/***
	* Description:学生登录
	* param:[loginQuery, request]
	* return:void
	* Author:yu wenhao
	* date:2019/12/19
	*/
	public void login(LoginQuery loginQuery, HttpServletRequest request, ResultVo resultVo);

	/***
	* ClassName:StudentService
	* Description:实现学生签到
	* param:[stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2019/12/31
	*/
	public void signIn(String stuId,ResultVo resultVo);
	/***
	* ClassName:StudentService
	* Description: 获得本月的所有签到
	* param:[stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/10
	*/
	public void signMonth(String stuId,ResultVo resultVo);
	/***
	* ClassName:StudentService
	* Description: 提交请假
	* param:[]
	* return:void
	* Author:yu wenhao
	* date:2020/3/11
	*/
	public void stuLeave(LeaveQuery leaveQuery,ResultVo resultVo);
}
