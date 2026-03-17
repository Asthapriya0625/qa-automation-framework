@alerts
Feature: JavaScript Alert Handling

  Background:
    Given the user is on the JavaScript alerts page

  @smoke @regression
  Scenario: User accepts a JS alert
    When the user triggers a JS alert
    And the user accepts the alert
    Then the result should show "You successfuly clicked an alert"

  @regression
  Scenario: User accepts a JS confirm dialog
    When the user triggers a JS confirm
    And the user accepts the alert
    Then the result should show "You clicked: Ok"

  @regression
  Scenario: User dismisses a JS confirm dialog
    When the user triggers a JS confirm
    And the user dismisses the alert
    Then the result should show "You clicked: Cancel"

  @regression
  Scenario: User types in a JS prompt and accepts
    When the user triggers a JS prompt
    And the user types "Hello QA World" in the prompt and accepts
    Then the result should show "You entered: Hello QA World"
