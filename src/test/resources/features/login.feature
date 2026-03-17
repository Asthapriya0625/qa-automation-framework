# ══════════════════════════════════════════════════════
# Feature: User Login & Authentication
#
# Business Value:
#   Validates the core authentication gate — the most
#   critical user journey on any web application.
#
# Tags:
#   @smoke      → critical path, run on every build
#   @regression → full suite, run before every release
#   @login      → feature-level tag for selective runs
# ══════════════════════════════════════════════════════

@login
Feature: User Login and Authentication

  Background:
    Given the user is on the login page

  # ── SMOKE TESTS ──────────────────────────────────
  @smoke @regression
  Scenario: Successful login with valid credentials
    When the user enters username "tomsmith" and password "SuperSecretPassword!"
    And the user clicks the login button
    Then the user should be logged in successfully
    And the success message should contain "You logged into a secure area!"

  @smoke @regression
  Scenario: Failed login with invalid password
    When the user enters username "tomsmith" and password "wrongpassword"
    And the user clicks the login button
    Then the user should see a login error message
    And the error message should contain "Your password is invalid!"

  # ── REGRESSION TESTS ─────────────────────────────
  @regression
  Scenario: Failed login with invalid username
    When the user enters username "invaliduser" and password "SuperSecretPassword!"
    And the user clicks the login button
    Then the user should see a login error message
    And the error message should contain "Your username is invalid!"

  @regression
  Scenario: Failed login with empty credentials
    When the user enters username "" and password ""
    And the user clicks the login button
    Then the user should see a login error message

  @regression
  Scenario: Successful logout after login
    When the user enters username "tomsmith" and password "SuperSecretPassword!"
    And the user clicks the login button
    Then the user should be logged in successfully
    When the user logs out
    Then the user should be redirected to the login page

  # ── DATA-DRIVEN: Outline ─────────────────────────
  @regression
  Scenario Outline: Login with multiple credential combinations
    When the user enters username "<username>" and password "<password>"
    And the user clicks the login button
    Then the <expectedOutcome>

    Examples:
      | username  | password               | expectedOutcome                           |
      | tomsmith  | SuperSecretPassword!   | user should be logged in successfully     |
      | tomsmith  | wrongpass              | user should see a login error message     |
      | baduser   | SuperSecretPassword!   | user should see a login error message     |
