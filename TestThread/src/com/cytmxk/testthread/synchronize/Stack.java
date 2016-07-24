package com.cytmxk.testthread.synchronize;

public class Stack {

	private int index = 0;
	private char [] date = new char[9];
	
	public char pop(){
		index -- ;
		android.util.Log.i("chenyang","pop = " + date[index]);
		return date[index];
	}
	
	public void push(char c){
		date[index] = c;
		android.util.Log.i("chenyang","push = " + c);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index ++;
	}
}
