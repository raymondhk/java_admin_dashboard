package com.project.AdminDashboard.controllers;

import com.project.AdminDashboard.models.User;
import com.project.AdminDashboard.services.UserService;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Users{
	private UserService uService;

	public Users(UserService uService){
		this.uService = uService;
	}
	
	@RequestMapping("/")
	public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model){
		// System.out.println(uService.findAllUsers().size());
		System.out.println(error);
		if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
		return "login";
	}
	
	@RequestMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user){
		return "register";
	}
	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult res, Model model, HttpSession session) {
		if(res.hasErrors()) {
			return "register";
		}
		if(uService.findAllUsers().size() == 0) {
			uService.saveWithAdminRole(user);
		} else {
			uService.saveWithUserRole(user);
		}
		return "redirect:/login";
	}
	@RequestMapping("/dashboard")
	public String dashboard(Principal principal, Model model) {
		String email = principal.getName();
		User curUser = uService.findByEmail(email);
		// System.out.println(email);
		model.addAttribute("currentUser", curUser);
		System.out.println(curUser.getRoles());
		model.addAttribute("users", uService.findAllUsers());
		return "dashboard";
	}
}
