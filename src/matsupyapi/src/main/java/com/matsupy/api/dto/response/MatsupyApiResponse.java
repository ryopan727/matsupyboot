package com.matsupy.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatsupyApiResponse<T> {
	private T data;
	private String result;
	private List<ResponseErrInfo> errInfo;
}
