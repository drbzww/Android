package com.cytmxk.testbaas;

import cn.bmob.v3.BmobObject;

public class Person extends BmobObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3067964990450613696L;

	private String name;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
