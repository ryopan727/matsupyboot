package com.matsupy.api.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class GetUserDto {
	@NotEmpty(message = "E40002 IDいれてや")
	private final String id;
}
