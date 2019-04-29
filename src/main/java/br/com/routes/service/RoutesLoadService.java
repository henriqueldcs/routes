package br.com.routes.service;

import br.com.routes.domain.City;
import br.com.routes.exceptions.InvalidStringRouteException;
import br.com.routes.exceptions.RouteAlreadyExistsException;
import br.com.routes.factory.CityFactory;
import br.com.routes.factory.RouteFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
public class RoutesLoadService {

	public Map<String, City> loadCityWithRoutes(final BufferedReader file) throws IOException, InvalidStringRouteException, RouteAlreadyExistsException {

		String routeString;
		while ((routeString = file.readLine()) != null) {
			RouteFactory.create(routeString);
		}
		return CityFactory.getCreatedCities();
	}

	public static BufferedReader createDefaultReader() throws FileNotFoundException {
		File f = new File("/resources/input.txt");
		FileReader fr = new FileReader(f);
		return new BufferedReader(fr);
	}

	public static void main(String args[]) throws IOException, InvalidStringRouteException, RouteAlreadyExistsException {

		RoutesLoadService rls = new RoutesLoadService();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write("GRU,BRC,10\n".getBytes());
		baos.write("GRU,z,10\n".getBytes());

		Reader reader = new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()));
		BufferedReader br = new BufferedReader(reader);

		rls.loadCityWithRoutes(br);

	}
}

