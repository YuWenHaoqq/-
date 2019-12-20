package priv.wenhao.dormitory.service;

import priv.wenhao.base.pojo.vo.ResultVo;
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
}
