package org.socialnet2.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor
public class Post {
	// TODO add comments
	@Column(name = "created_at", columnDefinition = "BIGINT UNSIGNED")
	private final long createdAt = System.currentTimeMillis(); // TODO somehow improve long -> unsigned long
	@Id
	@Column(columnDefinition = "bigint unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String content;
	private LocalDate publishDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id")
	private User author;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_likes_post",
			joinColumns = @JoinColumn(name = "post_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> likedByUsers = new ArrayList<>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_dislikes_post",
			joinColumns = @JoinColumn(name = "post_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> dislikedByUsers = new ArrayList<>();
	@Column(name = "updated_at", columnDefinition = "BIGINT UNSIGNED")
	private long updatedAt; // TODO somehow improve long -> unsigned long

	public Post(User author, String content, LocalDate publishDate) {
		this.content = content;
		this.author = author;
		this.publishDate = publishDate;
	}
	public int getLikes() {
		return likedByUsers.size() - dislikedByUsers.size();
	}
}
