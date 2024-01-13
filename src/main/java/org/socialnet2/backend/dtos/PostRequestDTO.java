package org.socialnet2.backend.dtos;

import java.time.LocalDate;

public record PostRequestDTO(String imageURL, String name, LocalDate date, String content, String likes, String comments, String shares) {
}
