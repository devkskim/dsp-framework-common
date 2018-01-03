package common.persistence.dao.handler;

public abstract class RowHandler<T, R> implements StreamHandler<T, R> {

	/** The stop. */
	private boolean stop = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#open()
	 */
	public void open() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#close()
	 */
	public void close() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#isStop()
	 */
	public boolean isStop() {
		return this.stop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#stop()
	 */
	public void stop() {
		this.stop = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#handleRow(java.lang.Object)
	 */
	public abstract R handleRow(T valueObject);

}