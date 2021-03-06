package priv.wenhao.dormitory.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import priv.wenhao.base.aop.AdminLogingAop;
import priv.wenhao.base.configvalue.TemplateValue;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.FileUtil;
import priv.wenhao.dormitory.pojo.query.StudentQuery;
import priv.wenhao.dormitory.pojo.vo.TeacherVo;
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
	* Description: 使用excel添加学生
	* param:[multipartFile]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/26
	*/
	@ApiOperation(value = "添加学生",httpMethod = "POST")
	@PostMapping("/addStu")
	@AdminLogingAop
	public ResultVo addStu(@RequestParam("file")MultipartFile multipartFile) throws BussinessException {
		ResultVo resultVo=new ResultVo();
		administratorsService.addStu(resultVo,multipartFile);
		return resultVo;
	}

	/***
	* ClassName:AdministratorsController
	* Description: 使用excel表增加学生信息
	* param:[multipartFile]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/28
	*/
	@ApiOperation(value = "使用excel添加教师",httpMethod = "POST")
	@PostMapping("/addTeaByExcel")
	@AdminLogingAop
	public ResultVo addTeaByExcel(@RequestParam("file")MultipartFile file) throws BussinessException {
		ResultVo resultVo=new ResultVo();
		administratorsService.addTeaByExcel(resultVo,file);
		return resultVo;

	}

	/***
	* ClassName:AdministratorsController
	* Description: 修改学生信息
	* param:[studentQuery]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/27
	*/
	@ApiOperation(value = "修改学生",httpMethod = "POST")
	@PostMapping("/modifyStu")
	@AdminLogingAop
	public ResultVo modifyStu(@RequestBody StudentQuery studentQuery){
		ResultVo resultVo=new ResultVo();
		administratorsService.modifyStu(resultVo,studentQuery);
		return resultVo;

	}

	/***
	* ClassName:AdministratorsController
	* Description: 获得所有学生的信息
	* param:[]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/27
	*/
	@ApiOperation(value = "获得所有学生信息",httpMethod = "POST")
	@PostMapping("/getAllStu")
	@AdminLogingAop
	public ResultVo getAllStu(){
		ResultVo resultVo=new ResultVo();
		administratorsService.getAllStu(resultVo);
		return resultVo;

	}

	/***
	* ClassName:AdministratorsController
	* Description:
	* param:[]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/28
	*/
	@ApiOperation(value = "获得所有的教师信息",httpMethod = "POST")
	@PostMapping("/getAllTea")
	@AdminLogingAop
	public ResultVo getAllTea(){
		ResultVo resultVo=new ResultVo();
		administratorsService.getAllTea(resultVo);
		return resultVo;
	}

	/***
	* ClassName:AdministratorsController
	* Description:修改教师信息
	* param:[teacherVo]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/28
	*/

	@ApiOperation(value = "修改教师信息",httpMethod = "POST")
	@PostMapping("/modifyTea")
	@AdminLogingAop
	public ResultVo modifyTea(@RequestBody TeacherVo teacherVo){
		ResultVo resultVo=new ResultVo();
		administratorsService.modifyTea(resultVo,teacherVo);
		return resultVo;
	}

	/***
	* ClassName:AdministratorsController
	* Description: 删除学生信息
	* param:[stuId]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/28
	*/
	@ApiOperation(value = "删除学生信息",httpMethod = "POST")
	@PostMapping("/delStu")
	@AdminLogingAop
	public ResultVo delStu(String stuId){
		ResultVo resultVo=new ResultVo();
		administratorsService.delStu(resultVo,stuId);
		return resultVo;
	}

	/***
	* ClassName:AdministratorsController
	* Description:
	* param:[teaId]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2020/3/28
	*/
	@ApiOperation(value = "删除教师信息",httpMethod = "POST")
	@PostMapping("/delTea")
	@AdminLogingAop
	public ResultVo delTea(String teaId){
		ResultVo resultVo=new ResultVo();
		administratorsService.delTea(resultVo,teaId);

		return resultVo;
	}

//	/***
//	* ClassName:AdministratorsController
//	* Description: 修改学生信息接口
//	* param:[studentVo]
//	* return:priv.wenhao.base.pojo.vo.ResultVo
//	* Author:yu wenhao
//	* date:2020/3/28
//	*/
//	@ApiOperation(value = "修改学生信息",httpMethod = "POST")
//	@PostMapping("/modifyStu")
//	@AdminLogingAop
//	public ResultVo modifyStu(@RequestBody StudentVo studentVo){
//		ResultVo resultVo=new ResultVo();
//		return resultVo;
//	}
}
