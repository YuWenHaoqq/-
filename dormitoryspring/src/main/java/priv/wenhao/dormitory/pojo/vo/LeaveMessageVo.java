package priv.wenhao.dormitory.pojo.vo;

import lombok.Data;
import priv.wenhao.base.pojo.dto.SchoolLeaveHistoryDto;

import java.util.List;

@Data
public class LeaveMessageVo {
	private Integer total;
	private List<SchoolLeaveHistoryDto> list;
}
