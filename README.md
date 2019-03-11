# API RESTFUL de e-commerce
### Tecnologias:
* MySQL
* Java
* Spring Boot
* Spring Data Jpa
* Spring Security

### Para executar a API:

1. Baixe todas depedência do arquivo pom.xml
2. Configure o arquivo **application.properties** com as configurações do seu banco de dados.
3. Execute o sistema .

### Endpoints REST:
/refresh_token
/forgot
/categorias
/clientes
/estados
/pedidos
/produtos

### É necessário está logado na API para conseguir acessar alguns endpoints, que não foram citados anteriormente.
#### Para efetuar o login você deverá executar os seguintes passos listados abaixo: 

1. enviar uma requisição com o verbo HTTP POST para /login
2. colocar no HEADER o token JWT retornado do endpoint /login

**Para acessar os endpoints com que necessitam de permissão de administrador, você poderá usar o email: rafaelmatheusdecastro@hotmail.com.br e a senha: 123**

