package pl.poleng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.poleng.dao.UserRepository;
import pl.poleng.dao.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository dao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(Long id) {
		return (User) this.dao.findOne(id);
	}

	public User findByUsername(String username) {
		User user = this.dao.findByUsername(username);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		this.dao.save(user);
	}

	public void updateUser(User user) {
		User entity = (User) this.dao.findOne(user.getId());
		if (entity != null) {
			entity.setUsername(user.getUsername());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(this.passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	public void deleteUserByUsername(String username) {
		this.dao.deleteByUsername(username);
	}

	public List<User> findAllUsers() {
		return (List) this.dao.findAll();
	}

	public boolean isUsernameUnique(Long id, String username) {
		User user = findByUsername(username);
		return (user == null) || ((id != null) && (user.getId() == id));
	}
}
