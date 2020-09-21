/**
 * 
 */
package com.derushan.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.derushan.common.CommonResponse;
import com.derushan.common.Constants;

/**
 * @author Derushan Sep 21, 2020
 */
@Component
public class CommonAccessDeniedHandler implements AccessDeniedHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String message = CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg("Access Denied")
				.statusCode(Constants.CODE_HTTP_FORBBIDEN).error(accessDeniedException.toString()).json();
		response.setStatus(Constants.CODE_HTTP_FORBBIDEN);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(message);
	}
}
