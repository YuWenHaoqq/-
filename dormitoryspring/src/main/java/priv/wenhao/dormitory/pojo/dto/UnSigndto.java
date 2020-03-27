package priv.wenhao.dormitory.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import priv.wenhao.base.pojo.dto.SchoolUnsignHistoryDto;

import java.util.Date;

@Data
public class UnSigndto {
	@ApiModelProperty("学生id")
	private String stuId;
	@ApiModelProperty("教师id")
	private String teaId;
	@ApiModelProperty("学生名字")
	private String stuName;
	@ApiModelProperty("请假是否审批成功")
	private Integer success;
	@ApiModelProperty("时候签到")
	private String pkStuId;

	/***
	* ClassName:UnSigndto
	* Description: 注意只允许调用noSignInTask文件中
	* param:[]
	* return:priv.wenhao.base.pojo.dto.SchoolUnsignHistoryDto
	* Author:yu wenhao
	* date:2020/3/27
	*/
	public SchoolUnsignHistoryDto getSchoolUnsignHistoryDto(){
		SchoolUnsignHistoryDto schoolUnsignHistoryDto=new SchoolUnsignHistoryDto();
		schoolUnsignHistoryDto.setStuId(this.stuId);
		schoolUnsignHistoryDto.setTeaId(this.teaId);
		schoolUnsignHistoryDto.setStuName(this.stuName);
		schoolUnsignHistoryDto.setChecked(0);
		schoolUnsignHistoryDto.setDeleted(0);
		schoolUnsignHistoryDto.setCreateTime(new Date());
		return schoolUnsignHistoryDto;
	}
}
