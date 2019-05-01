package br.com.routes.exceptions;

/**
 * Exceção para string de importação rota inválida.
 */
public class InvalidStringRouteException extends Exception {

	public InvalidStringRouteException() {
		super("Rota invalida!");
	}

}
