package com.cytmxk.testsqlite.test;

import java.util.List;

import com.cytmxk.testsqlite.dao.StudentDao;
import com.cytmxk.testsqlite.db.SchoolOpenHelper;
import com.cytmxk.testsqlite.entity.Student;

import android.test.AndroidTestCase;

public class TestStudentDao extends AndroidTestCase {

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
		StudentDao dao = new StudentDao(getContext());
		dao.addStudent(new Student(0,"陈阳",18,1000));
		dao.addStudent(new Student(0,"周芷若",18,1000));
		dao.addStudent(new Student(0,"赵敏",18,1000));
		dao.addStudent(new Student(0,"小昭",18,1000));
		dao.addStudent(new Student(0,"蛛儿",18,1000));
	}
	
	public void testDeleteStudent(){
		StudentDao dao = new StudentDao(getContext());
		dao.deleteStudent(5);
	}
	
	public void testUpdateStudent(){
		StudentDao dao = new StudentDao(getContext());
		Student student = new Student(5, "小笼包",500000,1000);
		dao.updateStudent(student);
	}
	
	public void testQuaryAllStudent(){
		StudentDao dao = new StudentDao(getContext());
		List<Student> list = dao.quaryAllStudent();
		for(Student student : list){
			android.util.Log.i(TAG,student.toString());
		}
	}
	
	public void testQuaryStudentById(){
		StudentDao dao = new StudentDao(getContext());
		Student student = dao.quaryStudentById(5);
		android.util.Log.i(TAG,student.toString());
	}
	
}
