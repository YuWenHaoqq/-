package priv.wenhao.dormitory.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.dto.SchoolAdminDto;
import priv.wenhao.base.pojo.dto.SchoolClassDto;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;
import priv.wenhao.base.pojo.dto.SchoolTeacherDto;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.UUIDUtil;
import priv.wenhao.base.util.WorkBookUtils;
import priv.wenhao.dormitory.mapper.SchoolAdminMapper;
import priv.wenhao.dormitory.mapper.SchoolClassMapper;
import priv.wenhao.dormitory.mapper.SchoolStudentMapper;
import priv.wenhao.dormitory.mapper.SchoolTeacherMapper;
import priv.wenhao.dormitory.pojo.query.LoginQuery;
import priv.wenhao.dormitory.pojo.query.StudentQuery;
import priv.wenhao.dormitory.pojo.vo.TeacherVo;
import priv.wenhao.dormitory.pojo.vo.UserVo;
import priv.wenhao.dormitory.service.AdministratorsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName: AdministratorsServiceImpl
 * Description: 管理员实现层
 * Author: yuWenHao
 * Date: 2020/3/26
 */
@Service
@Slf4j
@Transactional(rollbackFor = BussinessException.class)
public class AdministratorsServiceImpl implements AdministratorsService {

	@Autowired
	private SchoolStudentMapper schoolStudentMapper;

	@Autowired
	private SchoolTeacherMapper schoolTeacherMapper;

	@Autowired
	private SchoolClassMapper schoolClassMapper;


	@Autowired
	private SchoolAdminMapper schoolAdminMapper;

	@Resource(name = "fourthTemplate")
	private RedisCommands<String,String> fourthTemplate;

	/***
	 * ClassName:AdministratorsServiceImpl
	 * Description: 使用excel添加学生
	 * param:[resultVo, file]
	 * return:void
	 * Author:yu wenhao
	 * date:2020/3/26
	 */
	@Override
	public void addStu(ResultVo resultVo, MultipartFile file) throws BussinessException {
//		使用HSSFWorkbook,打开或者创建excel对象
//		使用HSSFworkbook,返回对象或者城建sheet对象
//		使用sheet返回行对象,并用行对象得到cell对象,
//		对cell对象进行读写
		Workbook workbook = WorkBookUtils.getWorkBook(file);
//		获得文件名
		String fileName = file.getOriginalFilename();
		List<String> stuIdList = new ArrayList<>();
//		获得工作表
		Sheet sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
//		获得横向数目
		int cells = sheet.getRow(0).getPhysicalNumberOfCells();
		if (rows < 2) {
			resultVo.setCode(1);
			resultVo.setMessage("请填写数据");
			return;
		}
		List<SchoolStudentDto> list = new ArrayList<>();

		HashMap<String, Integer> hashMap = new HashMap<>();
//		获得所有的班级表,查询excel中的班级输入是否正确
		{
			QueryWrapper<SchoolClassDto> queryWrapper = new QueryWrapper<SchoolClassDto>()
					.eq("is_deleted", 0);
//			局部变量
			List<SchoolClassDto> classList = schoolClassMapper.selectList(queryWrapper);
			for (SchoolClassDto schoolClassDto : classList) {
				hashMap.put(schoolClassDto.getClassName(), schoolClassDto.getClassId());
			}
		}
//		前两行没有数据
		for (int i = 2; i <= rows; i++) {
			Row row = sheet.getRow(i);
			WorkBookUtils.checkVal(i, cells, row);
			SchoolStudentDto schoolStudentDto = new SchoolStudentDto();
//			通过excel获得表数据
//			确认插入数据没有重复的数据
			if (stuIdList.contains(row.getCell(0).getStringCellValue())) {
				throw new BussinessException(1, "第" + (i + 1) + "行有重复的学生学号");
			}
			schoolStudentDto.setStuId(row.getCell(0).getStringCellValue());
			stuIdList.add(row.getCell(0).getStringCellValue());

			schoolStudentDto.setStuName(row.getCell(1).getStringCellValue());
			//			判断性别并转成数字
			if (row.getCell(3).getStringCellValue().equals("女")){
				schoolStudentDto.setStuSex(0);
			}else if (row.getCell(3).getStringCellValue().equals("男")){
				schoolStudentDto.setStuSex(1);
			}else{
				resultVo.setCode(1);
				resultVo.setMessage("请确认第"+(i+1)+"行的性别填写正确,只允许写入男或女");
				return;
			}
//			schoolStudentDto.setStuSex(row.getCell(3).getStringCellValue() == "男" ? 1 : 0);
			schoolStudentDto.setStuPassword(row.getCell(4).getStringCellValue());
			if (row.getCell(5).getCellType() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(row.getCell(5))) {
				schoolStudentDto.setStuBirthday(HSSFDateUtil.getJavaDate(row.getCell(5).getNumericCellValue()));
			} else {
				throw new BussinessException(1, "第" + (i + 1) + "行请输入正确的日期格式");
			}
			schoolStudentDto.setTeacherId(row.getCell(6).getStringCellValue());
			schoolStudentDto.setDormName(row.getCell(7).getStringCellValue());
			if (hashMap.get(row.getCell(2).getStringCellValue()) == null) {
				throw new BussinessException(1, "请先添加第" + (i + 1) + "行的班级");
			}
			schoolStudentDto.setClassId(hashMap.get(row.getCell(2).getStringCellValue()));
//				添加插入数据
			list.add(schoolStudentDto);
		}
		Integer row = schoolStudentMapper.addStuList(list);

		if (row == list.size()) {
			log.info("excel新增" + row + "条记录");
			resultVo.setMessage("全部新增成功");
		} else {
			log.info("excel新增" + row + "条记录");
			resultVo.setCode(1);
			resultVo.setMessage("新增" + row + "条,以去除" + (rows - row) + "条重复");
		}

	}

