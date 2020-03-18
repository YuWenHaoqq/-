package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("school_unsign_history")
public class UnsignHistoryDto {
	@TableId(value = "unsign_history_id",type = IdType.AUTO)
	@ApiModelProperty("学生未签到表自增id")
	private Integer id;
	@TableField("pk_stu_id")
	@ApiModelProperty("关联学生表学号")
	private String stuId;
	@TableField("pk_teacher_id")
	@ApiModelProperty("teaId")
	private String teaId;
	@TableField("stu_name")
	@ApiModelProperty("学生姓名")
	private String stuName;
	@TableField("is_checked")
	@ApiModelProperty("教师是否确认")
	private Integer checked;
	@TableField("unsign_history_create")
	@ApiModelProperty("创建时间")
	private Date createTime;
	@TableField("unsign_history_modify")
	@ApiModelProperty("修改时间")
	private Date modifyTime;
	@TableField("is_deleted")
	@ApiModelProperty("是否删除")
	private Integer deleted;
}
