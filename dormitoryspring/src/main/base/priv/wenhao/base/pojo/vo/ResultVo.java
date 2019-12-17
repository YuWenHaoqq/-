package priv.wenhao.base.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResultVo {
	@ApiModelProperty("返回的请求码,0表示成功,其他表示失败")
	private int code=0;
	@ApiModelProperty("返回的描述")
	private String message;
	@ApiModelProperty("返回的数据")
	private Object data;
}
