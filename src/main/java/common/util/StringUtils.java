package common.util;

import java.util.ArrayList;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	/**
	 * <pre>
	 * ArrayList 데이터를 받아 separator 구분자를 가진 String 형태로 반환 한다.
	 * </pre>
	 *
	 * @param values
	 *            ArrayList 형 입력 데이터
	 * @param separator
	 *            String 형 구분자
	 * @return String ArrayList[0]{separator}ArrayList[1]{separator}...
	 */
	@SuppressWarnings("rawtypes")
	public static String convertArrayListToString(ArrayList values, String separator) {
		StringBuffer strBuffer = new StringBuffer();
		if (values != null && values.size() > 0) {
			for (int i = 0; i < values.size(); i++) {
				strBuffer.append(values.get(i));
				if (i < values.size() - 1) {
					strBuffer.append(separator);
				}
			}
		}
		return strBuffer.toString();
	}
}
