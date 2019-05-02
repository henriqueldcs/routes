package br.com.routes.resources;

import br.com.routes.dto.BestRoutePriceDTO;
import br.com.routes.exceptions.CityNotFoundException;
import br.com.routes.exceptions.InvalidStringRouteException;
import br.com.routes.exceptions.RouteAlreadyExistsException;
import br.com.routes.exceptions.RouteNotFoundException;
import br.com.routes.facades.RouteFacade;
import br.com.routes.request.NewRouteRequest;
import br.com.routes.response.ApiError;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.logging.Logger;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/routes")
@Api(value = "Rotas", description = "Adição e consulta de rotas.", tags = {"Rotas"})
public class RoutesResource {

	@Autowired
	RouteFacade routeFacade;

	/**
	 * LOG
	 */
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@GetMapping("/best/from/{from}/to/{to}")
	@ApiOperation(value = "Encontra melhor rota entre duas cidades.", tags = {"Rotas"})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Melhor caminho encontrado!", response = BestRoutePriceDTO.class),
			@ApiResponse(code = 400, message = "Dados obrigatorios não informados!"),
			@ApiResponse(code = 404, message = "Rota entre as cidades não existe!")
	})
	public ResponseEntity<Object> getBestRoute(
			@ApiParam(name = "from", value = "cidade de origem", required = true) @PathVariable("from") @NotNull final String from,
			@ApiParam(name = "to", value = "cidade de destino", required = true) @PathVariable("to") @NotNull final String to) {

		logger.info(String.format("Encontra melhor caminho de: %s, para: %s", from, to));
		try {
			return ResponseEntity.ok(routeFacade.findBestRoute(from, to));
		} catch (final CityNotFoundException | RouteNotFoundException e) {
			return ResponseEntity.status(NOT_FOUND).body(new ApiError(NOT_FOUND, e.getMessage()));
		}
	}

	@PostMapping
	@ApiOperation(value = "Adiciona rota entre duas cidades.", tags = {"Rotas"})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Rota incluida com sucesso!"),
			@ApiResponse(code = 400, message = "Dados obrigatorios não informados ou inválidos!"),
			@ApiResponse(code = 409, message = "Rota entre as cidades já existe!")
	})
	public ResponseEntity createRoute(@ApiParam(name = "newRouteRequest", value = "Nova rota", required = true) @RequestBody @Valid final NewRouteRequest newRouteRequest) {

		logger.info(String.format("Adiciona rota: %s", ""));

		try {
			routeFacade.createRoute(newRouteRequest);
			return ResponseEntity.status(CREATED).build();

		} catch (final InvalidStringRouteException e) {

			return ResponseEntity.badRequest().body(new ApiError(BAD_REQUEST, e.getMessage()));

		} catch (final RouteAlreadyExistsException e) {
			return ResponseEntity.status(CONFLICT).body(new ApiError(CONFLICT, e.getMessage()));

		} catch (final IOException e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiError(INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@ApiIgnore
	@GetMapping(value = "console/best/from/{from}/to/{to}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Object> getBestRouteToConsole(
			@PathVariable("from") @NotNull final String from,
			@PathVariable("to") @NotNull final String to) {

		logger.info(String.format("Encontra melhor caminho de: %s, para: %s", from, to));
		try {
			return ResponseEntity.ok(routeFacade.findBestRoute(from, to).getBestRouteMessage());
		} catch (final CityNotFoundException | RouteNotFoundException e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
}
