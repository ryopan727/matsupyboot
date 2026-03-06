package com.matsupy.api.controller.gpt;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matsupy.api.constant.MsgId;
import com.matsupy.api.dto.request.gpt.GptChatDto;
import com.matsupy.api.dto.response.MatsupyApiResponse;
import com.matsupy.api.dto.response.gpt.GptChatResDto;
import com.matsupy.api.service.gpt.GptService;
import com.matsupy.api.util.ResponseUtil;
import com.matsupy.api.util.ValidationUtil;
import com.matsupy.cmn.constant.ResultCd;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.exception.MatsupyBizException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GptController {

	@Qualifier("GptServiceImpl")
	private final GptService gptService;

	private final ResponseUtil responseUtil;

	private final ValidationUtil validationUtil;

	@PostMapping("/api/chat")
	public ResponseEntity<MatsupyApiResponse<?>> chat(@RequestBody GptChatDto req) {
		MatsupyContext mc = new MatsupyContext();

		try {

			// バリデーション実行
			validationUtil.validation(mc, req);

			mc.setInputDto(req);

			// GPT呼び出し
			gptService.execute(mc);

			return responseUtil.createResponse(mc, GptChatResDto.class);
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
