/**
 * 
 */
package com.derushan.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.derushan.entities.User;
import com.derushan.exception.CommonAuthenticationExceptionHandler;
import com.derushan.services.authentication.CommonUserDetailsService;

/**
 * @author Derushan Sep 21, 2020
 */
@Component
public class CommonJwtAuthenticationFilter extends BasicAuthenticationFilter {

	private CommonJwtTokenUtil commonJwtTokenUtil;

	private CommonUserDetailsService commonUserDetailsService;

	/**
	 * @param authenticationManager
	 * @param apptimusUserDetailsService2
	 */
	public CommonJwtAuthenticationFilter(AuthenticationManager authenticationManager,
			CommonJwtTokenUtil commonJwtTokenUtil, CommonUserDetailsService commonUserDetailsService) {
		super(authenticationManager);
		this.commonJwtTokenUtil = commonJwtTokenUtil;
		this.commonUserDetailsService = commonUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		System.out.println(header);
		if (header == null || !header.startsWith("Bearer")) {
			throw new CommonAuthenticationExceptionHandler("No JWT token found in the request headers");
		}

		String token = header.substring(7);

		commonJwtTokenUtil.validateToken(token);
		User user = commonJwtTokenUtil.getUser(token);
		UserDetails userDetails = commonUserDetailsService.loadUserByUsername(user.getEmail());

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}

		filterChain.doFilter(request, response);
	}
}
