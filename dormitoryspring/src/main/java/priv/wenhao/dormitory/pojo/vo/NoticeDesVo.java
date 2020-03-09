package priv.wenhao.dormitory.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class NoticeDesVo {
	@ApiModelProperty("总页数")
	private Integer pageNumber;
	@ApiModelProperty("公告")
	private List<NoticeVo> noticeVos;
}
