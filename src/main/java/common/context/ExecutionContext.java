package common.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * <p>
 * 작업 실행과정중 발생하는 메세지 및 처리 결과를 저장함
 * </p>
 * <ul>
 * <li>Updated on : 2014-11-11</li>
 * <li>Updated by : 아키텍쳐팀, SK플래닛.</li>
 * </ul>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "code", "message", "responseTime", "response" })
@SuppressWarnings("serial")
public class ExecutionContext<T> implements Serializable {

	private final StringBuilder message = new StringBuilder();

	private String code = DspReturnCodeSpec.SUCC_PROCESS.code();

	private String responseTime;

	@JsonProperty("response")
	private T result;

	@JsonIgnore
	private final List<MessageCode> messageCodes = new ArrayList<MessageCode>();

	private ResultStatus resultStatus;

	/**
	 * 생성자.
	 */
	public ExecutionContext() {
	}

	/**
	 * 생성자.
	 *
	 * @param messageCode
	 *            메시지코드
	 */
	public ExecutionContext(MessageCode messageCode) {
		this.messageCodes.add(messageCode);
	}

	/**
	 * 생성자.
	 *
	 * @param resultStatus
	 *            결과상태
	 * @param message
	 *            메시지
	 */
	public ExecutionContext(ResultStatus resultStatus, String message) {
		this.resultStatus = resultStatus;
		this.message.append(message);
	}

	/**
	 * 생성자.
	 *
	 * @param resultStatus
	 *            결과상태
	 * @param message
	 *            메시지
	 */
	public ExecutionContext(ResultStatus resultStatus, String code, String message) {
		this.resultStatus = resultStatus;
		this.code = code;
		this.message.append(message);
	}

	/**
	 * 결과 상태.
	 *
	 * @return ResultStatus
	 */
	public ResultStatus getResultStatus() {
		return this.resultStatus;
	}

	/**
	 * 결과 상태 설정.
	 *
	 * @param resultStatus
	 *            결과 상태
	 * @return ExecutionContext
	 */
	public ExecutionContext<T> setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
		return this;
	}

	/**
	 * 메시지.
	 *
	 * @return String message
	 */
	public String getMessage() {
		return this.message.toString();
	}

	/**
	 * 메시지 추가.
	 *
	 * @param message
	 *            메시지
	 * @return ExecutionContext
	 */
	public ExecutionContext<T> addMessage(String message) {
		this.message.append(message);

		return this;
	}

	/**
	 * 결과 설정.
	 *
	 * @param result
	 *            result
	 * @return ExecutionContext
	 */
	public ExecutionContext<T> setResult(T result) {
		this.result = result;

		return this;
	}

	/**
	 * 결과.
	 *
	 * @return Object
	 */
	public T getResult() {
		return this.result;
	}

	/**
	 * 메시지 코드.
	 *
	 * @return List<MessageCode>
	 */
	public List<MessageCode> getMessageCodes() {
		return this.messageCodes;
	}

	/**
	 * 메시지 코드 추가.
	 *
	 * @param messageCode
	 *            메시지코드
	 */
	public void addMessageCodes(MessageCode messageCode) {
		this.messageCodes.add(messageCode);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

}
