package pl.poleng.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pl.poleng.dao.UserRepository;
import pl.poleng.dao.model.User;
import pl.poleng.dao.model.UserProfile;
import pl.poleng.dao.model.UserProfileType;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class LoginServiceTest {

	@Autowired
	LoginService loginService;

	@Autowired
	UserService userService;

	@Autowired
	private UserRepository dao;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Before
	public void init() {
		User user = new User();
		user.setUsername("olek");
		String pass = this.passwordEncoder.encode("nowak");
		user.setPassword(pass);
		user.setConfirmPassword(pass);
		user.setFirstName("jan");
		user.setLastName("Nowak");
		user.setEmail("mail@mail.com");
		Set<UserProfile> userProfiles = new HashSet<UserProfile>();
		UserProfile profile = new UserProfile();
		profile.setType(UserProfileType.ADMIN.getUserProfileType());
		profile.setId((long) 2);
		userProfiles.add(profile);
		user.setUserProfiles(userProfiles);
		user.setEmail("mail@mail.com");

		dao.save(user);
	}

	@Test
	public final void givenExistingUser_whenAuthenticate_thenRetrieveFromDb() {

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("olek", "nowak");
		Authentication authentication = authenticationProvider.authenticate(auth);

		assertEquals(authentication.getName(), "olek");
	}

	@Test
	@WithMockUser(username = "admin", authorities = { "ADMIN"})
	public void getPrincipalTest() {
	
//		Authentication authentication = Mockito.mock(Authentication.class);
//		// Mockito.whens() for your authorization object
//		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
//		SecurityContextHolder.setContext(securityContext);
//		
		
		assertEquals(loginService.getPrincipal(),"admin");
	}

	@Test
	@WithMockUser(username = "admin", authorities = { "ADMIN"})
	public void isCurrentAuthenticationAnonymousTest() {
		assertFalse(loginService.isCurrentAuthenticationAnonymous());
	}	
		
	@After
	public void tearDown() {
		userService.deleteUserByUsername("olek");
	}
}
