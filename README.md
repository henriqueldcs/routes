# A aplicação #

Foi criado com o objetivo de atender as necessidades de viajantes que precisam escolher a 
melhor rota para sua viagem.
 
## Requisitos para a execução ##

- JDK 8
- Docker
- Gradle 5

## Como executar a aplicação ##

### Execução via linha de comandos ###

Acesse o diretório raiz do projeto e adicione a ele o arquivo de rotas desejado. 

Após isso execute a seguinte instrução, pelo terminal, dentro da raiz do projeto:

```bash
./routes ARQUIVO_DE_INPUT
```

No terminal, será solicitada a rota que deseja saber o menor caminho. 
Utilize o formato `GRU-BRC` e pressione a tecla ENTER. 

Tambem estará disponível a api para consulta de rota e cadastro de novas rotas, que pode ser 
acessada através do link: http://localhost:8080/swagger-ui.html

### Encerrando a execução ##

O encerramento do script no terminal pode ser realizado digitando `exit` quando uma rota for solicitada.

O encerramento da linha de comandos não encerra a API, para que isso ocorra será preciso executar
a seguinte instrução:

```docker stop routes```

## Testes ##

Para executar os testes, na raiz do projeto, via terminal, execute:

```./gradlew build test```

É gerado um relatório da execução dos testes na pasta `build/spock-reports/index.html` que pode ser visualizado através 
de qualquer navegador.


## Decisões de design adotadas para a solução ##

O projeto foi construido utilizando [Spring boot](https://spring.io/projects/spring-boot) com o 
objetivo de tornar mais produtivo o processo de criação da aplicação, configuração e disponibilização dos endpoints.

O desenvolvimento foi realizado utilizando a metodologia de desenvolvimento guiado por comportamento ([BDD](https://pt.wikipedia.org/wiki/Behavior_Driven_Development)), 
para a criação dos testes foi utilizado o [Spock](http://spockframework.org/), framework que permite a escrita de especificações
e testes de maneira legivel e possibilita a criação de um relatório de execução de testes. 

A aplicação é publicada em um container no [docker](https://docs.docker.com/).

O [Swagger](https://swagger.io/) foi utilizado para criar uma documentação da API e facilitar a execução das mesmas.

Para atender os requisitos de que a aplicação deveria ser executada via linha de comandos, foi criado um script bash que 
abstrai o processo de build, publicação, execução do projeto e inicialização da app na linha de comandos.

 
## Descreva sua API Rest de forma simplificada ##

Dois enpoints foram disponibilizados.

- Melhor caminho: Acessado através de uma requisição de GET para a url `http://localhost:8080/routes/best/from/{from}/to/{to} `.
- Adição de novas rotas: Executado por uma requisição de POST para `http://localhost:8080/routes`

## Estrutura dos arquivos/pacotes ##

Na raiz do projeto temos os seguinte arquivos:
- routes: Os códigos do projeto.
- routes.sh: Script responsavel por executar o projeto e executar consulta de rotas via linha de comandos.
- calculate.sh: Executa apenas a consulta da melhor rota. Pode ser chamado caso o projeto já esteja em execução.
- input.txt: arquivo de input de exemplo, utilizado para validações durante o desenvolvimento.


### Input Example ##

O arquivo de input a ser utilizado deve ser inserido na raiz do projeto.

```csv
GRU,BRC,10
BRC,SCL,5
GRU,CDG,75
GRU,SCL,20
GRU,ORL,56
ORL,CDG,5
SCL,ORL,20
```

## Explicando ## 
Caso desejemos viajar de **GRU** para **CDG** existem as seguintes rotas:

1. GRU - BRC - SCL - ORL - CDG ao custo de **$40**
2. GRU - ORL - CGD ao custo de **$64**
3. GRU - CDG ao custo de **$75**
4. GRU - SCL - ORL - CDG ao custo de **$48**
5. GRU - BRC - CDG ao custo de **$45**

O melhor preço é da rota **4** logo, o output da consulta deve ser **CDG - SCL - ORL - CDG**.
