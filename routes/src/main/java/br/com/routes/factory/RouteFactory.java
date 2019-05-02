package br.com.routes.factory;

import br.com.routes.domain.City;
import br.com.routes.domain.Route;
import br.com.routes.exceptions.InvalidStringRouteException;
import br.com.routes.exceptions.RouteAlreadyExistsException;

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

	public static Route create(final String routeStirng) throws InvalidStringRouteException, RouteAlreadyExistsException {

		LOGGER.info(String.format("rota: %s", routeStirng));

		final String[] routeElements = routeStirng.split(",");
		validate(routeElements);

		final City from = CityFactory.create(routeElements[0]);
		final City to = CityFactory.create(routeElements[1]);
		final Integer distance = getIntDistance(routeElements[2]);
		final Route route = new Route(from, to, distance);

		return route;
	}

	/**
	 * Converte a distancia de string para inteiro.
	 *
	 * @param distance distancia
	 * @return distancia convertida
	 * @throws InvalidStringRouteException Quando nao for possivel converter a distancia.
	 */
	private static Integer getIntDistance(final String distance) throws InvalidStringRouteException {

		try {
			return Integer.valueOf(distance);
		} catch (final NumberFormatException e) {
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
	private static void validate(final String[] routeElements) throws InvalidStringRouteException {

		if (routeElements.length != 3) {
			LOGGER.warning(String.format("Rota invalida: %s", String.join(",", routeElements)));
			throw new InvalidStringRouteException();
		} else if (routeElements[0].equals(routeElements[1])) {
			throw new InvalidStringRouteException("Impossivel criar rota com mesma origem e destino!");
		}
	}
}
