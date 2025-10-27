package com.matsupy.api.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matsupy.api.constant.MsgId;
import com.matsupy.api.dto.request.user.GetUserDto;
import com.matsupy.api.dto.response.user.GetUserResDto;
import com.matsupy.api.dto.response.user.UserInfo;
import com.matsupy.cmn.constant.ResultCd;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.entity.gen.UserTbl;
import com.matsupy.cmn.exception.MatsupyBizException;
import com.matsupy.cmn.mapper.UserMapperExt;
import com.matsupy.cmn.util.MtpLogUtil;

import lombok.RequiredArgsConstructor;

@Service("UserService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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

			GetUserDto inDto = (GetUserDto) mc.getInputDto();

			if (inDto.getId() != null) {
				condEnt.setId(Integer.parseInt(inDto.getId()));
			}

			// SELECT
			UserTbl userEnt = userDao.selUser(condEnt);

			if (userEnt == null) {
				// 結果なし
				mc.setError(HttpStatus.NOT_FOUND, ResultCd.ERROR, MsgId.MSGE003.getValue());
				throw new MatsupyBizException("error!");
			}

			userInfos.add(new UserInfo(userEnt.getId(), userEnt.getName()));

			// レスポンスネタ格納
			mc.setStatus(HttpStatus.OK);
			mc.setResult(ResultCd.NORMAL);
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

			GetUserDto inDto = (GetUserDto) mc.getInputDto();

			if (inDto.getId() != null) {
				condEnt.setId(Integer.parseInt(inDto.getId()));
			}

			// SELECT
			List<UserTbl> userEnt = userDao.selUserMulti();

			if (userEnt == null) {
				// 結果なし
				mc.setError(HttpStatus.NOT_FOUND, ResultCd.ERROR, MsgId.MSGE003.getValue());
				throw new MatsupyBizException("error!!");
			}

			for (UserTbl userOne : userEnt) {
				userInfos.add(new UserInfo(userOne.getId(), userOne.getName()));
			}

			// レスポンスネタ格納
			mc.setStatus(HttpStatus.OK);
			mc.setResult(ResultCd.NORMAL);
			dto.setUserInfo(userInfos);
			mc.setOutputDto(dto);

		} finally {
			apiLogUtil.endLog(MNAME);
		}
	}
}
