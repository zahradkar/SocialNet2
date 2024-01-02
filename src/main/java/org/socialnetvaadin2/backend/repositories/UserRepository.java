package org.socialnetvaadin2.backend.repositories;

import org.socialnetvaadin2.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
	boolean existsUsersByUsername(String username);

	//	Optional<User> findByUsername(String username);
	User findByUsername(String username);
}
