package com.jdjtlibrary.wssocket;

import java.util.LinkedList;

/**
 * 基于socket的返回值分发器，本地登录的情况。
 * @author chenfei
 */
public class SckResultDispatcher{
    private static final String TAG = "SckCommunicator";

    protected final LinkedList<OnResultDispatchListener> mDispatchListeners;
    {
        mDispatchListeners = new LinkedList<OnResultDispatchListener>();
    }

    protected final SckCommunicator mCommunicator;


    public SckResultDispatcher(SckCommunicator communicator) {
        if(communicator == null)throw new NullPointerException("communicator is null");
        mCommunicator = communicator;
    }

    /**
     * 添加一个返回值分发监听器
     * @param listener 监听器
     */
    public void addOnResultDispatchListener(OnResultDispatchListener listener){
        synchronized (mDispatchListeners){
            if(!mDispatchListeners.contains(listener)){
                mDispatchListeners.add(listener);
            }
            System.out.println(TAG +"listener size " + mDispatchListeners.size());
        }
    }

    /**
     * 移除一个返回值监听器
     * @param listener 监听器
     */
    public void removeOnResultDispatchListener(OnResultDispatchListener listener){
        synchronized (mDispatchListeners){
            mDispatchListeners.remove(listener);
            System.out.println(TAG + "listener size " + mDispatchListeners.size());
        }
    }

    /**
     * 实现了在本地通过socket连接主机的返回值分发器。<p>
     * 这种情况下主机的返回值可能是多条连在一起返回回来的，
     * 如果满足这种情况则先拆分成单条的返回值直接循环分发。
     * @param result 来自于主机的返回值
     */
    protected void dispatchResult(String result) {
        /**无意义，不进行派分*/
        if(result == null || result.isEmpty())return;
        /**粘包处理，不考虑半包的情况*/
        String[] splitResult = result.split("(?<=.)(?=RESPONSE)", -1);
        for(String ret : splitResult){
            for(OnResultDispatchListener listener : mDispatchListeners){
                //这种机制当有接收者抢点了返回值，则不会向后面的接收者派分
               /* try{
                    if(listener.onResultDispatch(mCommunicator, ret))break;
                }catch(Exception e){
                    //捕获异常保证分发下去的过程中如果发生异常时，不影响后续分发
                    e.printStackTrace();
                }*/

                //这种方式类似于广播，会向每个接收者派分
                listener.onResultDispatch(mCommunicator, ret);
            }
        }
    }

    /**
     * 返回值分发时的监听器
     * @author chenfei
     */
    public interface OnResultDispatchListener {
        /**
         * 当分发返回值时
         * @param communicator 返回值来源的通信器
         * @param result 来自主机的返回值
         * @return true已经获取到需要的返回值，false未获取到需要的返回值;
         */
        boolean onResultDispatch(SckCommunicator communicator, String result);
    }
}
