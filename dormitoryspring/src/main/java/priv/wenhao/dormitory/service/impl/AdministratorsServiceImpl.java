package priv.wenhao.dormitory.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import priv.wenhao.base.configvalue.TemplateValue;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.dto.SchoolClassDto;
import priv.wenhao.base.pojo.dto.SchoolStudentDto;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.dormitory.mapper.SchoolClassMapper;
import priv.wenhao.dormitory.mapper.SchoolStudentMapper;
import priv.wenhao.dormitory.mapper.SchoolTeacherMapper;
import priv.wenhao.dormitory.pojo.query.StudentQuery;
import priv.wenhao.dormitory.service.AdministratorsService;

import java.io.IOException;
import java.util.ArrayList;
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
	private TemplateValue templateValue;

	private final static String XLS = "xls";
	private final static String XLSX = "xlsx";

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
		Workbook workbook = null;
//		获得文件名
		String fileName = file.getOriginalFilename();
		List<String> stuIdList = new ArrayList<>();
		log.info("对excel表进行操作-" + fileName);
		try {
			if (fileName.endsWith(XLS)) {
//				2003版本
				workbook = new HSSFWorkbook(file.getInputStream());
			} else if (fileName.endsWith(XLSX)) {
				workbook = new XSSFWorkbook(file.getInputStream());

			} else {
				resultVo.setCode(1);
				resultVo.setMessage("请上传excel文件");
				return;
			}

			Sheet sheet = workbook.getSheet("Sheet1");
			int rows = sheet.getLastRowNum();
			String title = sheet.getRow(0).getCell(0).getStringCellValue();
//		获得横向数目
//		int cells=sheet.getRow(0).getPhysicalNumberOfCells();
			if (rows < 3) {
				resultVo.setCode(1);
				resultVo.setMessage("请填写数据");
				return;
			}
			if (!templateValue.getStuTitle().equals(title)) {
				resultVo.setCode(1);
				resultVo.setMessage("请不要修改模板信息");
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
			for (int i = 2; i < rows; i++) {
				Row row = sheet.getRow(i);
				SchoolStudentDto schoolStudentDto = new SchoolStudentDto();
//			通过excel获得表数据
				row.getCell(0).setCellType(CellType.STRING);
//				确认插入数据没有重复的数据
				if (stuIdList.contains(row.getCell(0).getStringCellValue())) {
					throw new BussinessException(1, "第" + (i + 1) + "行有重复的学生主键");
				}
				schoolStudentDto.setStuId(row.getCell(0).getStringCellValue());
				stuIdList.add(row.getCell(0).getStringCellValue());

				row.getCell(1).setCellType(CellType.STRING);
				schoolStudentDto.setStuName(row.getCell(1).getStringCellValue());
				row.getCell(3).setCellType(CellType.STRING);
				schoolStudentDto.setStuSex(row.getCell(3).getStringCellValue() == "男" ? 1 : 0);
				row.getCell(4).setCellType(CellType.STRING);
				schoolStudentDto.setStuPassword(row.getCell(4).getStringCellValue());
//			row.getCell(5).setCellType(CellType.STRING);
				if (row.getCell(5).getCellType() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(row.getCell(5))) {
					schoolStudentDto.setStuBirthday(HSSFDateUtil.getJavaDate(row.getCell(5).getNumericCellValue()));
				} else {
					throw new BussinessException(1, "第" + (i + 1) + "行请输入正确的日期格式");
				}
				row.getCell(6).setCellType(CellType.STRING);
				schoolStudentDto.setTeacherId(row.getCell(6).getStringCellValue());
				row.getCell(7).setCellType(CellType.STRING);
				schoolStudentDto.setDormName(row.getCell(7).getStringCellValue());
				row.getCell(2).setCellType(CellType.STRING);
				if (hashMap.get(row.getCell(2).getStringCellValue()) == null) {
					throw new BussinessException(1, "请先添加第" + (i + 1) + "行的班级");
				}
				schoolStudentDto.setClassId(hashMap.get(row.getCell(2).getStringCellValue()));
//				添加插入数据
				list.add(schoolStudentDto);
			}
			Integer row = schoolStudentMapper.addStuList(list);

			if (row == list.size()) {
				resultVo.setMessage("全部新增成功");
			} else {
				throw new BussinessException(1, "新增" + row + "条,以去除" + (rows - row) + "条重复");
			}
		} catch (IOException e) {
			log.info("excel表错误");
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void modifyStu(ResultVo resultVo, StudentQuery studentQuery) {

	}

	/***
	 * ClassName:AdministratorsServiceImpl
	 * Description: 获得所有的血神信息
	 * param:[resultVo]
	 * return:void
	 * Author:yu wenhao
	 * date:2020/3/27
	 */
	@Override
	public void getAllStu(ResultVo resultVo) {
		resultVo.setCode(0);
		resultVo.setMessage("查询成功");
		resultVo.setData(schoolStudentMapper.getAllStu());
	}
}
