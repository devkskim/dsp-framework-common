package common.context;

/**
 * <p>
 * 메시지 코드.
 * </p>
 * <ul>
 * <li>Updated on : 2014-11-11</li>
 * <li>Updated by : 아키텍쳐팀, SK플래닛.</li>
 * </ul>
 */
public class MessageCode {

	private final String code;
	private final Object[] arguments;
	private final String defaultMessage;

	/**
	 * 생성자.
	 *
	 * @param code
	 *            코드
	 */
	public MessageCode(String code) {
		this(code, null, null);
	}

	/**
	 * 생성자.
	 *
	 * @param code
	 *            코드
	 * @param defaultMessage
	 *            기본 메시지
	 */
	public MessageCode(String code, String defaultMessage) {
		this(code, null, defaultMessage);
	}

	/**
	 * 생성자.
	 *
	 * @param code
	 *            코드
	 * @param arguments
	 *            아규먼트
	 * @param defaultMessage
	 *            기본 메시지
	 */
	public MessageCode(String code, Object[] arguments, String defaultMessage) {
		this.code = code;
		this.arguments = arguments;
		this.defaultMessage = defaultMessage;
	}

	/**
	 * 코드.
	 *
	 * @return code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 아큐먼트.
	 *
	 * @return arguments
	 */
	public Object[] getArguments() {
		return this.arguments;
	}

	/**
	 * 기본 메시지.
	 *
	 * @return defaultMessage
	 */
	public String getDefaultMessage() {
		return this.defaultMessage;
	}

}
