@Avianca @Regresion
Feature: Vuelos
  Como usuario requiero validar que el sitio me permite realizar una busqueda de vuelos
  Para posteriormente realizar una compra

  @Avianca
  Scenario Outline: Validar que la app permite el ingreso a buscar vuelos
    Given Ingresa a la app de avianca en el <Dispositivo>
    When Busca un vuelo de solo ida
    And  Selecciona el primer aeropuerto de origen
    And  Selecciona el primer aeropuerto de destino
    And  Selecciona la fecha actual para el vuelo
    And  Busca un vuelo
    Then Valida que se muestra la pagina de buscar vuelo

    Examples: 
      | Dispositivo |
      | "SAMSUNG"  |
