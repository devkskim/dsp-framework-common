package common.exception;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import com.dsp.common.context.DspReturnCodeSpec;
import com.dsp.common.exception.domain.ErrorInfo;

public class DspNetworkException extends DspException {
	private static final long serialVersionUID = -9051058832079905941L;

	private static final String ERROR_CODE = DspReturnCodeSpec.ERR_NET_GENERAL.code();

	/**
	 * <pre>
	 * Contructor.
	 * </pre>
	 *
	 * @param cause
	 *            예외 유발 정보.
	 */
	public DspNetworkException(Throwable cause) {
		super(ERROR_CODE, cause);
	}

	/**
	 * <pre>
	 * Contructor.
	 * </pre>
	 *
	 * @param errorInfo
	 *            에러 모델 객체.
	 * @param cause
	 *            예외 유발 정보.
	 */
	public DspNetworkException(ErrorInfo errorInfo, Throwable cause) {
		super(errorInfo, cause);
	}

	/**
	 * <pre>
	 * 네트워크 관련 예외 인지의 여부를 반환한다.
	 * </pre>
	 *
	 * @param throwable
	 *            판단 대상 예외 정보
	 * @return 네트워크 관련 예외 정보인지의 여부 (true/false)
	 */
	public static boolean isNetworkException(Throwable throwable) {
		if (throwable instanceof IOException
				&& StringUtils.startsWith(throwable.getClass().getPackage().getName(), "java.net")) {
			return true;
		}

		else if (throwable instanceof HttpClientErrorException) {
			return true;
		}

		return false;
	}
}