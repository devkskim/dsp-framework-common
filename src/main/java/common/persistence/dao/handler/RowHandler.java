package common.persistence.dao.handler;

public abstract class RowHandler<T, R> implements StreamHandler<T, R> {

	/** The stop. */
	private boolean stop = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#open()
	 */
	@Override
	public void open() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#close()
	 */
	@Override
	public void close() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#isStop()
	 */
	@Override
	public boolean isStop() {
		return this.stop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#stop()
	 */
	@Override
	public void stop() {
		this.stop = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sktelecom.tlife.framework.core.persistence.dao.handler.StreamHandler#handleRow(java.lang.Object)
	 */
	@Override
	public abstract R handleRow(T valueObject);

}