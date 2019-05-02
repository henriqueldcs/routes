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
public class RoutesFileManagementService {

	/**
	 * Default file path.
	 */
	private static final String defaultFilePath = "/resources/input.txt";

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

		file.close();
		return CityFactory.getCreatedCities();
	}

	/**
	 * Abre arquivo de rotas entre cidades informado como parametro na inicialização da aplicação para leitura.s
	 *
	 * @return reader do arquivo de rotas.
	 * @throws FileNotFoundException
	 */
	public BufferedReader createDefaultReader() throws FileNotFoundException {
		final File f = new File(defaultFilePath);
		final FileReader fr = new FileReader(f);
		return new BufferedReader(fr);
	}

	/**
	 * Adiciona a nova rota ao arquivo.
	 *
	 * @param routeString
	 */
	public void saveRoute(final String routeString, final BufferedWriter writer) throws IOException {
		writer.write(routeString + "\n");
		writer.close();
	}

	/**
	 * Abre arquivo de rotas entre cidades informado como parametro na inicialização da aplicação para gravação.
	 *
	 * @return reader do arquivo de rotas.
	 * @throws FileNotFoundException
	 */
	public BufferedWriter createDefaultWriter() throws IOException {
		final File f = new File(defaultFilePath);
		final FileWriter fw = new FileWriter(f, true);
		return new BufferedWriter(fw);
	}
}

