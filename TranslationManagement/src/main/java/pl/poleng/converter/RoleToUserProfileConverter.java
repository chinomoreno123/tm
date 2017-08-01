package pl.poleng.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.poleng.dao.model.UserProfile;
import pl.poleng.service.UserProfileService;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {
	
	static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);
	
	@Autowired
	UserProfileService userProfileService;

	public UserProfile convert(Object element) {
		Long id = Long.valueOf(Long.parseLong((String) element));
		UserProfile profile = this.userProfileService.findById(id);
		logger.info("Profile : {}", profile);
		return profile;
	}
}
