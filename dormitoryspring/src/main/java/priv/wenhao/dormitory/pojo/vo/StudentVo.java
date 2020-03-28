package priv.wenhao.dormitory.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class StudentVo {
	@ApiModelProperty("学生主键id")
	private String stuId;
	@ApiModelProperty("班级名字")
	private String className;
	@ApiModelProperty("学生名字")
	private String stuName;
	@ApiModelProperty("学生性别")
	private Integer stuSex;
	@ApiModelProperty("密码")
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty("出生年月")
	private Date birthday;
	@ApiModelProperty("教师名字")
	private String teacherName;
	@ApiModelProperty("寝室编号")
	private String dormName;
	@ApiModelProperty("教工号")
	private String teaId;
}
