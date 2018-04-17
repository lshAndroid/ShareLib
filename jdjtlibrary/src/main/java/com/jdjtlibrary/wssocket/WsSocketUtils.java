package com.jdjtlibrary.wssocket;

import com.jdjtlibrary.wssocket.SckResultDispatcher.OnResultDispatchListener;

public class WsSocketUtils {
	private static final String HOST_IP = "192.168.1.105";
	private static final int HOST_PORT =1234;
//	private static final String HOST_SN = "THN0701011020170922A0001";
	private static final String HOST_SN = "THN0701011020170922A0001";
	public static void sendMsg(String hostIP,String hostSN,String msgSocket){
		SckCommunicator communicator = null;
		try{
			System.out.println("连接设备成功0");
			communicator = new SckCommunicator();
			//这里注册常驻内存的返回值接收者
			System.out.println("连接设备成功1");
			OnResultDispatchListener listener = new OnResultDispatchListener() {
				@Override
				public boolean onResultDispatch(SckCommunicator communicator, String result) {
					System.out.println("###常驻内存的回调接收到的返回值->" + result);
					return false;
					//当注册后，这种操作可以一直接收任何返回值
				}
			};
			System.out.println("连接设备成功2");
			//注册一个常驻内存的返回值接收者，不使用一定要remove!!!!!
			communicator.getResultDispatcher().addOnResultDispatchListener(listener);
			String cmd = "SHAKEHANDS:REQUEST:MACHINEID=" + hostSN;
			communicator.connect(hostIP, HOST_PORT, 12000);
			System.out.println("连接设备成功3");
			boolean isSuccess = communicator.send(cmd, "SHAKEHANDS:ACK.+", 5000) != null;
			if(isSuccess){
				System.out.println("连接设备成功");
			}
			if(!isSuccess){
				System.out.println("握手失败");
				return;
			}
			//某些需要需要一直接收返回值，可以用上面的方式

			//更多的操作只需要下面的几种方式即可
			//这种方法会忽略处理主机返回值
			communicator.send("CMD:DATA:TARGET=T28_SRL_01&OPERATION=51");
			//这里在回调中过滤出需要的返回值
			String ret = communicator.send("CMD:DATA:TARGET=T28_SRL_01&OPERATION=51", new OnResultDispatchListener() {

				@Override
				public boolean onResultDispatch(SckCommunicator communicator, String result) {
					//这里可以过滤出需要的返回值(权限一条)，当本函数返回true的时候，send函数的返回值就为当前参数中的result
					return result.contains("T28_SRL_01");
				}
			}, 5000);
			System.out.println("回调中过滤的返回值->" + ret);
			//这里通过正则过滤返回值
			ret = communicator.send("CMD:DATA:TARGET=T28_SRL_01&OPERATION=52", ".+?T28_SRL_01.+", 5000);
			System.out.println("通过正则过滤的返回值->" + ret);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(communicator != null)communicator.release();
		}
	}

	public static void TestSocket() {
		SckCommunicator communicator = null;
		try{
			communicator = new SckCommunicator();
			//这里注册常驻内存的返回值接收者
			OnResultDispatchListener listener = new OnResultDispatchListener() {
				@Override
				public boolean onResultDispatch(SckCommunicator communicator, String result) {
					System.out.println("###常驻内存的回调接收到的返回值->" + result);
					return false;
					//当注册后，这种操作可以一直接收任何返回值
				}
			};
			//注册一个常驻内存的返回值接收者，不使用一定要remove!!!!!
			communicator.getResultDispatcher().addOnResultDispatchListener(listener);
			String cmd = "SHAKEHANDS:REQUEST:MACHINEID=" + HOST_SN;
			communicator.connect(HOST_IP, HOST_PORT, 12000);
			boolean isSuccess = communicator.send(cmd, "SHAKEHANDS:ACK.+", 5000) != null;
			if(isSuccess){
				System.out.println("连接设备成功");
			}
			if(!isSuccess){
				System.out.println("握手失败");
				return;
			}

			//某些需要需要一直接收返回值，可以用上面的方式

			//更多的操作只需要下面的几种方式即可
			//这种方法会忽略处理主机返回值
			communicator.send("CMD:DATA:TARGET=T32_SRL_01&OPERATION=51");
			//这里在回调中过滤出需要的返回值
			String ret = communicator.send("CMD:DATA:TARGET=T32_SRL_01&OPERATION=51", new OnResultDispatchListener() {
				@Override
				public boolean onResultDispatch(SckCommunicator communicator, String result) {
					//这里可以过滤出需要的返回值(权限一条)，当本函数返回true的时候，send函数的返回值就为当前参数中的result
					return result.contains("T32_SRL_01");
				}
			}, 5000);
			System.out.println("回调中过滤的返回值->" + ret);
			//这里通过正则过滤返回值
			ret = communicator.send("CMD:DATA:TARGET=T32_SRL_01&OPERATION=52", ".+?T32_SRL_01.+", 5000);
			System.out.println("通过正则过滤的返回值->" + ret);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(communicator != null)communicator.release();
		}
	}
}
