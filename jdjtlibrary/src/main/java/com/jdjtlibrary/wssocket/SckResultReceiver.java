package com.jdjtlibrary.wssocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * 通信基于socket的返回值接收器
 * @author chenfei
 */
public class SckResultReceiver {
    /**单次接收超时时间,作为调试时观察是否正常工作， ms*/
    private static final int SO_TIMEOUT = 8000;
    /**允许的连续出错次数, 10次连续异常值认为socket存在异常*/
    private static final int ALLOW_ERROR_COUNT = 10;
    /**接收到的返回值的编码*/
    private static final String RECEIVE_DATA_ENCODING = "UTF-8";
    /**接收返回值线程*/
    private RecvTask mRecvThread;
    /**通信器*/
    private SckCommunicator mCommunicator;

    public SckResultReceiver(SckCommunicator communicator){
        if(communicator == null)throw new NullPointerException("communicator is null");
        this.mCommunicator = communicator;
    }

    /**
     * 启动接收器
     * @param socket 连接到主机的套字节
     * @throws Exception 出现异常表明接收器启动失败
     */
    public synchronized void start(Socket socket) throws Exception{
        if(mRecvThread != null){
            mRecvThread.stopSelf();
        }
        mRecvThread = new RecvTask(socket);
        mRecvThread.start();
    }

    /**
     * 停止接收器
     */
    public synchronized void stop(){
        if(mRecvThread != null){
            mRecvThread.stopSelf();
        }
    }

    /**
     * 接收器任务
     */
    private class RecvTask extends Thread {
        private int mExceCount;
        private StringBuilder mRecvData;
        private BufferedReader mReader;
        /**返回值分发器*/
        private SckResultDispatcher mResultDispatcher;
        private boolean mIsRun;

        public RecvTask(Socket socket) throws Exception{
            try{
                this.mResultDispatcher = (SckResultDispatcher)mCommunicator.getResultDispatcher();
                socket.setSoTimeout(SO_TIMEOUT);
                InputStreamReader inputReader = new InputStreamReader(socket.getInputStream(), RECEIVE_DATA_ENCODING);
                mReader = new BufferedReader(inputReader);
                mIsRun = true;
            }catch(Exception e){
                e.printStackTrace();
                release();
                throw e;
            }
        }

        private boolean isRun(){
            return mIsRun && !interrupted();
        }

        public void stopSelf() {
            mIsRun = false;
            interrupt();
        }

        public void run(){
            while(isRun() && !Thread.interrupted()){
                try{
                    String data = listen();
                    mResultDispatcher.dispatchResult(data);
                    /**这个异常只是故意弄的接收超时，调试时观察接收器是否正常*/
                }catch(SocketTimeoutException e){
                    System.out.println("********SoTimeout*********");
                }catch(Exception e){
                    System.out.println("********连接异常*********");
                    e.printStackTrace();
                    break;
                }
            }
            /**释放资源*/
            release();
            /**如果条件成立，则证明不是主动调用的停止方法来终止的，进行异常通知*/
            if(isRun()){
                System.out.println("*******线程异常停止*********");
                mCommunicator.notifyCommunicatorException();
            }
        }

        /**
         * 监听从主机返回的数据
         * @return 返回值
         * @throws Exception
         */
        private String listen() throws Exception{
            /**接收异常值次数+1*/
            mExceCount++;
            int oneCharAsc;
            mRecvData = new StringBuilder();
            while((oneCharAsc = mReader.read()) != -1) {
                char temp = (char) oneCharAsc;
                mRecvData.append(temp);
                if(!mReader.ready() || oneCharAsc == 10) {
                    /**能运行到这里表示接收到了有效值，异常归零*/
                    mExceCount = 0;
                    break;
                }
            }
            /**连续接收异常数据次数已经超过了上限，认为通信已经发生严重异常*/
            if(mExceCount > ALLOW_ERROR_COUNT){
                throw new Exception();
            }
            return mRecvData.toString().trim();
        }

        /**
         * 释放资源
         */
        private void release(){
            try{
                /**关闭流*/
                if(mReader != null){
                    mReader.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
