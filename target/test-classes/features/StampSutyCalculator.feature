Feature: Check motor vehicle stamp duty calculation

  Scenario: User verifies stamp duty calculation for a vehicle
    Given the user is on the Service NSW stamp duty page
    When the user clicks the Check online button
    Then the Revenue NSW calculator page should be displayed

    When the user selects Yes for passenger vehicle
    And the user enters a purchase price of "45000"
    And the user clicks the Calculate button
    Then the calculation popup should display correct information