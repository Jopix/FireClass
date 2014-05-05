package com.example.fileclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FCJServerSocketManager { 
	private ServerSocket server = null;
	private static final int PORT = 9999;
	private BufferedWriter writer;
	private BufferedReader reader;
    private Thread thead = null;
    
    
//    public String FC_DID_RECEIVERT_MES = "FC_DID_RECEIVERT_MES";
    
	private static FCJServerSocketManager instance = null;
	
	private FCJServerSocketManager(){}    //私有无参构造方法
   
	public static FCJServerSocketManager shareInstance(){
        if(instance==null){
        	instance=new FCJServerSocketManager();
        	instance.init();
        }
        return instance;
    }
	
	private void init() {
		thead =  new Thread() { 
			 @Override 
			 public void run() {
				 threadRun();
			 }
		};
		
		thead.start();
	}
	
	private void threadRun()
	{
		try {
			CreateSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 //创建Socket服务器
		Socket client;
		String txt;
		try 
		{
			while (true)
		    //线程无限循环，实时监听socket端口
		    {
				client=ResponseSocket();
				//响应客户端链接请求。。
				while(true)
				{
					txt=ReceiveMsg(client);
					System.out.println(txt);
					
					//链接获得客户端发来消息，并将其显示在Server端的屏幕上
					SendMsg(client,txt);
					//向客户端返回消息
					if(true)
						break;
					//中断，继续等待链接请求
				}
				CloseSocket(client);
				//关闭此次链接
		    }
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	private void CreateSocket() throws IOException
	{
	   server = new ServerSocket(PORT, 100);
	   System.out.println("Server starting..");  
	}

	private Socket ResponseSocket() throws IOException
	{
	   Socket client = server.accept(); 
	   System.out.println("client connected..");
	  
	   return client;
	}

	private void CloseSocket(Socket socket) throws IOException
	{
	   reader.close();
	   writer.close();
	   socket.close();
	   System.out.println("client closed..");
	}

	private void SendMsg(Socket socket,String Msg) throws IOException 
	{
	   writer = new BufferedWriter(
	      new OutputStreamWriter(socket.getOutputStream(), "GBK"));
	    writer.write(Msg+"\n");
	    writer.flush();
	   
	}

	private String ReceiveMsg(Socket socket) throws IOException
	{
	   reader = new BufferedReader(
	     new InputStreamReader(socket.getInputStream(), "GBK"));     
	    System.out.println("server get input from client socket..");
	    String txt="Sever send:"+reader.readLine();
	   
	    return txt;
	}
}
