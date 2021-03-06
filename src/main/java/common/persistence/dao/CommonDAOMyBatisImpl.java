package common.persistence.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.domain.PagenateableVO;
import common.exception.DspException;
import common.persistence.dao.handler.StreamHandler;
import common.persistence.dao.page.PageInfo;
import common.persistence.dao.page.PageStatement;
import common.persistence.dao.page.PagenateInfo;

public class CommonDAOMyBatisImpl implements CommonDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonDAOMyBatisImpl.class);

	protected SqlSessionTemplate template;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryForInt(java.lang.String, java.lang.Object)
	 */
	
	public Integer queryForInt(String statementId, Object parameter) {
		Integer value = (Integer) this.template.selectOne(statementId, parameter);

		if (value == null) {
			value = 0;
		}

		return value;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryForLong(java.lang.String,
	 * java.lang.Object)
	 */
	
	public Long queryForLong(String statementId, Object parameter) {
		return (Long) this.template.selectOne(statementId, parameter);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryForObject(java.lang.String,
	 * java.lang.Object, java.lang.Class)
	 */
	
	public <T> T queryForObject(String statementId, Object parameter, Class<T> clazz) {
		return clazz.cast(this.template.selectOne(statementId, parameter));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryForObject(java.lang.String,
	 * java.lang.Object)
	 */
	
	public Object queryForObject(String statementId, Object parameter) {
		return this.template.selectOne(statementId, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryForMap(java.lang.String, java.lang.String,
	 * java.lang.Object)
	 */
	
	public Map<?, ?> queryForMap(String statementId, String mapKey, Object parameter) {
		return this.template.selectMap(statementId, parameter, mapKey);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryForList(java.lang.String,
	 * java.lang.Object, java.lang.Class)
	 */
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(String statementId, Object parameter, Class<T> clazz) {
		List<T> list = (List<T>) this.template.selectList(statementId, parameter);

		LOGGER.debug("Statement[{}] Executed ({}) : {} Records retrieved.", new Object[] { statementId, new Date(),
				list == null ? 0 : list.size() });

		return list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryForList(java.lang.String,
	 * java.lang.Object)
	 */
	
	public List<?> queryForList(String statementId, Object parameter) {
		List<?> list = this.template.selectList(statementId, parameter);

		LOGGER.debug("Statement[{}] Executed ({}) : {} Records retrieved.", new Object[] { statementId, new Date(),
				list == null ? 0 : list.size() });

		return list;
	}

	@SuppressWarnings("unchecked")
	
	public <T> PageInfo<T> queryForPagenatedList(PageStatement statement, Object parameter) {
		int totalCount = this.queryForInt(statement.getTotalCountStatementId(), parameter);

		List<T> list = null;
		PagenateInfo pim = new PagenateInfo();

		try {
			pim = (PagenateInfo) (parameter.getClass().getMethod("getPage")).invoke(parameter);
			pim.setTotalCount(totalCount);
			(parameter.getClass().getMethod("setPage", PagenateInfo.class)).invoke(parameter, pim);
		} catch (Exception ex) {
			LOGGER.error("queryForPagenatedList 중 에러가 발생 했습니다. \\n{}", ex);
			throw new DspException("ValueObject : " + parameter.getClass() + "에 PagenateInfoVO 객체를 확인 하세요.", ex);
		}

		if (totalCount > 0) {
			list = (List<T>) this.template.selectList(statement.getDataStatementId(), parameter);
		}

		PageInfo<T> pageInfo = new PageInfo<T>(list == null ? new ArrayList<T>(0) : list);
		pageInfo.setPage(pim);

		return pageInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryForPagenatedList(com.sktelecom.tlife
	 * .framework.core.persistence.dao.page.PageStatement, java.lang.Object, int, int, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	
	public <T> PageInfo<T> queryForPagenatedList(PageStatement statement, Object parameter, int pageNum, int pageRows) {
		int totalCount = this.queryForInt(statement.getTotalCountStatementId(), parameter);

		List<T> list = null;
		PageInfo<T> pageInfo = null;

		boolean hasPagenateInfoVO = false;

		if (PagenateableVO.class.isAssignableFrom(parameter.getClass())) {
			hasPagenateInfoVO = true;
		} else {
			for (Field field : parameter.getClass().getDeclaredFields()) {
				if (field.getType().isAssignableFrom(PagenateInfo.class)) {
					hasPagenateInfoVO = true;
				}
			}
		}

		if (hasPagenateInfoVO) {
			PagenateInfo pim = new PagenateInfo();
			try {
				pim.setTotalCount(totalCount);
				pim.setNo(pageNum);
				pim.setRows(pageRows);
				(parameter.getClass().getMethod("setPage", PagenateInfo.class)).invoke(parameter, pim);
			} catch (Exception ex) {
				LOGGER.error("queryForPagenatedList 중 에러가 발생 했습니다. \\n{}", ex);
				throw new DspException("ValueObject : " + parameter.getClass() + "에 PagenateInfoVO 객체를 확인 하세요.", ex);
			}

			if (totalCount > 0) {
				list = (List<T>) this.template.selectList(statement.getDataStatementId(), parameter);

				LOGGER.debug("Statement[{}] Executed ({}) : {} Records retrieved.",
						new Object[] { statement.getDataStatementId(), new Date(), list == null ? 0 : list.size() });
			}

			pageInfo = new PageInfo<T>(list == null ? new ArrayList<T>(0) : list);
			pageInfo.setPage(pim);
		} else {
			if (totalCount > 0) {
				int skipRows = (pageNum - 1) * pageRows;

				list = (List<T>) this.template.selectList(statement.getDataStatementId(), parameter, new RowBounds(
						skipRows, pageRows));

				LOGGER.debug("Statement[{}] Executed ({}) : {} Records retrieved.",
						new Object[] { statement.getDataStatementId(), new Date(), list == null ? 0 : list.size() });
			}
			pageInfo = new PageInfo<T>(list == null ? new ArrayList<T>(0) : list);
		}
		return pageInfo;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryWithResultHandler(java.lang.String,
	 * java.lang.Object, com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler)
	 */
	
	public <T, R> List<R> queryWithResultHandler(String statementId, Object parameter,
			final StreamHandler<T, R> streamHandler) {
		streamHandler.open();

		try {
			final List<R> list = new ArrayList<R>();

			this.template.select(statementId, parameter, new ResultHandler() {
				
				@SuppressWarnings("unchecked")
				public void handleResult(ResultContext context) {
					R result = streamHandler.handleRow((T) context.getResultObject());

					if (result != null) {
						list.add(result);
					}

					if (streamHandler.isStop()) {
						context.stop();
					}
				}
			});

			return list;
		} finally {
			streamHandler.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#queryWithResultHandler(com.sktelecom.tlife
	 * .framework.core.persistence.dao.page.PageStatement, java.lang.Object, int, int,
	 * com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler)
	 */
	
	public <T, R> PageInfo<R> queryWithResultHandler(PageStatement statement, Object parameter, int pageNum,
			int pageRows, final StreamHandler<T, R> streamHandler) {
		streamHandler.open();

		try {
			int totalRowCount = this.queryForInt(statement.getTotalCountStatementId(), parameter);
			final List<R> list = new ArrayList<R>();

			if (totalRowCount > 0) {
				int startRows = (pageNum - 1) * pageRows;
				int endRows = pageRows;

				LOGGER.debug("Start={}, End={}", startRows, endRows);

				this.template.select(statement.getDataStatementId(), parameter, new RowBounds(startRows, endRows),
						new ResultHandler() {

							@SuppressWarnings("unchecked")
							
							public void handleResult(ResultContext context) {
								R result = streamHandler.handleRow((T) context.getResultObject());

								if (result != null) {
									list.add(result);
								}

								if (streamHandler.isStop()) {
									context.stop();
								}
							}
						});
			}

			LOGGER.debug("Statement[{}] Executed ({}) : {} Records retrieved.",
					new Object[] { statement.getDataStatementId(), new Date(), list == null ? 0 : list.size() });

			return new PageInfo<R>(totalRowCount, list);

		} finally {
			streamHandler.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#update(java.lang.String, java.lang.Object)
	 */
	
	public Integer update(String statementId, Object parameter) {
		return this.template.update(statementId, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#insert(java.lang.String, java.lang.Object)
	 */
	
	public Integer insert(String statementId, Object parameter) {
		return this.template.insert(statementId, parameter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.CommonDAO#delete(java.lang.String, java.lang.Object)
	 */
	
	public Integer delete(String statementId, Object parameter) {
		return this.template.delete(statementId, parameter);
	}

	/**
	 * {@link SqlSessionTemplate}에 대한 의존성을 직접 주입.
	 *
	 * @param template
	 *            template
	 */
	public void setSqlSessionTemplate(SqlSessionTemplate template) {
		this.template = template;
	}
}
