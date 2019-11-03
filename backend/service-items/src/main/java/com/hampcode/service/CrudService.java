package com.hampcode.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

	//List<T> getAll();

	//T getOne(ID id);
	
	T create(T  entity);

	List<T> getAll();

	Optional<T> getOne(ID id);
	
	void deleteById(ID id);

}
