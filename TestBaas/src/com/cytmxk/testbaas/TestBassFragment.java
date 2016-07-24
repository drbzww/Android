package com.cytmxk.testbaas;

import java.util.List;

import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestBassFragment extends Fragment implements View.OnClickListener {

	private Button mBtnAddPerson = null;
	private Button mBtnDeletePerson = null;
	private Button mBtnQuaryPerson = null;
	private Button mBtnModifyPerson = null;
	private EditText mEtQuaryName = null;
	private EditText mEtModifyPerson = null;
	
	private Button mBtnPushAll = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_test_bass, container,
				false);
		mBtnAddPerson = (Button) view.findViewById(R.id.bt_add_person);
		mBtnDeletePerson = (Button) view.findViewById(R.id.bt_delete_person);
		mBtnQuaryPerson = (Button) view.findViewById(R.id.bt_quary_person);
		mBtnModifyPerson = (Button) view.findViewById(R.id.bt_modify_person);
		mEtQuaryName = (EditText) view.findViewById(R.id.et_quary_name);
		mEtModifyPerson = (EditText) view.findViewById(R.id.et_modify_name);
		mBtnAddPerson.setOnClickListener(this);
		mBtnDeletePerson.setOnClickListener(this);
		mBtnQuaryPerson.setOnClickListener(this);
		mBtnModifyPerson.setOnClickListener(this);
		
		mBtnPushAll = (Button) view.findViewById(R.id.button_push_all);
		mBtnPushAll.setOnClickListener(this);
		
		return view;
	}
	
    private void pushAll() {
        new BmobPushManager(getContext()).pushMessageAll("推送消息测试。。。。");
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_add_person:
			for (int i = 0; i < 10; i++) {
				Person person = new Person();
				person.setName("chenyang" + (i + 1));
				person.setAddress("北京海淀");
				addPerson(person);
			}
			break;
		case R.id.bt_delete_person:
			deletePerson();
			break;
		case R.id.bt_quary_person:
			quaryPerson(mEtQuaryName.getText().toString());
			break;
		case R.id.bt_modify_person:
			modifyPerson(mEtModifyPerson.getText().toString());
			break;
		case R.id.button_push_all:
			pushAll();
			break;

		default:
			break;
		}
	}

	private void addPerson(final Person person) {
		person.save(getContext(), new SaveListener() {
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(),
						"添加数据成功，返回objectId为：" + person.getObjectId(),
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "创建数据失败：" + msg, Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	private void deletePerson() {
		Person p2 = new Person();
		p2.setObjectId("c023893d23");
		p2.delete(getContext(), new DeleteListener() {
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "删除成功", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "删除失败：" + msg, Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	private void quaryPerson(String name) {
		// 查找Person表里面指定name的数据
		BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
		bmobQuery.addWhereEqualTo("name", name);
		bmobQuery.findObjects(getContext(), new FindListener<Person>() {

			@Override
			public void onError(int arg0, String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "查询失败：" + msg, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(List<Person> persons) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
				builder.setTitle("Quary");
				String str = "";
				for (Person person : persons) {
					str += person.getName() + " : " + person.getAddress();
				}
				builder.setMessage(str).show();
			}
		});
	}
	
	private void modifyPerson(String address) {
		//更新Person表里面id为1d4dbff714的数据，address内容更新为“北京朝阳”
		final Person p2 = new Person();
		p2.setAddress(address);
		p2.update(getContext(), "1d4dbff714", new UpdateListener() {
		    @Override
		    public void onSuccess() {
		        // TODO Auto-generated method stub
		        Toast.makeText(getContext(), "更新成功：" + p2.getUpdatedAt(), Toast.LENGTH_LONG).show();
		    }
		    @Override
		    public void onFailure(int code, String msg) {
		        // TODO Auto-generated method stub
		        Toast.makeText(getContext(), "更新失败：" + msg, Toast.LENGTH_LONG).show();
		    }
		});
	}
}
