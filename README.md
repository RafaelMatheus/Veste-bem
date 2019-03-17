# E-COMMERCE 

### API RESTFUL

</br>

> Este projeto é uma API que implementa </br>
> as bos práticas de arquitetura REST proposto por: [Roy Fielding] (https://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm). </br>
> o frontend que foi escrito em Ionic está localizado neste [repositório](https://github.com/RafaelMatheus/veste-bem-frontend.git)</br>
</br>


#### Tecnologias:
```sh
$ MySQL
$ Java
$ Spring Boot
$ Spring Data Jpa
$ Spring Security
```

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

1. Baixe todas depedência do arquivo pom.xml
2. Configure o arquivo **application.properties** com as configurações do seu banco de dados.
3. Execute o sistema .

### Endpoints REST:
* /refresh_token
* /forgot
* /categorias
* /clientes
* /estados
* /pedidos
* /produtos

#### É necessário está logado na API para conseguir acessar alguns endpoints, que não foram citados anteriormente.
#### Para efetuar o login você deverá executar os seguintes passos listados abaixo: 

* enviar uma requisição com o verbo HTTP POST para /login
* colocar no HEADER o token JWT retornado do endpoint /login

**Para acessar os endpoints com que necessitam de permissão de administrador, você poderá usar o email: rafaelmatheusdecastro@hotmail.com.br e a senha: 123**

