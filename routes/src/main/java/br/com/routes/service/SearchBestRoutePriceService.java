package br.com.routes.service;

import br.com.routes.domain.City;
import br.com.routes.dto.BestRoutePriceDTO;
import br.com.routes.exceptions.CityNotFoundException;
import br.com.routes.exceptions.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Serviço responsavel por encontrar o menor caminho entre duas cidades.
 */
@Service
public class SearchBestRoutePriceService {

	@Autowired
	CityShortestPathService cityShortestPathService;

	@Autowired
	BestRouteService bestRouteService;

	/**
	 * LOG
	 */
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Encontra o menor caminho entre duas cidades.
	 *
	 * @param from      cidade de origem.
	 * @param to        cidade de destino.
	 * @param citiesMap mapa de cidades e suas rotas.
	 * @return o menor caminho encontrado.
	 * @throws CityNotFoundException  caso a cidade não seja encontrada.
	 * @throws RouteNotFoundException caso uma rota não seja encontrada entre as cidades de origem e destino.
	 */
	public BestRoutePriceDTO findBest(final String from, final String to, final Map<String, City> citiesMap) throws CityNotFoundException, RouteNotFoundException {

		logger.info(String.format("Encontra menor caminho de: %s para: %s", from, to));
		citiesMap.values().stream().forEach(City::resetBestRouteData);

		final City fromCity = getCityByName(from, citiesMap);
		final City toCity = getCityByName(to, citiesMap);

		final List<City> toVisit = new ArrayList<>();
		final List<City> visited = new ArrayList<>();

		toVisit.add(fromCity);
		fromCity.setDistanceFromOrigin(0);
		City visitingCity;

		while ((visitingCity = nextToVisit(toVisit)) != null) {

			visitingCity.getDestinationCity().values().stream()
					.forEach(route -> {
						final City destinationCityFromRoute = route.getTo();

						if (!visited.contains(destinationCityFromRoute)) {
							cityShortestPathService.updateShortestPath(destinationCityFromRoute, route);

							if (!toCity.equals(destinationCityFromRoute)) {
								toVisit.add(destinationCityFromRoute);
							}
						}
					});

			toVisit.remove(visitingCity);
			visited.add(visitingCity);
		}

		return bestRouteService.createBestRouteDto(fromCity, toCity);
	}

	/**
	 * Retorna a primeira cidade com o menor caminho encontrada.
	 *
	 * @param cities lista de cidades.
	 * @return cidade com menor caminho ou null caso a lista esteja vazia.
	 */
	private City nextToVisit(final List<City> cities) {

		return cities.stream()
				.min(Comparator.comparing(City::getDistanceFromOrigin))
				.orElse(null);
	}

	/**
	 * Retorna cidade por nome.
	 *
	 * @param city      nome da cidade.
	 * @param citiesMap map de cidades.
	 * @return cidade encontrada.
	 * @throws CityNotFoundException lançada caso a cidade não seja encontrada.
	 */
	private City getCityByName(final String city, final Map<String, City> citiesMap) throws CityNotFoundException {

		final City c = citiesMap.getOrDefault(city, null);

		if (c == null) {
			throw new CityNotFoundException(city);
		}

		return c;
	}
}
