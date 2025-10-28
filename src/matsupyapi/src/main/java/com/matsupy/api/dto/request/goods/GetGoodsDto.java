package com.matsupy.api.dto.request.goods;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetGoodsDto {
	@Min(value = 11, message = "E400004:グッズID:11")
	private String goodsId;

	@NotNull(message = "E400002:廃棄フラグ")
	private Boolean deletedFlag;
}
