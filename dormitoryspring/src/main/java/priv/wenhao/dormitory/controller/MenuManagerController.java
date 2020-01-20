package priv.wenhao.dormitory.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.wenhao.base.pojo.vo.ResultVo;

@Api(tags = "获得菜单中的路径")
@RestController
@RequestMapping("/menu")
public class MenuManagerController {
	@ApiOperation(value = "学生端菜单获取",httpMethod = "GET")
	@GetMapping("/stu")
	public ResultVo stuMenu(){
		ResultVo resultVo=new ResultVo();
		return resultVo;
	}
}
