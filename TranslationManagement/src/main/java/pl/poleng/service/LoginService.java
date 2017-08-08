package pl.poleng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import pl.poleng.dao.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	public boolean isCurrentAuthenticationAnonymous() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return this.authenticationTrustResolver.isAnonymous(authentication);
	}

	public String getPrincipal() {
		String userName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if ((principal instanceof UserDetails)) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	public boolean isValid() {
		return true;
	}
}
