package org.socialnet2.backend.services;

import com.vaadin.flow.spring.security.AuthenticationContext;
import org.socialnet2.backend.entities.User;
import org.socialnet2.backend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthenticatedUserService {

	private final UserRepository userRepository;
	private final AuthenticationContext authenticationContext;

	public AuthenticatedUserService(AuthenticationContext authenticationContext, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.authenticationContext = authenticationContext;
	}

	@Transactional
	public Optional<User> get() {
//		return authenticationContext.getAuthenticatedUser(UserDetails.class).map(userDetails -> userRepository.findByUsername(userDetails.getUsername()));
		return authenticationContext.getAuthenticatedUser(UserDetails.class).map(userDetails -> userRepository.getReferenceById(userDetails.getUsername()));
	}

	public void logout() {
		authenticationContext.logout();
	}
}
