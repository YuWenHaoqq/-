package priv.wenhao.dormitory.pojo.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
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
	private String className;
	@ApiModelProperty(value = "学生姓名",required = true)
	private String stuName;
	@ApiModelProperty(value = "学生性别",required = true)
	private int stuSex;
	@ApiModelProperty(value = "密码",required = true)
	private String password;
	@ApiModelProperty(value = "学生出生年月",required = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	@ApiModelProperty(value = "教师工号",required = true)
	private String teaId;
	@ApiModelProperty("教师名字")
	private String teacherName;
	@ApiModelProperty(value = "寝室表id",required = true)
	private String dormName;

	@JsonIgnore
	public SchoolStudentDto getSchoolStudentDto(){
		SchoolStudentDto schoolStudentDto=new SchoolStudentDto();
		schoolStudentDto.setStuId(this.stuId);
//		schoolStudentDto.setClassId(this.className);
		schoolStudentDto.setStuName(this.stuName);
		schoolStudentDto.setStuSex(this.stuSex);
		schoolStudentDto.setStuPassword(this.password);
		schoolStudentDto.setStuBirthday(this.birthday);
		if (!StringUtils.isNotBlank(this.teaId)) {
			schoolStudentDto.setTeacherId(null);
		}else{
			schoolStudentDto.setTeacherId(this.teaId);
		}
		schoolStudentDto.setDormName(this.dormName);
		return schoolStudentDto;
	}
}
