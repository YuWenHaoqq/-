package priv.wenhao.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import priv.wenhao.base.pojo.dto.SchoolTeacherDto;
import priv.wenhao.dormitory.pojo.vo.TeacherVo;

import java.util.List;

/**
 * ClassName: SchoolTeacherMapper
 * Description:
 * Author: yuWenHao
 * Date: 2020/3/9
 */

public interface SchoolTeacherMapper extends BaseMapper<SchoolTeacherDto> {
	public List<TeacherVo> getAllTea();
}