	/***
	 * ClassName:AdministratorsServiceImpl
	 * Description: 使用excel添加教师
	 * param:[resultVo, file]
	 * return:void
	 * Author:yu wenhao
	 * date:2020/3/28
	 */
	@Override
	public void addTeaByExcel(ResultVo resultVo, MultipartFile file) throws BussinessException{
		//		使用HSSFWorkbook,打开或者创建excel对象
//		使用HSSFworkbook,返回对象或者城建sheet对象
//		使用sheet返回行对象,并用行对象得到cell对象,
//		对cell对象进行读写
		Workbook workbook = WorkBookUtils.getWorkBook(file);
//		获得文件名
		String fileName = file.getOriginalFilename();
		List<String> stuIdList = new ArrayList<>();
//		获得工作表
		Sheet sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
//		获得横向数目
		int cells = sheet.getRow(0).getPhysicalNumberOfCells();
		if (rows < 2) {
			resultVo.setCode(1);
			resultVo.setMessage("请填写数据");
			return;
		}
		List<SchoolTeacherDto> list = new ArrayList<>();


//		获得所有的班级表,查询excel中的班级输入是否正确
		HashMap<String, Integer> hashMap = new HashMap<>();
		{
			QueryWrapper<SchoolClassDto> queryWrapper = new QueryWrapper<SchoolClassDto>()
					.eq("is_deleted", 0);
//			局部变量
			List<SchoolClassDto> classList = schoolClassMapper.selectList(queryWrapper);
			for (SchoolClassDto schoolClassDto : classList) {
				hashMap.put(schoolClassDto.getClassName(), schoolClassDto.getClassId());
			}
		}
		for (int i = 2; i <= rows; i++) {
			Row row = sheet.getRow(i);
			WorkBookUtils.checkVal(i, cells, row);
			SchoolTeacherDto schoolTeacherDto=new SchoolTeacherDto();
			schoolTeacherDto.setTeacherId(row.getCell(0).getStringCellValue());
			if (hashMap.get(row.getCell(1).getStringCellValue())==null){
				resultVo.setCode(1);
				resultVo.setMessage("请确认第"+(i+1)+"行的班级正确");
				return;
			}
			schoolTeacherDto.setClassId(hashMap.get(row.getCell(1).getStringCellValue()));
			schoolTeacherDto.setTeacherName(row.getCell(2).getStringCellValue());
			schoolTeacherDto.setPassword(row.getCell(3).getStringCellValue());
//			判断性别并转成数字
			if (row.getCell(4).getStringCellValue().equals("女")){
				schoolTeacherDto.setSex(0);
			}else if (row.getCell(4).getStringCellValue().equals("男")){
				schoolTeacherDto.setSex(1);
			}else{
				resultVo.setCode(1);
				resultVo.setMessage("请确认第"+(i+1)+"行的性别填写正确,只允许写入男或女");
				return;
			}

//			填写日期
			if (row.getCell(5).getCellType() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(row.getCell(5))) {
				schoolTeacherDto.setBirthday(HSSFDateUtil.getJavaDate(row.getCell(5).getNumericCellValue()));
			} else {
				throw new BussinessException(1, "第" + (i + 1) + "行请输入正确的日期格式");
			}
			schoolTeacherDto.setTeacherCreate(new Date());
			schoolTeacherDto.setDeleted(0);
			list.add(schoolTeacherDto);
		}
		int row=
		schoolTeacherMapper.insertTeaList(list);
		if (row == list.size()) {
			log.info("excel新增" + row + "条记录");
			resultVo.setMessage("全部新增成功");
		} else {
			log.info("excel新增" + row + "条记录");
			resultVo.setCode(1);
			resultVo.setMessage("新增" + row + "条,以去除" + (rows - row) + "条重复");
		}
	}

