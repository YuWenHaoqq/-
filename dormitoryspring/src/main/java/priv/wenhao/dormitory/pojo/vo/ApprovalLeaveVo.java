package priv.wenhao.dormitory.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import priv.wenhao.base.pojo.dto.SchoolLeaveHistoryDto;

import java.util.List;

@Data
public class ApprovalLeaveVo {
	@ApiModelProperty("总数量")
	private Integer total;
	@ApiModelProperty("数据")
	private List<SchoolLeaveHistoryDto> list;
}
