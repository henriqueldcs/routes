package br.com.routes.service;

import br.com.routes.domain.City;
import br.com.routes.dto.BestRoutePriceDTO;
import br.com.routes.exceptions.RouteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Serviço responsavel por carregar informções sobre a melhor rota encontrada.
 */
@Service
public class BestRouteService {

	/**
	 * Cria {@link BestRoutePriceDTO} com melhor custo.
	 *
	 * @param fromCity cidade de origem.
	 * @param toCity   cidade de destino.
	 * @return melhor custo.
	 * @throws RouteNotFoundException lançada caso não exista rota.
	 */
	public BestRoutePriceDTO createBestRouteDto(final City fromCity, final City toCity) throws RouteNotFoundException {

		checkIfRouteWasFound(fromCity, toCity);

		final List<City> bestRoute = new LinkedList<>(toCity.getShortestPath());
		bestRoute.add(toCity);

		return new BestRoutePriceDTO(bestRoute, toCity.getDistanceFromOrigin());
	}

	/**
	 * Verifica se rota para a cidade de destino foi encontrada.
	 *
	 * @param from cidade de origem.
	 * @param to   cidade de destino.
	 * @throws RouteNotFoundException Lançada caso não exista rota da cidade de origem para a cidade de destino.
	 */
	private void checkIfRouteWasFound(final City from, final City to) throws RouteNotFoundException {

		if (to.getShortestPath().isEmpty()) {
			throw new RouteNotFoundException(from.getName(), to.getName());
		}
	}
}
