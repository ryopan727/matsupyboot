package com.matsupy.api.dto.response.goods;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsInfo {
	private Integer goodsId;
	private String name;
	private String createdAt;
	private String createdBy;
}
