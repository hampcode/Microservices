package com.hampcode.service;

import com.hampcode.model.entity.Account;

public interface UserService {
	public Account findByUsername(String username);
}
