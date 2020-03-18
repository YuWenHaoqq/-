package priv.wenhao.base.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseQuery {
	@ApiModelProperty("第几页")
	private Integer page;
	@ApiModelProperty("一页中获得的数据数")
	private Integer size;
}
