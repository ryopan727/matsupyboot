package com.matsupy.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.matsupy.cmn.constant.InternalErrCd;
import com.matsupy.cmn.constant.MtpHttpStatus;
import com.matsupy.cmn.exception.ErrInfo;
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
	public <T> void validation(T req) throws MatsupyBizException {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();

		Set<ConstraintViolation<T>> violations = validator.validate(req);

		List<ErrInfo> errInfolst = new ArrayList<>();

		for (ConstraintViolation<T> violation : violations) {
			// エラーありの場合。
			// String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			String msgId = message.split(" ")[0];

			errInfolst.add(new ErrInfo(InternalErrCd.CHK_ERROR.getValue(), msgId, message));
		}

		if (errInfolst.isEmpty() != true) {
			// エラーあり
			throw new MatsupyBizException(MtpHttpStatus.BAD_REQUEST,
					MtpHttpStatus.BAD_REQUEST.getReason(), errInfolst);
		}

	}
}
