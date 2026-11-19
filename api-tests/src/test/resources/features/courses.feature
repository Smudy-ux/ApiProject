Feature: Courses

  @Happy
  Scenario: Get all courses
    When I request all courses
    Then the response status should be 200
    And the response should contain all courses

  @Happy
  Scenario: Request one course
    When I request course 1
    Then the response status should be 200
    And the response should contain course details

  @Sad
  Scenario Outline: Request course with invalid id
    When I request course "<id>"
    Then the response status should be <status>

    Examples:
      | id   | status |
      | 0    | 404    |
      | -1   | 404    |
      | 6    | 404    |
      | 999  | 404    |
      | abc  | 400    |
      | 1.5  | 400    |
      | !@#  | 400    |