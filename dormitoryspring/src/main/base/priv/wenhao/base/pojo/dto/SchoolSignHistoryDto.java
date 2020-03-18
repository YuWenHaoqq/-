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
@TableName("school_sign_history")
public class SchoolSignHistoryDto {
	@JsonIgnore
	@ApiModelProperty("学生签到表id")
	@TableId(value = "sign_history_id",type = IdType.AUTO )
	private Integer signHistoryId;
	@ApiModelProperty("关联学生表学号")
	@TableField("pk_stu_id")
	private String stuId;
	@ApiModelProperty("关联教师表工号")
	@TableField("pk_teacher_id")
	@JsonIgnore
	private String teacherId;
	@ApiModelProperty("学生名字")
	@TableField("stu_name")
	private String stuName;
	@ApiModelProperty("当月连续签到的天数")
	@TableField("continue_sign_month")
	private Integer continueSignMonth;
	@ApiModelProperty("创建时间")
	@TableField("sign_history_create")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	@JsonIgnore
	@ApiModelProperty("修改时间")
	@TableField("sign_history_modify")
	private Date modifyTime;
	@JsonIgnore
	@ApiModelProperty("是否删除")
	@TableField("is_deleted")
	private Integer deleted;
}
