package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("school_leave_history")
public class SchoolLeaveHistoryDto {
	@ApiModelProperty("学生请假主键")
	@TableId(value = "leave_history_id",type = IdType.AUTO)
	private Integer leaveHistoryId;
	@ApiModelProperty("教师id")
	@TableField("pk_teacher_id")
	@JsonIgnore
	private String teaId;
	@ApiModelProperty("学生姓名")
	@TableField("pk_stu_name")
	private String stuName;
	@ApiModelProperty("学生id")
	@TableField("pk_stu_id")
	private String stuId;
	@ApiModelProperty("是否审批成功")
	@TableField("is_success")
	private Integer success;
	@JsonIgnore
	@ApiModelProperty("创建时间")
	@TableField("leave_history_create")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@ApiModelProperty("修改时间")
	@TableField("leave_history_modify")
	private Date leaveHistoryModify;
	@ApiModelProperty("开始时间")
	@TableField("start_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@ApiModelProperty("结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("end_time")
	private Date endTime;
	@ApiModelProperty("请假时长")
	@TableField("leave_time")
	private String leaveTime;
	@ApiModelProperty("请假类型")
	@TableField("leave_type")
	private Integer leaveType;
	@ApiModelProperty("请假说明")
	@TableField("leave_des")
	private String leaveDes;
	@ApiModelProperty("是否删除")
	@TableField("is_deleted")
	@JsonIgnore
	private Integer deleted;

}
