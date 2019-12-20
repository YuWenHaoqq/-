package priv.wenhao.dormitory.controller;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.service.StudentService;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "学生端接口")
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	/***
	* Description:学生端登录
	* param:[]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2019/12/19
	*/
	@ApiOperation(value = "学生登录", httpMethod = "POST")
	@PostMapping("/login")
	public ResultVo login(@ModelAttribute LoginQuery loginQuery, HttpServletRequest request) throws Exception {
		if (Strings.isNullOrEmpty(loginQuery.getAccount())||Strings.isNullOrEmpty(loginQuery.getPassword())){
			throw new BussinessException(3,"请输入账号或密码");
		}
		ResultVo resultVo = new ResultVo();
		studentService.login(loginQuery,request,resultVo);
		return resultVo;
	}
}
