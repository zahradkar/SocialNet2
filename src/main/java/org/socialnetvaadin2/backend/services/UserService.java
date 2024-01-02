package org.socialnetvaadin2.backend.services;

import org.socialnetvaadin2.backend.entities.User;
import org.socialnetvaadin2.backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void add(String username, String password) {
		if (userRepository.existsUsersByUsername(username))
			throw new IllegalArgumentException("Username already exists");

		userRepository.save(new User(username, passwordEncoder.encode(password)));
		logger.info("User " + username + " stored in the database!");
	}
}
