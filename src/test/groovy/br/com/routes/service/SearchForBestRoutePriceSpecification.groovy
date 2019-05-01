package br.com.routes.service


import spock.lang.Specification

class SearchForBestRoutePriceSpecification extends Specification {

    def "Tentar achar o melhor caminho para uma cidade inexistente."() {

        given: "Uma rota da cidade A para B de custo 5"

        when: "Executo a rotina para encontrar o menor caminho de A para C"

        then: "Uma exceção de cidade inexistente é lançada"
    }

    def "Tentar achar o melhor caminho para uma rota inexistente."() {

        given: "Uma rota da cidade A para B de custo 5"

        given: "Uma rota da cidade C para D de custo 15"

        when: "Executo a rotina para encontrar o menor caminho de A para C"

        then: "Uma exceção de rota inexistente é lançada"
    }

    def "Achar o melhor caminho de uma cidade A para B com o caminho direto."() {

        given: "Uma rota da cidade A para B de custo 5"

        when: "Executo a rotina para encontrar o menor caminho"

        then: "A rota do menor caminho deve ser A - B > \$5"
    }

    def "Achar o melhor caminho de uma cidade A para C com uma cidade intermediaria."() {

        given: "Uma rota da cidade A para B de custo 5"

        and: "Uma rota da cidade B para C de custo 8"

        when: "Executo a rotina para encontrar o menor caminho"

        then: "A rota do menor caminho deve ser A - B - C > \$13"
    }

    def "Achar o melhor caminho de uma cidade A para C com duas rotas, sendo: Uma rota direta e outra com uma cidade intermediaria."() {

        given: "Uma rota da cidade A para B de custo 5"

        and: "Uma rota da cidade B para C de custo 8"

        and: "Uma rota da cidade A para C de custo 18"

        when: "Executo a rotina para encontrar o menor caminho"

        then: "A rota do menor caminho deve ser A - B - C > \$13"
    }

    def "Achar o melhor caminho de uma cidade A para D com duas rotas, sendo: As duas rotas com cidades intermediarias."() {

        given: "Uma rota da cidade A para B de custo 5"

        and: "Uma rota da cidade B para C de custo 8"

        and: "Uma rota da cidade C para D de custo 15"

        and: "Uma rota da cidade A para C de custo 18"

        and: "Uma rota da cidade C para D de custo 4"

        when: "Executo a rotina para encontrar o menor caminho"

        then: "A rota do menor caminho deve ser A - C - D > \$22"
    }

}
