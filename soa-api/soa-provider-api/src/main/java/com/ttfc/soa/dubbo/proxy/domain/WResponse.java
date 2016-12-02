/**
 * Copyright 1999-2014 dangdang.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ttfc.soa.dubbo.proxy.domain;

/**
 * @author lishen
 */

public class WResponse {
	private WResult _result = null;

	public WResponse() {
	}

	public WResponse(WResult result) {
		this._result = result;
	}

	public WResponse msg(String message) {
		this._result.setMsg(message);
		return this;
	}

	public WResponse entity(Object obj) {
		this._result.setEntity(obj);
		return this;
	}

	public static WResponse success(Action action) {
		WResult result = new WResult();
		result.setIstrue(true);
		result.setStatus(action.getStatusCode());
		result.setMsg(action.getReasonPhrase());
		WResponse res = new WResponse(result);

		return res;
	}

	public static WResponse fail(Action action) {
		WResult result = new WResult();
		result.setIstrue(false);
		result.setStatus(action.getStatusCode());
		WResponse res = new WResponse(result);

		return res;
	}

	public WResult build() {
		return this._result;
	}

	/**
	 * Base interface for statuses used in responses.
	 *
	 * @since 1.1
	 */
	public interface ActionType {

		/**
		 * Get the associated status code.
		 *
		 * @return the status code.
		 */
		public int getStatusCode();

		/**
		 * Get the class of status code.
		 *
		 * @return the class of status code.
		 */
		public Action.Family getFamily();

		/**
		 * Get the reason phrase.
		 *
		 * @return the reason phrase.
		 */
		public String getReasonPhrase();
	}

	/**
	 * Commonly used status codes defined by HTTP, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10">HTTP/1.1* documentation</a>} 
	 * for the complete list. Additional status codes can be added by applications by creating an implementation of
	 * {@link StatusType}.
	 */
	public enum Action implements ActionType {

		/**
		 * 200 OK
		 */
		OK(200, "OK"),
		/**
		 * 201 Created, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.2">HTTP/1.1 documentation</a>}.
		 */
		CREATED(201, "Created"),
		/**
		 * 202 Accepted
		 */
		ACCEPTED(202, "Accepted"),
		/**
		 * 204 No Content, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.5">HTTP/1.1 documentation</a>}.
		 */
		NO_CONTENT(204, "No Content"),
		/**
		 * 205 Reset Content, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.6">HTTP/1.1 documentation</a>}.
		 *
		 * @since 2.0
		 */
		RESET_CONTENT(205, "Reset Content"),
		/**
		 * 206 Reset Content, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.7">HTTP/1.1 documentation</a>}.
		 *
		 * @since 2.0
		 */
		PARTIAL_CONTENT(206, "Partial Content"),
		/**
		 * 301 Moved Permanently, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.2">HTTP/1.1 documentation</a>}.
		 */
		MOVED_PERMANENTLY(301, "Moved Permanently"),
		/**
		 * 302 Found, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.3">HTTP/1.1 documentation</a>}.
		 *
		 * @since 2.0
		 */
		FOUND(302, "Found"),
		/**
		 * 303 See Other, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.4">HTTP/1.1 documentation</a>}.
		 */
		SEE_OTHER(303, "See Other"),
		/**
		 * 304 Not Modified, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.5">HTTP/1.1 documentation</a>}.
		 */
		NOT_MODIFIED(304, "Not Modified"),
		/**
		 * 305 Use Proxy, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.6">HTTP/1.1 documentation</a>}.
		 *
		 * @since 2.0
		 */
		USE_PROXY(305, "Use Proxy"),
		/**
		 * 307 Temporary Redirect, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.3.8">HTTP/1.1 documentation</a>}.
		 */
		TEMPORARY_REDIRECT(307, "Temporary Redirect"),
		/**
		 * 400 Bad Request, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.1">HTTP/1.1 documentation</a>}.
		 */
		BAD_REQUEST(400, "Bad Request"),
		/**
		 * 401 Unauthorized, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.2">HTTP/1.1 documentation</a>}.
		 */
		UNAUTHORIZED(401, "Unauthorized"),
		/**
		 * 402 Payment Required, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.3">HTTP/1.1 documentation</a>}.
		 *
		 * @since 2.0
		 */
		PAYMENT_REQUIRED(402, "Payment Required"),
		/**
		 * 403 Forbidden, see {@link <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.4.4">HTTP/1.1 documentation</a>}.
		 */
		FORBIDDEN(403, "Forbidden"),
		/**
		 * 404 Not Found
		 */
		NOT_FOUND(404, "Not Found"),
		/**
		 * 405 Method Not Allowed
		 *
		 * @since 2.0
		 */
		METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
		/**
		 * 406 Not Acceptable
		 */
		NOT_ACCEPTABLE(406, "Not Acceptable"),
		/**
		 * 407 Proxy Authentication Required
		 *
		 * @since 2.0
		 */
		PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
		/**
		 * 408 Request Timeout
		 *
		 * @since 2.0
		 */
		REQUEST_TIMEOUT(408, "Request Timeout"),
		/**
		 * 409 Conflict
		 */
		CONFLICT(409, "Conflict"),
		/**
		 * 410 Gone
		 */
		GONE(410, "Gone"),
		/**
		 * 411 Length Required
		 *
		 * @since 2.0
		 */
		LENGTH_REQUIRED(411, "Length Required"),
		/**
		 * 412 Precondition Failed
		 */
		PRECONDITION_FAILED(412, "Precondition Failed"),
		/**
		 * 413 Request Entity Too Large
		 *
		 * @since 2.0
		 */
		REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
		/**
		 * 414 Request-URI Too Long
		 *
		 * @since 2.0
		 */
		REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),
		/**
		 * 415 Unsupported Media Type
		 */
		UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
		/**
		 * 416 Requested Range Not Satisfiable
		 *
		 * @since 2.0
		 */
		REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
		/**
		 * 417 Expectation Failed
		 *
		 * @since 2.0
		 */
		EXPECTATION_FAILED(417, "Expectation Failed"),
		/**
		 * 500 Internal Server Error
		 */
		INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
		/**
		 * 501 Not Implemented
		 *
		 * @since 2.0
		 */
		NOT_IMPLEMENTED(501, "Not Implemented"),
		/**
		 * 502 Bad Gateway
		 *
		 * @since 2.0
		 */
		BAD_GATEWAY(502, "Bad Gateway"),
		/**
		 * 503 Service Unavailable
		 */
		SERVICE_UNAVAILABLE(503, "Service Unavailable"),
		/**
		 * 504 Gateway Timeout
		 *
		 * @since 2.0
		 */
		GATEWAY_TIMEOUT(504, "Gateway Timeout"),
		/**
		 * 505 HTTP Version Not Supported
		 *
		 * @since 2.0
		 */
		HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported");
		private final int code;
		private final String reason;
		private final Family family;

