package com.matsupy.api.controller.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matsupy.api.constant.MsgId;
import com.matsupy.api.dto.request.user.GetExtUserDto;
import com.matsupy.api.dto.request.user.GetUserDto;
import com.matsupy.api.dto.request.user.InsUserDto;
import com.matsupy.api.dto.response.MatsupyApiResponse;
import com.matsupy.api.dto.response.user.GetUserResDto;
import com.matsupy.api.service.user.InsUserService;
import com.matsupy.api.service.user.UserService;
import com.matsupy.api.util.ResponseUtil;
import com.matsupy.api.util.ValidationUtil;
import com.matsupy.cmn.constant.ResultCd;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.exception.MatsupyBizException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	@Qualifier("UserServiceImpl")
	private final UserService userService;

	@Qualifier("InsUserServiceImpl")
	private final InsUserService insUserService;

	private final ResponseUtil responseUtil;

	private final ValidationUtil validationUtil;

	@GetMapping("/api/users")
	public ResponseEntity<MatsupyApiResponse<?>> getUser(@ModelAttribute GetUserDto req) {
		MatsupyContext mc = new MatsupyContext();

		try {

			// バリデーション実行
			validationUtil.validation(mc, req);

			if (req != null) {
				mc.setInputDto(req);
			}

			// 検索
			if (req.getId() == null) {
				userService.executeMulti(mc);
			} else {
				userService.execute(mc);
			}

			return responseUtil.createResponse(mc, GetUserResDto.class);
		} catch (MatsupyBizException me) {
			me.printStackTrace();

			return responseUtil.createErrResponse(mc);
		} catch (Throwable e) {
			e.printStackTrace();

			return responseUtil.createErrResponse(mc);
		} finally {

		}

	}

	@PostMapping("/api/users")
	public ResponseEntity<MatsupyApiResponse<?>> insUser(@RequestBody InsUserDto req) {
		MatsupyContext mc = new MatsupyContext();

		try {

			validationUtil.validation(mc, req);

			mc.setInputDto(req);

			// 登録
			insUserService.execute(mc);

			return responseUtil.createResponse(mc, null);
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

	/**
	 * バリデーションエラーおためし
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/api/userall")
	public ResponseEntity<MatsupyApiResponse<?>> chkUser(@RequestBody GetExtUserDto req) {
		MatsupyContext mc = new MatsupyContext();

		try {

			validationUtil.validation(mc, req);

			mc.setInputDto(req);

			mc.setResult(ResultCd.NORMAL);
			mc.setStatus(HttpStatus.OK);
			return responseUtil.createResponse(mc, null);
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
