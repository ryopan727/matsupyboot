package com.matsupy.api.service.user;

import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.exception.MatsupyBizException;

public interface UserService {
	public void execute(MatsupyContext mc) throws MatsupyBizException;

	public void executeMulti(MatsupyContext mc) throws MatsupyBizException;
}
