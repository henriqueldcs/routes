package br.com.routes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RoutesCommand {

	public static void main(String[] args) throws Exception {
		URL routes = new URL("http://localhost:8080/routes");
		URLConnection uc = routes.openConnection();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
						uc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
	}
}
