@Login @Regresion
Feature: Login
  Como usuario requiero validar que el sitio me permite realizar login
  Para posteriormente realizar una compra

  @InicioSesionCorrecto
  Scenario Outline: Validar inicio de sesion correcto
    Given Ingresa al sitio
    When Escribe el <Usuario> y la <Clave>
    And Pulsa el botón Login
    Then Valida inicio de sesion exitoso

    Examples: 
      | Usuario | Clave    |
      ##@externaldata@./src/test/resources/DataDriven/Login/Login.xlsx@Caso01
      | "test"  | "secret" |
