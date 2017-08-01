package pl.poleng.service;

import java.util.List;

import pl.poleng.dao.model.UserProfile;

public abstract interface UserProfileService {
	public abstract UserProfile findById(Long paramLong);

	public abstract UserProfile findByType(String paramString);

	public abstract List<UserProfile> findAll();
}
