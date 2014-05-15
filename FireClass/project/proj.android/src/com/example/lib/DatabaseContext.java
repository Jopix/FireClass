/**
 * @author 	Irvin Pang
 * @email	lpohvbe@gmail.com
 */
package com.example.lib;

import java.io.File;
import java.io.IOException;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Environment;
import android.util.Log;

/**
 * 用于支持对存储在SD卡上的数据库的访问
 **/
public class DatabaseContext extends ContextWrapper {
	private static final String TAG = "sdcard-dbcontext";

	public DatabaseContext(Context base) {
		super(base);
	}

	/**
	 * 获得数据库路径
	 * 
	 */
	@Override
	public File getDatabasePath(String name) {
		File dbFile = null;
		
		boolean sdExist = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		if (!sdExist) {
			Log.e(TAG, "SDCard does not exist !");
		} else {
			/* 生成数据库路径 */
			String dbDir = Environment.getExternalStorageDirectory().toString();//获取跟目录 
			String dbPath = dbDir + "/" + name;
			File dirFile = new File(dbDir);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			dbFile = new File(dbPath);
			if (!dbFile.exists()) {
				try {
					/* 不存在则创建数据库文件 */
					dbFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					dbFile = null;
				}
			}
		}
		return dbFile;
	}

	/**
	 * For Android 2.3及以下版本
	 * 
	 */
	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
		SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
		return result;
	}

	/**
	 * For Android 4.0及以上版本
	 * 
	 */
	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory, DatabaseErrorHandler errorHandler) {
		SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
		return result;
	}
}