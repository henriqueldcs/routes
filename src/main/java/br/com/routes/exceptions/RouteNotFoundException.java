package br.com.routes.exceptions;

public class RouteNotFoundException extends Exception {

	public RouteNotFoundException(final String from, final String to) {
		super(String.format("Não ha rota de: %s para: %s", from, to));
	}

}
