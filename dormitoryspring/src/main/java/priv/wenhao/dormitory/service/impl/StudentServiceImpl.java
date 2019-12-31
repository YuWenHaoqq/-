package priv.wenhao.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.pojo.dto.SchoolSignHistory;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.UUIDUtil;
import priv.wenhao.dormitory.mapper.SchoolSignHistoryMapper;
import priv.wenhao.dormitory.mapper.SchoolStudentMapper;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.vo.UserVo;
import priv.wenhao.dormitory.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Description: StudentServiceImpl
 * Author: yuWenHao
 * Date: 2019/12/19
 */

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private SchoolStudentMapper schoolStudentMapper;

	@Autowired
	private SchoolSignHistoryMapper schoolSignHistoryMapper;

	@Autowired
	private RedisCommands<String, String> firstTemplate;

	/***
	 * Description:学生端登录
	 * param:[loginQuery, request, resultVo]
	 * return:void
	 * Author:yu wenhao
	 * date:2019/12/20
	 */
	@Override
	public void login(LoginQuery loginQuery, HttpServletRequest request, ResultVo resultVo) {
		QueryWrapper<SchoolStudentDto> wrapper = new QueryWrapper<SchoolStudentDto>()
				.eq("stu_id", loginQuery.getAccount())
				.eq("stu_password", loginQuery.getPassword())
				.eq("is_deleted", 0);
		List<SchoolStudentDto> list = schoolStudentMapper.selectList(wrapper);
		if (list.size() == 1) {
			UserVo userVo = new UserVo();
			userVo.setStuId(list.get(0).getStuId());
			//			生成token
			userVo.setToken(UUIDUtil.getUUID32());
			userVo.setUserName(list.get(0).getStuName());
//			userVo.setRsa(new String(Base64.getEncoder().encode(RsaUtil.publicKey.getEncoded())));
			resultVo.setMessage("登录成功");
			resultVo.setData(userVo);
//			将token添加到redis缓存
			SetArgs setArgs = SetArgs.Builder.nx().ex(60 * 60);
			firstTemplate.set(userVo.getStuId(), userVo.getToken(), setArgs);
		} else {
			resultVo.setCode(3);
			resultVo.setMessage("账号或密码错误");
		}
	}

	/***
	* ClassName:StudentServiceImpl
	* Description:学生签到
	* param:[stuId, resultVo]
	* return:void
	* Author:yu wenhao
	* date:2019/12/31
	*/
	@Override
	public void signIn(String stuId, ResultVo resultVo) {

		List<SchoolSignHistory> list = schoolSignHistoryMapper.getSignMessageByStuId(stuId);
		if (list.size() == 0) {
			resultVo.setCode(-1);
			resultVo.setMessage("学生信息缺失,请联系教师添加");
			return;
		} else if (list.size() > 1) {
			resultVo.setCode(-1);
			resultVo.setMessage("学生信息异常,请联系教师");
			return;
		}
		System.out.println(list.get(0).getContinueSignMonth());
		list.get(0).setStuId(stuId);
		list.get(0).setCreateTime(new Date());
		list.get(0).setDeleted(0);
		int row = schoolSignHistoryMapper.insert(list.get(0));
		if (row == 1) {
			resultVo.setMessage("签到成功");
		} else {
			resultVo.setCode(-1);
			resultVo.setMessage("签到异常");
		}
	}
}
