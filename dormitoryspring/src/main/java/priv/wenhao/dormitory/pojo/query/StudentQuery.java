package priv.wenhao.dormitory.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;

import java.util.Date;
/**
 * Description: StudentQuery
 * Author: yuWenHao
 * Date: 2019/12/18
 */
@Data
public class StudentQuery {
	@ApiModelProperty(value = "学号",required = true)
	private String stuId;
	@ApiModelProperty(value = "关联班级表id",required = true)
	private Integer classId;
	@ApiModelProperty(value = "学生姓名",required = true)
	private String stuName;
	@ApiModelProperty(value = "学生性别",required = true)
	private int stuSex;
	@ApiModelProperty(value = "账号",required = true)
	private String stuAccount;
	@ApiModelProperty(value = "密码",required = true)
	private String stuPassword;
	@ApiModelProperty(value = "学生出生年月",required = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date stuBirthday;
	@ApiModelProperty(value = "教师工号",required = true)
	private String teacherId;
	@ApiModelProperty(value = "寝室表id",required = true)
	private String dormId;

	public SchoolStudentDto getSchoolStudentDto(){
		SchoolStudentDto schoolStudentDto=new SchoolStudentDto();
		schoolStudentDto.setStuId(this.stuId);
		schoolStudentDto.setClassId(this.classId);
		schoolStudentDto.setStuName(this.stuName);
		schoolStudentDto.setStuSex(this.stuSex);
		schoolStudentDto.setStuAccount(this.stuAccount);
		schoolStudentDto.setStuPassword(this.stuPassword);
		schoolStudentDto.setStuBirthday(this.stuBirthday);
		schoolStudentDto.setTeacherId(this.teacherId);
		schoolStudentDto.setDormId(this.dormId);
		return schoolStudentDto;
	}
}
