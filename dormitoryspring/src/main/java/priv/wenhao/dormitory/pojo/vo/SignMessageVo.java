package priv.wenhao.dormitory.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import priv.wenhao.base.pojo.dto.SchoolSignHistoryDto;

import java.util.List;

@Data
public class SignMessageVo {
	@ApiModelProperty("总条数")
	private Integer total;
	@ApiModelProperty("记录")
	private List<SchoolSignHistoryDto> list;
}
