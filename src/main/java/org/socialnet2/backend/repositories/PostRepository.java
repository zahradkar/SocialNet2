package org.socialnet2.backend.repositories;

import org.socialnet2.backend.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
