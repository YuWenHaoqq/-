package priv.wenhao.dormitory.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.wenhao.base.aop.TeacherLoginCheckAop;
import priv.wenhao.base.pojo.query.BaseQuery;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.NoticeFormQuery;
import priv.wenhao.dormitory.pojo.query.UpdateLeaveQuery;
import priv.wenhao.dormitory.service.TeacherService;

@Api(tags = "教师接口")
@RestController
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	/***
	* ClassName:TeacherController
	* Description: 通过教师id获得学生请假记录
	* param:[]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@ApiOperation(value = "教师端获得学生请假",httpMethod = "GET")
	@GetMapping("/leaveByTea")
	@TeacherLoginCheckAop
	public ResultVo leaveByTea(@ModelAttribute BaseQuery baseQuery, String teaId){
		ResultVo resultVo=new ResultVo();
		teacherService.leaveByTea(teaId,resultVo);
		return resultVo;
	}
	/**
	* ClassName:TeacherController
	* Description:审批学生请假
	* param:[]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/18
	*/
	@ApiOperation(value = "审批学生请假",httpMethod = "POST")
	@PostMapping("/approvalLeave")
	@TeacherLoginCheckAop
	public ResultVo approvalLeave(@RequestBody UpdateLeaveQuery updateLeaveQuery)throws Exception{
		ResultVo resultVo=new ResultVo();
		if (updateLeaveQuery.getType()==null){
			resultVo.setCode(2);
			resultVo.setMessage("参数错误");
			return resultVo;
		}
		if (updateLeaveQuery.getHistoryId().size()==0){
			resultVo.setMessage("修改成功");
			return resultVo;
		}
		teacherService.updateLeave(updateLeaveQuery,resultVo);
		return resultVo;
	}

	/***
	* ClassName:TeacherController
	* Description: 教师推送公告
	* param:[noticeFormQuery]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/21
	*/
	@ApiOperation(value = "教师推送公告",httpMethod = "POST")
	@PostMapping("/pushNotice")
	@TeacherLoginCheckAop
	public ResultVo pushNotice(@RequestBody NoticeFormQuery noticeFormQuery) throws Exception {
		ResultVo resultVo=new ResultVo();
		teacherService.pushNotice(resultVo,noticeFormQuery);
		return  resultVo;
	}

	/***
	* ClassName:TeacherController
	* Description: 教师查询请假记录
	* param:[baseQuery]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/21
	*/
	@ApiOperation(value = "教师查询请假记录",httpMethod = "GET")
	@GetMapping("/getLeaveByTeacher")
//	@TeacherLoginCheckAop
	public ResultVo getLeaveByTeacher(@ModelAttribute BaseQuery baseQuery){
		ResultVo resultVo=new ResultVo();
		teacherService.getLeaveByTeacher(resultVo,baseQuery);
		return resultVo;

	}

	/***
	* ClassName:TeacherController
	* Description: 教师查询签到记录
	* param:[]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/21
	*/
	@ApiOperation(value = "教师查询签到记录",httpMethod = "GET")
	@GetMapping("/getSignByTeacher")
//	@TeacherLoginCheckAop
	public ResultVo getSignByTeacher(@ModelAttribute BaseQuery baseQuery){
		ResultVo resultVo =new ResultVo();
		teacherService.getSignByTeacher(resultVo,baseQuery);
		return resultVo;
	}

	/***
	* ClassName:TeacherController
	* Description: 教师查询未签到记录
	* param:[baseQuery]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/21
	*/
	@ApiOperation(value = "教师查询未签到记录",httpMethod = "GET")
	@GetMapping("/getUnSignByTeacher")
//	@TeacherLoginCheckAop
	public ResultVo getUnsignByTeacher(@ModelAttribute BaseQuery baseQuery){
		ResultVo resultVo=new ResultVo();
		teacherService.getUnsignByTeacher(resultVo,baseQuery);
		return resultVo;

	}
}
