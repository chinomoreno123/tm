package pl.poleng.dao;

import pl.poleng.dao.model.User;

public abstract interface UserRepositoryCustom {
	public abstract User findByUsername(String paramString);

	public abstract void deleteByUsername(String paramString);
}
