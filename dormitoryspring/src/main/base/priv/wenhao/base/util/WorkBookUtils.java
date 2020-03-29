package priv.wenhao.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import priv.wenhao.base.exception.BussinessException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WorkBookUtils {
	private final static String XLS = "xls";
	private final static String XLSX = "xlsx";

	public static Workbook getWorkBook(MultipartFile file) throws BussinessException {
		Workbook workbook = null;

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
				throw new BussinessException(1, "请上传excel文件");
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
		return workbook;
	}


	/***
	* ClassName:WorkBookUtils
	* Description: 檢查excel表中的数据,不能为空,并且将所有类型都转为string类型,除了date
	* param:[i, cells, row]
	* return:void
	* Author:yu wenhao
	* date:2020/3/28
	*/
	public static void checkVal(int i, int cells, Row row) throws BussinessException {
		for (int j=0;j<cells;j++){
			if (row.getCell(j)==null){
				throw new BussinessException(1,"第"+(1+i)+"行,第"+(1+j)+"列的数据为空,请及时补充");
			}
			if (row.getCell(j).getCellType() != CellType.STRING && HSSFDateUtil.isCellDateFormatted(row.getCell(j))){
				continue;
			}
			row.getCell(j).setCellType(CellType.STRING);
			if (!StringUtils.isNotBlank(row.getCell(j).getStringCellValue())){
				throw new BussinessException(1,"第"+(1+i)+"行,第"+(1+j)+"列的数据为空,请及时补充");
			}
		}

	}

}