package priv.wenhao.dormitory.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.service.SchoolNoticeService;

@RestController
@Api(tags = "公告板块")
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private SchoolNoticeService schoolNoticeService;

	/***
	* ClassName:NoticeController
	* Description: 获取公告
	* param:[]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/9
	*/
	@ApiOperation(value = "获取公告",httpMethod = "POST")
	@PostMapping("/getNotice")
	public ResultVo getNotice(@ApiParam(value = "页数",required = true) @RequestParam(required = true) int page) throws Exception{
		ResultVo resultVo=new ResultVo();
		schoolNoticeService.getNotice(page,resultVo);
		return resultVo;
	}
	/***
	* ClassName:NoticeController
	* Description: 推送公告
	* param:[]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/11
	*/
	@ApiOperation(value = "获取完整公告",httpMethod = "POST")
	@PostMapping("/getNoticeContent")
	public ResultVo getNoticeContent(@ApiParam("公告id")@RequestParam Integer id){
		ResultVo resultVo=new ResultVo();
		if (id==null || id==0 ){
			resultVo.setCode(1);
			resultVo.setMessage("参数错误");
			return resultVo;
		}
		schoolNoticeService.getNoticeContent(resultVo,id);
		return resultVo;
	}
}
