package common.exception;

public class DspXssInvalidException extends DspException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4224646555760972785L;

	public DspXssInvalidException(String code, Object... args) {
		super(code, args);
	}
}
