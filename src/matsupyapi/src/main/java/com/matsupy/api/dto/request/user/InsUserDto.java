package com.matsupy.api.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InsUserDto {
	@NotNull(message = "E400002:ID")
	private final Integer id;

	@NotEmpty(message = "E400002:名前")
	private final String name;
}
