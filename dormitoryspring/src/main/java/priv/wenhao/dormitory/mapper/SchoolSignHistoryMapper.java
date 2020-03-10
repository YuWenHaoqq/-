package priv.wenhao.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import priv.wenhao.base.pojo.dto.SchoolSignHistoryDto;

import java.util.List;

public interface SchoolSignHistoryMapper extends BaseMapper<SchoolSignHistoryDto> {
	/***
	* ClassName:SchoolSignHistoryMapper
	* Description:获得登录日志表需要的信息
	* param:[stuId]
	* return:priv.wenhao.base.pojo.dto.SchoolSignHistory
	* Author:yu wenhao
	* date:2019/12/31
	*/
	List<SchoolSignHistoryDto> getSignMessageByStuId(String stuId);
}
