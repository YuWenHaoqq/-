package priv.wenhao.dormitory.service.impl; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.DormitoryApplicationTests;
import priv.wenhao.dormitory.service.StudentService;

/** 
* StudentServiceImpl Tester. 
* 
* @author yu wenhao
* @since <pre>12/31/2019</pre> 
* @version 1.0 
*/ 

public class StudentServiceImplTest extends DormitoryApplicationTests {

	@Autowired
	private StudentService studentService;


/** 
* 
* Method: login(LoginQuery loginQuery, HttpServletRequest request, ResultVo resultVo) 
* 
*/ 
@Test
public void testLogin() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: signIn(String stuId, ResultVo resultVo) 
* 
*/ 
@Test
public void testSignIn() throws Exception { 
	ResultVo resultVo=new ResultVo();
	studentService.signIn("173067113",resultVo);
} 


} 
