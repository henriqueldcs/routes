package br.com.routes.service

import br.com.routes.domain.City
import br.com.routes.factory.CityFactory
import spock.lang.Specification

class RoutesLoadServiceSpecification extends Specification {

    def "Carregar arquivo de rotas com tres rotas"() {

        given: "Reader de um arquivo com tres rotas da cidade A para B, C e D"
        BufferedReader reader = createReader(Arrays.asList("A,B,5\n", "A,C,7\n", "A,D,9\n"))

        when: "As rotas forem carregadas"
        Map<String, City> actual = new RoutesLoadService().loadCityWithRoutes(reader)

        then: "Devemos ter 4 cidades incluidas"
        actual.size() == 4

        then: "Devemos ter tres rotas cadastradas na cidade A"
        actual.get("A").getDestinationCity().size() == 3

    }

    def BufferedReader createReader(List<String> routes) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        routes.each { r -> baos.write(r.getBytes()) }
        Reader reader = new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()));
        return new BufferedReader(reader);
    }

    void cleanup() {
        CityFactory.getCreatedCities().clear()
    }
}
