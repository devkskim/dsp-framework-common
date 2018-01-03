package common.context;

public enum DspDefaultMessageCodeSpec {
	MSG_DEFAULT_CREATE_SUCC("operation.default.msg.create.succ"),
	MSG_DEFAULT_MODIFY_SUCC("operation.default.msg.modify.succ"),
	MSG_DEFAULT_REMOVE_SUCC("operation.default.msg.remove.succ"),
	MSG_DEFAULT_RETRIEVE_SUCC("operation.default.msg.retrieve.succ"),
	MSG_DEFAULT_SEARCH_SUCC("operation.default.msg.search.succ"),

	MSG_DEFAULT_CREATE_FAIL("operation.default.msg.create.fail"),
	MSG_DEFAULT_MODIFY_FAIL("operation.default.msg.modify.fail"),
	MSG_DEFAULT_REMOVE_FAIL("operation.default.msg.remove.fail"),
	MSG_DEFAULT_RETRIEVE_FAIL("operation.default.msg.retrieve.fail"),
	MSG_DEFAULT_SEARCH_FAIL("operation.default.msg.search.fail"),

	MSG_DEFAULT_LOGIN_SUCC("msg.login.succ"),
	MSG_DEFAULT_LOGIN_FAIL("msg.login.fail"),

	MSG_TYPE_MISMATCH_NUMBER("typeMismatch.java.lang.NumberFormatException"),
	MSG_TYPE_MISMATCH_INTEGER("typeMismatch.java.lang.Integer"),
	MSG_TYPE_MISMATCH_LONG("typeMismatch.java.lang.Long"),
	MSG_TYPE_MISMATCH_FLOAT("typeMismatch.java.lang.Float"),
	MSG_TYPE_MISMATCH_DOUBLE("typeMismatch.java.lang.Double"),
	MSG_TYPE_MISMATCH_INT("typeMismatch.int"),
	MSG_TYPE_MISMATCH_GENERAL("typeMismatch"),

	NOT_ALLOW_FALSE("AssertFalse"),
	NOT_ALLOW_TRUE("AssertTrue"),
	NOT_ALLOW_GREATER_DECIMAL("DecimalMax"),
	NOT_ALLOW_LESS_DECIMAL("DecimalMin"),
	NOT_ALLOW_NOT_FUTURE_TIME("Future"),
	NOT_ALLOW_GREATER("Max"),
	NOT_ALLOW_LESS("Min"),
	NOT_ALLOW_NOTNULL("NotNull"),
	NOT_ALLOW_NULL("Null"),
	NOT_ALLOW_NOT_PAST_TIME("Past"),
	NOT_ALLOW_NOT_MATCHED_PATTERN("Pattern"),
	NOT_ALLOW_SIZE("Size"),

	INVALID_CARD_NO("CreditCardNumber"),
	INVALID_EMAIL("Email"),
	INVALID_LENGTH("Length"),
	NOT_ALLOW_BLANK("NotBlank"),
	NOT_ALLOW_EMPTY("NotEmpty"),
	INVALID_RANGE("Range"),
	INVALID_SAFE_HTML("SafeHtml"),
	INVALID_SAFE_SCRIPT("ScriptAssert"),
	INVALID_URL("URL");

	private String msgCode;

	private DspDefaultMessageCodeSpec(String msgCode) {
		this.msgCode = msgCode;
	}

	/**
	 * @return the msgCode
	 */
	public String messageCode() {
		return this.msgCode;
	}
}
