package priv.wenhao.dormitory.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import priv.wenhao.base.aop.AdminLogingAop;
import priv.wenhao.base.configvalue.TemplateValue;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.FileUtil;
import priv.wenhao.dormitory.service.AdministratorsService;

import java.io.File;
import java.io.IOException;

@Api(tags = "管理员接口")
@RequestMapping("/admin")
@RestController
@Slf4j
public class AdministratorsController {
	@Autowired
	private TemplateValue templateValue;
	@Autowired
	private AdministratorsService administratorsService;

	/***
	 * ClassName:AdministratorsController
	 * Description: 获得学生/教师录入模板文件
	 * param:[type]
	 * return:org.springframework.http.ResponseEntity<org.springframework.core.io.FileSystemResource>
	 * Author:yu wenhao
	 * date:2020/3/26
	 */
	@ApiOperation(value = "获得学生录入模板", httpMethod = "POST")
	@PostMapping("/stuFile")
	@AdminLogingAop
	public ResponseEntity<FileSystemResource> stuFile(Integer type) throws Exception {
		String router;
		switch (type) {
			case 1:
				router = templateValue.getStuTemplate();
				break;
			case 2:
				router = templateValue.getTeaTemplate();
				break;
			default:
				return null;
		}
		Resource resource = new ClassPathResource(router);
		try {
			File file = resource.getFile();
			return FileUtil.exportFile(file);
		} catch (IOException e) {
			log.info(type + "模板下载错误");
//			throw new BussinessException(2,"服务器错误,请联系管理员");
		}
		return null;

	}


	/***
	* ClassName:AdministratorsController
	* Description: 添加学生
	* param:[multipartFile]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/26
	*/
	@ApiOperation(value = "添加学生",httpMethod = "POST")
	@PostMapping("/addStu")
	@AdminLogingAop
	public ResultVo addStu(@RequestParam("file")MultipartFile multipartFile){
		ResultVo resultVo=new ResultVo();
		System.out.println(123);
		administratorsService.addStu(resultVo,multipartFile);
		return resultVo;
	}

}
