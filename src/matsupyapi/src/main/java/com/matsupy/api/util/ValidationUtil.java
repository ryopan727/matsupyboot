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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class ValidationUtil {

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
			// String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			String msgId = message.split(" ")[0];

			errInfolst.add(new ErrMsg(msgId, message));
		}

		if (errInfolst.isEmpty() != true) {
			// エラーあり
			mc.setError(HttpStatus.BAD_REQUEST, ResultCd.CHK_ERROR, errInfolst);
			throw new MatsupyBizException("validate!");
		}

	}
}
