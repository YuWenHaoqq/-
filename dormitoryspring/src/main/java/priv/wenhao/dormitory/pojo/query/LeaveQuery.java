package priv.wenhao.dormitory.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LeaveQuery {
	@ApiModelProperty("请假类型")
	private int leaveType;
	@ApiModelProperty("请假时间")
	private List<Date> leaveDate;
//	@ApiModelProperty("开始时间")
//	private Date startTime;
//	@ApiModelProperty("结束时间")
//	private Date endTime;
	@ApiModelProperty("请假时长")
	private String leaveTime;
	@ApiModelProperty("请假理由")
	private String leaveDes;
	@ApiModelProperty("学生学号")
	private String stuNumber;
}