	/***
	 * ClassName:AdministratorsServiceImpl
	 * Description: 修改学生信息
	 * param:[resultVo, studentQuery]
	 * return:void
	 * Author:yu wenhao
	 * date:2020/3/28
	 */
	@Override
	public void modifyStu(ResultVo resultVo, StudentQuery studentQuery) {
		if (StringUtils.isNotBlank(studentQuery.getTeaId())) {
//			查询教工号和教师名字是否正确
			QueryWrapper<SchoolTeacherDto> queryWrapper = new QueryWrapper<SchoolTeacherDto>()
					.eq("teacher_id", studentQuery.getTeaId())
					.eq("teacher_name", studentQuery.getTeacherName())
					.eq("is_deleted", 0);
			int size = schoolTeacherMapper.selectCount(queryWrapper);
			if (size == 0) {
				resultVo.setCode(1);
				resultVo.setMessage("请输入正确的教工号和教师名字");
				return;
			}
		}
//		查询对应的班级id
		QueryWrapper<SchoolClassDto> queryWrapper = new QueryWrapper<SchoolClassDto>()
				.eq("class_name", studentQuery.getClassName())
				.eq("is_deleted", 0);
		List<SchoolClassDto> list = schoolClassMapper.selectList(queryWrapper);
		if (list.size() == 0) {
			resultVo.setCode(1);
			resultVo.setMessage("请输入正确的班级");
			return;
		}
		SchoolStudentDto schoolStudentDto = studentQuery.getSchoolStudentDto();
		schoolStudentDto.setClassId(list.get(0).getClassId());
		schoolStudentDto.setStuModify(new Date());
//		更新学生数据
		int row =
				schoolStudentMapper.updateById(schoolStudentDto);
		if (row == 1) {
			resultVo.setMessage("修改成功");
		} else {
			resultVo.setCode(1);
			resultVo.setMessage("修改失败,请确认输入学号是否正确");
		}


	}

	/***
	 * ClassName:AdministratorsServiceImpl
	 * Description: 获得所有的学生信息
	 * param:[resultVo]
	 * return:void
	 * Author:yu wenhao
	 * date:2020/3/27
	 */
	@Override
	public void getAllStu(ResultVo resultVo) {
		resultVo.setMessage("查询成功");
		resultVo.setData(schoolStudentMapper.getAllStu());
	}

	/***
	 * ClassName:AdministratorsServiceImpl
	 * Description: 获得所有的教师信息
	 * param:[resultVo]
	 * return:void
	 * Author:yu wenhao
	 * date:2020/3/28
	 */
	@Override
	public void getAllTea(ResultVo resultVo) {
		resultVo.setMessage("查询成功");
		resultVo.setData(schoolTeacherMapper.getAllTea());

	}

