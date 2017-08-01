package pl.poleng.service;

import java.util.List;

import pl.poleng.dao.model.User;

public abstract interface UserService {
	public abstract User findById(Long paramLong);

	public abstract User findByUsername(String paramString);

	public abstract void saveUser(User paramUser);

	public abstract void updateUser(User paramUser);

	public abstract void deleteUserByUsername(String paramString);

	public abstract List<User> findAllUsers();

	public abstract boolean isUsernameUnique(Long paramLong, String paramString);
}
