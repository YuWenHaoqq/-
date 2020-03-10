package priv.wenhao.dormitory.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
}
