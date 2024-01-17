package org.socialnet2.backend.dtos;

import org.socialnet2.backend.entities.User;

import java.util.List;

public record PostResponseDTO(long createdAt, String id, String content, User author, String likes, String comments, String shares,long updatedAt, String previewImageURL, String previewTitle, String previewDescription) {
}
