# ══════════════════════════════════════════════════════
# Feature: Checkbox Interactions
#
# Business Value:
#   Validates form element interactions — checkboxes are
#   commonly used in preference settings, filters, and
#   bulk-action UIs across SaaS platforms.
# ══════════════════════════════════════════════════════

@checkboxes
Feature: Checkbox Interactions and State Management

  Background:
    Given the user is on the checkboxes page

  @smoke @regression
  Scenario: Page displays correct number of checkboxes
    Then the page should display 2 checkboxes

  @regression
  Scenario: User can check an unchecked checkbox
    When the user checks checkbox number 1
    Then checkbox number 1 should be checked

  @regression
  Scenario: User can uncheck a checked checkbox
    When the user checks checkbox number 1
    Then checkbox number 1 should be checked
    When the user unchecks checkbox number 1
    Then checkbox number 1 should be unchecked

  @regression
  Scenario: User can check all checkboxes
    When the user checks all checkboxes
    Then all checkboxes should be checked

  @regression
  Scenario: Checkbox state persists after page interaction
    When the user checks checkbox number 1
    And the user checks checkbox number 2
    Then checkbox number 1 should be checked
    And checkbox number 2 should be checked
