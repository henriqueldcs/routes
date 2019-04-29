package br.com.routes.service

import br.com.routes.domain.City
import br.com.routes.factory.CityFactory
import spock.lang.Specification

class RoutesLoadServiceSpecification extends Specification {

    def "Carregar arquivo de rotas com tres rotas"() {

        given: "Reader de um arquivo com tres rotas da cidade A1 para B1, C1 e D1"
        BufferedReader reader = createReader(Arrays.asList("A1,B1,5\n", "A1,C1,7\n", "A1,D1,9\n"))

        when: "As rotas forem carregadas"
        Map<String, City> actual = new RoutesLoadService().loadCityWithRoutes(reader)

        then: "Devemos ter 4 cidades incluidas"
        actual.size() == 4

        then: "Devemos ter tres rotas cadastradas na cidade A1"
        actual.get("A1").getDestinationCity().size() == 3

    }

    def createReader(List<String> routes) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        routes.each { r -> baos.write(r.getBytes()) }
        Reader reader = new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()))
        return new BufferedReader(reader)
    }

    void setup() {
        CityFactory.getCreatedCities().clear()
    }
}
