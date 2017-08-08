package pl.poleng.converter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pl.poleng.dao.model.UserProfile;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class RoleToUserProfileConverterTest {
	
	static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverterTest.class);

	@Autowired
	RoleToUserProfileConverter converter;
	
	@Test
	public final void testConvert() {
		UserProfile profile = converter.convert("1");
		logger.info("Profile : {}", profile);
		assertTrue(true);
	}

}
