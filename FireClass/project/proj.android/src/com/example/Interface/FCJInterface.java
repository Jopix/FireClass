package com.example.Interface;

public class FCJInterface {
	
	public native static int checkUserNameAndPassWord(String username, String password);
//	public native static String getData(String type);
	
	static {
		try{
		   System.loadLibrary("fireclass");
		}catch(UnsatisfiedLinkError ule){
			System.err.println("WARNING: Could not load library fireclass!");
	   }
    }
}
 