package com.project.AdminDashboard.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.AdminDashboard.models.User;
import com.project.AdminDashboard.repositories.RoleRepository;
import com.project.AdminDashboard.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	private BCryptPasswordEncoder bCrypt;
		
	public UserService(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder bCrypt){
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.bCrypt = bCrypt;
	}

	//sets user with role user
	public void saveWithUserRole(User user) {
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setRoles(roleRepo.findByName("ROLE_USER"));
		userRepo.save(user);
	}
	//sets user with role admin
	public void saveWithAdminRole(User user) {
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
		userRepo.save(user);
	}
	//find user by email
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	//find all users
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}
	//login with updated timestamp
	public User login(String email) {
		User user = userRepo.findByEmail(email);
		user.setUpdatedAt(new Date());
		userRepo.save(user);
		return user;
	}
}
