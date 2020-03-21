package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("school_teacher")
public class SchoolTeacherDto {
	@ApiModelProperty("教师编号")
	@TableId("teacher_id")
	private String teacherId;
	@ApiModelProperty("班级编号")
	@TableField("pk_class_id")
	private Integer classId;
	@ApiModelProperty("教师名字")
	@TableField("teacher_name")
	private String teacherName;
	@ApiModelProperty("密码")
	@TableField("teacher_password")
	private String password;
	@ApiModelProperty("教师性别")
	@TableField("teacher_sex")
	private Integer sex;
	@ApiModelProperty("出生日期")
	@TableField("teacher_birthday")
	private Date birthday;
	@ApiModelProperty("教工职称")
	@TableField("teacher_prof")
	private String teacherProf;
	@ApiModelProperty("关联部门表")
	@TableField("teacher_depart_id")
	private Integer departId;
	@ApiModelProperty("创建时间")
	@TableField("teacher_create")
	private Date teacherCreate;
	@ApiModelProperty("修改时间")
	@TableField("teacher_modify")
	private Date teacherModify;
	@ApiModelProperty("是否删除")
	@TableField("is_deleted")
	private Integer deleted;
}
