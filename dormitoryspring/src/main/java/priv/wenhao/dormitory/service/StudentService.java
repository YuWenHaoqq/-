package priv.wenhao.dormitory.service;

import priv.wenhao.base.pojo.query.BaseQuery;
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

	/***
	* ClassName:StudentService
	* Description: 获得自己的签到记录
	* param:[page]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	public void getSignMessage(BaseQuery baseQuery, String stuId, ResultVo resultVo);

	/***
	* ClassName:StudentService
	* Description: 获得自己未签到的记录
	* param:[baseQuery, stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	public void getUnsignMessage(BaseQuery baseQuery,String stuId,ResultVo resultVo);

	/***
	* ClassName:StudentService
	* Description: 获得请假记录
	* param:[baseQuery, stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/18
	*/
	public void getLeave(BaseQuery baseQuery,String stuId,ResultVo resultVo);
}
