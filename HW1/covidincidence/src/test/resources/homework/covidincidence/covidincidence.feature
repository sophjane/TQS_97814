Feature: Check Covid Incidence

    Scenario: Find statistics of country
        When I navigate to "http://localhost:3000/"
        And I click on "Statistics"
        And I select "barbados" on the country option
        And I click on Search
        Then I should see the results in a table

    Scenario: Find statistics
        When I navigate to "http://localhost:3000/"
        And I click on "Statistics"
        And I click on Search
        Then I should see the results in a table

    Scenario: Find history of country in a certain day
        When I navigate to "http://localhost:3000/"
        And I click on "History"
        And I select "australia" on the country option
        And I select "07-04-2021" on the date option
        And I click on Search
        Then I should see the results in a table


 Scenario: Find history of country
        When I navigate to "http://localhost:3000/"
        And I click on "History"
        And I select "albania" on the country option
        And I click on Search
        Then I should see the results in a table

    