	/***
	 * ClassName:AdministratorsServiceImpl
	 * Description: 修改教师信息
	 * param:[resultVo, teacherVo]
	 * return:void
	 * Author:yu wenhao
	 * date:2020/3/28
	 */
	@Override
	public void modifyTea(ResultVo resultVo, TeacherVo teacherVo) {
//		先查询时候有对应的班级
		QueryWrapper<SchoolClassDto> queryWrapper = new QueryWrapper<SchoolClassDto>()
				.eq("class_name", teacherVo.getClassName())
				.eq("is_deleted", 0);

		List<SchoolClassDto> list =
				schoolClassMapper.selectList(queryWrapper);

		SchoolTeacherDto schoolTeacherDto = teacherVo.getTeacherDto();
		if (list.size() == 0) {
			resultVo.setCode(1);
			resultVo.setMessage("请输入正确的班级");
			return;
		}
		schoolTeacherDto.setClassId(list.get(0).getClassId());
		schoolTeacherDto.setTeacherModify(new Date());
//		进行修改教师信息
		int row =
				schoolTeacherMapper.updateById(schoolTeacherDto);
		if (row == 1) {
			resultVo.setMessage("修改成功");
		} else {
			resultVo.setCode(1);
			resultVo.setMessage("修改失败,请确认输入正确的教工号");
		}

	}

	/***
	 * ClassName:AdministratorsServiceImpl
	 * Description: 删除学生信息
	 * param:[resultVo, stuId]
	 * return:void
	 * Author:yu wenhao
	 * date:2020/3/28
	 */
	@Override
	public void delStu(ResultVo resultVo, String stuId) {
		SchoolStudentDto schoolStudentDto = new SchoolStudentDto();
		schoolStudentDto.setStuId(stuId);
		schoolStudentDto.setDeleted(1);
		int row =
				schoolStudentMapper.updateById(schoolStudentDto);
		if (row == 1) {
			resultVo.setMessage("删除成功");
		} else {
			resultVo.setCode(1);
			resultVo.setMessage("删除失败");
			return;
		}


	}

	/***
	 * ClassName:AdministratorsServiceImpl
	 * Description: 删除教师信息
	 * param:[resultVo, stuId]
	 * return:void
	 * Author:yu wenhao
	 * date:2020/3/28
	 */
	@Override
	public void delTea(ResultVo resultVo, String teaId) {
		SchoolTeacherDto schoolTeacherDto = new SchoolTeacherDto();
		schoolTeacherDto.setTeacherId(teaId);
		schoolTeacherDto.setDeleted(1);
		int row = schoolTeacherMapper.updateById(schoolTeacherDto);
		if (row == 1) {
			resultVo.setMessage("删除成功");
		} else {
			resultVo.setCode(1);
			resultVo.setMessage("删除失败");
			return;
		}


	}

	/***
	* ClassName:AdministratorsServiceImpl
	* Description: 管理员登录
	* param:[resultVo, loginQuery, request]
	* return:void
	* Author:yu wenhao
	* date:2020/3/28
	*/
	@Override
	public void login(ResultVo resultVo, LoginQuery loginQuery, HttpServletRequest request) {
		QueryWrapper<SchoolAdminDto> wrapper = new QueryWrapper<SchoolAdminDto>()
				.eq("account", loginQuery.getAccount())
				.eq("password", loginQuery.getPassword())
				.eq("is_deleted", 0);
		List<SchoolAdminDto> list = schoolAdminMapper.selectList(wrapper);
		if (list.size() == 1) {
			UserVo userVo = new UserVo();
			userVo.setStuId(list.get(0).getAccount());
//			删除原本的token 因为设置时间后不能直接使用set进行更改
			if (fourthTemplate.exists(userVo.getStuId())!=0){
				fourthTemplate.del(userVo.getStuId());
			}
			//			生成token
			userVo.setToken(UUIDUtil.getUUID32());
			userVo.setUserName(list.get(0).getName());
//			userVo.setRsa(new String(Base64.getEncoder().encode(RsaUtil.publicKey.getEncoded())));
			resultVo.setMessage("登录成功");
			resultVo.setData(userVo);
//			将token添加到redis缓存
			SetArgs setArgs = SetArgs.Builder.nx().ex(60 * 60);
			fourthTemplate.set(userVo.getStuId(), userVo.getToken(), setArgs);
		} else {
			resultVo.setCode(3);
			resultVo.setMessage("账号或密码错误");
		}

	}

}
