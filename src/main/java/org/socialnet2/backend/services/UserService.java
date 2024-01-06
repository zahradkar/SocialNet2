package org.socialnet2.backend.services;

import com.vaadin.flow.spring.security.AuthenticationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.backend.entities.User;
import org.socialnet2.backend.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationContext authenticationContext;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationContext authenticationContext) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationContext = authenticationContext;
	}

	private static List<GrantedAuthority> getAuthorities(User user) {
		return user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
				.collect(Collectors.toList());
	}

	public void add(String email, String password) {
		if (userRepository.existsById(email))
			throw new IllegalArgumentException("E-mail already exists");

		userRepository.save(new User(email, passwordEncoder.encode(password)));
		logger.info("User with e-mail " + email + " stored in the database!");
	}

	@Transactional
	public Optional<User> getAuthenticatedUser() {
		return authenticationContext.getAuthenticatedUser(UserDetails.class).map(userDetails -> userRepository.getReferenceById(userDetails.getUsername()));
	}

	public void logout() {
		authenticationContext.logout();
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) {
		var user = userRepository.getReferenceById(email);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
	}
}
