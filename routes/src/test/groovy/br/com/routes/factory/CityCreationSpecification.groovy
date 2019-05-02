package br.com.routes.factory

import br.com.routes.domain.City
import spock.lang.Specification

class CityCreationSpecification extends Specification {

    def "Criar uma nova cidade unica"() {

        given: "Um nome de cidade nao criado ainda"
        String name = "SP"

        when: "For chamada a rotina de criacao de cidades"
        City city = CityFactory.create(name)

        then: "A nova cidade com o nome SP deve ser criada"
        name == city.getName()

    }

    def "Criar uma cidade ja criada"() {

        given: "Um nome de cidade ja criado"
        String name = "SP"
        City cityExpected = CityFactory.create(name)

        when: "For chamada a rotina de criacao de cidades"
        City city = CityFactory.create(name)

        then: "A cidade ja criada deve ser retornada"
        city == cityExpected
    }

}
