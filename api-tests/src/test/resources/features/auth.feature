Feature: Authentication
  @Happy
  Scenario: Login with valid credentials
    When I log in with username "sparta" and password "global"
    Then the response status should be 200
    And the response should contain a token

  @Sad
  Scenario Outline: Login with invalid credentials
    When I log in with username "<username>" and password "<password>"
    Then the response status should be 401

    Examples:
      | username | password |
      | sparta   | wrong    |
      | wrong    | global   |
      | wrong    | wrong    |
      | sparta   |          |
      |          |          |
      | Sparta   | global   |
      | sparta   | Global   |
      | global   | sparta   |
