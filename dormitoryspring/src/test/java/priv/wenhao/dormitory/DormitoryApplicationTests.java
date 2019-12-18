package priv.wenhao.dormitory;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DormitoryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//实现自动回滚
@Transactional
public class DormitoryApplicationTests {
	@Before
	public void init() throws Exception {
		System.out.println("开始测试---");
	}

	@After
	public void after() throws Exception {
		System.out.println("测试结束---");
	}

	@Test
	void contextLoads() {
	}

}
