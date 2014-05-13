package com.example.fileclass;

import android.app.Application;

public class MyApp extends Application{

	private static MyApp context;
	
	
	public static MyApp getContext(){
		return context;
	}


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context=this;
	}
}