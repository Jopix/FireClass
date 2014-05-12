package com.example.fileclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import android.content.res.Resources.Theme;

import com.example.notification.FCJNotificationCenter;


//import org.json.JSONObject;



public class FCJClientSocketManager {
    private String HOST = "192.168.173.12"; 
    private static final int PORT = 9999;   
    private Queue<String> queue = new LinkedList<String>();
    private Thread thread = null;
    private String line;
    
    
    public static String FC_DID_RECEIVERT_MES = "FC_DID_RECEIVERT_MES";
    public static String FC_DID_SEND_MES = "FC_DID_SEND_MES";
    
    
	private static FCJClientSocketManager instance = null;
	
	private FCJClientSocketManager(){}    //˽���޲ι��췽��
   
	public static FCJClientSocketManager shareInstance(){
        if(instance==null){
        	instance=new FCJClientSocketManager();
        }
        return instance;
    }
	
	public  void init(String host) {
		HOST = host;
		thread =  new Thread() { 
			 @Override 
			 public void run() {
				 threadRun();
			 }
		};
		thread.start();
	}
	
	public void threadStart()
	{
		if (!thread.isAlive()) {
			thread.start();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void threadStop() {
		if (thread.isAlive()) {
			thread.stop();
		}
	}
	
	public int getPost() {
		return PORT;
	}
	
	
//	public String getLocalIpAddress() {
//		try {
//			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
//				NetworkInterface intf = en.nextElement();
//				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
//					InetAddress inetAddress = enumIpAddr.nextElement();
//					if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
//						return inetAddress.getHostAddress().toString();
//					}
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return "";
//		} 
//	
	

	
	
	private void threadRun()
	{
		// ִ����Ϻ��handler����һ������Ϣ  
		try 
		{
			// ���������  
			 while(true)
			 {
				 while(!queue.isEmpty())
				 {
					 String str = queue.poll();
          		
					 // ʵ����Socket 
					 
					 Socket socket = new Socket(HOST, PORT); 
					 if(socket.isConnected())
					 {
						 PrintWriter out = new PrintWriter(new BufferedWriter(
              					new OutputStreamWriter(  
              					socket.getOutputStream(), "GBK")), true);
						 out.println(str);
						 
						 Map<String, Object> dist = new HashMap<String, Object>();
						 dist.put("msg", str);
						 FCJNotificationCenter.sharedNotificationCenter().postNotification(FC_DID_SEND_MES, dist);
						
						 
						 BufferedReader br = new BufferedReader( 
              					new InputStreamReader(socket.getInputStream(),"GBK"));
						 
						 line = br.readLine();
						 Map<String, Object> dist2 = new HashMap<String, Object>();
						 dist2.put("msg", line);
						 FCJNotificationCenter.sharedNotificationCenter().postNotification(FC_DID_RECEIVERT_MES, dist2);
						
//						 System.out.println("���յ�����Ϣ   "+line);
						 br.close();
						 out.close();
						 Thread.sleep(2000);
						 System.out.println("�����С���"); 
					}
					else{
						System.out.println("�Ͽ��ˡ�");
					}
				}
				Thread.sleep(5000);
			}
		} catch (UnknownHostException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		handler.sendEmptyMessage(0); 
	}
	
	public void addSendData(String msg)
	{
//		JSONObject params = new JSONObject(); 
		System.out.println("sendData��������"+msg);
		queue.offer(msg);
	}
	
	
	
//	public void setHost(String host){
//		if (HOST.equals(host)) {
//			return ;
//		}
//		HOST = host;
//		System.out.println("�˿����ú�:"+HOST);
//	}
//	
	public String getHost() {
		return HOST;
	}
	

}
