package com.cytmxk.bindservice;

public interface ICounterService {
	
	public void startCounter(int val,ICounterCallback callback);
	public void stopCounter();

}
