package br.com.routes.factory

class RouteCreationSpecification {

    def "Criar rota para entre duas cidades"() {


        given: "Uma string com o formato GRU,BRC,10"
        String pathString = "GRU,BRC,10"

        when: "For chamada a rotina de criacao de rotas"
        Route createdRoute = RouteFactory.create(pathString);

        then: "O rota deve ser criado"
        createdRoute.equals(getExpectedRoute())
    }

    def "Tentar criar rota com rota invalido"() {

        given: "Uma string com o formato ivalido:  GRU;BRC10"
        String pathString = "GRU;BRC10"

        when: "For chamada a rotina de criacao de rotas"
        Route createdRoute = RouteFactory.create(pathString);

        then: "Deve lancar a excecao de rota invalido"
        InvalidStringRouteException ex = thrown()
        ex.message == "Caminho invalido!"
    }

}
