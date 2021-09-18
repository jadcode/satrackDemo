@ErrorPage @Regresion
Feature: ErrorPage
  Como usuario requiero validar que el sitio me permite validar las páginas de errores

  @ErrorPage
  Scenario Outline: Validar errores de página
    Given Ingresa al sitio de Sahi Tests
    When Selecciona la opción <Opcion>
    And Selecciona el error a validar <Error>
    Then Valida que muestra el error esperado

    Examples: 
      | Opcion        | Error      |
      | "Error Pages" | "500 Page" |
