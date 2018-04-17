package com.jdjtlibrary.wssocket;

/**
 * 超时器
 * @author chenfei
 */
public class TimeoutKit {
	/**超时精确度，超小越精确，单位是ms*/
	public static final int DEF_ACCURACY = 20;
	/**是否继续超时*/
	private boolean mIsContinue;
	/**操作的回调*/
	private TimeoutUtilListener mTimeoutUtilListener;

	/**
	 * 获取是否继续超时
	 * @return
	 */
	private synchronized boolean isContinue(){
		return this.mIsContinue;
	}

	/**
	 * 中断超时
	 */
	public synchronized void interrupt(){
		this.mIsContinue = false;
	}

	/**
	 * 超时工作前的准备工作<p>
	 * 调用{{@link #start(int, Integer)}时，要正确的执行超时，请先调用此方法。
	 * 这样做的目的在于，执行与中断异步时，就算中断在启动之前，那么中断后直接调用{{@link #start(int, Integer)}
	 * 此时并不会超时。
	 * @return
	 */
	public synchronized TimeoutKit prepare(){
		this.mIsContinue = true;
		return this;
	}

	/**
	 * 开时超时工作，中途调用{@link #interrupt()}则会停止工作。
	 * @param timeout 超时时间
	 * @param accuracy 最小越精确，但不能为负数，单位是ms
	 */
	public void start(int timeout, Integer accuracy){
		if(timeout < 0)throw new IllegalArgumentException("the parameter 'timeout' is less than zero!");
		if(accuracy != null && accuracy < 1)throw new IllegalArgumentException("the parameter 'accuracy' is less than one!");
		if(accuracy == null)accuracy = DEF_ACCURACY;
		int useTime = 0;
		for(; useTime < timeout && isContinue(); useTime += accuracy){
			try {
				if(mTimeoutUtilListener != null){
					mTimeoutUtilListener.onProgress(useTime);
				}
				Thread.sleep(accuracy);
			} catch (InterruptedException e) {
				System.out.println("TimeoutKit Interrupted");
			}
		}
		if(mTimeoutUtilListener != null){
			if(isContinue()){
				mTimeoutUtilListener.onTimeout(timeout);
			}else{
				mTimeoutUtilListener.onInterrupted(timeout, useTime);
			}
		}
	}

	/**
	 * 开时超时工作，中途调用{@link #interrupt()}则会停止工作。精度则使用{@link #DEF_ACCURACY}
	 * @param timeout 超时时间
	 */
	public void start(int timeout){
		start(timeout, DEF_ACCURACY);
	}

	/**
	 * 设置超时监听器
	 * @param timeoutListener
	 */
	public void setTimeoutListener(TimeoutUtilListener timeoutListener) {
		this.mTimeoutUtilListener = timeoutListener;
	}

	/**
	 * 超时监听器
	 * @author chenfei
	 */
	public interface TimeoutUtilListener{

		/**
		 * 超时过程中调用，以精度为周期
		 * @param time
		 */
		void onProgress(int time);

		/**
		 * 超时的时候调用
		 * @param timeout 设置的超时时间。
		 */
		void onTimeout(int timeout);

		/**
		 * 中断时调用，中断方式是调用{@link #interrupt()}
		 * @param timeout 设置的超时时间。
		 * @param interruptTime 中断时已经阻塞了的时间
		 */
		void onInterrupted(int timeout, int interruptTime);
	}

	/**
	 * 空实现{@link TimeoutUtilListener}<p>
	 * 有时只全用其中一个方法，为了提高代码简洁性可用此类
	 * @author chenfei
	 */
	public static class TimeoutUtilListenerAdapter implements TimeoutUtilListener{
		@Override
		public void onProgress(int time) {}
		@Override
		public void onTimeout(int timeout) {}
		@Override
		public void onInterrupted(int timeout, int interruptTime) {}
	}
}
