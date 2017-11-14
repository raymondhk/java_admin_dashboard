package com.project.AdminDashboard.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.AdminDashboard.models.Role;
import com.project.AdminDashboard.models.User;
import com.project.AdminDashboard.repositories.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	private UserRepository userRepo;
		
	public UserDetailsServiceImplementation(UserRepository userRepo){
		this.userRepo = userRepo;
	}
	
	//finds user by their email, if found the correct authorities are returned
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		System.out.println(email);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		user.setUpdatedAt(new Date());
		userRepo.save(user);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
	}
	//returns a list of permissions
	private List<GrantedAuthority> getAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role : user.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
			authorities.add(grantedAuthority);
		}
		return authorities;
	}
}
