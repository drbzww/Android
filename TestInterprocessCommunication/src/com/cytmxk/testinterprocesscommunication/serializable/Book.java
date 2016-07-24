package com.cytmxk.testinterprocesscommunication.serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{

	private String name = null;
	private float price = 0;
	
	public Book(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeString(this.name);
		out.writeFloat(this.price);
	}
	
	public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {
		
		@Override
		public Book[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Book[size];
		}
		
		@Override
		public Book createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new Book(in);
		}
	};
	
	private Book(Parcel in) {
		this.name = in.readString();
		this.price = in.readFloat();
	}
}
