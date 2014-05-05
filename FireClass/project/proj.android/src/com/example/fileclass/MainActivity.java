package com.example.fileclass;

import java.util.Map;

import com.example.notification.FCJNotificationCenter;
import com.example.notification.FCJNotificationListener;
import com.example.tools.FCJNetworkManager;
import com.example.tools.FCJWifiHotPot;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint; 
import android.content.Context;
import android.widget.Button; 
import android.widget.EditText;
import android.widget.TextView; 


@SuppressLint("HandlerLeak") public class MainActivity extends Activity {

    private Button btn_send;
    private Button btn_Server; 
    
    private TextView txt;
    private EditText edit;
    private  String line;
	private Context mContext = null;
	private boolean isInited = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        mContext = this;
//        FCJClientSocket_Java.addDevice("你好啊");
//        System.loadLibrary("libfireclass.so");
    }
    
    protected void onStart(){
    	super.onStart();
    	
    	FCJNotificationCenter.sharedNotificationCenter().addListener(new FCJNotificationListener(){
    		
    		public void run(Map<String, Object> dist){
    			
    			String msg = (String)dist.get("msg");
    			line = "向服务器发送数据:" + msg;	
    			handler.sendEmptyMessage(0);
    		}
    		
    	}, FCJClientSocketManager.FC_DID_SEND_MES);
    	
    	
    	FCJNotificationCenter.sharedNotificationCenter().addListener(new FCJNotificationListener(){
    		
    		public void run(Map<String, Object> dist){   			
    			String msg = (String)dist.get("msg");
    			line = "接收服务器信息:" + msg;	
    			handler.sendEmptyMessage(0);
    		}
    		
    	}, FCJClientSocketManager.FC_DID_RECEIVERT_MES);
    	
    	initControl();
    }
   
    
    private void initControl() { 
    	btn_send = (Button)findViewById(R.id.Button02);
    	btn_Server = (Button)findViewById(R.id.button1);
        txt = (TextView)findViewById(R.id.TextView); 
        edit = (EditText)findViewById(R.id.EditText01);
        
        if (!isInited) {
            FCJNetworkManager mc = FCJNetworkManager.getInstance();
            mc.setContext(mContext);
       	 
            System.out.println( mc.getIPAddress());
            System.out.println( mc.getGateWay());
            System.out.println("wifi: "+  mc.getWifiInfo());
            
            String host = mc.getGateWay();
            FCJClientSocketManager.shareInstance().init(host);
            isInited = true;
		}

        btn_send.setOnClickListener(new Button.OnClickListener() {  
             @Override  
             public void onClick(View v) {  
                 String msg = edit.getText().toString();  
                 FCJClientSocketManager.shareInstance().addSendData(msg);
            	 }
             	
         });
        
        btn_Server.setOnClickListener(new Button.OnClickListener() {  
            @Override  
            public void onClick(View v) {
    			FCJWifiHotPot wifiAp = new FCJWifiHotPot(mContext);
				wifiAp.startWifiAp("\"HotSpot\"", "hhhhhh123");
				FCJServerSocketManager.shareInstance();
            }  
        });
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

	 // ∂®“ÂHandler∂‘œÛ  
	 @SuppressLint("HandlerLeak") private Handler handler = new Handler() { 
		 @Override
		 // µ±”–œ˚œ¢∑¢ÀÕ≥ˆ¿¥µƒ ±∫ÚæÕ÷¥––Handlerµƒ’‚∏ˆ∑Ω∑®  
		 public void handleMessage(Message msg) { 
			 super.handleMessage(msg); 
			 txt.setText(line);
		}
	}; 
}
