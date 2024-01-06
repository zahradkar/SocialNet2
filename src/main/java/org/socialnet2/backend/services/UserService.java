package org.socialnet2.backend.services;

import org.socialnet2.backend.entities.User;
import org.socialnet2.backend.repositories.UserRepository;
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

	public void add(String email, String password) {
		if (userRepository.existsById(email))
			throw new IllegalArgumentException("E-mail already exists");

		userRepository.save(new User(email, passwordEncoder.encode(password)));
		logger.info("User " + email + " stored in the database!");
	}
}
