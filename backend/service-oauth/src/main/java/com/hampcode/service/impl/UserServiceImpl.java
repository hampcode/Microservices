package com.hampcode.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hampcode.client.UserFeignClient;
import com.hampcode.model.entity.Account;
import com.hampcode.service.UserService;

@Service
public class UserServiceImpl implements UserDetailsService,UserService {

	@Autowired
	private UserFeignClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username)
			 throws UsernameNotFoundException {

		Account user = userClient.findByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario " 
			+ username + " en el sistema");
		}

		List<GrantedAuthority> authorities = 
				user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(
						role.getName()))
				.collect(Collectors.toList());

		return new User(user.getUserName(), user.getPassword(), 
				user.getEnabled(), true, true, true, authorities);
	}

	@Override
	public Account findByUsername(String username) {
		return userClient.findByUserName(username);
	}

	
	
}




