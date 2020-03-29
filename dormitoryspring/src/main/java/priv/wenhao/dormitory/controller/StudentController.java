package priv.wenhao.dormitory.controller;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.wenhao.base.advice.SecurityParameter;
import priv.wenhao.base.aop.CheckTimeAop;
import priv.wenhao.base.aop.StuLoginCheckAop;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.query.BaseQuery;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.LeaveQuery;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.service.AdministratorsService;
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

	@Autowired
	private AdministratorsService administratorsService;

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
		}else if (loginQuery.getIdentity()==3){

			administratorsService.login(resultVo,loginQuery,request);

		}
		return resultVo;
	}

	/***
	* ClassName:StudentController
	* Description: 学生签到
	* param:[stuId]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@ApiOperation(value = "学生签到", httpMethod = "POST")
	@PostMapping("/signin")
	@StuLoginCheckAop
	@CheckTimeAop
	public ResultVo singIn(String stuId) {
		ResultVo resultVo = new ResultVo();
		studentService.signIn(stuId,resultVo);
		return resultVo;
	}

	/***
	* ClassName:StudentController
	* Description: 返回本月学生签到的数目
	* param:[stuId]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@ApiOperation(value = "返回本月学生签到的数目",httpMethod = "POST")
	@PostMapping("/signMonth")
	@StuLoginCheckAop
	public ResultVo signMonth(String stuId){
		ResultVo resultVo=new ResultVo();
		studentService.signMonth(stuId,resultVo);
		return resultVo;
	}
	/***
	* ClassName:StudentController
	* Description: 实现学生请假
	* param:[leaveQuery]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@ApiOperation(value = "学生请假",httpMethod = "POST")
	@PostMapping("/stuLeave")
	@StuLoginCheckAop
	public ResultVo stuLeave(@RequestBody LeaveQuery leaveQuery){
		ResultVo resultVo=new ResultVo();
//		System.out.println(leaveQuery.getLeaveDes());
		studentService.stuLeave(leaveQuery,resultVo);
		return resultVo;
	}


	/***
	* ClassName:StudentController
	* Description: 通过学生id获得签到的记录
	* param:[stuId, baseQuery]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@ApiOperation(value = "获得学生签到记录",httpMethod = "GET")
	@GetMapping("/getSignMessage")
	@StuLoginCheckAop
	public ResultVo getSignMessage(String stuId, @ModelAttribute BaseQuery baseQuery)throws Exception{
		ResultVo resultVo=new ResultVo();
		studentService.getSignMessage(baseQuery,stuId,resultVo);
		return resultVo;

	}
	/***
	* ClassName:StudentController
	* Description: 获得学生未签到的记录
	* param:[stuId, baseQuery]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@ApiOperation(value = "获得学生未签到的记录",httpMethod = "GET")
	@GetMapping("/getUnSignMessage")
	@StuLoginCheckAop
	public ResultVo getUnsignMessage(String stuId, @ModelAttribute BaseQuery baseQuery)throws Exception{
		ResultVo resultVo=new ResultVo();
		studentService.getUnsignMessage(baseQuery,stuId,resultVo);
		return resultVo;
	}
	@ApiOperation(value = "获得学生请假记录",httpMethod = "GET")
	@GetMapping("/getLeave")
	@StuLoginCheckAop
	public ResultVo getLeave(String stuId,@ModelAttribute BaseQuery baseQuery)throws Exception{
		ResultVo resultVo=new ResultVo();
		studentService.getLeave(baseQuery,stuId,resultVo);
		return resultVo;
	}
}
