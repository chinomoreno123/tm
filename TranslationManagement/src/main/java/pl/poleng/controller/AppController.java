package pl.poleng.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.poleng.dao.model.User;
import pl.poleng.dao.model.UserProfile;
import pl.poleng.service.UserProfileService;
import pl.poleng.service.UserService;

@Controller
@RequestMapping({ "/admin/user/" })
@SessionAttributes({ "roles" })
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/", "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String listUsers(ModelMap model) {
		List<User> users = this.userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("errorMessage", "ERROR MESSAGE");
		return "userslist";
	}

	@RequestMapping(value = { "/newuser" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", Boolean.valueOf(false));
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	@RequestMapping(value = { "/newuser" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "registration";
		}
		if (!this.userService.isUsernameUnique(user.getId(), user.getUsername())) {
			FieldError ssoError = new FieldError("user", "username", this.messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}
		this.userService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());

		return "registrationsuccess";
	}

	@RequestMapping(value = { "/edit-user-{username}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public String editUser(@PathVariable String username, ModelMap model) {
		User user = this.userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", Boolean.valueOf(true));
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	@RequestMapping(value = { "/edit-user-{username}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String username) {
		if (result.hasErrors()) {
			return "registration";
		}
		this.userService.updateUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/delete-user-{username}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public String deleteUser(@PathVariable String username) {
		this.userService.deleteUserByUsername(username);
		return "redirect:/list";
	}

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return this.userProfileService.findAll();
	}

	@RequestMapping(value = { "/Access_Denied" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ((principal instanceof UserDetails)) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}