package priv.wenhao.dormitory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.mapper.SchoolStudentMapper;
import priv.wenhao.dormitory.pojo.query.StudentQuery;
import priv.wenhao.dormitory.pojo.query.UserMessageQuery;
import priv.wenhao.dormitory.service.ModifyStudentService;

import java.util.Date;

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
	public void addStudent(UserMessageQuery userMessageQuery, StudentQuery studentQuery, ResultVo resultVo)throws Exception {
		SchoolStudentDto schoolStudentDto=studentQuery.getSchoolStudentDto();
		schoolStudentDto.setDeleted(0);
		schoolStudentDto.setStuCreate(new Date());
		int row=schoolStudentMapper.insert(schoolStudentDto);
		if (row==1){
			resultVo.setMessage("新增成功");
		}else{
			throw new BussinessException(2,"新增失败");
		}
	}
}
