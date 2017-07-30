package com.triador.springboot.service;


import com.triador.springboot.model.User;

import java.util.Date;
import java.util.List;

public interface UserService {
	
	User findById(Long id);

	User findByUsername(String name);

	User findByEmail(String email);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();

	boolean isUserExist(User user);

	List<User> findAllByBirthday(Date date);

	List<User> findAllByEmail(String email);

	List<User> findByFirstnameContainingIgnoreCase(String firstname);

	List<User> findByLastnameContainingIgnoreCase(String lastname);
}