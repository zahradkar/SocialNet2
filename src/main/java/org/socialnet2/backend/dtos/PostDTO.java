package org.socialnet2.backend.dtos;

public record PostDTO(String image, String name, String date, String content, String likes, String comments, String shares) {
}
