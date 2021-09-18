@Compras @Regresion
Feature: Compras
  Como usuario requiero realizar compras de libros

  Background: Login Usuario
    Given Ingresa al sitio
    When Escribe el "test" y la "secret"
    And Pulsa el botón Login
    Then Valida inicio de sesion exitoso

  @Compra
  Scenario Outline: Comprar libros
    Given Ingresa a la página de libros disponibles
    When Ingresa la <CantidadLibros1> del <Titulo1>
    When Ingresa la <CantidadLibros2> del <Titulo2>
    When Ingresa la <CantidadLibros3> del <Titulo3>
    And Pulsa el botón adicionar
    Then Verifica que la suma total a pagar sea correcta

    Examples: 
      | CantidadLibros1 | Titulo1     | CantidadLibros2 | Titulo2          | CantidadLibros3 | Titulo3           |
      ##@externaldata@./src/test/resources/DataDriven/Compras/Compras.xlsx@Caso01
      | "3"             | "Core Java" | "5"             | "Ruby for Rails" | "2"             | "Python Cookbook" |

