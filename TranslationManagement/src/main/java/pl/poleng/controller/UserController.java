package pl.poleng.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.annotation.JsonView;

import pl.poleng.dao.UserRepository;
import pl.poleng.dao.model.User;
import pl.poleng.dao.model.UserProfile;
import pl.poleng.security.MyUserPrincipal;
import pl.poleng.service.UserProfileService;
import pl.poleng.service.UserService;
import pl.poleng.validator.PasswordValidator;

@Controller
@RequestMapping({ "/admin/user/" })
@SessionAttributes({ "roles" })
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PasswordValidator passwordValidator;

	@InitBinder("user")
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(passwordValidator);
	}

	@RequestMapping(value = { "/", "/list" }, method = { RequestMethod.GET })
	public String listUsers(ModelMap model) {
		List<User> users = this.userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("errorMessage", "ERROR MESSAGE");
		return "userslist";
	}

	@RequestMapping(value = { "/newuser" }, method = { RequestMethod.GET })
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", Boolean.valueOf(false));
		model.addAttribute("loggedinuser", getPrincipal());
		return "newuser";
	}

	@RequestMapping(value = { "/newuser" }, method = { RequestMethod.POST })
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());

		if (result.hasErrors()) {
			return "newuser";
		}
		if (!this.userService.isUsernameUnique(user.getId(), user.getUsername())) {
			FieldError ssoError = new FieldError("user", "username", this.messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(ssoError);
			return "newuser";
		}
		this.userService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");

		return "redirect:/admin/user/list";
	}

	@RequestMapping(value = { "/edit/{id}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String editUser(@PathVariable Long id, ModelMap model) {
		User user = this.userService.findByIdAndLoadProfiles(id);
		model.addAttribute("user", user);
		model.addAttribute("edit", Boolean.valueOf(true));
		model.addAttribute("loggedinuser", getPrincipal());
		return "newuser";
	}

	@RequestMapping(value = { "/edit/{id}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable Long id) {
		model.addAttribute("edit", Boolean.valueOf(true));
		model.addAttribute("loggedinuser", getPrincipal());
		if (result.hasErrors()) {
			return "newuser";
		}
		this.userService.updateUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/admin/user/list";
	}

	@RequestMapping(value = { "/delete-user-{username}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public String deleteUser(@PathVariable String username) {
		this.userService.deleteUserByUsername(username);
		return "redirect:/admin/user/list";
	}

	@RequestMapping(value = { "/delete/{id}" }, method = { RequestMethod.GET })
	public String deleteUserById(@PathVariable Long id) {
		this.userService.deleteUserById(id);
		return "redirect:/admin/user/list";
	}

	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/listData", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<User> getUsers(@Valid DataTablesInput input) {
		return userService.findAllUsers(input);
	}

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return this.userProfileService.findAll();
	}

	private String getPrincipal() {
		String fullName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ((principal instanceof MyUserPrincipal)) {
			fullName = ((MyUserPrincipal) principal).getFirstName() + " " + ((MyUserPrincipal) principal).getLastName();
		} else {
			fullName = principal.toString();
		}
		return fullName;
	}

}