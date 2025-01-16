Feature: PTT AVM Social Media Icons Test
  As a user
  I want to verify all social media icons on PTT AVM homepage
  So that I can ensure they are working correctly

  Scenario: Verify social media icons are present and clickable
    Given I am on the PTT AVM homepage
    Then I should see the following social media icons
      | Facebook  |
      | Twitter   |
      | Instagram |
      | YouTube   |
    When I click on each social media icon
    Then each social media page should open in a new tab
