package com.gianni.service.model;

import java.io.Serializable;

public class UtenteLoginRequest implements Serializable {

	private static final long serialVersionUID = -8135418068623413826L;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
