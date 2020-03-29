package priv.wenhao.dormitory.service;

import org.springframework.web.multipart.MultipartFile;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.query.StudentQuery;
import priv.wenhao.dormitory.pojo.vo.TeacherVo;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: AdministratorsService
 * Description: 管理员service层
 * Author: yuWenHao
 * Date: 2020/3/26
 */

public interface AdministratorsService {

	/***
	* ClassName:AdministratorsService
	* Description:
	* param:[]
	* return:void
	* Author:yu wenhao
	* date:2020/3/26
	*/
	public void addStu(ResultVo resultVo, MultipartFile file) throws BussinessException;

	/***
	* ClassName:AdministratorsService
	* Description: 使用excel添加学生
	* param:[resultVo, file]
	* return:void
	* Author:yu wenhao
	* date:2020/3/28
	*/
	public void addTeaByExcel(ResultVo resultVo,MultipartFile file) throws BussinessException;

	/***
	* ClassName:AdministratorsService
	* Description: 修改学生
	* param:[resultVo, studentQuery]
	* return:void
	* Author:yu wenhao
	* date:2020/3/27
	*/
	public void modifyStu(ResultVo resultVo, StudentQuery studentQuery);
	/***
	* ClassName:AdministratorsService
	* Description: 获得所有学生的信息
	* param:[]
	* return:void
	* Author:yu wenhao
	* date:2020/3/27
	*/
	public void getAllStu(ResultVo resultVo);

//	/***
//	* ClassName:AdministratorsService
//	* Description: 修改学生信息
//	* param:[resultVo, studentVo]
//	* return:void
//	* Author:yu wenhao
//	* date:2020/3/28
//	*/
//	public void modifyStu(ResultVo resultVo, StudentVo studentVo);
	/***
	* ClassName:AdministratorsService
	* Description: 获得所有额教师信息
	* param:[resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/28
	*/
	public void getAllTea(ResultVo resultVo);

	/***
	* ClassName:AdministratorsService
	* Description: 修改教师信息
	* param:[resultVo, teacherVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/28
	*/
	public void modifyTea(ResultVo resultVo, TeacherVo teacherVo);

	/***
	* ClassName:AdministratorsService
	* Description: 删除学生信息
	* param:[resultVo, stuId]
	* return:void
	* Author:yu wenhao
	* date:2020/3/28
	*/
	public void delStu(ResultVo resultVo,String stuId);

	/***
	* ClassName:AdministratorsService
	* Description: 删除学生信息
	* param:[resultVo, stuId]
	* return:void
	* Author:yu wenhao
	* date:2020/3/28
	*/
	public void delTea(ResultVo resultVo,String teaId);

	/***
	* ClassName:AdministratorsService
	* Description: 管理员登录
	* param:[resultVo, loginQuery, request]
	* return:void
	* Author:yu wenhao
	* date:2020/3/28
	*/
	public void login(ResultVo resultVo, LoginQuery loginQuery, HttpServletRequest request);
}
