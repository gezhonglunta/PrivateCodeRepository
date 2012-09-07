package learn.webimage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 * @author hongbo.ling
 *
 */
public class DateUtil {

	public static final String FORMAT_DEFAULT = "yyyy-MM-dd";
	private final static TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");
	public static final String FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_SMALL = "yyyyMMddHHmmss";
	public static final String FORMAT_ALL_M = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String FORMAT_DEFAULT_SMALL = "yyyyMMdd";
	public static final String FORMAT_TIME = "HH:mm:ss";
	public static final String FORMAT_TIME_HOUR = "HH";
	public static final String FORMAT_TIME_MINUTE = "mm";
	public static final String FORMAT_TIME_SECOND = "ss";

	/**
	 * 自定义格式化dateformat类（不可对其赋值）
	 */

	private static DateFormat getDateFormat(String patten) {

		if (StringUtils.equals(FORMAT_DEFAULT, patten)) {
			return new SimpleDateFormat(FORMAT_DEFAULT);
		} else if (StringUtils.equals(FORMAT_ALL, patten)) {
			return new SimpleDateFormat(FORMAT_ALL);
		} else if (StringUtils.equals(FORMAT_ALL_M, patten)) {
			return new SimpleDateFormat(FORMAT_ALL_M);
		} else if (StringUtils.equals(FORMAT_SMALL, patten)) {
			return new SimpleDateFormat(FORMAT_SMALL);
		} else if (StringUtils.equals(FORMAT_DEFAULT_SMALL, patten)) {
			return new SimpleDateFormat(FORMAT_DEFAULT_SMALL);
		} else {
			return new SimpleDateFormat(patten);
		}
	}

	public static int getIntervalDays(String startdate, String enddate) {
		Calendar d1 = new GregorianCalendar();
		Calendar d2 = new GregorianCalendar();
		try {

			d1.setTime(getDateFormat(FORMAT_DEFAULT).parse(startdate));
			d2.setTime(getDateFormat(FORMAT_DEFAULT).parse(enddate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long ei = d2.getTimeInMillis() - d1.getTimeInMillis();
		int dd = (int) (ei / (1000 * 60 * 60 * 24));
		return dd;
	}

	public static int getIntervalDays(Date startdate, Date enddate) {
		Calendar d1 = new GregorianCalendar();
		d1.setTime(startdate);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(enddate);

		long ei = d2.getTimeInMillis() - d1.getTimeInMillis();
		int dd = (int) (ei / (1000 * 60 * 60 * 24));
		return dd;
	}

	public static String getToday() {
		return getDateFormat(FORMAT_ALL).format(Calendar.getInstance().getTime());
	}

	/**
	 * 以当前时间加天数的日期
	 * @param day  推移的天数
	 * @return
	 */
	public static String addCurrentDate(int day) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return getDateFormat(FORMAT_DEFAULT).format(calendar.getTime());
	}

	/**
	 * 将一个字符串的日期描述转换为java.util.Date对象
	 *
	 * @param strDate
	 *            字符串的日期描述
	 * @param format
	 *            字符串的日期格式，比如:“yyyy-MM-dd HH:mm”
	 * @return 字符串转换的日期对象java.util.Date
	 */
	public static Date getDate(String strDate, String format) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(timeZone);
		Date date = null;
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 解析时间成字符串 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_ALL);
		formatter.setTimeZone(timeZone);
		return formatter.format(date);
	}

	/**
	 * 解析时间成指定字符串
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, String patten) {

		return getDateFormat(patten).format(date);
	}

	/**
	 * 解析指定字符串为日期
	 * @param date
	 * @return
	 */
	public static Date parseDate(String source, String patten) {
		if (StringUtils.isBlank(source) || StringUtils.isBlank(patten)) {
			return null;
		}
		try {
			return getDateFormat(patten).parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	public static Date plusDays(Date time, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + days);
		return cal.getTime();
	}

	/**
	 * 距离小时数
	 * @param startdate
	 * @param enddate
	 * @return
	 * @throws Exception
	 */
	public static int getIntervalHour(String startdate, String enddate) throws Exception {
		Date bDate = getDateFormat(FORMAT_ALL).parse(startdate);
		Date eDate = getDateFormat(FORMAT_ALL).parse(enddate);
		Calendar d1 = new GregorianCalendar();
		d1.setTime(bDate);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(eDate);

		long ei = d2.getTimeInMillis() - d1.getTimeInMillis();
		int dd = (int) (ei / (1000 * 60 * 60));
		if (dd < 1) {
			dd = 1;
		}
		return dd;
	}

	public static String getToday(String format) {
		return getDateFormat(format).format(Calendar.getInstance().getTime());
	}

	public static int getWeekDayFromCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDay = 0;
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			weekDay = 7;
			break;
		case 2:
			weekDay = 1;
			break;
		case 3:
			weekDay = 2;
			break;
		case 4:
			weekDay = 3;
			break;
		case 5:
			weekDay = 4;
			break;
		case 6:
			weekDay = 5;
			break;
		case 7:
			weekDay = 6;
			break;
		default:
			break;
		}
		return weekDay;
	}

	public static int compareDate(Date beforeDate, Date afterDate) {
		return getDateFormat(FORMAT_ALL).format(beforeDate).compareTo(getDateFormat(FORMAT_ALL).format(afterDate));
	}

	public static int compareTime(String beforeTime, String afterTime) {//18:00,19:00
		if (beforeTime.length() < 6 && afterTime.length() < 6) {
			int btime = Integer.parseInt(beforeTime.split(":")[0]);
			int etime = Integer.parseInt(afterTime.split(":")[0]);
			if (btime >= etime) {
				return 1;
			} else {
				return 0;
			}
		}
		return -1;
	}

}