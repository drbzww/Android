package com.cytmxk.testsqlite.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cytmxk.testsqlite.db.SchoolOpenHelper;
import com.cytmxk.testsqlite.entity.Student;

public class StudentDao2 {

	private static final String TAG = "StudentDao2";
	private SchoolOpenHelper mOpenHelper;

	public StudentDao2(Context context) {
		mOpenHelper = new SchoolOpenHelper(context);
	}

	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			ContentValues values = new ContentValues();
			values.put(SchoolOpenHelper.STUDENT_TABLE_COL_NAME,
					student.getName());
			values.put(SchoolOpenHelper.STUDENT_TABLE_COL_AGE, student.getAge());
			values.put(SchoolOpenHelper.STUDENT_TABLE_COL_BALANCE,
					student.getBalance());
			long id = db.insert(SchoolOpenHelper.STUDENT_TABLE_NAME, null,
					values);
			android.util.Log.i(TAG, "id : " + id);
			db.close();
		}

	}

	public void deleteStudent(int id) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			int count = db.delete(SchoolOpenHelper.STUDENT_TABLE_NAME,
					SchoolOpenHelper.STUDENT_TABLE_COL_ID + "=?",
					new String[] { id + "" });
			android.util.Log.i(TAG, "一共删除了：" + count + "行");
			db.close();
		}
	}

	public void updateStudent(Student student) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			ContentValues values = new ContentValues();
			values.put(SchoolOpenHelper.STUDENT_TABLE_COL_NAME,
					student.getName());
			values.put(SchoolOpenHelper.STUDENT_TABLE_COL_AGE, student.getAge());
			values.put(SchoolOpenHelper.STUDENT_TABLE_COL_BALANCE,
					student.getBalance());
			int count = db.update(SchoolOpenHelper.STUDENT_TABLE_NAME, values,
					SchoolOpenHelper.STUDENT_TABLE_COL_ID + "=?",
					new String[] { student.getId() + "" });
			android.util.Log.i(TAG, "一共修改了：" + count + "行");
			db.close();
		}
	}

	public List<Student> quaryAllStudent() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db.query(SchoolOpenHelper.STUDENT_TABLE_NAME,
					new String[] { SchoolOpenHelper.STUDENT_TABLE_COL_ID,
							SchoolOpenHelper.STUDENT_TABLE_COL_NAME,
							SchoolOpenHelper.STUDENT_TABLE_COL_AGE,
							SchoolOpenHelper.STUDENT_TABLE_COL_BALANCE }, null,
					null, null, null, null);
			if (cursor != null && cursor.getCount() > 0) {
				List<Student> list = new ArrayList<Student>();
				while (cursor.moveToNext()) {
					Student student = new Student();
					student.setId(cursor.getInt(0));
					student.setName(cursor.getString(1));
					student.setAge(cursor.getInt(2));
					student.setBalance(cursor.getDouble(3));
					list.add(student);
				}
				cursor.close();
				db.close();
				return list;
			}
			db.close();
		}
		return null;
	}

	public Student quaryStudentById(int id) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db.query(SchoolOpenHelper.STUDENT_TABLE_NAME,
					new String[] { SchoolOpenHelper.STUDENT_TABLE_COL_NAME,
							SchoolOpenHelper.STUDENT_TABLE_COL_AGE,
							SchoolOpenHelper.STUDENT_TABLE_COL_BALANCE},
					SchoolOpenHelper.STUDENT_TABLE_COL_ID + "=?",
					new String[] { id + "" }, null, null, null);
			if (cursor != null && cursor.moveToFirst()) {
				Student student = new Student();
				student.setId(id);
				student.setName(cursor.getString(0));
				student.setAge(cursor.getInt(1));
				student.setBalance(cursor.getDouble(2));
				cursor.close();
				db.close();
				return student;
			}
			db.close();
		}
		return null;
	}
}
