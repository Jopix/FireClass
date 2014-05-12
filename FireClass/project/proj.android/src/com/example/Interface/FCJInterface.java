package com.example.Interface;

public class FCJInterface {
	
	public native static int checkUserNameAndPassWord(String username, String password);
	public native static String getData(String type);
	
	static {
        System.loadLibrary("FClib");
    }
}
 