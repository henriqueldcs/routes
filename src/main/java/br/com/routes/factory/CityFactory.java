package br.com.routes.factory;

import br.com.routes.domain.City;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory de {@link City}
 */
public class CityFactory {


	private final static Map<String, City> cities = new HashMap<>();

	/**
	 * Cria uma cidade ({@link City}) caso nao exista uma cidade com o nome informado e a adiciona ao map de cidade.
	 * Caso a cidade exista no map apenas a retorna.
	 *
	 * @param name Nome da cidade
	 * @return A cidade criada.
	 */
	public static City create(String name) {

		if (!cities.containsKey(name)) {
			cities.put(name, new City(name));
		}

		return cities.get(name);
	}
}
