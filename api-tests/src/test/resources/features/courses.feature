Feature: Courses
  @Happy
  Scenario: Get all courses
    When I request all courses
    Then the response status should be 200
    And the response should contain all courses
