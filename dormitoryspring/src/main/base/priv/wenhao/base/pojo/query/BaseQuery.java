package priv.wenhao.base.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseQuery {
	@ApiModelProperty("第几页")
	private Integer page=1;
	@ApiModelProperty("一页中获得的数据数")
	private Integer size=10;
	@ApiModelProperty("学生id")
	private String stuId;
	@ApiModelProperty("教师id")
	private String teaId;
	@ApiModelProperty("学生姓名")
	private String stuNme;
}
