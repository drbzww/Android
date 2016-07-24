package com.cytmxk.testothersqlite.test;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

public class TestSchoolContentProvider extends AndroidTestCase {

	/* URI Authority */
	private static final String AUTHORITY = "com.cytmxk.testsqlite.provider.SchoolContentProvider";

	/* Content URI */
	public static final Uri CONTENT_BASE_URI = Uri.parse("content://"
			+ AUTHORITY + "/student");
	public static final Uri CONTENT_ADD_URI = Uri.parse("content://"
			+ AUTHORITY + "/student/add");
	public static final Uri CONTENT_DELETE_URI = Uri.parse("content://"
			+ AUTHORITY + "/student/delete");
	public static final Uri CONTENT_UPDATE_URI = Uri.parse("content://"
			+ AUTHORITY + "/student/update");
	public static final Uri CONTENT_QUARY_ALL_URI = Uri.parse("content://"
			+ AUTHORITY + "/student/quaryAll");
	public static final Uri CONTENT_QUARY_ITEM_URI = Uri.parse("content://"
			+ AUTHORITY + "/student/quary/#");

	private static final String TAG = "TestSchoolContentProvider";

	public void testAddStudent() {
		ContentResolver mResolver = getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", "陈阳");
		values.put("age", 18);
		Uri uri = mResolver.insert(CONTENT_ADD_URI, values);
		android.util.Log.i(TAG, "添加 uri：" + uri);
	}

	public void testDeleteStudent() {
		ContentResolver mResolver = getContext().getContentResolver();
		int count = mResolver.delete(CONTENT_DELETE_URI, "_id = ?",
				new String[] { "18" });
		android.util.Log.i(TAG, "删除了：" + count + "行");
	}

	public void testUpdateStudent() {
		ContentResolver mResolver = getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", "情场高手");
		int count = mResolver.update(CONTENT_UPDATE_URI, values, "_id = ?",
				new String[] { "17" });
		android.util.Log.i(TAG, "修改了：" + count + "行");
	}

	public void testQuaryAll() {
		ContentResolver mResolver = getContext().getContentResolver();
		Cursor cursor = mResolver.query(CONTENT_QUARY_ALL_URI, new String[] {
				"_id", "name", "age" }, null, null, "_id desc");
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				android.util.Log.i(
						TAG,
						"_id = " + cursor.getInt(0) + ", name = "
								+ cursor.getString(1) + ", age = "
								+ cursor.getInt(2));
			}
			cursor.close();
		}
	}

	public void testQuaryItem() {
		ContentResolver mResolver = getContext().getContentResolver();
		Uri uri = ContentUris.withAppendedId(CONTENT_QUARY_ITEM_URI, 17);
		Cursor cursor = mResolver.query(uri, new String[] { "_id", "name",
				"age" }, null, null, null);
		if (cursor != null && cursor.moveToFirst()) {
			android.util.Log.i(TAG, "_id = " + cursor.getInt(0) + ", name = "
					+ cursor.getString(1) + ", age = " + cursor.getInt(2));
			cursor.close();
		}
	}
}
