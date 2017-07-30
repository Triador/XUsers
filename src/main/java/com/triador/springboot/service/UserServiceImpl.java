package com.triador.springboot.service;

import java.util.Date;
import java.util.List;

import com.triador.springboot.model.User;
import com.triador.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	public User findByUsername(String name) {
		return userRepository.findByUsername(name);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(User user){
		saveUser(user);
	}

	public void deleteUserById(Long id){
		userRepository.delete(id);
	}

	public void deleteAllUsers(){
		userRepository.deleteAll();
	}

	public List<User> findAllUsers(){
		return userRepository.findAll();
	}

	public boolean isUserExist(User user) {
		return findByUsername(user.getUsername()) != null || findByEmail(user.getEmail())!= null;
	}

	@Override
	public List<User> findAllByBirthday(Date date) {
		return userRepository.findAllByBirthday(date);
	}

	@Override
	public List<User> findAllByEmail(String email) {
		return userRepository.findAllByEmail(email);
	}

	@Override
	public List<User> findByFirstnameContainingIgnoreCase(String firstname) {
		return userRepository.findByFirstnameContainingIgnoreCase(firstname);
	}

	@Override
	public List<User> findByLastnameContainingIgnoreCase(String firstname) {
		return userRepository.findByLastnameContainingIgnoreCase(firstname);
	}

}
