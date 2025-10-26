package com.matsupy.api.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matsupy.api.constant.MsgId;
import com.matsupy.api.dto.request.user.InsUserDto;
import com.matsupy.cmn.constant.InternalErrCd;
import com.matsupy.cmn.constant.MtpHttpStatus;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.entity.UserTbl;
import com.matsupy.cmn.exception.MatsupyBizException;
import com.matsupy.cmn.mapper.UserTblMapper;
import com.matsupy.cmn.util.MtpLogUtil;

import lombok.RequiredArgsConstructor;

@Service("InsUserService")
@Transactional(readOnly=false)
@RequiredArgsConstructor
public class InsUserServiceImpl implements InsUserService{
	
	private final MtpLogUtil apiLogUtil;
	
	private final UserTblMapper userDao;

	public void execute(MatsupyContext mc) throws MatsupyBizException {
		
		final String MNAME = "execute";
		
		try {
			apiLogUtil.startLog(MNAME);

			// SQL条件設定
			InsUserDto inDto = (InsUserDto)mc.getInputDto();
			
			UserTbl condEnt = new UserTbl();
			
			if (inDto.getId() != null) {				
				condEnt.setId(inDto.getId());
				condEnt.setName(inDto.getName());
			}

			// INSERT
			int cnt = userDao.insert(condEnt);
			
			if (cnt == 0) {
				//　結果なし
				throw new MatsupyBizException(MtpHttpStatus.INTERNAL_SERVER_ERROR, "error!", InternalErrCd.ERROR.getValue(), MsgId.MSGE002.getValue());
			}

		} finally {
			apiLogUtil.endLog(MNAME);			
		}
	}


}