		/**
		 * An enumeration representing the class of status code. Family is used
		 * here since class is overloaded in Java.
		 */
		public enum Family {

			/**
			 * {@code 1xx} HTTP status codes.
			 */
			INFORMATIONAL,
			/**
			 * {@code 2xx} HTTP status codes.
			 */
			SUCCESSFUL,
			/**
			 * {@code 3xx} HTTP status codes.
			 */
			REDIRECTION,
			/**
			 * {@code 4xx} HTTP status codes.
			 */
			CLIENT_ERROR,
			/**
			 * {@code 5xx} HTTP status codes.
			 */
			SERVER_ERROR,
			/**
			 * Other, unrecognized HTTP status codes.
			 */
			OTHER;

			/**
			 * Get the response status family for the status code.
			 *
			 * @param statusCode
			 *            response status code to get the family for.
			 * @return family of the response status code.
			 */
			public static Family familyOf(final int statusCode) {
				switch (statusCode / 100) {
				case 1:
					return Family.INFORMATIONAL;
				case 2:
					return Family.SUCCESSFUL;
				case 3:
					return Family.REDIRECTION;
				case 4:
					return Family.CLIENT_ERROR;
				case 5:
					return Family.SERVER_ERROR;
				default:
					return Family.OTHER;
				}
			}
		}

		Action(final int statusCode, final String reasonPhrase) {
			this.code = statusCode;
			this.reason = reasonPhrase;
			this.family = Family.familyOf(statusCode);
		}

		/**
		 * Get the class of status code.
		 *
		 * @return the class of status code.
		 */

		public Family getFamily() {
			return family;
		}

		/**
		 * Get the associated status code.
		 *
		 * @return the status code.
		 */

		public int getStatusCode() {
			return code;
		}

		/**
		 * Get the reason phrase.
		 *
		 * @return the reason phrase.
		 */

		public String getReasonPhrase() {
			return toString();
		}

		/**
		 * Get the reason phrase.
		 *
		 * @return the reason phrase.
		 */
		@Override
		public String toString() {
			return reason;
		}

		/**
		 * Convert a numerical status code into the corresponding Status.
		 *
		 * @param statusCode
		 *            the numerical status code.
		 * @return the matching Status or null is no matching Status is defined.
		 */
		public static Action fromStatusCode(final int statusCode) {
			for (Action s : Action.values()) {
				if (s.code == statusCode) {
					return s;
				}
			}
			return null;
		}
	}

}
