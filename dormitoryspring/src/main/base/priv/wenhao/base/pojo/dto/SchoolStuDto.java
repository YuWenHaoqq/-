package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("school_student")
public class SchoolStuDto {
	@ApiModelProperty("学生id")
	@TableId("stu_id")
	private String stuId;
	@ApiModelProperty("班级表")
	@TableField("pk_class_id")
	private Integer classId;
	@ApiModelProperty("关联角色")
	@TableField("pk_role_id")
	private Integer roleId;
	@ApiModelProperty("学生姓名")
	@TableField("stu_name")
	private String stuName;
	@ApiModelProperty("学生性别")
	@TableField("stu_sex")
	private Integer stuSex;
	@ApiModelProperty("学生密码")
	@TableField("stu_password")
	private String password;
	@ApiModelProperty("学生出生年月")
	@TableField("stu_birthday")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	@ApiModelProperty("班主任编号")
	@TableField("pk_teacher_id")
	private String teacherId;
	@ApiModelProperty("寝室编号")
	@TableField("dorm_name")
	private String dormName;
	@ApiModelProperty("创建时间")
	@TableField("stu_create")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	@ApiModelProperty("修改时间")
	@TableField("stu_modify")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date modifyTime;
	@ApiModelProperty("是否删除")
	@TableField("is_deleted")
	private Integer deleted;

}
