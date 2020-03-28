package priv.wenhao.dormitory.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import priv.wenhao.base.pojo.dto.SchoolTeacherDto;

import java.util.Date;

@Data
public class TeacherVo {
	private String teaId;
	private String className;
	private String name;
	private String password;
	private Integer sex;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@JsonIgnore
	public SchoolTeacherDto getTeacherDto(){
		SchoolTeacherDto schoolTeacherDto=new SchoolTeacherDto();
		schoolTeacherDto.setTeacherId(teaId);
		schoolTeacherDto.setTeacherName(name);
		schoolTeacherDto.setPassword(password);
		schoolTeacherDto.setSex(sex);
		schoolTeacherDto.setBirthday(birthday);
		return schoolTeacherDto;
	}
}
