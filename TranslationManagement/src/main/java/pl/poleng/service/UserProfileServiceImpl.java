package pl.poleng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.poleng.dao.UserProfileRepository;
import pl.poleng.dao.model.UserProfile;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired
	UserProfileRepository dao;

	public UserProfile findById(Long id) {
		return (UserProfile) this.dao.findOne(id);
	}

	public UserProfile findByType(String type) {
		return this.dao.findByType(type);
	}

	public List<UserProfile> findAll() {
		return (List) this.dao.findAll();
	}
}
