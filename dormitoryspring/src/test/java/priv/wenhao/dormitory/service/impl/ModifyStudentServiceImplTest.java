package priv.wenhao.dormitory.service.impl; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.DormitoryApplicationTests;
import priv.wenhao.dormitory.pojo.query.StudentQuery;
import priv.wenhao.dormitory.pojo.query.UserMessageQuery;
import priv.wenhao.dormitory.service.ModifyStudentService;

/** 
* ModifyStudentServiceImpl Tester. 
* 
* @author yu wenhao
* @since <pre>12/18/2019</pre> 
* @version 1.0 
*/ 

public class ModifyStudentServiceImplTest extends DormitoryApplicationTests {
	@Autowired
	private ModifyStudentService modifyStudentService;


/** 
* 
* Method: addStudent(UserMessageQuery userMessageQuery, StudentQuery studentQuery, ResultVo resultVo) 
* 
*/ 
@Test
public void testAddStudent() throws Exception {
	UserMessageQuery userMessageQuery=new UserMessageQuery();
	StudentQuery studentQuery=new StudentQuery();
	ResultVo resultVo=new ResultVo();
	modifyStudentService.addStudent(userMessageQuery,studentQuery,resultVo);
	System.out.println(resultVo.getCode());
	System.out.println(resultVo.getMessage());
}


} 
