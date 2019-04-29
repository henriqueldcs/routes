package br.com.routes.factory

import br.com.routes.domain.Route
import br.com.routes.exceptions.InvalidStringRouteException
import br.com.routes.exceptions.RouteAlreadyExistsException
import spock.lang.Specification

class RouteCreationSpecification extends Specification {

    def "Criar rota para entre duas cidades"() {


        given: "Uma string de rota com o formato GRU,BRC,10"
        String pathString = "GRU,BRC,10"

        when: "For chamada a rotina de criacao de rotas"
        Route createdRoute = RouteFactory.create(pathString);

        then: "O rota deve ser criado"
        createdRoute.equals(getExpectedRoute())
    }

    def "Tentar criar rota com string invalida"() {

        given: "Uma string de rota com o formato ivalido:  GRU;BRC10"
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

    def "Inserir Rota criada na cidade de origem"() {

        given: "Uma string de rota com o formato A,B,10"
        String pathString = "A,B,10"

        when: "For chamada a rotina de criacao de rotas"
        Route createdRoute = RouteFactory.create(pathString);

        then: "A rota deve ter estar presente na cidade A como rotas de destino"
        CityFactory.create("A").getDestinationCity().containsKey(CityFactory.create("B"))

    }

    def "Inserir Rota duplicada na cidade de origem"() {

        given: "Uma string de rota ja incluida com o formato C,D,10"
        String pathString = "C,D,10"
        RouteFactory.create(pathString);

        when: "For chamada a rotina de criacao de rotas"
        RouteFactory.create(pathString);

        then: "Uma excecao de rota duplicada deve ocorrer"
        thrown RouteAlreadyExistsException

    }
}
