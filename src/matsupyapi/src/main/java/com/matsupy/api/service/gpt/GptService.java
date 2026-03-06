package com.matsupy.api.service.gpt;

import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.exception.MatsupyBizException;

public interface GptService {
	void execute(MatsupyContext mc) throws MatsupyBizException;
}
