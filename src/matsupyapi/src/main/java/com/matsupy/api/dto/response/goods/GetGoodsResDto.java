package com.matsupy.api.dto.response.goods;

import java.util.List;

import lombok.Data;

@Data
public class GetGoodsResDto {
	private List<GoodsInfo> goodsInfo;
}
