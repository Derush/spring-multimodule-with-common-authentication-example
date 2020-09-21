/**
 * 
 */
package com.derushan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.derushan.common.Constants;
import com.derushan.exception.CommonAccessDeniedHandler;
import com.derushan.services.authentication.CommonUserDetailsService;

/**
 * @author Derushan Sep 21, 2020
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CommonSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private CommonUserDetailsService commonUserDetailsService;

	@Autowired
	private CommonJwtTokenUtil commonJwtTokenUtil;

	@Autowired
	private CommonAuthenticationEntryPoint commonAuthenticationEntryPoint;

	@Autowired
	private CommonAccessDeniedHandler commonAccessDeniedHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", Constants.BASE_URI_AUTH + "/**");
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(commonUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/v2/api-docs").permitAll()
				.antMatchers(Constants.BASE_URI_AUTH + "/**").permitAll().anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(commonAuthenticationEntryPoint)
				.accessDeniedHandler(commonAccessDeniedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(new CommonJwtAuthenticationFilter(authenticationManagerBean(), commonJwtTokenUtil,
				commonUserDetailsService), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public RegistrationBean jwtAuthFilterRegister(CommonJwtAuthenticationFilter filter) {
		FilterRegistrationBean<CommonJwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(filter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
