package br.com.routes.facades;

import br.com.routes.dto.BestRoutePriceDTO;
import br.com.routes.exceptions.CityNotFoundException;
import br.com.routes.exceptions.InvalidStringRouteException;
import br.com.routes.exceptions.RouteAlreadyExistsException;
import br.com.routes.exceptions.RouteNotFoundException;
import br.com.routes.factory.CityFactory;
import br.com.routes.factory.RouteFactory;
import br.com.routes.request.NewRouteRequest;
import br.com.routes.service.RoutesFileManagementService;
import br.com.routes.service.SearchBestRoutePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.logging.Logger;


/**
 * Facade para manipular rotas.
 */
@Service
public class RouteFacade {

	@Autowired
	RoutesFileManagementService routesFileManagementService;

	@Autowired
	SearchBestRoutePriceService searchBestRoutePriceService;

	/**
	 * LOG
	 */
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@PostConstruct
	public void init() {
		try {
			routesFileManagementService.loadCityWithRoutes(routesFileManagementService.createDefaultReader());
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final InvalidStringRouteException e) {
			e.printStackTrace();
		} catch (final RouteAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Encontra o menor caminho entre duas cidades.
	 *
	 * @param from cidade de origem.
	 * @param to   cidade de destino.
	 * @return o menor caminho encontrado.
	 * @throws CityNotFoundException  caso a cidade não seja encontrada.
	 * @throws RouteNotFoundException caso uma rota não seja encontrada entre as cidades de origem e destino.
	 */
	public BestRoutePriceDTO findBestRoute(final String from, final String to) throws RouteNotFoundException, CityNotFoundException {
		return searchBestRoutePriceService.findBest(from.toUpperCase(), to.toUpperCase(), CityFactory.getCreatedCities());
	}


	/**
	 * Adiciona nova rota ao sistema.
	 *
	 * @param newRouteRequest nova rota.
	 * @throws InvalidStringRouteException caso rota seja invalida.
	 * @throws RouteAlreadyExistsException caso rota já tenha sido inserida.
	 * @throws IOException                 caso ocorra erro de gravação no arquivo.
	 */
	public void createRoute(final NewRouteRequest newRouteRequest) throws InvalidStringRouteException, RouteAlreadyExistsException, IOException {

		final String routeString = String.format("%s,%s,%s", newRouteRequest.getFrom().toUpperCase(), newRouteRequest.getTo().toUpperCase(), newRouteRequest.getDistance().toString());

		RouteFactory.create(routeString);
		routesFileManagementService.saveRoute(routeString, routesFileManagementService.createDefaultWriter());
	}
}
