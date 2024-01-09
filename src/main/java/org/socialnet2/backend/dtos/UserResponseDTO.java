package org.socialnet2.backend.dtos;

import java.time.LocalDate;

public record UserResponseDTO(Long createdAt, String firstName, String lastName, String profilePictureURL, LocalDate birthday, String location, Long updatedAt) {
}
