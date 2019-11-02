package com.hampcode.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.hampcode.model.entity.Account;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<Account,Long>{

	@Query("SELECT u FROM Account u WHERE u.userName=?1")
	Account getByUserName(String username);

    @RestResource(path="getUsername")
	Account findByUserName(@Param("username") String username);
	
}
