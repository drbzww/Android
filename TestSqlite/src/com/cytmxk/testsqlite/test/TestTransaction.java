package com.cytmxk.testsqlite.test;

import com.cytmxk.testsqlite.db.SchoolOpenHelper;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class TestTransaction extends AndroidTestCase {

	private static final String TAG = "TestTransaction";

	public void testTransactionTransfer(){
		SchoolOpenHelper mOpenHelper = new SchoolOpenHelper(getContext());
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			try {
				db.beginTransaction();
				db.execSQL("UPDATE student SET balance = balance + 1000 WHERE name = '赵敏';");
				//int result = 100/0;
				db.execSQL("UPDATE student SET balance = balance - 1000 WHERE name = '小笼包';");
				db.setTransactionSuccessful();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				db.endTransaction();
			}
			db.close();
		}
	}
	
	public void testTransactionInsert(){
		SchoolOpenHelper mOpenHelper = new SchoolOpenHelper(getContext());
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			long start = System.currentTimeMillis();
			try {
				db.beginTransaction();
				for(int i = 0;i < 10000;i ++){
					db.execSQL("INSERT INTO student(name,age,balance) VALUES('chenyang" + (i + 1) + "',18,1000)");
				}
				db.setTransactionSuccessful();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				db.endTransaction();
			}
			long end = System.currentTimeMillis();
			android.util.Log.i(TAG,"耗时：" + (end - start) + "毫秒");
			db.close();
		}
	}
}
