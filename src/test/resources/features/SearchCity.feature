Feature: Search city functionality
  As a user
  I want to search for a city
  So that I can see weather information

  Scenario: Search for a city
    Given I open OpenWeatherMap website
    When I search for city
    And I click the city on the result popup
    Then It should be show the city name displayed as city
    And It should be display temperature displayed as a number
    And It should be display current date correctly