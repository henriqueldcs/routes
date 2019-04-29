package br.com.routes.factory

import br.com.routes.domain.Route
import br.com.routes.exceptions.InvalidStringRouteException
import spock.lang.Specification

class RouteCreationSpecification extends Specification {

    def "Criar rota para entre duas cidades"() {


        given: "Uma string com o formato GRU,BRC,10"
        String pathString = "GRU,BRC,10"

        when: "For chamada a rotina de criacao de rotas"
        Route createdRoute = RouteFactory.create(pathString);

        then: "O rota deve ser criado"
        createdRoute.equals(getExpectedRoute())
    }

    def "Tentar criar rota com string invalida"() {

        given: "Uma string com o formato ivalido:  GRU;BRC10"
        String pathString = "GRU;BRC10"

        when: "For chamada a rotina de criacao de rotas"
        Route createdRoute = RouteFactory.create(pathString);

        then: "Deve lancar a excecao de rota invalido"
        InvalidStringRouteException ex = thrown()
        ex.message == "Rota invalida!"
    }

    def getExpectedRoute() {
        return new Route(CityFactory.create("GRU"), CityFactory.create("BRC"), 10)
    }
}
