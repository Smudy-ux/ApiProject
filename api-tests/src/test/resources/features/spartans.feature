Feature: Spartans

  @Happy
  Scenario: Get all spartans with a valid token
    When I have a valid token
    And I request all spartans
    Then the response status should be 200
    And the response should contain all spartans

  @Sad
  Scenario: Get all spartans with an invalid token
    When I don't have a valid token
    And I request all spartans
    Then the response status should be 401

  @Happy
  Scenario: Create a new spartan with valid details
    When I have a valid token
    And I create a spartan with valid details
    Then the response status should be 200
    And the response should contain the created spartan details

  @Sad
  Scenario: Create a new spartan with an invalid token
    When I don't have a valid token
    And I create a spartan with valid details
    Then the response status should be 401

  @Sad
  Scenario Outline: Create a spartan with invalid data
    When I have a valid token
    And I create a spartan with firstName "<firstName>", lastName "<lastName>", university "<university>", degree "<degree>", courseId <courseId>, and graduated "<graduated>"
    Then the response status should be 400

    Examples:
      | firstName | lastName | university        | degree           | courseId | graduated |
      |           | Smith    | Oxford University | Computer Science | 2        | true      |
      | Jane      |          | Oxford University | Computer Science | 2        | true      |
      | Jane      | Smith    |                   | Computer Science | 2        | true      |
      | Jane      | Smith    | Oxford University |                  | 2        | true      |
      | Jane      | Smith    | Oxford University | Computer Science | 999      | true      |