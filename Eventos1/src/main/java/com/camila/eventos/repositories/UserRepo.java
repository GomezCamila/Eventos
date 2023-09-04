package com.camila.eventos.repositories;

import org.springframework.data.repository.CrudRepository;

import com.camila.eventos.models.User;

public interface UserRepo extends CrudRepository<User, Long> {

	
	User findByEmail(String email);
}