package priv.wenhao.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import priv.wenhao.base.pojo.dto.SchoolLeaveHistoryDto;

import java.util.List;

/**
 * ClassName: SchoolLeaveHistoryMApper
 * Description: 这是一个请假的接口
 * Author: yuWenHao
 * Date: 2020/3/11
 */


public interface SchoolLeaveHistoryMapper extends BaseMapper<SchoolLeaveHistoryDto> {
	public Integer updateLeave(@Param("list") List<Integer> list,@Param("type") Integer type);
}
