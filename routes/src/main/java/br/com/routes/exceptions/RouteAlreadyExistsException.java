package br.com.routes.exceptions;

/**
 * Exceção para rota duplicada.
 */
public class RouteAlreadyExistsException extends Exception {

	public RouteAlreadyExistsException() {
		super("Rota ja incluida!");
	}

}
