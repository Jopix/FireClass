package com.example.fileclass;

public class FCJClientSocket_Java {
	
	
	public static void send(String data){
		FCJClientSocketManager.shareInstance().addSendData(data);
	}
	
	public static native void addDevice(String name);
	
	public static void setText(String msg) {
		System.out.println("收到消息+");
	}
}
