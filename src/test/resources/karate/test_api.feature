Feature: Validate api tests
  Yo... Como... Para...

#  Background:
#    * url 'https://rickandmortyapi.com/api/'
#
#  Scenario: Validar get de api rick and morty para un personaje sea exitosa
#    Given url
#    When method get
#    Then status 200
#    And match response != 'Error'
#    And match response contains {gender: #notnull}

  Background:
    * url urlBase
#    * configure headers = {'Content-type: 'application/json'}

  Scenario: Validar get de api rick and morty para un personaje sea exitosa
    Given path 'character/2'
    When method get
    Then status 200
    And match response != 'Error'
    And match response contains {gender: #notnull}

  Scenario: Validar get de api rick and morty con filtro de nombre para personaje y su estado 'vivo' sea exitosa
    Given path 'character/?name=rick&status=alive'
    When method get
    Then status 200
    And match response != 'Error'
    And match response contains {id: #notnull, status: "Alive"}