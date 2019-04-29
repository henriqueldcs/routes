package br.com.routes.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class City {

	private String name;
	private List<City> shortestPath;
	private Integer distanceFromOrigin;
	private City previousCity;

	public City(final String name) {
		this.name = name;
		shortestPath = new LinkedList<>();
		distanceFromOrigin = Integer.MAX_VALUE;
		previousCity = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<City> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public int getDistanceFromOrigin() {
		return distanceFromOrigin;
	}

	public void setDistanceFromOrigin(int distanceFromOrigin) {
		this.distanceFromOrigin = distanceFromOrigin;
	}

	public City getPreviousCity() {
		return previousCity;
	}

	public void setPreviousCity(City previousCity) {
		this.previousCity = previousCity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		City city = (City) o;
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
}
