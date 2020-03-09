package priv.wenhao.dormitory.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.mapper.SchoolNoticeMapper;
import priv.wenhao.dormitory.pojo.vo.NoticeDesVo;
import priv.wenhao.dormitory.pojo.vo.NoticeVo;
import priv.wenhao.dormitory.service.SchoolNoticeService;

import java.util.List;

/**
 * ClassName: SchoolNoticeServiceImpl
 * Description: 公告实现类
 * Author: yuWenHao
 * Date: 2020/3/9
 */

@Service
public class SchoolNoticeServiceImpl implements SchoolNoticeService {

	@Autowired
	private SchoolNoticeMapper schoolNoticeMapper;

	/***
	* ClassName:SchoolNoticeServiceImpl
	* Description: 查询公告页面的内容
	* param:[page, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2020/3/9
	*/
	@Override
	public void getNotice(int page, ResultVo resultVo) {
		PageHelper.startPage(1,2);
		List<NoticeVo> list=schoolNoticeMapper.getNoticeDes();
		NoticeDesVo noticeDesVo=new NoticeDesVo();
		noticeDesVo.setNoticeVos(list);
		resultVo.setCode(0);
		resultVo.setMessage("查询成功");
		resultVo.setData(noticeDesVo);
	}
}
