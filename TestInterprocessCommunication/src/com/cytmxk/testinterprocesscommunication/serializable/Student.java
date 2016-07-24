package com.cytmxk.testinterprocesscommunication.serializable;

import java.util.UUID;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.cytmxk.utils.serializer.SerializerUtils;

public class Student implements Parcelable {

	private UUID userId = null;
	private String userName = null;
	private boolean isMale = true;
	private Book book = null;

	public Student(String userName, boolean isMale, Book book) {
		super();
		this.userId = UUID.randomUUID();
		this.userName = userName;
		this.isMale = isMale;
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public static Student readObject(Context context, String filename)
			throws Exception {
		return (Student) SerializerUtils.readObjectFromExternalStorage(context,
				filename);
	}

	public void writeObject(Context context, String filename) throws Exception {
		SerializerUtils.writeObjectToExternalStorage(context, this, filename);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeSerializable(this.userId);
		dest.writeString(this.userName);
		dest.writeBooleanArray(new boolean[] { this.isMale });
		dest.writeParcelable(this.book, 0);
	}
	
	public static final Parcelable.Creator<Student> CREATOR = new Creator<Student>() {
		
		@Override
		public Student[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Student[size];
		}
		
		@Override
		public Student createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Student(source);
		}
	};
	
	private Student(Parcel in) {
		this.userId = (UUID) in.readSerializable();
		this.userName = in.readString();
		boolean[] val = new boolean[1];
		in.readBooleanArray(val);
		this.isMale = val[0];
		this.book = in.readParcelable(Thread.currentThread().getContextClassLoader());
	}
}
