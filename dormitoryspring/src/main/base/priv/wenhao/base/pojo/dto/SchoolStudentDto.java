package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("school_student")
public class SchoolStudentDto {
	@ApiModelProperty("学号")
	@TableId(value = "stu_id")
	private String stuId;
	@ApiModelProperty(value = "关联班级表id")
	@TableField("pk_class_id")
	private Integer classId;
	@ApiModelProperty(value = "关联角色表")
	@TableField("pk_role_id")
	private Integer roleId=4;
	@ApiModelProperty("学生姓名")
	@TableField(value = "stu_name")
	private String stuName;
	@ApiModelProperty("学生性别")
	@TableField(value = "stu_sex")
	private int stuSex;
//	@ApiModelProperty("账号")
//	@TableField(value = "stu_account")
//	private String stuAccount;
	@ApiModelProperty("密码")
	@TableField(value = "stu_password")
	private String stuPassword;
	@ApiModelProperty("学生出生年月")
	@TableField(value = "stu_birthday")
	private Date stuBirthday;
	@ApiModelProperty("教师工号")
	@TableField(value = "pk_teacher_id")
	private String teacherId;
	@ApiModelProperty("寝室表id")
	@TableField(value = "pk_dorm_id")
	private String dormId;
	@ApiModelProperty("创建时间")
	@TableField(value = "stu_create")
	private Date stuCreate;
	@ApiModelProperty("修改时间")
	@TableField(value = "stu_modify")
	private Date stuModify;
	@ApiModelProperty("是否删除")
	@TableField(value = "is_deleted")
	private Integer deleted;
}
