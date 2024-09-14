# DSMovie
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/henriquecesarjr/DSMovie/blob/main/LICENSE)

# Sobre o projeto

Este é um projeto de filmes e avaliações de filmes. A visualização dos dados dos filmes é pública (não necessita login), porém as alterações de filmes (inserir, atualizar, deletar) são permitidas apenas para usuários ADMIN. As avaliações de filmes podem ser registradas por qualquer usuário logado CLIENT ou ADMIN. A entidade Score armazena uma nota de 0 a 5 (score) que cada usuário deu a cada filme. Sempre que um usuário registra uma nota, o sistema calcula a média das notas de todos usuários, e armazena essa nota média (score) na entidade Movie, juntamente com a contagem de votos (count).   

## Modelo conceitual
![Modelo Conceitual](https://github.com/henriquecesarjr/DSMovie/blob/main/assets/Modelo%20Conceitual.png)

# Tecnologias utilizadas
- Java
- Spring Boot
- Spring Security
- JPA / Hibernate
- H2 Database
- Maven
- JUnit
- Mockito
- JaCoCo
- Swagger
- Hateoas

# Autor

Henrique César Jr. C. Marques

<a href="www.linkedin.com/in/henriquejrmarques" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
