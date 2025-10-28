package com.matsupy.api.service.goods;

import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.exception.MatsupyBizException;

public interface GoodsService {
	void execute(MatsupyContext mc) throws MatsupyBizException;
}
