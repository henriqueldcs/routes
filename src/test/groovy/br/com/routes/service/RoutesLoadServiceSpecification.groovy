package br.com.routes.service


import spock.lang.Specification

class RoutesLoadServiceSpecification extends Specification {

    def "Carregar arquivo de rotas com tres rotas"() {

        given: "Reader de um arquivo com tres rotas da cidade A para B, C e D"

        when: "As rotas forem carregadas"

        then: "Devemos ter 4 cidades incluidas"

        then: "Devemos ter tres rotas cadastradas na cidade A"

    }

}
