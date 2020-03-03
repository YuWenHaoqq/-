package priv.wenhao.dormitory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"priv.wenhao.dormitory","priv.wenhao.base"})
@ServletComponentScan(basePackages = {"priv.wenhao.dormitory","priv.wenhao.base"})
@MapperScan(basePackages = {"priv.wenhao.dormitory.mapper"})
//启动定时器
@EnableScheduling
public class DormitoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DormitoryApplication.class, args);
//		SpringApplication springApplication = new SpringApplication(DormitoryApplication.class);
//		ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
		System.out.println("Ready Perfectly!");
//		RocketMQProperties rocketMQProperties=configurableApplicationContext
//				.getBean(RocketMQProperties.class);
//		System.out.println(rocketMQProperties.getSubscribe().get(0));
	}

}
