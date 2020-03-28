package priv.wenhao.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;
import priv.wenhao.dormitory.pojo.vo.StudentVo;

import java.util.List;

/**
 * Description: SchoolStudentMapper
 * Author: yuWenHao
 * Date: 2019/12/18
 */

public interface SchoolStudentMapper extends BaseMapper<SchoolStudentDto> {
	/***
	* ClassName:SchoolStudentMapper
	* Description: 添加学生
	* param:[list]
	* return:java.lang.Integer
	* Author:yu wenhao
	* date:2020/3/27
	*/
	public Integer addStuList(@Param("list")List<SchoolStudentDto> list);

	/***
	* ClassName:SchoolStudentMapper
	* Description: 获得所有的学生信息
	* param:[]
	* return:java.util.List<priv.wenhao.dormitory.pojo.vo.StudentVo>
	* Author:yu wenhao
	* date:2020/3/27
	*/
	public List<StudentVo> getAllStu();
}
