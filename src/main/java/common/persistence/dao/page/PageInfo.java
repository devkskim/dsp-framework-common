package common.persistence.dao.page;

import java.util.List;

/**
 * <p>
 * Page Info.
 * </p>
 * <ul>
 * <li>Updated on : 2014-11-05</li>
 * <li>Updated by : 아키텍쳐팀, SK플래닛.</li>
 * </ul>
 *
 * @param <T>
 *            오브젝트 제너릭
 */
public class PageInfo<T> implements Pagenateable {

	/**
	 * 생성자.
	 */
	public PageInfo() {
	}

	/**
	 * @param data
	 *            data
	 */
	public PageInfo(List<T> data) {
		this.data = data;
	}

	/**
	 *
	 * @param totalCount
	 *            totalCount
	 * @param data
	 *            data
	 */
	public PageInfo(int totalCount, List<T> data) {
		if (this.page == null)
			this.page = new PagenateInfo();
		this.data = data;
		this.page.setTotalCount(totalCount);
	}

	/**
	 * paging 객체.
	 */
	private PagenateInfo page = new PagenateInfo();

	public PagenateInfo getPage() {
		return this.page;
	}

	public void setPage(PagenateInfo pim) {
		this.page = pim;
	}

	private List<T> data;

	/**
	 * get totalCount.
	 *
	 * @return totalRowCount
	 */
	public int getTotalRowCount() {
		return this.page.getTotalCount();
	}

	/**
	 * List<데이터 테이블>.
	 *
	 * @return List<T>
	 */
	public List<T> getData() {
		return this.data;
	}

}
