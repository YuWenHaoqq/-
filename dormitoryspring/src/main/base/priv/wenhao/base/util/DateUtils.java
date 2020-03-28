package priv.wenhao.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static Date stringToDate(String val) throws ParseException {
		DateFormat fmt =new SimpleDateFormat("yyyy/MM/dd");
		Date date = fmt.parse(val);
		return date;

	}
}
