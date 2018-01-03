package common.persistence.dao.handler;

/**
 * <p>
 * 대용량 데이터의 스트리밍 처리시 사용되며, MyBatis의 ResultHandler와 함께 사용된다.
 * </p>
 * <ul>
 * <li>Updated on : 2014-11-05</li>
 * <li>Updated by : 아키텍쳐팀, SK플래닛.</li>
 * </ul>
 * 
 * @param <T>
 *            오브젝트 제너릭
 * @param <R>
 *            오브젝트 제너릭
 */
public interface StreamHandler<T, R> {

	/**
	 * 스트리밍 헤더 쓰기.
	 */
	void open();

	/**
	 * 스트리밍 마무리.
	 */
	void close();

	/**
	 * 행 단위 처리.
	 *
	 * @param valueObject
	 *            행 데이터.
	 * @return the r
	 */
	R handleRow(T valueObject);

	/**
	 * Stop.
	 */
	void stop();

	/**
	 * Checks if is stop.
	 *
	 * @return true, if is stop
	 */
	boolean isStop();

}
