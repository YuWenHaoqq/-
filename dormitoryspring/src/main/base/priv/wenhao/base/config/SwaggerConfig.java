package priv.wenhao.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: SwaggerConfig
 * Author: yuWenHao
 * Date: 2019/12/17
 */

@Configuration
@EnableSwagger2
//@Profile({"dev"})
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
		//在配置好的配置类中增加此段代码即可
		ParameterBuilder ticketPar = new ParameterBuilder();
		ParameterBuilder ticketPar2=new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		ticketPar.name("token").description("登录校验")//name表示名称，description表示描述
				.modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();//required表示是否必填，defaultvalue表示默认值
		ticketPar2.name("aes").description("随机生成的res")
				.modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
		pars.add(ticketPar.build());//添加完此处一定要把下边的带***的也加上否则不生效
		pars.add(ticketPar2.build());

//————————————————
//        版权声明：本文为CSDN博主「QiaoRui_」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/QiaoRui_/article/details/80439815

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("宿舍管理")
				.description("毕业设计-余文豪")
				.version("1.0.3")
				.contact(new Contact("余文豪", "", "1471689575@qq.com"))
				.termsOfServiceUrl("")
				.build();
	}
}
