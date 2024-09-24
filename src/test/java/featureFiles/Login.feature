@SmokeTest
Feature: User Login

  Scenario: Successful user login
    Given Navigate to the Ikea
    When user clicks on the login button
    Then user enters valid login credentials
      | Email    | layibej345@sgatra.com |
      | Password | TestPassword123!      |
    And user submits the login form
    Then user should be redirected to the my orders page