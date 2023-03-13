package com.example.sptingsecuritydemo.model;

public enum Permission {

	DEVELOPERS_READ("developers:read"), 
	DEVELOPERS_WRITE("developers:write");

	private final String permission;

	private Permission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

}
