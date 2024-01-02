package org.socialnetvaadin2.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(unique = true, nullable = false)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>(Collections.singletonList(Role.USER));

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User() {

	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
