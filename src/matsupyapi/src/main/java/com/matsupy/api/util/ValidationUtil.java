package com.matsupy.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.matsupy.cmn.constant.ResultCd;
import com.matsupy.cmn.dto.MatsupyContext;
import com.matsupy.cmn.exception.ErrMsg;
import com.matsupy.cmn.exception.MatsupyBizException;
import com.matsupy.cmn.util.MtpLogUtil;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

	private final MtpLogUtil mtpLogUtil;

	/**
	 * バリデーション処理
	 * 
	 * @param <T>
	 * @param req
	 * @throws MatsupyBizException
	 */
	public <T> void validation(MatsupyContext mc, T req) throws MatsupyBizException {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();

		Set<ConstraintViolation<T>> violations = validator.validate(req);

		List<ErrMsg> errInfolst = new ArrayList<>();

		for (ConstraintViolation<T> violation : violations) {
			// エラーありの場合。
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();

			errInfolst.add(createErrMsg(message, field));
		}

		if (errInfolst.isEmpty() != true) {
			// エラーあり
			mc.setError(HttpStatus.BAD_REQUEST, ResultCd.CHK_ERROR, errInfolst);
			throw new MatsupyBizException("validate!");
		}

	}

	private ErrMsg createErrMsg(String message, String field) {
		final String NM = "createErrMsg";

		ErrMsg errMsg = null;

		try {
			mtpLogUtil.startLog(NM);

			if (message == null || "".equals(message)) {
				// 未定義の場合、予期せぬエラー
				return new ErrMsg("E400001", field, new Object[0]);
			}

			// messageを分解する
			String[] mstrs = message.split(":");
			String msgId = mstrs[0];
			List<Object> prm = new ArrayList<>();

			for (int i = 1; i < mstrs.length; i++) {
				prm.add(mstrs[i]);
			}

			Object[] prmObj = prm.toArray();

			if (prmObj.length == 0) {
				prmObj = null;
			}

			errMsg = new ErrMsg(msgId, field, prmObj);

			return errMsg;
		} finally {
			mtpLogUtil.endLog(NM);
		}

	}
}
