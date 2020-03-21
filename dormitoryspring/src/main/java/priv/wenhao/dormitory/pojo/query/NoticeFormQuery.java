package priv.wenhao.dormitory.pojo.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import priv.wenhao.base.pojo.dto.SchoolNoticeDto;

@Data
public class NoticeFormQuery {
	@ApiModelProperty("标题")
	private String title;
	@ApiModelProperty("封面图片链接")
	private String imgurl;
	@ApiModelProperty("公告,描述")
	private String noticeDes;
	@ApiModelProperty("公告内容")
	private String noticeContent;
	@ApiModelProperty("教师id")
	private String teaId;
	@JsonIgnore
	public SchoolNoticeDto getDto(){
		SchoolNoticeDto schoolNoticeDto=new SchoolNoticeDto();
		schoolNoticeDto.setContent(noticeContent);
		schoolNoticeDto.setNoticeDes(noticeDes);
		schoolNoticeDto.setImgUrl(imgurl);
		schoolNoticeDto.setNoticeTitle(title);
		schoolNoticeDto.setTeaId(teaId);
		return schoolNoticeDto;
	}
}
