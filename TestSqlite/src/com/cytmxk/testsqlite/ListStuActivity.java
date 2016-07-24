package com.cytmxk.testsqlite;

import java.util.List;

import com.cytmxk.testsqlite.dao.StudentDao;
import com.cytmxk.testsqlite.entity.Student;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListStuActivity extends Activity {

	private List<Student> mStudents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_list_stu);
		
		StudentDao dao = new StudentDao(this);
		mStudents = dao.quaryAllStudent();
		//上面两句话必须卸载下面两句话的上面
		ListView lvStu = (ListView) findViewById(R.id.lv_stu);
		lvStu.setAdapter(new MyAdapter());
	}
	
	class MyAdapter extends BaseAdapter{

		private static final String TAG = "MyAdapter";

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(mStudents == null){
				return 0;
			}
			return mStudents.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			if(convertView == null){
				android.util.Log.i(TAG,"创建" + position);
				LayoutInflater inflater = ListStuActivity.this.getLayoutInflater();
				view = inflater.inflate(R.layout.list_stu_item, null);
			}else{
				android.util.Log.i(TAG,"复用" + position);
				view = convertView;
			}
			TextView tvName = (TextView) view.findViewById(R.id.tv_stu_name);
			TextView tvAge = (TextView) view.findViewById(R.id.tv_stu_age);
			Student student = mStudents.get(position);
			tvName.setText("姓名：" + student.getName());
			tvAge.setText("年龄：" + student.getAge());
			
			return view;
		}
		
	}
}
