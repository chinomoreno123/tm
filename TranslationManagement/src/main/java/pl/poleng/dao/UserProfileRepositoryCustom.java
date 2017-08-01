package pl.poleng.dao;

import pl.poleng.dao.model.UserProfile;

public abstract interface UserProfileRepositoryCustom {
	public abstract UserProfile findByType(String paramString);
}
