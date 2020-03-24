package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("school_notice")
public class SchoolNoticeDto {
	@ApiModelProperty("")
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;
	@JsonIgnore
	@ApiModelProperty("关联教师表id")
	@TableField("pk_tea")
	private String teaId;
	@ApiModelProperty("公告封面连接")
	@TableField("img_url")
	private String imgUrl;
	@ApiModelProperty("发布时间")
	@TableField("release_time")
	private Date releaseTime;
	@ApiModelProperty("文章内容")
	@TableField("content")
	private String content;
	@ApiModelProperty("文章标题")
	@TableField("notice_title")
	private String noticeTitle;
	@ApiModelProperty("文章描述")
	@TableField("notice_des")
	private String noticeDes;
	@JsonIgnore
	@ApiModelProperty("创建时间")
	@TableField("notice_create")
	private Date noticeCreate;
	@JsonIgnore
	@ApiModelProperty("修改时间")
	@TableField("notice_modify")
	private Date notice_modify;
	@JsonIgnore
	@ApiModelProperty("是否删除")
	@TableField("is_deleted")
	private Integer deleted;

}