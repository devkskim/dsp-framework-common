package common.exception;

import com.dsp.common.context.DspReturnCodeSpec;
import com.dsp.common.exception.domain.ErrorInfo;
import com.dsp.common.util.StringUtils;

/**
 * <p>
 * Tlife Framework Exception.
 * </p>
 * <ul>
 * <li>Updated on : 2014-11-05</li>
 * <li>Updated by : 아키텍쳐팀, SK플래닛.</li>
 * </ul>
 */
public class DspException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorInfo errorInfo;

	/**
	 * @param errorInfo
	 *            에러정보
	 */
	public DspException(ErrorInfo errorInfo) {
		super(errorInfo.getCode());
		this.errorInfo = errorInfo;

	}

	/**
	 * @param errorInfo
	 *            에러정보
	 * @param cause
	 *            원인 에러
	 */
	public DspException(ErrorInfo errorInfo, Throwable cause) {
		super(errorInfo.getCode(), cause);
		this.errorInfo = errorInfo;

	}

	/**
	 * @param code
	 *            에러코드
	 */
	public DspException(String code) {
		this(code, null, (Object[]) null);

	}

	/**
	 * @param code
	 *            에러코드
	 * @param args
	 *            에러메시지 아큐먼트
	 */
	public DspException(String code, Object... args) {
		this(code, null, args);
	}

	/**
	 * @param code
	 *            에러코드
	 * @param cause
	 *            원인 에러
	 */
	public DspException(String code, Throwable cause) {
		this(code, cause, (Object[]) null);

	}

	/**
	 * @param code
	 *            에러코드
	 * @param cause
	 *            원인 에러
	 *
	 * @param args
	 *            에러메시지 파라미터
	 */
	public DspException(String code, Throwable cause, Object... args) {
		super(code, cause);
		this.errorInfo = new ErrorInfo();
		this.errorInfo.setCode(code);
		this.errorInfo.setArgs(args);
	}

	/**
	 * @param code
	 *            에러코드
	 * @param cause
	 *            원인 에러
	 *
	 * @param args
	 *            에러메시지 파라미터
	 */
	public DspException(DspReturnCodeSpec codespec, Throwable cause, Object... args) {
		super(codespec.code(), cause);
		this.errorInfo = new ErrorInfo();
		this.errorInfo.setCode(codespec.code());
		this.errorInfo.setArgs(args);
	}

	/**
	 * <pre>
	 * 에러정보 취득 메소드.
	 * </pre>
	 *
	 * @return 에러정보
	 */
	public ErrorInfo getErrorInfo() {
		return this.errorInfo;
	}

	/**
	 * <pre>
	 * 에러정보 설정 메소드.
	 * </pre>
	 *
	 * @param errorInfo
	 *            에러정보
	 */
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	/**
	 * <pre>
	 * 코드를 가져오는 메소드.
	 * </pre>
	 *
	 * @return 코드
	 */
	public String getCode() {
		if (this.errorInfo == null || StringUtils.isEmpty(this.errorInfo.getCode())) {
			return null;
		}

		return this.errorInfo.getCode();
	}

}
