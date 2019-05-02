package br.com.routes.response;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * ApiError é usada para retorno de mensagens de error.
 */
public class ApiError implements Serializable {

	private static final long serialVersionUID = 4921428620510764723L;
	private final HttpStatus status;
	private final String message;


	public ApiError(final HttpStatus status, final String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
