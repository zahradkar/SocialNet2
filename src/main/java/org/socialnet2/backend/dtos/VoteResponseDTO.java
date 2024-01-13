package org.socialnet2.backend.dtos;

public record VoteResponseDTO(int votes, String result, boolean status) {
}
// todo update: status is not used anywhere