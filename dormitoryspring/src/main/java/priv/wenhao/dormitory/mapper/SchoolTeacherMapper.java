package priv.wenhao.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
	/***
	* ClassName:SchoolTeacherMapper
	* Description: 获得所有的教师信息
	* param:[]
	* return:java.util.List<priv.wenhao.dormitory.pojo.vo.TeacherVo>
	* Author:yu wenhao
	* date:2020/3/28
	*/
	public List<TeacherVo> getAllTea();

	/***
	* ClassName:SchoolTeacherMapper
	* Description: 新增教师
	* param:[list]
	* return:java.lang.Integer
	* Author:yu wenhao
	* date:2020/3/28
	*/
	public Integer insertTeaList(@Param("list") List list);
}
