package common.context;

/**
 * <p>
 * 메시지 코드 빌더.
 * </p>
 * <ul>
 * <li>Updated on : 2014-11-11</li>
 * <li>Updated by : 아키텍쳐팀, SK플래닛.</li>
 * </ul>
 */
public class MessageCodeBuilder {

	/**
	 * 메시지 코드 생성.
	 *
	 * @param code
	 *            코드
	 * @return {@link MessageCode}
	 */
	public static MessageCode buildMessageCode(String code) {
		return new MessageCode(code);
	}

	/**
	 * 메시지 코드 생성.
	 *
	 * @param code
	 *            코드
	 * @param defaultMessage
	 *            기본 메시지
	 * @return {@link MessageCode}
	 */
	public static MessageCode buildMessageCode(String code, String defaultMessage) {
		return new MessageCode(code, defaultMessage);
	}

	/**
	 * 메시지 코드 생성.
	 *
	 * @param code
	 *            코드
	 * @param arguments
	 *            아규먼트
	 * @param defaultMessage
	 *            기본 메시지
	 * @return {@link MessageCode}
	 */
	public static MessageCode buildMessageCode(String code, Object[] arguments, String defaultMessage) {
		return new MessageCode(code, arguments, defaultMessage);
	}

}
