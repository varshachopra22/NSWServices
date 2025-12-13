Feature: Validate Author Details from Open Library API

  Scenario: Verify personal name and alternate names from OL1A endpoint
    Given I perform a GET call to "https://openlibrary.org/authors/OL1A.json"
    Then the response status code should be 200
    And the response body should contain
      | field            | value                      |
      | personal_name    | Sachi Rautroy              |
      | alternate_names  | Yugashrashta Sachi Routray |