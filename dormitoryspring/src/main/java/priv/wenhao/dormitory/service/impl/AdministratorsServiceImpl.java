package priv.wenhao.dormitory.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.mapper.SchoolStudentMapper;
import priv.wenhao.dormitory.mapper.SchoolTeacherMapper;
import priv.wenhao.dormitory.service.AdministratorsService;

/**
 * ClassName: AdministratorsServiceImpl
 * Description: 管理员实现层
 * Author: yuWenHao
 * Date: 2020/3/26
 */
@Service
public class AdministratorsServiceImpl implements AdministratorsService {

	@Autowired
	private SchoolStudentMapper schoolStudentMapper;

	@Autowired
	private SchoolTeacherMapper schoolTeacherMapper;
	/***
	* ClassName:AdministratorsServiceImpl
	* Description: 使用excel添加学生
	* param:[resultVo, file]
	* return:void
	* Author:yu wenhao
	* date:2020/3/26
	*/
	@Override
	public void addStu(ResultVo resultVo, MultipartFile file) {

	}
}
