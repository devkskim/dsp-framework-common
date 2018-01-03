package common.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import common.util.StringUtils;

/**
 * <p>
 * Error code translator interface.
 * </p>
 * <ul>
 * <li>Updated on : 2014-11-05</li>
 * <li>Updated by : 아키텍쳐팀, SK플래닛.</li>
 * </ul>
 */
public abstract class DspErrorCodeTranslator {

	/**
	 * 기본 package 명
	 */
	protected static final String BASE_PACKAGE_NAME = "com.sktelecom.tlife.";

	/**
	 * 에러코드 MAP
	 */
	protected Map<String, String> errorCodeMap;

	/**
	 *
	 * <pre>
	 * 생성자.
	 * </pre>
	 */
	protected DspErrorCodeTranslator() {

		this.errorCodeMap = new HashMap<String, String>();

		String frkPkgNm = BASE_PACKAGE_NAME + "framework";

		this.errorCodeMap.put(frkPkgNm, TlifeFrameworkErrorCodeSpec.ERR_FRWK_COMMON.code());
		this.errorCodeMap.put(frkPkgNm + ".core.cryto", TlifeFrameworkErrorCodeSpec.ERR_FRWK_CRYTO.code());
		this.errorCodeMap.put(frkPkgNm + ".core.persistence.dao", TlifeFrameworkErrorCodeSpec.ERR_FRWK_DAO.code());
		this.errorCodeMap.put(frkPkgNm + ".core.log", TlifeFrameworkErrorCodeSpec.ERR_FRWK_LOG.code());
		this.errorCodeMap.put(frkPkgNm + ".web", TlifeFrameworkErrorCodeSpec.ERR_FRWK_WEB.code());
	}

	/**
	 *
	 * <pre>
	 * 최상위 package 명 반환.
	 * </pre>
	 * 
	 * @param packagee
	 *            package 명
	 * @return 최상위 package 명
	 */
	public String translate(String packagee) {
		java.util.List<String> splitedPackageList = Arrays.asList(StringUtils.split(packagee, "."));

		String result;

		for (int index = splitedPackageList.size() - 1, min = 0; index >= min; index--) {
			String matchPkgNm = StringUtils.join(splitedPackageList.subList(0, index), ".");
			result = this.errorCodeMap.get(matchPkgNm);

			if (result != null) {
				return result;
			}
		}

		return null;
	}

	enum TlifeFrameworkErrorCodeSpec {
		ERR_FRWK_COMMON("ERR_FRWK_0001", "ERR_FRWK_0001"), ERR_FRWK_CRYTO("ERR_CRYP_0001", "ERR_CRYP_0001"), ERR_FRWK_DAO(
				"ERR_DAO_0001", "ERR_DAO_0001"), ERR_FRWK_LOG("ERR_LOG_0001", "ERR_LOG_0001"), ERR_FRWK_WEB(
				"ERR_WEB_0001", "ERR_WEB_0001");

		private String code;

		private String msgCode;

		private TlifeFrameworkErrorCodeSpec(String code, String msgCode) {
			this.code = code;
			this.msgCode = msgCode;
		}

		/**
		 * @return the code
		 */
		public String code() {
			return this.code;
		}

		/**
		 * @return the msgCode
		 */
		public String messageCode() {
			return this.msgCode;
		}
	}
}
