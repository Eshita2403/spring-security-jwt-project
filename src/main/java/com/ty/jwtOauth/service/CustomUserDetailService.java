package com.ty.jwtOauth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ty.jwtOauth.dto.MyUserDetails;
import com.ty.jwtOauth.dto.User;
import com.ty.jwtOauth.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserbyUsername(username);
		if(user!=null) {
		MyUserDetails details=new MyUserDetails();
		details.setUser(user);
		return details;
		}
		else {
			throw new UsernameNotFoundException("user with username:"+username+" not found");
		}
	}

}
