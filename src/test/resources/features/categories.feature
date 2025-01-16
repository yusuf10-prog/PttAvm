Feature: PTT AVM Category Navigation Test
  As a user
  I want to navigate through different categories
  So that I can browse products in specific categories

  Scenario: Navigate to Electronics and Cosmetics categories
    Given I am on the PTT AVM homepage
    When I click on "Elektronik" category
    Then I should see the electronics page
    When I navigate back to homepage
    And I click on "Kozmetik" category
    Then I should see the cosmetics page
