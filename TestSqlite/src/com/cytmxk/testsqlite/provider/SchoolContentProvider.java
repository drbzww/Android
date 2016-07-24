package com.cytmxk.testsqlite.provider;

import com.cytmxk.testsqlite.db.SchoolOpenHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class SchoolContentProvider extends ContentProvider {

	private static UriMatcher mUriMatcher;

	/* URI Authority */
	private static final String AUTHORITY = "com.cytmxk.testsqlite.provider.SchoolContentProvider";

	/* Match Code */
	private static final int MATCH_STUDENT_ADD = 0;
	private static final int MATCH_STUDENT_DELETE = 1;
	private static final int MATCH_STUDENT_UPDATE = 2;
	private static final int MATCH_STUDENT_QUARY_ALL = 3;
	private static final int MATCH_STUDENT_QUARY_ITEM = 4;

	/* MIME */
	private static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.cytmxk.article";
	private static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.cytmxk.article";

	static {
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		mUriMatcher.addURI(AUTHORITY, "/student/add", MATCH_STUDENT_ADD);
		mUriMatcher.addURI(AUTHORITY, "/student/delete", MATCH_STUDENT_DELETE);
		mUriMatcher.addURI(AUTHORITY, "/student/update", MATCH_STUDENT_UPDATE);
		mUriMatcher.addURI(AUTHORITY, "/student/quaryAll",
				MATCH_STUDENT_QUARY_ALL);
		mUriMatcher.addURI(AUTHORITY, "/student/quary/#",
				MATCH_STUDENT_QUARY_ITEM);
	}

	private SchoolOpenHelper mOpenHelper = null;

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mOpenHelper = new SchoolOpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		switch (mUriMatcher.match(uri)) {
		case MATCH_STUDENT_QUARY_ALL:
			if (db.isOpen()) {
				Cursor cursor = db.query(SchoolOpenHelper.STUDENT_TABLE_NAME,
						projection, selection, selectionArgs, null, null,
						sortOrder);
				return cursor;
			}
			break;
		case MATCH_STUDENT_QUARY_ITEM:
			if (db.isOpen()) {
				long id = ContentUris.parseId(uri);
				Cursor cursor = db.query(SchoolOpenHelper.STUDENT_TABLE_NAME, projection,
						"_id = ?", new String[]{id + ""}, null, null, sortOrder);
				return cursor;
			}
			break;

		default:
			break;
		}
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (mUriMatcher.match(uri)) {
		case MATCH_STUDENT_QUARY_ALL:
			return CONTENT_TYPE;
		case MATCH_STUDENT_QUARY_ITEM:
			return CONTENT_ITEM_TYPE;

		default:
			break;
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		switch (mUriMatcher.match(uri)) {
		case MATCH_STUDENT_ADD:
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if (db.isOpen()) {
				long id = db.insert(SchoolOpenHelper.STUDENT_TABLE_NAME, null,
						values);
				db.close();
				return ContentUris.withAppendedId(uri, id);
			}
			break;

		default:
			throw new IllegalArgumentException("不匹配的 URI:" + uri);
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		switch (mUriMatcher.match(uri)) {
		case MATCH_STUDENT_DELETE:
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if (db.isOpen()) {
				int count = db.delete(SchoolOpenHelper.STUDENT_TABLE_NAME,
						selection, selectionArgs);
				db.close();
				return count;
			}
			break;

		default:
			throw new IllegalArgumentException("不匹配的 URI:" + uri);
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		switch (mUriMatcher.match(uri)) {
		case MATCH_STUDENT_UPDATE:
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if (db.isOpen()) {
				int count = db.update(SchoolOpenHelper.STUDENT_TABLE_NAME,
						values, selection, selectionArgs);
				db.close();
				return count;
			}
			break;

		default:
			throw new IllegalArgumentException("不匹配的 URI:" + uri);
		}
		return 0;
	}

}
