package br.com.routes.dto;

import br.com.routes.domain.City;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO de menor caminho.
 */
public class BestRoutePriceDTO {

	private final LinkedList<City> bestRoute;

	private final Integer bestPrice;

	public BestRoutePriceDTO(final LinkedList<City> bestRoute, final Integer bestPrice) {
		this.bestRoute = bestRoute;
		this.bestPrice = bestPrice;
	}

	/**
	 * Cria mensagem informando o menor caminho e seu custo.
	 *
	 * @return mensagem com o menor caminho e custo.
	 */
	public String getBestRouteMessage() {

		final List<String> citiesBestRoute = bestRoute.stream()
				.map(City::getName)
				.collect(Collectors.toList());

		return String.format("best route: %s > $%s", String.join(" - ", citiesBestRoute), bestPrice.toString());
	}
}
