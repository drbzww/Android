package com.cytmxk.testsqlite.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cytmxk.testsqlite.db.SchoolOpenHelper;
import com.cytmxk.testsqlite.entity.Student;

public class StudentDao {

	private SchoolOpenHelper mOpenHelper;

	public StudentDao(Context context) {
		mOpenHelper = new SchoolOpenHelper(context);
	}

	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.execSQL("INSERT INTO student(name,age,balance) VALUES(?,?,?);",
					new Object[] { student.getName(), student.getAge() });

			db.close();
		}

	}

	public void deleteStudent(int id) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.execSQL("DELETE FROM student WHERE _id = ?;",
					new Integer[] { id });
			db.close();
		}
	}

	public void updateStudent(Student student) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if (db.isOpen()) {
			db.execSQL(
					"UPDATE student SET name = ?,age = ?,balance = ? where _id = ?;",
					new Object[] { student.getName(), student.getAge(),student.getBalance(),
							student.getId() });
			db.close();
		}
	}

	public List<Student> quaryAllStudent() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		if (db.isOpen()) {
			Cursor cursor = db.rawQuery("SELECT _id,name,age,balance FROM student;",
					null);
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
			Cursor cursor = db.rawQuery("SELECT name,age,balance FROM student WHERE _id IN(?);",
					new String[] { id + ""});
			if(cursor != null && cursor.moveToFirst()){
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
