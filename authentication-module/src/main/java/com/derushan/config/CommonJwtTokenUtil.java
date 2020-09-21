/**
 * 
 */
package com.derushan.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.derushan.entities.User;
import com.derushan.exception.CommonAuthenticationExceptionHandler;
import com.derushan.services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * @author Derushan Sep 21, 2020
 */
@Component
public class CommonJwtTokenUtil {

	@Value("${com.derushan.jwt.secret}")
	private String jwtSecret;

	@Value("${com.derushan.jwt.expire.milliseconds}")
	private Long tokenValidity;

	@Autowired
	private UserService userService;

	public User getUser(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			User user = userService.getOneByEmail(body.getSubject());
			if (user.getToken() != null && user.getToken().equals(token)) {
				return user;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}

	public String generateToken(User user) {
		Claims claims = Jwts.claims().setSubject(user.getEmail());
		claims.put("roles", user.getRoles());
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + tokenValidity;
		Date exp = new Date(expMillis);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public void validateToken(final String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new CommonAuthenticationExceptionHandler("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new CommonAuthenticationExceptionHandler("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new CommonAuthenticationExceptionHandler("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new CommonAuthenticationExceptionHandler("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new CommonAuthenticationExceptionHandler("JWT claims string is empty.");
		}
	}
}
