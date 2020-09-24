/**
 * 
 */
package com.derushan.common;

/**
 * @author Derushan
 * Sep 21, 2020
 */
public class Constants {
		// Common Contant
		public static final String BASE_URI_COMMON = "/api/v1";
		public static final String BASE_URI_AUTH = "/api/v1/auth";

		// HTTP Status Codes
		public static final Integer CODE_HTTP_SUCCESS = 200;
		public static final Integer CODE_HTTP_BAD_REQUEST = 400;
		public static final Integer CODE_HTTP_UNAUTHORIZED = 401;
		public static final Integer CODE_HTTP_FORBBIDEN = 403;
		public static final Integer CODE_HTTP_NOTFOUND = 404;
		public static final Integer CODE_HTTP_METHOD_NOT_ALLOWED = 405;
		public static final Integer CODE_HTTP_EXPECTATION_FAILED = 417;
		public static final Integer CODE_HTTP_INTERNAL_SERVER_ERROR = 500;

		// HTTP Messages
		public static final String MESSAGE_HTTP_SUCCESS = "Success";
		public static final String MESSAGE_HTTP_FAILED = "Failed";
		public static final String MESSAGE_COMMON = "Something went wrong, Please try again!!!";

		// Pagination
		public static final Integer PAGINATION_LIMIT = 10;

		// Roles

		public final static String ROLE_ADMIN = "ROLE_ADMIN";
		public final static String ROLE_USER = "ROLE_USER";

		// JWT
		public final static String KEY_JWT_AUTH_HEADER = "${jwt.auth.header}";
		public final static String KEY_JWT_EXPIRE_HOURS = "${jwt.expire.hours}";
		public final static String KEY_JWT_SECRET = "${jwt.secret}";
}
