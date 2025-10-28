package com.matsupy.api.controller.goods;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matsupy.api.constant.MsgId;
import com.matsupy.api.dto.request.goods.GetGoodsDto;
import com.matsupy.api.dto.response.MatsupyApiResponse;
import com.matsupy.api.dto.response.goods.GetGoodsResDto;
import com.matsupy.api.service.goods.GoodsService;
import com.matsupy.api.util.ResponseUtil;
import com.matsupy.api.util.ValidationUtil;
import com.matsupy.cmn.constant.ResultCd;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.exception.MatsupyBizException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GoodsController {

	@Qualifier("GoodsServiceImpl")
	private final GoodsService goodsService;

	private final ResponseUtil responseUtil;

	private final ValidationUtil validationUtil;

	@PostMapping("/api/goods")
	public ResponseEntity<MatsupyApiResponse<?>> getGoods(@RequestBody GetGoodsDto req) {
		MatsupyContext mc = new MatsupyContext();

		try {

			// バリデーション実行
			validationUtil.validation(mc, req);

			if (req != null) {
				mc.setInputDto(req);
			}

			// 検索
			goodsService.execute(mc);

			return responseUtil.createResponse(mc, GetGoodsResDto.class);
		} catch (MatsupyBizException me) {
			me.printStackTrace();

			return responseUtil.createErrResponse(mc);
		} catch (Throwable e) {
			e.printStackTrace();
			mc.setError(HttpStatus.INTERNAL_SERVER_ERROR, ResultCd.ERROR, MsgId.MSGE001.getValue());
			return responseUtil.createErrResponse(mc);
		} finally {

		}

	}

}
