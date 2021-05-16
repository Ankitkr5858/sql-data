package net.javaguides.springboot.web;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.service.UserServiceImpl;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import java.lang.reflect.Method;
import java.util.List;

@Controller
public class MainController {


	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/user")
	public String viewUsers(Model model) {
		List<User> listUsers = userServiceImpl.listAll();
		model.addAttribute("listUsers", listUsers);
		return "user";
	}



	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("product") User user) {
		userServiceImpl.save(user);

		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_user");
		User user = userServiceImpl.get(id);
		mav.addObject("user", user);

		return mav;
	}

	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
		userServiceImpl.delete(id);
		return "redirect:/";
	}

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

}
