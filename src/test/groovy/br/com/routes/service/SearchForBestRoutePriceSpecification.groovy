package br.com.routes.service

import br.com.routes.dto.BestRoutePriceDTO
import br.com.routes.exceptions.CityNotFoundException
import br.com.routes.exceptions.RouteNotFoundException
import br.com.routes.factory.CityFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SearchForBestRoutePriceSpecification extends Specification {

    def "Tentar achar o melhor caminho para uma cidade inexistente C."() {

        given: "Uma rota da cidade A para B de custo 5"
        routes.add("A,B,5")

        when: "Executo a rotina para encontrar o menor caminho de A para C"
        searchBestRoutePriceService.findBest("A", "C", loadCities())

        then: "Uma exceção de cidade inexistente é lançada"
        thrown CityNotFoundException
    }

    def "Achar o melhor caminho de uma cidade A para B com o caminho direto."() {

        given: "Uma rota da cidade A para B de custo 5"
        routes.add("A,B,5")

        when: "Executo a rotina para encontrar o menor caminho de A para B"
        BestRoutePriceDTO bestRoutePriceDTO = searchBestRoutePriceService.findBest("A", "B", loadCities())

        then: "A rota do menor caminho deve ser A - B > \$5"
        bestRoutePriceDTO.getBestRouteMessage() == "best route: A - B > \$5"

    }

    def "Achar o melhor caminho de uma cidade A para C com uma cidade intermediaria."() {

        given: "Uma rota da cidade A para B de custo 5"
        routes.add("A,B,5")

        and: "Uma rota da cidade B para C de custo 8"
        routes.add("B,C,8")

        when: "Executo a rotina para encontrar o menor caminho A para C"
        BestRoutePriceDTO bestRoutePriceDTO = searchBestRoutePriceService.findBest("A", "C", loadCities())

        then: "A rota do menor caminho deve ser A - B - C > \$13"
        bestRoutePriceDTO.getBestRouteMessage() == "best route: A - B - C > \$13"

    }

    def "Achar o melhor caminho de uma cidade A para C com duas rotas, sendo: Uma rota direta e outra com uma cidade intermediaria."() {

        given: "Uma rota da cidade A para B de custo 5"
        routes.add("A,B,5")

        and: "Uma rota da cidade B para C de custo 8"
        routes.add("B,C,8")

        and: "Uma rota da cidade A para C de custo 18"
        routes.add("A,C,18")

        when: "Executo a rotina para encontrar o menor caminho A para C"
        BestRoutePriceDTO bestRoutePriceDTO = searchBestRoutePriceService.findBest("A", "C", loadCities())

        then: "A rota do menor caminho deve ser A - B - C > \$13"
        bestRoutePriceDTO.getBestRouteMessage() == "best route: A - B - C > \$13"

    }

    def "Achar o melhor caminho de uma cidade A para E com duas rotas, sendo: As duas rotas com cidades intermediarias."() {

        given: "Uma rota da cidade A para B de custo 5"
        routes.add("A,B,5")

        and: "Uma rota da cidade B para D de custo 12"
        routes.add("B,D,12")

        and: "Uma rota da cidade D para E de custo 6"
        routes.add("D,E,6")

        and: "Uma rota da cidade A para C de custo 18"
        routes.add("A,C,18")

        and: "Uma rota da cidade C para E de custo 2"
        routes.add("C,E,2")

        when: "Executo a rotina para encontrar o menor caminho A para E"
        BestRoutePriceDTO bestRoutePriceDTO = searchBestRoutePriceService.findBest("A", "E", loadCities())

        then: "A rota do menor caminho deve ser A - C - E > \$20"
        bestRoutePriceDTO.getBestRouteMessage() == "best route: A - C - E > \$20"
    }

    def "Tentar achar o melhor caminho para uma rota inexistente, A para C."() {

        given: "Uma rota da cidade A para B de custo 5"
        routes.add("A,B,5")

        and: "Uma rota da cidade C para D de custo 15"
        routes.add("C,D,5")

        when: "Executo a rotina para encontrar o menor caminho de A para C"
        searchBestRoutePriceService.findBest("A", "C", loadCities())

        then: "Uma exceção de rota inexistente é lançada"
        thrown RouteNotFoundException

    }

    def "Tentar adicionar uma rota ciclica."() {

        given: "Uma rota da cidade A para B de custo 5"
        routes.add("A,B,5")

        and: "Uma rota da cidade B para C de custo 8"
        routes.add("B,C,8")

        and: "Uma rota da cidade B para A de custo 1"
        routes.add("B,A,1")

        when: "Executo a rotina para encontrar o menor caminho A para C"
        BestRoutePriceDTO bestRoutePriceDTO = searchBestRoutePriceService.findBest("A", "C", loadCities())

        then: "A rota do menor caminho deve ser A - B - C > \$13"
        bestRoutePriceDTO.getBestRouteMessage() == "best route: A - B - C > \$13"

    }


    List<String> routes

    @Autowired
    SearchBestRoutePriceService searchBestRoutePriceService

    void setup() {
        routes = new ArrayList<>()
        CityFactory.getCreatedCities().clear()
    }

    def loadCities() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        routes.each { r -> baos.write(getBytes(r)) }
        Reader reader = new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()))

        return new RoutesLoadService().loadCityWithRoutes(new BufferedReader(reader))
    }

    def getBytes(String s) {
        return (s + "\n").getBytes();
    }
}
