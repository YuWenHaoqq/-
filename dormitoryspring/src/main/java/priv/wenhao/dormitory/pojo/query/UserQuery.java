package priv.wenhao.dormitory.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * ClassName: UserQuery
 * Description: 未来方便演示消息的发送和消费,我们创建一个简单的user类,作为发送和消息的载体
 * Author: yuWenHao
 * Date: 2020/2/24
 */

@Data
public class UserQuery implements Serializable {
	@ApiModelProperty("id")
	private String id;
	@ApiModelProperty("userNAme")
	private String userName;
}
