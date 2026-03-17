@dropdown
Feature: Dropdown Selection and Validation

  Background:
    Given the user is on the dropdown page

  @smoke @regression
  Scenario: Dropdown loads with correct number of options
    Then the dropdown should contain 3 options

  @regression
  Scenario: User can select Option 1
    When the user selects "Option 1" from the dropdown
    Then the selected option should be "Option 1"

  @regression
  Scenario: User can select Option 2
    When the user selects "Option 2" from the dropdown
    Then the selected option should be "Option 2"

  @regression
  Scenario: Dropdown contains expected options
    Then the dropdown should include option "Option 1"
    And the dropdown should include option "Option 2"

  @regression
  Scenario Outline: Data-driven dropdown selection
    When the user selects "<option>" from the dropdown
    Then the selected option should be "<option>"

    Examples:
      | option   |
      | Option 1 |
      | Option 2 |
