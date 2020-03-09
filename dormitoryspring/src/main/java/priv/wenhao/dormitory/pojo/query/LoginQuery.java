package priv.wenhao.dormitory.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginQuery {
	@ApiModelProperty(value = "账号")
	private String account;
	@ApiModelProperty(value = "密码")
	private String password;
	@ApiModelProperty(value = "类型")
	private Integer identity;
}
