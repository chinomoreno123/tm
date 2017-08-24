package pl.poleng.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import pl.poleng.dao.model.User;

public abstract interface UserService {
	public abstract User findById(Long paramLong);
	
	public abstract User findByIdAndLoadProfiles(Long paramLong);

	public abstract User findByUsername(String paramString);

	public abstract void saveUser(User paramUser);

	public abstract void updateUser(User paramUser);

	public abstract void deleteUserByUsername(String paramString);
	
	public abstract void deleteUserById(Long id);

	public abstract DataTablesOutput<User> findAllUsers(DataTablesInput input);
	
	public abstract List<User> findAllUsers();

	public abstract boolean isUsernameUnique(Long paramLong, String paramString);
	
	public abstract void sendUserToQueue(User user);
	
	public abstract void receiveFromQueue();

//	public abstract Page<User> listAllByPage(Pageable pageable);

}
