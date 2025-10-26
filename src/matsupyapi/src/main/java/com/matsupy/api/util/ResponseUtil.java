package com.matsupy.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.matsupy.api.constant.MsgId;
import com.matsupy.api.dto.response.MatsupyApiResponse;
import com.matsupy.api.dto.response.ResponseErrInfo;
import com.matsupy.cmn.constant.InternalErrCd;
import com.matsupy.cmn.constant.MtpHttpStatus;
import com.matsupy.cmn.exception.ErrInfo;
import com.matsupy.cmn.exception.MatsupyBizException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ResponseUtil {

	private final MessageSource messageSource;

	public <T> ResponseEntity<MatsupyApiResponse<?>> createResponse(T dto, MtpHttpStatus status,
			InternalErrCd result) {

		MatsupyApiResponse<T> resApi = new MatsupyApiResponse<>();

		if (dto != null) {
			resApi.setData(dto);
		}

		resApi.setResult(result.getValue());
		return ResponseEntity.status(status.getCode()).body(resApi);
	}

	/**
	 * 例外からエラーレスポンスを作成。
	 */
	public ResponseEntity<MatsupyApiResponse<?>> createErrResponse(MatsupyBizException me) {
		List<ResponseErrInfo> errList = new ArrayList<>();

		for (ErrInfo errInfo : me.getErrInfo()) {
			ResponseErrInfo resErrInfo = new ResponseErrInfo();
			resErrInfo.setErrCd(errInfo.getErrCd());

			String msg = errInfo.getMessage();
			if (!"".equals(errInfo.getErrCd()) && "".equals(errInfo.getMessage())) {
				// メッセージがすでに入っている場合は変換しない
				msg = messageSource.getMessage(errInfo.getMessage(), null, Locale.JAPANESE);
			}
			resErrInfo.setMessage(msg);

			errList.add(resErrInfo);
		}

		MatsupyApiResponse<String> resApi = new MatsupyApiResponse<>();
		resApi.setResult(me.getErrCd());

		if (!errList.isEmpty()) {
			resApi.setErrInfo(errList);
		}

		return ResponseEntity.status(me.getStatus()).body(resApi);
	}

	public ResponseEntity<MatsupyApiResponse<?>> createErrResponse() {
		ResponseErrInfo errInfo = new ResponseErrInfo();
		errInfo.setErrCd(InternalErrCd.ERROR.getValue());

		// 予期せぬエラー。
		String msg = messageSource.getMessage(MsgId.MSGE001.getValue(), null, Locale.JAPANESE);
		errInfo.setMessage(msg);

		MatsupyApiResponse<String> resApi = new MatsupyApiResponse<>();
		resApi.setResult(InternalErrCd.ERROR.getValue());
		resApi.setErrInfo(List.of(errInfo));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resApi);
	}

}
