package com.matsupy.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.matsupy.api.dto.response.MatsupyApiResponse;
import com.matsupy.api.dto.response.ResponseErrInfo;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.exception.ErrMsg;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ResponseUtil {

	private final MessageSource messageSource;

	public <T> ResponseEntity<MatsupyApiResponse<?>> createResponse(MatsupyContext mc,
			Class<T> clazz) {

		MatsupyApiResponse<T> resApi = new MatsupyApiResponse<>();

		if (mc.getOutputDto() != null) {
			T obj = clazz.cast(mc.getOutputDto());
			resApi.setData(obj);
		}

		resApi.setResult(mc.getResult().getValue());
		return new ResponseEntity<>(resApi, mc.getResHeader(), mc.getStatus());
	}

	/**
	 * エラーレスポンスを作成。
	 */
	public ResponseEntity<MatsupyApiResponse<?>> createErrResponse(MatsupyContext mc) {
		List<ResponseErrInfo> errList = new ArrayList<>();

		for (ErrMsg errMsg : mc.getErrMsgLst()) {
			ResponseErrInfo resErrInfo = new ResponseErrInfo();

			String msg = errMsg.getMessage();
			if (!"".equals(errMsg.getMsgId()) && "".equals(msg)) {
				// メッセージがすでに入っている場合は変換しない
				msg = messageSource.getMessage(errMsg.getMsgId(), null, Locale.JAPANESE);
			}
			resErrInfo.setMessage(msg);
			resErrInfo.setMsgId(errMsg.getMsgId());

			errList.add(resErrInfo);
		}

		MatsupyApiResponse<String> resApi = new MatsupyApiResponse<>();
		resApi.setResult(mc.getResult().getValue());

		if (!errList.isEmpty()) {
			resApi.setErrInfo(errList);
		}

		return new ResponseEntity<MatsupyApiResponse<?>>(resApi, mc.getResHeader(), mc.getStatus());
	}

}
