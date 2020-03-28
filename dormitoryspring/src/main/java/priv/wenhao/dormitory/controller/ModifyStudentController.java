package priv.wenhao.dormitory.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.wenhao.base.aop.AdminLogingAop;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.pojo.query.StudentQuery;
import priv.wenhao.dormitory.pojo.query.UserMessageQuery;
import priv.wenhao.dormitory.service.ModifyStudentService;

@Api(tags = "操作学生信息接口")
@RestController
@RequestMapping("/modifyStudent")
public class ModifyStudentController {
	@Autowired
	private ModifyStudentService modifyStudentService;

	/***
	 * Description:ModifyStudentController
	 * param:[studentQuery, userMessageQuery]
	 * return:priv.wenhao.base.pojo.vo.ResultVo
	 * Author:yu wenhao
	 * date:2019/12/18
	 */
	@ApiOperation(value = "新增学生", httpMethod = "POST")
	@PostMapping("/addStudent")
	@AdminLogingAop
	public ResultVo addStudent(@ModelAttribute StudentQuery studentQuery,
							   @ModelAttribute UserMessageQuery userMessageQuery) throws Exception {
		ResultVo resultVo = new ResultVo();
		modifyStudentService.addStudent(userMessageQuery,studentQuery,resultVo);
		return resultVo;
	}
}
