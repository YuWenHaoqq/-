package priv.wenhao.dormitory.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVo {
	@ApiModelProperty("学号")
	private String  stuId;
	@ApiModelProperty("用户名字")
	private String userName;
	@ApiModelProperty("凭证")
	private String token;
}
