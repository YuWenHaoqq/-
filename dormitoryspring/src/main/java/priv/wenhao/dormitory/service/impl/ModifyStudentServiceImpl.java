package priv.wenhao.dormitory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.mapper.SchoolStudentMapper;
import priv.wenhao.dormitory.pojo.query.StudentQuery;
import priv.wenhao.dormitory.pojo.query.UserMessageQuery;
import priv.wenhao.dormitory.service.ModifyStudentService;

/**
 * Description: ModifyStudentServiceImpl
 * Author: yuWenHao
 * Date: 2019/12/18
 */

@Service
public class ModifyStudentServiceImpl implements ModifyStudentService {

	@Autowired
	private SchoolStudentMapper schoolStudentMapper;
	/***
	* Description:新增学生表
	* param:[userMessageQuery, studentQuery, resultVo]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2019/12/18
	*/
	@Override
	public ResultVo addStudent(UserMessageQuery userMessageQuery, StudentQuery studentQuery, ResultVo resultVo) {
		SchoolStudentDto schoolStudentDto=new SchoolStudentDto();

		int row=schoolStudentMapper.insert(schoolStudentDto);
		if (row==0){
			resultVo.setMessage("新增成功");
		}
		return resultVo;
	}
}
