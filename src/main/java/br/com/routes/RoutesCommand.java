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
		final URL routes = new URL("http://localhost:8080/routes");
		final URLConnection uc = routes.openConnection();
		final BufferedReader in = new BufferedReader(
				new InputStreamReader(
						uc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
	}
}
