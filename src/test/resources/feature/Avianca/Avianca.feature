@Avianca @Regresion
Feature: Vuelos
  Como usuario requiero validar que el sitio me permite realizar login
  Para posteriormente realizar una compra

  @Avianca
  Scenario Outline: Validar tickets
    Given Ingresa a la app de avianca
    #When Escribe el <Usuario> y la <Clave>
    #And Pulsa el botón Login
    #Then Valida inicio de sesion exitoso

    Examples: 
      | Usuario | Clave    |
      | "test"  | "secret" |
