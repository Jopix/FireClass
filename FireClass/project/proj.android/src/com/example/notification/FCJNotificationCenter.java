package com.example.notification;

import java.util.HashMap;
import java.util.Map;

public class FCJNotificationCenter {
	
	
	private static FCJNotificationCenter s_sharedNotifCenter = null;

	private FCJNotificationCenter(){}
	
	private Map<String, FCJNotificationListener> map;
	
	public static  FCJNotificationCenter sharedNotificationCenter(){
		if (s_sharedNotifCenter == null)
		{
			s_sharedNotifCenter = new FCJNotificationCenter();
			s_sharedNotifCenter.init();
		}
		return s_sharedNotifCenter;
	}
	
	private void init() {
		map = new HashMap<String, FCJNotificationListener>();
	}
	
	public void addListener(FCJNotificationListener  listener , String name){
		if(map.containsKey(name))
		{
			map.remove(name);
		}
		
		map.put(name, listener);
	}
	
	
	public void removeListener(String name)
  	{
		if(map.containsKey(name))
		{
			map.remove(name);
		}
  	}

  	public void removeAllListener()
  	{
  		map.clear();
  	}

  	public void postNotification(String name, Map<String, Object> dist)
  	{
  		if(map.containsKey(name) == false){
  			return ;
  		}
  		
  		FCJNotificationListener listener = map.get(name);
  		listener.run(dist);
  	}

  	public void postNotification(String name)
  	{
  		postNotification(name, null);
  	}
}
