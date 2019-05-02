package br.com.routes.service

import br.com.routes.domain.City
import br.com.routes.domain.Route
import br.com.routes.factory.CityFactory
import br.com.routes.factory.RouteFactory
import spock.lang.Specification

class CityShortestPathServiceSpecification extends Specification {

    def "Atualizar menor caminho de cidade"() {

        given: "A cidade de origem A"
        City a = CityFactory.create("A");
        a.setDistanceFromOrigin(0)

        and: "A cidade de destino B"
        City b = CityFactory.create("B");

        and: "A rota de A para B: A,B,5"
        Route route = RouteFactory.create("A,B,5")

        when: "Executar a rotina de atualização de menor caminho"
        new CityShortestPathService().updateShortestPath(b, route)

        then: "A lista de menor caminho da cidade B deve conter apenas a cidade A"
        Arrays.asList(a).containsAll(b.getShortestPath())

        and: "A distancia da origem deve ser 5"
        b.getDistanceFromOrigin() == 5

    }

    def "Tentar atualizar menor caminho de cidade D que já possui uma rota melhor"() {

        given: "A cidade de origem A"
        City a = CityFactory.create("A");
        a.setDistanceFromOrigin(0)

        and: "A cidade de destino D"
        City d = CityFactory.create("D");

        and: "A cidade B"
        City b = CityFactory.create("B");
        b.setDistanceFromOrigin(1)

        and: "A rota de B para D: B,D,4"
        Route routeBD = RouteFactory.create("B,D,4")

        and: "A rota de A para D: A,D,1"
        Route routeAD = RouteFactory.create("A,D,1")

        and: "D possui A como menor caminho"
        d.getShortestPath().add(a)

        and: "A distancia da origem é 1"
        d.setDistanceFromOrigin(1)

        when: "Executar a rotina de atualização de menor caminho passando a rota B - D"
        new CityShortestPathService().updateShortestPath(d, routeBD)

        then: "A lista de menor caminho da cidade D deve conter apenas a cidade A"
        Arrays.asList(a).containsAll(d.getShortestPath())

        and: "A distancia da origem deve ser 1"
        b.getDistanceFromOrigin() == 1
    }

    void setup() {
        CityFactory.getCreatedCities().clear()
    }

}
