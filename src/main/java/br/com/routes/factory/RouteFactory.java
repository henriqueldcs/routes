package br.com.routes.factory;

import br.com.routes.domain.City;
import br.com.routes.domain.Route;
import br.com.routes.exceptions.InvalidStringRouteException;

import java.util.logging.Logger;

/**
 * Factory de {@link Route}
 */
public class RouteFactory {

	/**
	 * LOG
	 */
	private static final Logger LOGGER = Logger.getLogger(RouteFactory.class.getName());


	/**
	 * Cria uma {@link Route} entre duas {@link City}.
	 *
	 * @param routeStirng Rota com cidade de origem, destino e distancia.
	 * @return A rota criada.
	 * @throws InvalidStringRouteException Quando a string de rota for invalida.
	 */

	public static Route create(String routeStirng) throws InvalidStringRouteException {

		LOGGER.info(String.format("rota: %s", routeStirng));

		String routeElements[] = routeStirng.split(",");
		validate(routeElements);

		final String from = routeElements[0];
		final String to = routeElements[1];
		final Integer distance = getIntDistance(routeElements[2]);

		return new Route(CityFactory.create(from), CityFactory.create(to), distance);
	}

	/**
	 * Converte a distancia de string para inteiro.
	 *
	 * @param distance distancia
	 * @return distancia convertida
	 * @throws InvalidStringRouteException Quando nao for possivel converter a distancia.
	 */
	private static Integer getIntDistance(String distance) throws InvalidStringRouteException {

		try {
			return Integer.valueOf(distance);
		} catch (NumberFormatException e) {
			LOGGER.warning(String.format("Distancia nao eh um inteiro: %s", distance));
			throw new InvalidStringRouteException();
		}

	}

	/**
	 * Valida rota passaca como parametro.
	 *
	 * @param routeElements rota a validar
	 * @throws InvalidStringRouteException Quando a string de rota for invalida.
	 */
	private static void validate(String[] routeElements) throws InvalidStringRouteException {

		if (routeElements.length != 3) {
			LOGGER.warning(String.format("Rota invalida: %s", routeElements));
			throw new InvalidStringRouteException();
		}
	}
}
