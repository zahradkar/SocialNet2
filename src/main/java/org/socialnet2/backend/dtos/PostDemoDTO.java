package org.socialnet2.backend.dtos;

import java.time.LocalDate;

public record PostDemoDTO(String profileImageURL, String name, LocalDate date, String content, String likes, String comments, String shares) {
}
