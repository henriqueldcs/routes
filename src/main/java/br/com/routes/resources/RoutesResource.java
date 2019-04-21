package br.com.routes.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/routes")
public class RoutesResource {

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity getRoutes() throws IOException {

		return ResponseEntity.ok().body("Hello Routes");
	}
}
