Feature: User Registration

  Scenario: Successful user registration
    Given Navigate to the Ikea
    When user clicks on the registration button
    Then user enters valid registration details
      | First Name       | John             |
      | Last Name        | Doe              |
      | Email            | john@test.com    |
      | Phone Number     | 5488716548       |
      | Password         | TestPassword123! |
      | Confirm Password | TestPassword123! |
    And user accepts the terms and conditions
    And user accepts the commercial communication consent
    And user selects SMS and E-Mail as the preferred communication method
    And user grants permission for data transfer abroad
    And user submits the registration form
    Then user should see a registration success message