package priv.wenhao.dormitory.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.util.FileUtil;

import java.io.File;
import java.io.IOException;

@Api(tags = "管理员接口")
@RequestMapping("/admin")
@RestController
@Slf4j
public class AdministratorsController {
	@ApiOperation(value = "获得学生录入模板",httpMethod = "POST")
	@PostMapping("/stuFile")
	public ResponseEntity<FileSystemResource> stuFile() throws Exception {
		Resource resource=new ClassPathResource("template"+"/嘉职院学生录入模板.xlsx");
		try {
			File file=resource.getFile();
//			System.out.println(new String(file.getName().getBytes("utf-8"),"ISO-8859-1"));
			return FileUtil.exportFile(file);
		} catch (IOException e) {
			log.info("学生模板下载错误");
			throw new BussinessException(2,"服务器错误,请联系管理员");
		}

	}
//	public void stuFile(HttpServletRequest request, HttpServletResponse response)throws Exception{
//		response.setCharacterEncoding(request.getCharacterEncoding());
//		response.setContentType("application/octet-stream");
//		FileInputStream fis=null;
//		OutputStream os=null;
//
//		try{
//			File file=new ClassPathResource("template"+"/嘉职院学生录入模板.xlsx").getFile();
//			fis=new FileInputStream(file);
//			response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
//			int len=0;
//			byte[]buffer=new byte[1024];
//			os=response.getOutputStream();
//			while ((len=fis.read(buffer))>0){
//				os.write(buffer,0,len);
//			}
//		}catch (Exception e){
//			log.info("学生模板下载错误");
//			e.printStackTrace();
//		}finally {
//			if (fis!=null) {
//				fis.close();
//			}
//			if (os!=null){
//				os.close();
//			}
//		}
//
//	}

	@ApiOperation(value = "获得教师录入模板",httpMethod = "POST")
	@PostMapping("/teaFile")
	public ResponseEntity<FileSystemResource> teaFile() throws Exception {
		return null;
	}
	
}
