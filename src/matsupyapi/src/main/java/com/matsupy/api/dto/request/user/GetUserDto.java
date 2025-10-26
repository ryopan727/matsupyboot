package com.matsupy.api.dto.request.user;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GetUserDto {
	@Size(min=1, message="E400003 IDは1以上にせい")
	private final String id;
}
