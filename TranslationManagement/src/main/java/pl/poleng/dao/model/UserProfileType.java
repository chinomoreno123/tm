package pl.poleng.dao.model;

import java.io.Serializable;

/**
 * 
 * @author Dariusz Pudlik
 *
 */
public enum UserProfileType implements Serializable {
	USER("USER"), DBA("DBA"), ADMIN("ADMIN");

	String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return this.userProfileType;
	}
}