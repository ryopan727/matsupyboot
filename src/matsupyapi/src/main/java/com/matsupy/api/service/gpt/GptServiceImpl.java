package com.matsupy.api.service.gpt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.matsupy.api.dto.request.gpt.GptChatDto;
import com.matsupy.api.dto.response.gpt.GptChatResDto;
import com.matsupy.cmn.constant.ResultCd;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.exception.MatsupyBizException;
import com.matsupy.cmn.util.MtpLogUtil;

import lombok.RequiredArgsConstructor;

@Service("GptServiceImpl")
@RequiredArgsConstructor
public class GptServiceImpl implements GptService {

	private final ChatClient chatClient;

	private final MtpLogUtil apiLogUtil;

	@Override
	public void execute(MatsupyContext mc) throws MatsupyBizException {

		final String MNAME = "execute";

		try {
			apiLogUtil.startLog(MNAME);

			GptChatDto inDto = (GptChatDto) mc.getInputDto();

			String answer = chatClient.prompt()
					.user(inDto.getMessage())
					.call()
					.content();

			GptChatResDto resDto = new GptChatResDto();
			resDto.setAnswer(answer);

			mc.setStatus(HttpStatus.OK);
			mc.setResult(ResultCd.NORMAL);
			mc.setOutputDto(resDto);

		} finally {
			apiLogUtil.endLog(MNAME);
		}
	}

}
