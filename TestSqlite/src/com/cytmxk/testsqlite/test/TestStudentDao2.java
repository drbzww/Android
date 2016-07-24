package com.cytmxk.testsqlite.test;

import java.util.List;

import com.cytmxk.testsqlite.dao.StudentDao2;
import com.cytmxk.testsqlite.db.SchoolOpenHelper;
import com.cytmxk.testsqlite.entity.Student;

import android.test.AndroidTestCase;

public class TestStudentDao2 extends AndroidTestCase {

	private static final String TAG = "com.cytmxk.testsqlite.test.TestStudentDao";

	/**
	 * 测试数据库什么时候被创建
	 */
	public void testDBCreate() {

		//下面一句执行后，数据库不会创建
		SchoolOpenHelper mOpenHelper = new SchoolOpenHelper(getContext());
		//第一次连接数据库时（下面一句执行后）数据库表会被创建，onCreate方法会被调用
		mOpenHelper.getReadableDatabase();
	}
	
	public void testAddStudent(){
		StudentDao2 dao = new StudentDao2(getContext());
		dao.addStudent(new Student(0,"陈阳",18,1000));
		dao.addStudent(new Student(0,"周芷若",18,1000));
		dao.addStudent(new Student(0,"赵敏",18,1000));
		dao.addStudent(new Student(0,"小昭",18,1000));
		dao.addStudent(new Student(0,"蛛儿",18,1000));
	}
	
	public void testDeleteStudent(){
		StudentDao2 dao = new StudentDao2(getContext());
		dao.deleteStudent(19);
	}
	
	public void testUpdateStudent(){
		StudentDao2 dao = new StudentDao2(getContext());
		Student student = new Student(2, "小笼包",500000,10000);
		dao.updateStudent(student);
	}
	
	public void testQuaryAllStudent(){
		StudentDao2 dao = new StudentDao2(getContext());
		List<Student> list = dao.quaryAllStudent();
		for(Student student : list){
			android.util.Log.i(TAG,student.toString());
		}
	}
	
	public void testQuaryStudentById(){
		StudentDao2 dao = new StudentDao2(getContext());
		Student student = dao.quaryStudentById(3);
		android.util.Log.i(TAG,student.toString());
	}
	
}
