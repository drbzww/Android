package com.cytmxk.utils.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import android.content.Context;

public class SerializerUtils {

	public static Object readObject(Context context, String filename)
			throws Exception {

		ObjectInputStream inputStream = null;
		Object object = null;
		try {
			inputStream = new ObjectInputStream(context.openFileInput(filename));
			object = inputStream.readObject();
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}
		return object;
	}

	public static void writeObject(Context context, Object object,
			String filename) throws Exception {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(context.openFileOutput(
					filename, Context.MODE_PRIVATE));
			outputStream.writeObject(object);
		} finally {
			if (null != outputStream) {
				outputStream.close();
			}
		}
	}
	
	public static Object readObjectFromExternalStorage(Context context, String filename)
			throws Exception {

		ObjectInputStream inputStream = null;
		Object object = null;
		try {
			
			File file = context.getExternalFilesDir(null);
			InputStream in = new FileInputStream(file.getPath() + "/"
					+ filename);
			inputStream = new ObjectInputStream(in);
			object = inputStream.readObject();
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}
		return object;
	}

	public static void writeObjectToExternalStorage(Context context, Object object,
			String filename) throws Exception {
		ObjectOutputStream outputStream = null;
		try {
			File file = context.getExternalFilesDir(null);
			OutputStream out = new FileOutputStream(file.getPath() + "/"
					+ filename);
			outputStream = new ObjectOutputStream(out);
			outputStream.writeObject(object);
		} finally {
			if (null != outputStream) {
				outputStream.close();
			}
		}
	}
}
