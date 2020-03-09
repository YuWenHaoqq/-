package priv.wenhao.dormitory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wenhao.base.pojo.dto.SchoolTeacherDto;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.UUIDUtil;
import priv.wenhao.dormitory.mapper.SchoolTeacherMapper;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.vo.UserVo;
import priv.wenhao.dormitory.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName: TeacherServiceImpl
 * Description:
 * Author: yuWenHao
 * Date: 2020/3/9
 */

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private SchoolTeacherMapper schoolTeacherMapper;

	@Autowired
	private RedisCommands<String, String> thirdTemplate;

	@Override
	public void login(LoginQuery loginQuery, HttpServletRequest request, ResultVo resultVo) {
		QueryWrapper<SchoolTeacherDto> wrapper = new QueryWrapper<SchoolTeacherDto>()
				.eq("teacher_id", loginQuery.getAccount())
				.eq("teacher_password", loginQuery.getPassword())
				.eq("is_deleted", 0);
		List<SchoolTeacherDto> list = schoolTeacherMapper.selectList(wrapper);
		if (list.size() == 1) {
			UserVo userVo = new UserVo();
			userVo.setStuId(list.get(0).getTeacherId());
			//			生成token
			userVo.setToken(UUIDUtil.getUUID32());
			userVo.setUserName(list.get(0).getTeacherName());
//			userVo.setRsa(new String(Base64.getEncoder().encode(RsaUtil.publicKey.getEncoded())));
			resultVo.setMessage("登录成功");
			resultVo.setData(userVo);
//			将token添加到redis缓存
			SetArgs setArgs = SetArgs.Builder.nx().ex(60 * 60);
			thirdTemplate.set(userVo.getStuId(), userVo.getToken(), setArgs);
		} else {
			resultVo.setCode(3);
			resultVo.setMessage("账号或密码错误");
		}

	}
}
