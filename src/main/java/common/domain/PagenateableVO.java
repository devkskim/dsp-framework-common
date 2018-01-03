/*
 * Copyright (c) 2014 SK telecom.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK telecom.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK telecom.
 */
package common.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.dsp.common.persistence.dao.page.PagenateInfo;
import com.dsp.common.persistence.dao.page.Pagenateable;

/**
 * <p>
 * X Life 페이지 처리 지원 Value Object abstract class.
 * </p>
 * <ul>
 * <li>Updated on : 2014-11-11</li>
 * <li>Updated by : 아키텍쳐팀, SK플래닛.</li>
 * </ul>
 */
@SuppressWarnings("serial")
public abstract class PagenateableVO extends CommonVO implements Pagenateable {

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	protected PagenateInfo page = null;

	@Override
	public PagenateInfo getPage() {
		return this.page;
	}

	@Override
	public void setPage(PagenateInfo pim) {
		this.page = pim;
	}

}
