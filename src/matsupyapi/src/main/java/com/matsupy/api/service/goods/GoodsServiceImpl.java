package com.matsupy.api.service.goods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matsupy.api.constant.MsgId;
import com.matsupy.api.dto.request.goods.GetGoodsDto;
import com.matsupy.api.dto.response.goods.GetGoodsResDto;
import com.matsupy.api.dto.response.goods.GoodsInfo;
import com.matsupy.cmn.constant.ResultCd;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.entity.gen.GoodsTbl;
import com.matsupy.cmn.exception.MatsupyBizException;
import com.matsupy.cmn.mapper.GoodsMapperExt;
import com.matsupy.cmn.util.DateUtil;
import com.matsupy.cmn.util.MtpLogUtil;

import lombok.RequiredArgsConstructor;

@Service("GoodsService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

	private final MtpLogUtil apiLogUtil;

	private final GoodsMapperExt goodsDao;

	@Override
	public void execute(MatsupyContext mc) throws MatsupyBizException {

		final String MNAME = "execute";

		try {
			apiLogUtil.startLog(MNAME);

			// 処理
			GetGoodsResDto dto = new GetGoodsResDto();
			List<GoodsInfo> goods = new ArrayList<>();

			// SQL条件設定
			GoodsTbl condEnt = new GoodsTbl();

			GetGoodsDto inDto = (GetGoodsDto) mc.getInputDto();

			if (inDto.getGoodsId() != null) {
				condEnt.setGoodsId(Integer.parseInt(inDto.getGoodsId()));
			}

			condEnt.setDeletedFlag(inDto.getDeletedFlag());

			// SELECT
			List<GoodsTbl> goodsEnt = goodsDao.selGoods(condEnt);

			if (goodsEnt == null) {
				// 結果なし
				mc.setError(HttpStatus.NOT_FOUND, ResultCd.ERROR, MsgId.MSGE003.getValue());
				throw new MatsupyBizException("error!");
			}

			for (GoodsTbl one : goodsEnt) {
				goods.add(new GoodsInfo(one.getGoodsId(), one.getName(),
						DateUtil.format(one.getCreatedAt()), one.getCreatedBy()));
			}

			// レスポンスネタ格納
			mc.setStatus(HttpStatus.OK);
			mc.setResult(ResultCd.NORMAL);
			dto.setGoodsInfo(goods);
			mc.setOutputDto(dto);

		} finally {
			apiLogUtil.endLog(MNAME);
		}
	}

}
