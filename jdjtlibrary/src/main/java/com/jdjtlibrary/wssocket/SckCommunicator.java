package com.jdjtlibrary.wssocket;

import com.jdjtlibrary.wssocket.SckResultDispatcher.OnResultDispatchListener;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 通信器
 * @author chenfei
 */
public class SckCommunicator{
    private static final String TAG = "SckCommunicator";
    /**发送数据时的编码*/
    private static final String TRANS_DATA_ENCODING = "UTF-8";
    /**与主机建立连接的套节字*/
    private Socket mSocket;
    /**输出*/
    private PrintWriter mPringWriter;
    /**返回值分发器*/
    private final SckResultDispatcher mResultDispatcher;
    /**返回值接收器*/
    private final SckResultReceiver mResultReceiver;
    /**通信器发生异常时的监听器*/
    private OnCommunicatorExcetionListener mOnCommunicatorExceptionListener;

    /**
     * 创建通信器
     */
    public SckCommunicator(){
        this.mResultDispatcher = new SckResultDispatcher(this);
        this.mResultReceiver = new SckResultReceiver(this);
    }

    /**
     * 通信是否正常
     * @return true正常；false不下常
     */
    public synchronized boolean isCommNormal(){
        return mPringWriter != null;
    }

    /**
     * 连接主机，调用时请在子线程中使用，
     * 连接时会首先调用{@link #release()}
     * @param host 主机地址
     * @param port 主机端口
     * @param timeout 连接超时时间
     * @throws Exception 抛出异常表示当前连接失败，并且之前建立的连接也会失效
     */
    public synchronized void connect(String host, int port, int timeout) throws Exception{
        release();
        Socket socket = null;
        try{
            SocketAddress sckAddress = new InetSocketAddress(host, port);
            socket = new Socket();
            socket.connect(sckAddress, timeout);
        }catch (Exception e){
            socket = null;
            throw e;
        }finally {
            if(socket != null){
                mSocket = socket;
                mPringWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream())),true);
                mResultReceiver.start(mSocket);
                System.out.println(TAG + " connect success");
            }else{
                release();
                System.out.println(TAG + " connect failed");
            }
        }
    }

    /**
     * 获取返回值分发器，用于注册常驻的监听器
     * @return 返回值监听器
     */
    public SckResultDispatcher getResultDispatcher() {
        return mResultDispatcher;
    }

    /**
     * 发送命令并且会等待返回值，获取到返回值会立即return，未获取到返回值之前，在超时范围内会一直阻塞<p>
     * 不要在UI线程中使用此函数，否则可能会ANR
     * @param data 需要向主机发送的数据
     * @param retRegex 提取返回值的正则，
     * 需要正则的原因是，收发数据是异步的，无法直接确定哪一条返回值是当前命令发送后主机做出的响应
     * @param timeout 超时时间
     * @return 返回值
     */
    public final String send(String data, final String retRegex, int timeout){
        return send(data, new OnResultDispatchListener() {
            @Override
            public boolean onResultDispatch(SckCommunicator communicator, String result) {
                return RegexKit.check(result, retRegex);
            }
        }, timeout);
    }

    /**
     * 发送命令并且会等待返回值，获取到返回值会立即return，未获取到返回值之前，在超时范围内会一直阻塞<p>
     * 不要在UI线程中使用此函数，否则可能会ANR
     * @param data 需要向主机发送的数据
     * @param listener 检验返回值是否是需要的
     * 需要正则的原因是，收发数据是异步的，无法直接确定哪一条返回值是当前命令发送后主机做出的响应
     * @param timeout 超时时间(大于等于0)
     * @return 返回值
     */
    public final String send(String data, final OnResultDispatchListener listener, int timeout){
        OnResultDispatchListener resultdispatchListener = null;
        final String[] arrRet = new String[1];
        try{
            final TimeoutKit timeoutKit = new TimeoutKit();
            /**只有先调了预备工作，再调用start方法，超时器才会正常进行超时*/
            timeoutKit.prepare();
            resultdispatchListener = new OnResultDispatchListener() {

                @Override
                public boolean onResultDispatch(SckCommunicator communicator, String result) {
                    boolean isReceived = listener.onResultDispatch(communicator, result);
                    if(isReceived){
                        /**将返回值赋值给外部变量，只有过滤后的返回值才会执行这个方法*/
                        arrRet[0] = result;
                        /**中断超时器，如果超时器此时是未运行的，则中断过后，下次运行
                         * 超时器时，超时器不会进行超时操作*/
                        timeoutKit.interrupt();
                    }
                    return isReceived;
                }
            };
            if(timeout > 0){
                getResultDispatcher().addOnResultDispatchListener(resultdispatchListener);
            }
            /**如果发送异常则直接中断超时*/
            if(!send(data)){
                timeoutKit.interrupt();
            }
            /**开始超时，如果调用之前已经中断了，这里不进行超时操作*/
            timeoutKit.start(timeout);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            /**一定要移除，释放掉资源*/
            getResultDispatcher().removeOnResultDispatchListener(resultdispatchListener);
        }
        return arrRet[0];
    }

    /**
     * 向主机发送数据
     * 这里得保证参数中传入的字符串本身不存在乱码。
     * @param cmd 需要向主机发送的数据
     * @return true发送正常，false发送异常(发送的数据为null或连接未创建等原因)
     */
    public boolean send(String cmd) {
        try{
            if(cmd == null || !isCommNormal())return false;
            /**用新的字符编码生成字符串*/
            cmd = new String(cmd.getBytes(), TRANS_DATA_ENCODING);
            mPringWriter.print(String.format("%s\0", cmd));
            mPringWriter.flush();
            System.out.println(TAG + "send->" + cmd);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            notifyCommunicatorException();
        }
        return false;
    }

    /**
     * 释放:<p>
     * printWriter<p>
     * socket<p>
     * 返回值接收器
     */
    public synchronized void release(){
        mResultReceiver.stop();
        try{
            if(mPringWriter != null){
                mPringWriter.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(mSocket != null){
                mSocket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mPringWriter = null;
        mSocket = null;
    }

    /**
     * 通知通信器发生异常
     */
    void notifyCommunicatorException(){
        release();
        if(mOnCommunicatorExceptionListener != null){
            mOnCommunicatorExceptionListener.onCommunicatorException(this);
        }
    }

    /**
     * 发生异常时的监听器
     */
    public interface OnCommunicatorExcetionListener{
        /**
         * 当发生异常时
         * @param communicator 发生异常的通信器
         */
        void onCommunicatorException(SckCommunicator communicator);
    }

    /**
     * 设置当通信器发生异常时的监听器
     * @param listener 当连接器发生异常时的监听器
     */
    public void setOnCommunicatorExceptionListener(OnCommunicatorExcetionListener listener){
        this.mOnCommunicatorExceptionListener = listener;
    }
}

