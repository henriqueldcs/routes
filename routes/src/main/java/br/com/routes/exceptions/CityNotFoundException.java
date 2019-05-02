package br.com.routes.exceptions;

/**
 * Exceção para cidade não encontrada.
 */
public class CityNotFoundException extends Exception {

	public CityNotFoundException(final String city) {
		super(String.format("Cidade não encontrada: %s", city));
	}

}
