package com.matsupy.api.dto.request.gpt;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GptChatDto {
	@NotBlank(message = "E400002:メッセージ")
	private String message;
}
