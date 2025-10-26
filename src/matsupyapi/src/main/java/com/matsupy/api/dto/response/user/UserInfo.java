package com.matsupy.api.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
	private Integer id;
	private String name;
}
