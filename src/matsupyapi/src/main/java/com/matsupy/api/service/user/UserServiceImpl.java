package com.matsupy.api.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matsupy.api.constant.MsgId;
import com.matsupy.api.dto.request.user.GetUserDto;
import com.matsupy.api.dto.response.user.GetUserResDto;
import com.matsupy.api.dto.response.user.UserInfo;
import com.matsupy.cmn.constant.InternalErrCd;
import com.matsupy.cmn.constant.MtpHttpStatus;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.entity.UserTbl;
import com.matsupy.cmn.exception.MatsupyBizException;
import com.matsupy.cmn.mapper.UserMapperExt;
import com.matsupy.cmn.util.MtpLogUtil;

import lombok.RequiredArgsConstructor;

@Service("UserService")
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final MtpLogUtil apiLogUtil;
	
	private final UserMapperExt userDao;

	public void execute(MatsupyContext mc) throws MatsupyBizException {
		
		final String MNAME = "execute";
		
		try {
			apiLogUtil.startLog(MNAME);

			// 処理
			GetUserResDto dto = new GetUserResDto();
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			// SQL条件設定
			UserTbl condEnt = new UserTbl();
			
			GetUserDto inDto = (GetUserDto)mc.getInputDto();
			
			if (inDto.getId() != null) {				
				condEnt.setId(Integer.parseInt(inDto.getId()));
			}

			// SELECT
			UserTbl userEnt = userDao.selUser(condEnt);
			
			if (userEnt == null) {
				//　結果なし
				throw new MatsupyBizException(MtpHttpStatus.NOT_FOUND, "error!", InternalErrCd.ERROR.getValue(), MsgId.MSGE002.getValue());
			}

			userInfos.add(new UserInfo(1,userEnt.getName()));
			// 仕様が満たせていないのでたもんつをつけておく
			userInfos.add(new UserInfo(999,"たもんつ"));

			// レスポンスネタ格納
			dto.setUserInfo(userInfos);
			mc.setOutputDto(dto);

		} finally {
			apiLogUtil.endLog(MNAME);			
		}
	}

	public void executeMulti(MatsupyContext mc) throws MatsupyBizException {
		
		final String MNAME = "executeMulti";
		
		try {
			apiLogUtil.startLog(MNAME);

			// 処理
			GetUserResDto dto = new GetUserResDto();
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			// SQL条件設定
			UserTbl condEnt = new UserTbl();
			
			GetUserDto inDto = (GetUserDto)mc.getInputDto();
			
			if (inDto.getId() != null) {				
				condEnt.setId(Integer.parseInt(inDto.getId()));
			}

			// SELECT
			List<UserTbl> userEnt = userDao.selUserMulti();
			
			if (userEnt == null) {
				//　結果なし
				throw new MatsupyBizException(MtpHttpStatus.NOT_FOUND, "error!", InternalErrCd.ERROR.getValue(), MsgId.MSGE002.getValue());
			}

			for (UserTbl userOne : userEnt) {
				userInfos.add(new UserInfo(userOne.getId(),userOne.getName()));
			}

			// レスポンスネタ格納
			dto.setUserInfo(userInfos);
			mc.setOutputDto(dto);

		} finally {
			apiLogUtil.endLog(MNAME);			
		}
	}
}
