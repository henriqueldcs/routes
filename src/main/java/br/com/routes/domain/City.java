package br.com.routes.domain;

import br.com.routes.exceptions.RouteAlreadyExistsException;

import java.util.*;

public class City {

	private String name;
	private List<City> shortestPath;
	private Integer distanceFromOrigin;
	private City previousCity;
	private final Map<City, Route> destinationCity;

	public City(final String name) {
		this.name = name;
		shortestPath = new LinkedList<>();
		distanceFromOrigin = Integer.MAX_VALUE;
		previousCity = null;
		destinationCity = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<City> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(final List<City> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public int getDistanceFromOrigin() {
		return distanceFromOrigin;
	}

	public void setDistanceFromOrigin(final int distanceFromOrigin) {
		this.distanceFromOrigin = distanceFromOrigin;
	}

	public City getPreviousCity() {
		return previousCity;
	}

	public void setPreviousCity(final City previousCity) {
		this.previousCity = previousCity;
	}

	public Map<City, Route> getDestinationCity() {
		return destinationCity;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final City city = (City) o;
		return Objects.equals(getName(), city.getName()) &&
				Objects.equals(getShortestPath(), city.getShortestPath()) &&
				Objects.equals(getDistanceFromOrigin(), city.getDistanceFromOrigin()) &&
				Objects.equals(getPreviousCity(), city.getPreviousCity());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getShortestPath(), getDistanceFromOrigin(), getPreviousCity());
	}

	@Override
	public String toString() {
		return "City{" +
				"name='" + name + '\'' +
				", shortestPath=" + shortestPath +
				", distanceFromOrigin=" + distanceFromOrigin +
				", previousCity=" + previousCity +
				'}';
	}

	/**
	 * Adiciona rota a lista de rotas de destino.
	 *
	 * @param to    cidade destino
	 * @param route rota
	 * @throws RouteAlreadyExistsException lancada caso haja uma rota para a cidade de destino cadastrada.
	 */
	public void addRoute(final City to, final Route route) throws RouteAlreadyExistsException {

		if (destinationCity.containsKey(to)) {
			throw new RouteAlreadyExistsException();
		}

		destinationCity.put(to, route);
	}

	/**
	 * Cria uma lista de menor caminho considerando o caminho da cidade passada como parametro e a adiciona a lista.
	 *
	 * @param city cidade de referencia para criação do menor caminho.
	 */
	public void createShortestPath(final City city) {
		this.shortestPath = new LinkedList<>(city.getShortestPath());
		this.shortestPath.add(city);
	}


}
