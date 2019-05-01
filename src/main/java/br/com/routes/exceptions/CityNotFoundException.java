package br.com.routes.exceptions;

public class CityNotFoundException extends Exception {

	public CityNotFoundException(final String city) {
		super(String.format("Cidade não encontrada: %s", city));
	}

}
