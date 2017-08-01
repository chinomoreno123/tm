package pl.poleng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.poleng.dao.UserRepository;

@Service
public class LoginService {
	@Autowired
	UserRepository userDao;

	public boolean validateUser(String userName, String password) {
		return (userName.equalsIgnoreCase("a")) && (password.equals("b"));
	}
}
