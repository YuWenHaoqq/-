package priv.wenhao.dormitory.service;

import priv.wenhao.base.pojo.query.BaseQuery;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.query.NoticeFormQuery;
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

	/***
	* ClassName:TeacherService
	* Description: 推送公告的接口
	* param:[]
	* return:void
	* Author:yu wenhao
	* date:2020/3/21
	*/
	public void pushNotice(ResultVo resultVo, NoticeFormQuery noticeFormQuery)throws Exception;

	/***
	* ClassName:TeacherService
	* Description: 教师查询请假记录
	* param:[resultVo, baseQuery]
	* return:void
	* Author:yu wenhao
	* date:2020/3/21
	*/
	public void getLeaveByTeacher(ResultVo resultVo, BaseQuery baseQuery);

	/***
	* ClassName:TeacherService
	* Description: 教师查询签到记录
	* param:[resultVo, baseQuery]
	* return:void
	* Author:yu wenhao
	* date:2020/3/23
	*/
	public void getSignByTeacher(ResultVo resultVo,BaseQuery baseQuery);

	/***
	* ClassName:TeacherService
	* Description: 教师查询未签到记录
	* param:[resultVo, baseQuery]
	* return:void
	* Author:yu wenhao
	* date:2020/3/23
	*/
	public void getUnsignByTeacher(ResultVo resultVo,BaseQuery baseQuery);
}
