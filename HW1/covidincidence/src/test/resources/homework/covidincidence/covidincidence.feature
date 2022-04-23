Feature: Check Covid Incidence

    # Scenario: Find statistics of country
    #     When I navigate to "http://localhost:3000/"
    #     And I click on "Statistics"
    #     And I select "barbados" on the country option
    #     And I click on Search
    #     Then I should see the results in a table

    Scenario: Find history of country in a certain day
        When I navigate to "http://localhost:3000/"
        And I click on "History"
        And I select "australia" on the country option
        And I select "2022-04-07" on the date option
        And I click on Search
        Then I should see the results in a table


    