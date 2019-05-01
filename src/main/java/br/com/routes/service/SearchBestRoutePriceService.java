package br.com.routes.service;

import br.com.routes.domain.City;
import br.com.routes.dto.BestRoutePriceDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Serviço responsavel por encontrar o menor caminho entre duas cidades.
 */
@Service
public class SearchBestRoutePriceService {


	public BestRoutePriceDTO findBest(final String from, final String to, final Map<String, City> citiesMap) {


		return null;
	}
}
