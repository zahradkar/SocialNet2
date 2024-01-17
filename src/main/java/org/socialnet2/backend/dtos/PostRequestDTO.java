package org.socialnet2.backend.dtos;

import java.time.LocalDate;

public record PostRequestDTO(String content, String previewImageURL, String previewTitle, String previewDescription) {
}
