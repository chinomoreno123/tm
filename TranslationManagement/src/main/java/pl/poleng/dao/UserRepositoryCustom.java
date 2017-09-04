package pl.poleng.dao;

import org.springframework.data.jpa.repository.Query;

import pl.poleng.dao.model.User;

public abstract interface UserRepositoryCustom {
	public abstract User findByUsername(String paramString);

	public abstract void deleteByUsername(String paramString);
	

}