package com.matsupy.api.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InsUserDto {
	@NotNull(message="E400003 IDを入力せいー")
	private final Integer id;

	@NotEmpty(message="E400004 名前を入力せい")
	private final String name;
}
