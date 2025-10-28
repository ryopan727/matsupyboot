package com.matsupy.api.dto.request.user;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class GetExtUserDto {

	@NotEmpty(message = "E400002:ユーザリスト")
	@Valid
	private final List<GetUserDto> userList;
}
