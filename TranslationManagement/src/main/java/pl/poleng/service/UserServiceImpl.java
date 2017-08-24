package pl.poleng.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.poleng.dao.UserRepository;
import pl.poleng.dao.model.User;
import pl.poleng.messaging.MessageReceiver;
import pl.poleng.messaging.MessageSender;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
	
	@Autowired
	private UserRepository dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MessageSender messageSender;
	
	@Autowired
	MessageReceiver messageReceiver;

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
				entity.setConfirmPassword(this.passwordEncoder.encode(user.getPassword()));
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

	public DataTablesOutput<User> findAllUsers(DataTablesInput input) {
		return this.dao.findAll(input);
	}

	public List<User> findAllUsers() {
		return (List<User>) this.dao.findAll();
	}

	public boolean isUsernameUnique(Long id, String username) {
		User user = findByUsername(username);
		return (user == null) || ((id != null) && (user.getId() == id));
	}

	@Override
	public void deleteUserById(Long id) {
		this.dao.delete(id);
	}

	@Override
	public User findByIdAndLoadProfiles(Long id) {
		User user = (User) this.dao.findOne(id);
		user.getUserProfiles().size();
		return user;
	}

	@Override
	public void sendUserToQueue(User user) {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Application : sending order request {}", user);
		messageSender.sendMessage(user);		
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	@Override
	public void receiveFromQueue() {
		User user = (User) messageReceiver.receive();
		
	}
}
