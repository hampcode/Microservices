package com.hampcode.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hampcode.model.entity.Account;

@FeignClient(name = "service-users", url = "localhost:9010")
public interface UserFeignClient {

	@GetMapping("/users/search/getUsername")
	Account findByUserName(@RequestParam String username);

}
