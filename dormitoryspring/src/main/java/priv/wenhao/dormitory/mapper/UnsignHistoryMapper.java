package priv.wenhao.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import priv.wenhao.base.pojo.dto.UnsignHistoryDto;

import java.util.List;

/**
 * ClassName: UnsignHistoryMapper
 * Description:
 * Author: yuWenHao
 * Date: 2020/3/17
 */

public interface UnsignHistoryMapper extends BaseMapper<UnsignHistoryDto> {
	/***
	* ClassName:UnsignHistoryMapper
	* Description: 查询没有签到的人数
	* param:[]
	* return:java.util.List<priv.wenhao.base.pojo.dto.UnsignHistoryDto>
	* Author:yu wenhao
	* date:2020/3/17
	*/
	public List<UnsignHistoryDto> selectUnsign();

	public Integer insertUnsign(@Param(value = "list")List<UnsignHistoryDto> list);
}
