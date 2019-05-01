package br.com.routes.service;

import br.com.routes.domain.City;
import br.com.routes.exceptions.InvalidStringRouteException;
import br.com.routes.exceptions.RouteAlreadyExistsException;
import br.com.routes.factory.CityFactory;
import br.com.routes.factory.RouteFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

/**
 * Serviço responsavel por carregar as rotas presentes em um arquivo.
 */
@Service
public class RoutesLoadService {

	/**
	 * Carrega cidades com suas rotas de um arquivo.
	 *
	 * @param file arquivo com lista de rotas.
	 * @return mapa de nomes / cidades.
	 * @throws IOException
	 * @throws InvalidStringRouteException
	 * @throws RouteAlreadyExistsException
	 */
	public Map<String, City> loadCityWithRoutes(final BufferedReader file) throws IOException, InvalidStringRouteException, RouteAlreadyExistsException {

		String routeString;
		while ((routeString = file.readLine()) != null) {
			RouteFactory.create(routeString);
		}
		return CityFactory.getCreatedCities();
	}

	/**
	 * Abre arquivo de rotas entre cidades informado como parametro na inicialização da aplicação.s
	 *
	 * @return reader do arquivo de rotas.
	 * @throws FileNotFoundException
	 */
	public static BufferedReader createDefaultReader() throws FileNotFoundException {
		final File f = new File("/resources/input.txt");
		final FileReader fr = new FileReader(f);
		return new BufferedReader(fr);
	}
}

