package br.com.routes.readers;


import java.io.*;
import java.util.*;

public class RoutesReader {

//	public static Map<String, Node> importRoutes() throws IOException {
//
//		String line;
//		Map<String, Node> nodes = new HashMap<>();
//		final BufferedReader file = createReader();
//
//		while((line = file.readLine()) != null) {
//
//			String[] items = line.split(",");
//			if(items.length == 3) {
//
//				final String from = items[0];
//				final String to = items[1];
//
//				if(!nodes.containsKey(from)) {
//					nodes.put(from, new Node(from));
//				}
//
//				if(!nodes.containsKey(to)) {
//					nodes.put(to, new Node(to));
//				}
//
//				nodes.get(from).addAdjacent(nodes.get(to), Integer.valueOf(items[2]));
//			}
//		}
//
//		return nodes;
//	}

	public static BufferedReader createReader() throws FileNotFoundException {
		File f = new File("/resources/input.txt");
		FileReader fr = new FileReader(f);
		return new BufferedReader(fr);
	}
}
