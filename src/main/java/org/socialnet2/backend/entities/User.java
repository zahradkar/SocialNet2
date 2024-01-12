package org.socialnet2.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
	@Column(name = "created_at", columnDefinition = "BIGINT UNSIGNED")
	private final long createdAt = System.currentTimeMillis(); // TODO somehow improve long -> unsigned long
	@Id
	@Column(unique = true, nullable = false)
	@NotBlank
	@Email // TODO consider improving e-mail validation; The current one is weird (e. g. a@a is allowed)
//	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$",message = "Incorrect format of e-mail address!")
	private String email;
	@NotBlank
	@Column(length = 60)
	private String password;
	@Column(name = "first_name", length = 30)
	private String firstName ="";
	@Column(name = "last_name", length = 50)
	private String lastName="";
	@Column(name = "profile_picture_URL")
	private String profilePictureURL="";
	private LocalDate birthday;
	private String location="";
	@Column(name = "updated_at", columnDefinition = "BIGINT UNSIGNED")
	private long updatedAt; // TODO somehow improve long -> unsigned long
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>(Collections.singletonList(Role.USER));

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}


	/*public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}*/
}
