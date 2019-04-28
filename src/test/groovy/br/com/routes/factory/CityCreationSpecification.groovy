package br.com.routes.factory

import spock.lang.Specification

class CityCreationSpecification extends Specification {

    def "one plus one should equal two"() {
        expect:
        1 + 1 == 2
    }

    def "Criar uma nova cidade unica"() {

        given: "Um nome de cidade nao criado ainda"
            String name = "SP"

        when: "For chamada a rotina de criacao de cidades"
            //TODO CityFactory

        then: "A nova cidade deve ser criada"
            //todo City
    }

    def "Criar uma cidade ja criada"() {

        given: "Um nome de cidade ja criado"

        when: "For chamada a rotina de criacao de cidades"
        //TODO CityFactory

        then: "A cidade ja criada deve ser retornada"
        //todo City

    }

}
