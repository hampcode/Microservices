package com.hampcode.service;

import java.util.List;
import java.util.Optional;


public interface CrudService<T, ID> {
	
	T create(T  entity);

	List<T> getAll();

	Optional<T> getOne(ID id);
	
	void deleteById(ID id);

}
