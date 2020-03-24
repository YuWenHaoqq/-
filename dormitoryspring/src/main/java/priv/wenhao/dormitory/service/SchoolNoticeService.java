package priv.wenhao.dormitory.service;

import priv.wenhao.base.pojo.vo.ResultVo;

/**
 * ClassName: SchoolNoticeService
 * Description: 公告接口
 * Author: yuWenHao
 * Date: 2020/3/9
 */

public interface SchoolNoticeService {
	/***
	* ClassName:SchoolNoticeService
	* Description: 获得公告
	* param:[page, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/9
	*/
	public void getNotice(int page, ResultVo resultVo);

	/***
	* ClassName:SchoolNoticeService
	* Description: 获取完整公告
	* param:[]
	* return:void
	* Author:yu wenhao
	* date:2020/3/23
	*/
	public void getNoticeContent(ResultVo resultVo,int id);
}
