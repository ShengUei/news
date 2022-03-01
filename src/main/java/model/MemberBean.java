package model;

import java.io.Serializable;

public class MemberBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String account;
	private String hashed_pwd;
	private String salt;
	private String username;
	
	public MemberBean() {
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getHashed_pwd() {
		return hashed_pwd;
	}

	public void setHashed_pwd(String hashed_pwd) {
		this.hashed_pwd = hashed_pwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
