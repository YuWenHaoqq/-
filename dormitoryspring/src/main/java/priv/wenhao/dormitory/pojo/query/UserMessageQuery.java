package priv.wenhao.dormitory.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: UserMessageQuery
 * Author: yuWenHao
 * Date: 2019/12/18
 */

@Data
public class UserMessageQuery {
	@ApiModelProperty("登录id")
	private String userId;
	@ApiModelProperty("登录名")
	private String userName;
}
