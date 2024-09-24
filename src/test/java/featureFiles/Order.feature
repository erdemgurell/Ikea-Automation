@RegressionTest
Feature: Order Process

  Background:
    Given Navigate to the Ikea

  Scenario: User places an order successfully
    Given user searches for a product "Masa"
    When user selects a product from the search results
    And user adds the product to the cart
    And user adds an address for delivery
      | Email        | john@test.com |
      | First Name   | John          |
      | Last Name    | Doe           |
      | Phone Number | 5488716548    |
    And user proceeds to the checkout page and completes the purchase