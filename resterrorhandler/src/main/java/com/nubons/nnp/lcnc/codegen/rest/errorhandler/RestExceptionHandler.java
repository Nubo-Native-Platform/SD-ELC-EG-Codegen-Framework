package com.nubons.nnp.lcnc.codegen.rest.errorhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nubons.nnp.lcnc.codegen.errorhandler.exception.BaseException;
import com.nubons.nnp.lcnc.codegen.rest.errorhandler.model.RestError;
import com.nubons.nnp.lcnc.codegen.rest.errorhandler.resolver.HTTPStatusResolver;

import lombok.extern.slf4j.Slf4j;

/**
 * A generic implementation of RestExceptionHandler.
 * 
 * @author Gourab Guha (egouguh)
 * @since May, 25 2021
 *
 */
@ControllerAdvice
@Slf4j
@Component
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	protected HTTPStatusResolver statusResolver;

	@Value("${unhandledErrorMessage}")
	protected String unhandledErrorMessage;

	@Value("${unhandledErrorCode}")
	protected String unhandledErrorCode;

	/**
	 * A generic implementation to handle BaseException or its sub-type in a Rest
	 * service. Also this method returns an empty HTTPHeader among other parameters.
	 * 
	 * @param BaseException
	 * @param WebRequest
	 * @return ResponseEntity<Object>
	 */

	@ExceptionHandler(value = { BaseException.class })
	protected ResponseEntity<Object> handleBaseException(final BaseException ex, final WebRequest request) {
		log.error(ex.getLocalizedMessage(), ex);

		ResponseEntity<Object> restEntityFinal = null;

		try {

			Object restEntity = createRestEntityFromException(ex);
			String httpStatusCode = getHttpStatusCode(restEntity);
			HttpStatus httpStatus = statusResolver.resolveHttpStatusFromCode(httpStatusCode);
			restEntityFinal = handleExceptionInternal(ex, restEntity, new HttpHeaders(), httpStatus, request);

		} catch (Exception excep) {
			log.error(excep.getLocalizedMessage(), excep);
			restEntityFinal = handleExceptionInternal(ex, createRestEntityForUnhandledException(), new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR, request);
		}
		return restEntityFinal;
	}

	/**
	 * This method returns a RestError object from BaseException. In case a specific
	 * implementation use different mapping between BaseException and RestError or
	 * use a different RestError object, then this method has to be overridden in
	 * the specific subclass of RestExceptionHandler.
	 * 
	 * @param BaseException
	 * @return Object, the RestError object
	 */
	protected Object createRestEntityFromException(final BaseException ex) {

		return new RestError().code(ex.getCode()).message(ex.getMessage()).reason(ex.getReason())
				.status(ex.getStatus());
	}

	/**
	 * This method returns a RestError when an exception occurs in this exception
	 * handler itself.
	 * 
	 * @return RestError
	 */
	protected RestError createRestEntityForUnhandledException() {
		return new RestError().message(unhandledErrorMessage).code(unhandledErrorCode);
	}

	/**
	 * This method returns HTTPStatusCode as string from the input a RestError
	 * object. In a specific RestExceptionHandler use a different entity model to
	 * represent error message other-than RestError then this method has to be
	 * overridden in the specific subclass of RestExceptionHandler.
	 * 
	 * @param restEntity
	 * @return String, HTTPStatusCode as String
	 * @throws IllegalArgumentException when restEntity (input param) is not an
	 *                                  instance of RestError
	 */
	protected String getHttpStatusCode(final Object restEntity) {

		if (restEntity instanceof RestError) {
			return ((RestError) restEntity).getStatus();
		} else {

			final String errMsg = "Illegal object type passed to the method getHttpStatusCode, found -> "
					+ restEntity.getClass() + ", required -> " + RestError.class
					+ " Solution -> Override this method in corresponding ExceptionHandler";
			log.error(errMsg);
			throw new IllegalArgumentException(errMsg);
		}

	}

}
