package br.com.routes.domain;

import java.util.Objects;

public class Route {

	private City from;
	private City to;
	private Integer distance;

	public Route(City from, City to, Integer distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}

	public City getFrom() {
		return from;
	}

	public void setFrom(City from) {
		this.from = from;
	}

	public City getTo() {
		return to;
	}

	public void setTo(City to) {
		this.to = to;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Route route = (Route) o;
		return Objects.equals(getFrom(), route.getFrom()) &&
				Objects.equals(getTo(), route.getTo()) &&
				Objects.equals(getDistance(), route.getDistance());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFrom(), getTo(), getDistance());
	}

	@Override
	public String toString() {
		return "Route{" +
				"from=" + from +
				", to=" + to +
				", distance=" + distance +
				'}';
	}
}
