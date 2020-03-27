package priv.wenhao.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import priv.wenhao.base.pojo.dto.SchoolUnsignHistoryDto;
import priv.wenhao.dormitory.pojo.dto.UnSigndto;

import java.util.List;

/**
 * ClassName: UnsignHistoryMapper
 * Description:
 * Author: yuWenHao
 * Date: 2020/3/17
 */

public interface UnsignHistoryMapper extends BaseMapper<SchoolUnsignHistoryDto> {
	/***
	* ClassName:UnsignHistoryMapper
	* Description: 查询没有签到的人数
	* param:[]
	* return:java.util.List<priv.wenhao.base.pojo.dto.UnsignHistoryDto>
	* Author:yu wenhao
	* date:2020/3/17
	*/
	public List<UnSigndto> selectUnsign();

	/***
	* ClassName:UnsignHistoryMapper
	* Description: 新增未签到的记录
	* param:[list]
	* return:java.lang.Integer
	* Author:yu wenhao
	* date:2020/3/21
	*/
	public Integer insertUnsign(@Param(value = "list")List<SchoolUnsignHistoryDto> list);
}
