package br.com.routes.service

import br.com.routes.domain.City
import br.com.routes.dto.BestRoutePriceDTO
import br.com.routes.exceptions.RouteNotFoundException
import br.com.routes.factory.CityFactory
import spock.lang.Specification

class BestRouteServiceSpecification extends Specification {

    def "Tentar Criar DTO para cidade que não possui rota."() {

        given: "A cidade de origem A"
        City a = CityFactory.create("A")

        and: "A cidade de origem B"
        City b = CityFactory.create("B")

        and: "A cidade B não possui rotas vindo de A"
        a.destinationCity.clear()

        when: "É chamado o serviço que cria o DTO do menor caminho"
        new BestRouteService().createBestRouteDto(a, b)

        then: "Uma exceção de rota não encontrada é lançada"
        thrown RouteNotFoundException

    }


    def "Criar DTO para cidade B com menor caminho vindo de A"() {

        given: "A cidade de origem A"
        City a = CityFactory.create("A")

        and: "A cidade de origem B"
        City b = CityFactory.create("B")

        and: "A cidade B possui menor caminho para a origem A"
        b.getShortestPath().add(a)

        and: "A distancia da origem é 5"
        b.setDistanceFromOrigin(5)

        when: "É chamado o serviço que cria o DTO do menor caminho"
        BestRoutePriceDTO dto = new BestRouteService().createBestRouteDto(a, b)

        then: "ss"
        dto.getBestRouteMessage() == "best route: A - B > \$5"

    }

}
