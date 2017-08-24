package pl.poleng.dao.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserPreferences {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

}
