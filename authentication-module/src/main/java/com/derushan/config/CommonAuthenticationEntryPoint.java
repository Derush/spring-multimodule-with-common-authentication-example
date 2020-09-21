/**
 * 
 */
package com.derushan.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.derushan.common.CommonResponse;
import com.derushan.common.Constants;

/**
 * @author Derushan Sep 21, 2020
 */
@Component
public class CommonAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		String message = CommonResponse.builder().status(Constants.MESSAGE_HTTP_FAILED).msg("Unauthorize")
				.statusCode(Constants.CODE_HTTP_UNAUTHORIZED).error(authException.toString()).json();
		response.setStatus(Constants.CODE_HTTP_UNAUTHORIZED);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(message);

	}
}
