package com.matsupy.api.dto.response.user;

import java.util.List;

import lombok.Data;

@Data
public class GetUserResDto {
	private List<UserInfo> userInfo;
}
