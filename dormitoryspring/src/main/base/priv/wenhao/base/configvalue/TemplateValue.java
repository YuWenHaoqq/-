package priv.wenhao.base.configvalue;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: TemplateValue
 * Description: 获取配置文件信息
 * Author: yuWenHao
 * Date: 2020/3/26
 */

@Configuration
@Getter
@PropertySource(value = {"classpath:config/filerouting.properties"},encoding = "utf-8")
public class TemplateValue {
	@Value("${template.stu}")
	private String stuTemplate;
	@Value("${template.stu.title}")
	private String stuTitle;
	@Value("${template.tea}")
	private String teaTemplate;
	@Value("${template.tea.title}")
	private String teaTitle;
}
