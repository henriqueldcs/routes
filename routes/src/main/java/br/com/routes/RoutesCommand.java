package br.com.routes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Classe usada pelo script para realizar chamadas pela linha de comandos.
 */
public class RoutesCommand {

	public static void main(final String[] args) throws Exception {

		final String urlBestRoute = "http://localhost:8080/routes/console/best/from/%s/to/%s";

		String route;
		System.out.print("please enter the route:");
		while (!(route = System.console().readLine()).equals("exit")) {

			final String[] cities = route.split("-");
			if (cities.length != 2) {
				System.out.println("Invalid route!!!");
			} else {
				try {
					final URL routes = new URL(String.format(urlBestRoute, cities[0], cities[1]));
					final URLConnection uc = routes.openConnection();
					final BufferedReader in = new BufferedReader(
							new InputStreamReader(
									uc.getInputStream()));

					String inputLine;

					while ((inputLine = in.readLine()) != null)
						System.out.println("\t" + inputLine);
					in.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}

			System.out.print("please enter the route:");
		}
	}
}
