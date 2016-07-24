package com.cytmxk.testsqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SchoolOpenHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "school.db";
	private static final int DB_VERSION = 2;
	public static final String STUDENT_TABLE_NAME = "student";
	public static final String STUDENT_TABLE_COL_ID = "_id";
	public static final String STUDENT_TABLE_COL_NAME = "name";
	public static final String STUDENT_TABLE_COL_AGE = "age";
	public static final String STUDENT_TABLE_COL_BALANCE = "balance";
	private static final String STUDENT_TABLE_CREATE_1 = "CREATE TABLE "
			+ STUDENT_TABLE_NAME + "(" + STUDENT_TABLE_COL_ID
			+ " INTEGER PRIMARY KEY," + STUDENT_TABLE_COL_NAME
			+ " VARCHAR(20)," + STUDENT_TABLE_COL_AGE + " INTEGER);";
	private static final String STUDENT_TABLE_CREATE_2 = "CREATE TABLE "
			+ STUDENT_TABLE_NAME + "(" + STUDENT_TABLE_COL_ID
			+ " INTEGER PRIMARY KEY," + STUDENT_TABLE_COL_NAME
			+ " VARCHAR(20)," + STUDENT_TABLE_COL_AGE + " INTEGER,"
			+ STUDENT_TABLE_COL_BALANCE + " DOUBLE DEFAULT (0));";
	private static final String TAG = "SchoolOpenHelper";

	public SchoolOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		android.util.Log.i(TAG,"onCreate DB_VERSION = " + DB_VERSION);
		if (1 == DB_VERSION) {
			db.execSQL(STUDENT_TABLE_CREATE_1);

		} else if (DB_VERSION == 2) {
			db.execSQL(STUDENT_TABLE_CREATE_2);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		android.util.Log.i(TAG,"onUpgrade DB_VERSION = " + DB_VERSION);
		if (oldVersion == DB_VERSION - 1 && newVersion == DB_VERSION) {
			db.execSQL("ALTER TABLE " + STUDENT_TABLE_NAME
					+ " ADD balance DOUBLE DEFAULT (0);");
		}
	}

}
