package model;

import java.io.Serializable;

public class MemberBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String account;
	private String encrypt_pwd;
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

	public String getEncrypt_pwd() {
		return encrypt_pwd;
	}

	public void setEncrypt_pwd(String encrypt_pwd) {
		this.encrypt_pwd = encrypt_pwd;
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
