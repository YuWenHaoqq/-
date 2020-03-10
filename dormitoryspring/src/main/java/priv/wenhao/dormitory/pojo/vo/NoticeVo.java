package priv.wenhao.dormitory.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class NoticeVo {
	@ApiModelProperty("公告id")
	private Integer id;
	@ApiModelProperty("教师名字")
	private String teaName;
	@ApiModelProperty("图片路径")
	private String imgUrl;
	@ApiModelProperty("发布时间")
	private Date releaseTime;
	@ApiModelProperty("文章内容")
	private String content;
	@ApiModelProperty("文章描述")
	private String noticeDes;
	@ApiModelProperty("文章标题")
	private String noticeTitle;
//	@ApiModelProperty("总页数")
//	private Integer pageNumber;
}
