package pl.poleng.service;

import java.util.List;
import java.util.Set;

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
import pl.poleng.dao.model.UserProfile;
import pl.poleng.messaging.MessageReceiver;
import pl.poleng.messaging.MessageSender;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
	
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MessageSender messageSender;
	
	@Autowired
	MessageReceiver messageReceiver;

	public User findById(Long id) {
		User user = this.userDao.findOne(id);
		Set<UserProfile> profiles = user.getUserProfiles();
		return user; 
	}
	
	public List<User> findTest(Long id) {
		List<User> users = (List<User>)this.userDao.findAll();
		User u = users.get(0);
		//Set<UserProfile> profiles = user.getUserProfiles();
		return users;
	}
	
	public User findByUsername(String username) {
		User user = this.userDao.findByUsername(username);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		this.userDao.save(user);
	}

	public void updateUser(User user) {
		User entity = (User) this.userDao.findOne(user.getId());
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
		this.userDao.deleteByUsername(username);
	}

	public DataTablesOutput<User> findAllUsers(DataTablesInput input) {
		return this.userDao.findAll(input);
	}

	public List<User> findAllUsers() {
		return (List<User>) this.userDao.findAll();
	}

	public boolean isUsernameUnique(Long id, String username) {
		User user = findByUsername(username);
		return (user == null) || ((id != null) && (user.getId() == id));
	}

	@Override
	public void deleteUserById(Long id) {
		this.userDao.delete(id);
	}

	@Override
	public User findByIdAndLoadProfiles(Long id) {
		return this.userDao.findByIdAndWihProfiles(id);
	}

	@Override
	public void sendUserToQueue(User user) {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("Application : sending order request {}", user);
		messageSender.sendMessage(user);		
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	@Override
	public User receiveFromQueue() {
		return (User) messageReceiver.receive();
		
	}
}
