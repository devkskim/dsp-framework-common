package common.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7354924158577055603L;

	/**
	 * Create Valid Interface Class.
	 *
	 */
	public interface Create {
	}

	/**
	 * Modify Valid Interface Class.
	 *
	 */
	public interface Modify {
	}

	/**
	 * View Valid Interface Class.
	 *
	 */
	public interface View {
	}

	/**
	 * Remove Valid Interface Class.
	 *
	 */
	public interface Remove {
	}

	/**
	 * 객체 정보 확인.
	 *
	 * @return string
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * 객체 비교.
	 *
	 * @param obj
	 *            obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
