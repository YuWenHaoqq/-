package priv.wenhao.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.RsaUtil;
import priv.wenhao.base.util.UUIDUtil;
import priv.wenhao.dormitory.mapper.SchoolStudentMapper;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.vo.UserVo;
import priv.wenhao.dormitory.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
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
			return;
		} else {
			resultVo.setCode(3);
			resultVo.setMessage("账号或密码错误");
		}
	}
}
