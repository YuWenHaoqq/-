package priv.wenhao.dormitory.controller;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.wenhao.base.advice.SecurityParameter;
import priv.wenhao.base.aop.StuLoginCheckAop;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.service.StudentService;
import priv.wenhao.dormitory.service.TeacherService;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "学生端接口")
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;

	/***
	 * Description:学生端/教师端 登录
	 * param:[]
	 * return:priv.wenhao.base.pojo.vo.ResultVo
	 * Author:yu wenhao
	 * date:2019/12/19
	 */
	@ApiOperation(value = "学生登录", httpMethod = "POST")
	@PostMapping("/login")
	@SecurityParameter(inDecode = true, outEncode = true)
	public ResultVo login(@RequestBody LoginQuery loginQuery, HttpServletRequest request) throws Exception {
		if (Strings.isNullOrEmpty(loginQuery.getAccount()) || Strings.isNullOrEmpty(loginQuery.getPassword())) {
			throw new BussinessException(3, "请输入账号或密码");
		}
		ResultVo resultVo = new ResultVo();
		if (loginQuery.getIdentity()==1) {
			studentService.login(loginQuery, request, resultVo);
		} else if (loginQuery.getIdentity()==2){
			teacherService.login(loginQuery,request,resultVo);
		}
		return resultVo;
	}

	@ApiOperation(value = "学生签到", httpMethod = "POST")
	@PostMapping("/signin")
	@StuLoginCheckAop
	public ResultVo singIn(String stuId) {
		ResultVo resultVo = new ResultVo();
		studentService.signIn(stuId,resultVo);
		return resultVo;
	}
	@ApiOperation(value = "返回本月学生签到的数目",httpMethod = "POST")
	@PostMapping("/signMonth")
	@StuLoginCheckAop
	public ResultVo signMonth(String stuId){
		ResultVo resultVo=new ResultVo();
		studentService.signMonth(stuId,resultVo);
		return resultVo;
	}
}
