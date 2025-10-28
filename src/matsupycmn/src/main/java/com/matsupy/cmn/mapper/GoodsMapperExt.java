package com.matsupy.cmn.mapper;

import java.util.List;

import com.matsupy.cmn.entity.gen.GoodsTbl;

/**
 * Goods拡張Mapper
 */
public interface GoodsMapperExt {

	public List<GoodsTbl> selGoods(GoodsTbl entity);

}
