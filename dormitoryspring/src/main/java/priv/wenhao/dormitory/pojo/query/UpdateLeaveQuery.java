package priv.wenhao.dormitory.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateLeaveQuery {
	@ApiModelProperty("修改的请假id")
	private List<Integer> historyId;
	@ApiModelProperty("同意or不同意,1代表同意,0代表不同意")
	private Integer type;

}
