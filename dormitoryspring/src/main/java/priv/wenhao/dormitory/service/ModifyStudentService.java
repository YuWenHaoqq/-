package priv.wenhao.dormitory.service;

import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.StudentQuery;
import priv.wenhao.dormitory.pojo.query.UserMessageQuery;

/**
 * Description: ModifyStudentService
 * Author: yuWenHao
 * Date: 2019/12/18
 */

public interface ModifyStudentService {

	/***
	* Description:新增学生
	* param:[userMessageQuery, studentQuery, resultVo]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2019/12/18
	*/
	public void addStudent(UserMessageQuery userMessageQuery, StudentQuery studentQuery, ResultVo resultVo)throws Exception;
}
