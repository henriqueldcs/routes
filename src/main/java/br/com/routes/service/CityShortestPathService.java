package br.com.routes.service;

import br.com.routes.domain.City;
import br.com.routes.domain.Route;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CityShortestPathService {

	/**
	 * LOG
	 */
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Atualiza informações do menor caminho da cidade caso necessário.
	 *
	 * @param city  cidade a atualizar.
	 * @param route rota a considerar.
	 */
	public void updateShortestPath(final City city, final Route route) {

		logger.info(String.format("Tentar atualizar menor caminho da cidade: %s com a rota: %s", city, route));

		final int distanceFromOrigin = calculateDistanceFromOrigin(route);

		if (city.getDistanceFromOrigin() > distanceFromOrigin) {
			city.setDistanceFromOrigin(distanceFromOrigin);
			city.setPreviousCity(route.getFrom());
			city.createShortestPath(route.getFrom());
		}
	}


	/**
	 * Calcula a distancia da origem considerando o inicio da rota e sua distancia.
	 *
	 * @param route rota a calcular.
	 * @return distancia da origem.
	 */
	private int calculateDistanceFromOrigin(final Route route) {
		return route.getFrom().getDistanceFromOrigin() + route.getDistance();
	}
}
