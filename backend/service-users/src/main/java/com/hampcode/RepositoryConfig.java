package com.hampcode;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.hampcode.model.entity.Account;
import com.hampcode.model.entity.Role;

@Configuration
public class RepositoryConfig  
		implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Account.class,Role.class);
	}	
}
