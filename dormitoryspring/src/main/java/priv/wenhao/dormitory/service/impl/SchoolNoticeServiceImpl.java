package priv.wenhao.dormitory.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
		int pageSize=6;
		PageHelper.startPage(page,pageSize);
		List<NoticeVo> list=schoolNoticeMapper.getNoticeDes();
		PageInfo<NoticeVo>pageInfo=new PageInfo<>(list);
		NoticeDesVo noticeDesVo=new NoticeDesVo();
		noticeDesVo.setPageNumber((int)pageInfo.getTotal());
		noticeDesVo.setNoticeVos(list);
		resultVo.setCode(0);
		resultVo.setMessage("查询成功");
		resultVo.setData(noticeDesVo);
	}
}