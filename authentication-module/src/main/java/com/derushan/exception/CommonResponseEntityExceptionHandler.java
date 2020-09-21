/**
 * 
 */
package com.derushan.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.derushan.common.CommonResponse;
import com.derushan.common.Constants;

/**
 * @author Derushan Sep 21, 2020
 */
@ControllerAdvice
@RestController
public class CommonResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg(errors.get(0))
				.statusCode(Constants.CODE_HTTP_BAD_REQUEST).error(errors.toString()).entity();

	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg(ex.getLocalizedMessage())
				.statusCode(Constants.CODE_HTTP_METHOD_NOT_ALLOWED).error(ex.toString()).entity();

	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg(ex.getLocalizedMessage())
				.statusCode(Constants.CODE_HTTP_INTERNAL_SERVER_ERROR).error(ex.toString()).entity();

	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg(ex.getLocalizedMessage())
				.statusCode(Constants.CODE_HTTP_NOTFOUND).error(ex.toString()).entity();

	}

}
