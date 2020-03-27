package priv.wenhao.dormitory.pojo.vo;

import lombok.Data;
import priv.wenhao.base.pojo.dto.SchoolUnsignHistoryDto;

import java.util.List;

@Data
public class UnsignMessageVo {
	private Integer total;
	private List<SchoolUnsignHistoryDto> list;
}
