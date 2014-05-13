package com.example.lib;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class FCJSDCardSQLite  {
	private static final String TAG = "sdcard-sqlite";
	private static String DATABASE_NAME = "gamedata.db";
	private static final int DATABASE_VERSION = 1;
	 
	 
	private static SDCardDBHelper mDatabaseOpenHelper = null;
	private static SQLiteDatabase mDatabase = null;
	 
	private static Map<String, Boolean> mDBTableExistMap;
	
	public static void init(Context context) {
    	if (context != null) {
    		Log.e(TAG, "初始化数据库");
    		mDatabaseOpenHelper = new SDCardDBHelper(context);
    		mDatabase = mDatabaseOpenHelper.getWritableDatabase();

    		try {
    			
        		mDatabase.execSQL("create table test("  
        		        + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT,"+"hp INTEGER DEFAULT 100,"+ "mp INTEGER DEFAULT 100,"  
        		        + "number INTEGER);" );
			} catch (Exception e) {
				// TODO: handle exception
			}
    		
    		
    		
    	}
	}
	
	public static void newOne(){
		File dbFile = null;
		boolean sdExist = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		if (!sdExist) {
			Log.e(TAG, "SDCard does not exist !");
		} else {
			/* 生成数据库路径 */
			String dbDir = Environment.getExternalStorageDirectory().toString();//获取跟目录 
			String dbPath = dbDir + "/" + DATABASE_NAME;
			File dirFile = new File(dbDir);
			Log.e(TAG, dbPath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
				Log.e(TAG, "创建目录");
			}else {
				Log.e(TAG, "存在目录");
			}
			dbFile = new File(dbPath);
			if (!dbFile.exists()) {
				try {
					/* 不存在则创建数据库文件 */
					Log.e(TAG, "创建数据库");
					dbFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					dbFile = null;
				}
			}else {
				Log.e(TAG, "存在");
			}
		}
	}
	
	public static void destory() {
	    if (mDatabase != null) {
	    	mDatabase.close();
	    }
	}
	
	public static void setItem(String tableName, String key, String value) {
	    	try {
	    		checkTable(tableName);
	    		String sql = "replace into " + tableName + "(key,value)values(?,?)";
	    		mDatabase.execSQL(sql, new Object[] { key, value });
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    public static String getItem(String tableName, String key) {
	    Log.e(TAG, "这里ok");
	    	String ret = null;
	    	try {
	    		checkTable(tableName);
	    		String sql = "select value from " + tableName + " where key=?";
	    		Cursor c = mDatabase.rawQuery(sql, new String[]{key});  
	    		while (c.moveToNext()) {
	    			// only return the first value
	    			if (ret != null) {
	    				Log.e(TAG, "The key contains more than one value.");
	    				break;
	    			}
	    			ret = c.getString(c.getColumnIndex("value")); 
	    		}
	    		c.close();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return ret == null ? "" : ret;
	    }
	    
	    public static void removeItem(String tableName, String key) {
	    	try {
	    		checkTable(tableName);
	    		String sql = "delete from " + tableName + " where key=?";
	    		mDatabase.execSQL(sql, new Object[] {key});
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    public static void exec(String sql) {
	    	try {
	    		mDatabase.execSQL(sql);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}	
	    }
	    
	    private static void checkTable(String tableName) {
	    	Boolean exist = mDBTableExistMap.get(tableName);
	    	if (Boolean.TRUE != exist) {
	    		mDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ tableName + "(key TEXT PRIMARY KEY,value TEXT);");
	    		mDBTableExistMap.put(tableName, Boolean.TRUE);
	    	}
	    }

	    /**
	     * This creates/opens the database.
	     */
	    private static class SDCardDBHelper extends SQLiteOpenHelper {

	    	SDCardDBHelper(Context context) {
	            super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        }

	        @Override
	        public void onCreate(SQLiteDatabase db) {

	        }
	        
	        @Override
	        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
	        }
	    }
	    
}
