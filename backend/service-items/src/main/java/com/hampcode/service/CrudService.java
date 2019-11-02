package com.hampcode.service;

import java.util.List;

public interface CrudService<T, ID> {

	List<T> getAll();

	T getOne(ID id);

}
