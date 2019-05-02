package br.com.routes.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NewRouteRequest {

	@NotNull
	private String from;

	@NotNull
	private String to;


	@NotNull
	@Min(value = 1L, message = "A distancia deve ser maior que zero.")
	private Integer distance;

	public String getFrom() {
		return from;
	}

	public void setFrom(final String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(final String to) {
		this.to = to;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(final Integer distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "NewRouteRequest{" +
				"from='" + from + '\'' +
				", to='" + to + '\'' +
				", distance=" + distance +
				'}';
	}
}
