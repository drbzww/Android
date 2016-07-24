package com.cytmxk.testinterprocesscommunication.serializable;

import java.io.Serializable;
import java.util.UUID;
import android.content.Context;
import com.cytmxk.utils.serializer.SerializerUtils;

public class User implements Serializable {

	private static final long serialVersionUID = -6896248794662071366L;

	private UUID userId = null;
	private String userName = null;
	private boolean isMale = true;
	
	public User(String userName, boolean isMale) {
		super();
		this.userId = UUID.randomUUID();
		this.userName = userName;
		this.isMale = isMale;
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
	
	public static User readObject(Context context, String filename) throws Exception {
		return (User) SerializerUtils.readObjectFromExternalStorage(context, filename);
	}
	
	public void writeObject(Context context, String filename) throws Exception {
		SerializerUtils.writeObjectToExternalStorage(context, this, filename);
	}
}
