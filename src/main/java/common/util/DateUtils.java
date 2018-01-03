package common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <p>
 * {@link org.apache.commons.lang3.time.DateUtils} 기반 X-Life Framework DateUtils 클래스.
 * </p>
 * <ul>
 * <li>Updated on : 2014-11-11</li>
 * <li>Updated by : 아키텍쳐팀, SK플래닛.</li>
 * </ul>
 *
 * @see org.apache.commons.lang3.time.DateUtils
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	/** 날짜시간형식 패턴 뮤직메이트 연동용 yyyy-MM-dd'T'HH:mm:ss */
	public static final String DATETIME_PATTERN_MUSICMATE = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * <pre>
	 * 주어진 Milisecond 값을 주어진 Date Format에 맞게 변환한 문자열을 반환 한다.
	 * </pre>
	 *
	 * @param duration
	 *            경과 시간
	 * @param dateFormat
	 *            출력 일시 형식
	 * @return 일시 형식의 문자열
	 */
	public static String converDurationTime(long duration, String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(duration);
		// 시간 정보 초기화 작업.
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.DATE, 31);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		String timeStr = new SimpleDateFormat(dateFormat).format(cal.getTime());
		return timeStr;
	}

	/**
	 * <pre>
	 * 현재시각(yyyyMMddHHmmssSS).
	 * </pre>
	 *
	 * @return
	 */
	public static String currentTime() {

		Calendar cal = Calendar.getInstance();

		String timeStr = new SimpleDateFormat("yyyyMMddHHmmssSS").format(cal.getTime());

		return timeStr;

	}

	public static String currentTime(String dateFormat) {

		Calendar cal = Calendar.getInstance();

		String timeStr = null;

		try {
			timeStr = new SimpleDateFormat(dateFormat).format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return timeStr;
	}

	/**
	 * <pre>
	 * input String + n hour (yyyyMMddHHmmssSS).
	 * </pre>
	 *
	 * @return
	 */
	public static String getDateStringAddHour(String dateStr, int addHour) throws Exception {

		Calendar cal = Calendar.getInstance();

		cal.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(dateStr)); // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, addHour); // adds one hour

		String timeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime());

		return timeStr;

	}

	/**
	 * <pre>
	 * 현재시각(yyyyMMddHHmmss).
	 * </pre>
	 *
	 * @return
	 */
	public static String currentDate() {
		return currentTime("yyyyMMddHHmmss");
	}

	/**
	 * <pre>
	 * 현재날짜(yyyyMMdd).
	 * </pre>
	 *
	 * @return
	 */
	public static String currentDay() {
		return currentTime("yyyyMMdd");
	}

	/**
	 * <pre>
	 * 현재날짜(yyyyMMdd).
	 * </pre>
	 *
	 * @return
	 */
	public static String currentMonth() {
		return currentTime("yyyyMM");
	}
}
