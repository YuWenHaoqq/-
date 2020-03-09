package priv.wenhao.dormitory.mapper;

import priv.wenhao.dormitory.pojo.vo.NoticeVo;

import java.util.List;

public interface SchoolNoticeMapper {
	/***
	* ClassName:SchoolNoticeMapper
	* Description: 按照时间进行公告排序
	* param:[page]
	* return:java.util.List<priv.wenhao.dormitory.pojo.vo.NoticeVo>
	* Author:yu wenhao
	* date:2020/3/9
	*/
	public List<NoticeVo> getNoticeDes();
}
