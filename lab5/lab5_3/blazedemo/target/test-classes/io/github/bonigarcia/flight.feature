Feature: Buy a flight ticket

  Scenario: Find flights
    When I navigate to "https://blazedemo.com"
    And I select to travel from "Paris" to "London"
    And I click on Find Flights
    And I select the flight number 3
    And I type "Sherlock Holmes" on the "inputName"
    And I type "221B Baker Street" on the "address"
    And I type "London" on the "city"
    And I type "NW16XE" on the "zipCode"
    And I type "1234567891011121" on the "creditCardNumber"
    And I type "2025" on the "creditCardYear"
    And I type "Sherlock Holmes" on the "nameOnCard"
    And I click on Remember Me
    And I click on Purchase Flight
    Then I should get the purchased flight information  