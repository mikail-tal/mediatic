package com.dta.mediatic.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.dta.mediatic.user.model.User;
import com.dta.mediatic.user.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User findById(Long id) throws NotFoundException {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new NotFoundException();
		}
		return user;
	}
	
	public User findByLogin(String login) throws NotFoundException {
		Optional<User> user = userRepository.findOneByLogin(login);
		if (!user.isPresent()) {
			throw new NotFoundException();
		}
		return user.get();
	}
	
	public User create(User user) {
		Assert.notNull(user, "Resource can't be null");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User update(Long id, User user) {
		User resource = userRepository.findOne(id);
		Assert.notNull(resource, "Resource can't be null");
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	public Page<User> findAllByOrderByNameAsc(Pageable pageable){
		return userRepository.findAllByOrderByNameAsc(pageable);
	}
	
	public Optional<User> findOneByLogin(String login) {
		return userRepository.findOneByLogin(login);
	}

	
}
