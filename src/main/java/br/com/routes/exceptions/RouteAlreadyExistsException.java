package br.com.routes.exceptions;

public class RouteAlreadyExistsException extends Exception {

	public RouteAlreadyExistsException() {
		super("Rota ja incluida!");
	}

}
