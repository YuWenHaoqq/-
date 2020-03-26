package priv.wenhao.dormitory.service;

import org.springframework.web.multipart.MultipartFile;
import priv.wenhao.base.pojo.vo.ResultVo;

/**
 * ClassName: AdministratorsService
 * Description: 管理员service层
 * Author: yuWenHao
 * Date: 2020/3/26
 */

public interface AdministratorsService {

	/***
	* ClassName:AdministratorsService
	* Description:
	* param:[]
	* return:void
	* Author:yu wenhao
	* date:2020/3/26
	*/
	public void addStu(ResultVo resultVo, MultipartFile file);
}
