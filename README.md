# 🧪 QA Automation Framework
### BDD Cucumber · Selenium WebDriver · Java · TestNG · CI/CD

[![Regression Tests](https://github.com/Asthapriya0625/qa-automation-framework/actions/workflows/regression.yml/badge.svg)](https://github.com/Asthapriya0625/qa-automation-framework/actions/workflows/regression.yml)
![Java](https://img.shields.io/badge/Java-11-orange?logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.18-green?logo=selenium)
![Cucumber](https://img.shields.io/badge/Cucumber-7.15-brightgreen?logo=cucumber)
![Maven](https://img.shields.io/badge/Maven-3.9-red?logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-blue)

---

An enterprise-grade BDD test automation framework built to validate web applications end-to-end. Designed with scalability, parallel execution, and CI/CD integration in mind — reflecting real-world QA practices used in AdTech SaaS environments.

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Running Tests](#-running-tests)
- [Test Scenarios Covered](#-test-scenarios-covered)
- [Reports](#-reports)
- [CI/CD Pipeline](#-cicd-pipeline)
- [Configuration](#-configuration)
- [Design Patterns](#-design-patterns)

---

## ✨ Features

| Feature | Details |
|---|---|
| **BDD Gherkin Scenarios** | Human-readable `.feature` files written in Given/When/Then syntax |
| **Page Object Model** | Clean separation of UI locators, actions, and test logic |
| **Data-Driven Testing** | Scenario Outlines with Examples tables for broad coverage |
| **Cross-Browser Support** | Chrome, Firefox, Edge — switchable via a single config flag |
| **Headless Execution** | Fully supported for CI/CD pipelines |
| **ThreadLocal WebDriver** | Parallel-safe driver management — no shared state between threads |
| **Auto Screenshots** | Captured on failure and embedded directly in HTML report |
| **Dual Reports** | Cucumber HTML + Extent Reports with dark theme dashboard |
| **Structured Logging** | Log4j2 — per-scenario console + rolling file logs |
| **CI/CD Ready** | GitHub Actions pipeline with nightly schedule + manual trigger |

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 11 |
| Browser Automation | Selenium WebDriver 4.18 |
| BDD Framework | Cucumber 7.15 |
| Test Runner | TestNG 7.9 |
| Build Tool | Maven |
| Driver Management | WebDriverManager (auto-downloads) |
| Reporting | Extent Reports 5 + Cucumber HTML |
| Logging | Log4j2 |
| CI/CD | GitHub Actions |
| Config Management | Owner (type-safe .properties) |

---

## 📁 Project Structure

```
qa-automation-framework/
│
├── .github/
│   └── workflows/
│       └── regression.yml          # GitHub Actions CI pipeline
│
├── src/
│   └── test/
│       ├── java/com/astha/qa/
│       │   ├── config/
│       │   │   └── FrameworkConfig.java     # Type-safe config interface
│       │   ├── hooks/
│       │   │   └── Hooks.java               # @Before/@After - driver + screenshots
│       │   ├── pages/
│       │   │   ├── BasePage.java            # Reusable Selenium helpers
│       │   │   ├── LoginPage.java
│       │   │   ├── CheckboxPage.java
│       │   │   ├── DropdownPage.java
│       │   │   └── AlertPage.java
│       │   ├── runners/
│       │   │   └── TestRunner.java          # Cucumber + TestNG entry point
│       │   ├── steps/
│       │   │   ├── LoginSteps.java
│       │   │   ├── CheckboxSteps.java
│       │   │   ├── DropdownSteps.java
│       │   │   └── AlertSteps.java
│       │   └── utils/
│       │       ├── DriverManager.java       # ThreadLocal WebDriver
│       │       └── ScreenshotUtils.java
│       │
│       └── resources/
│           ├── config/
│           │   └── framework.properties     # All config values
│           ├── features/
│           │   ├── login.feature
│           │   ├── checkboxes.feature
│           │   ├── dropdown.feature
│           │   └── alerts.feature
│           ├── extent-config.xml
│           ├── extent.properties
│           └── log4j2.xml
│
├── testng.xml                               # TestNG suite definition
├── pom.xml                                  # Maven dependencies
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- Java 11+
- Maven 3.8+
- Chrome / Firefox / Edge browser installed
- Git

### Clone & Install

```bash
git clone https://github.com/Asthapriya0625/qa-automation-framework.git
cd qa-automation-framework
mvn clean install -DskipTests
```

> **Note:** WebDriverManager automatically downloads the correct browser driver — no manual chromedriver setup needed.

---

## ▶️ Running Tests

### Run full regression suite
```bash
mvn test
```

### Run smoke tests only
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

### Run a specific feature
```bash
mvn test -Dcucumber.filter.tags="@login"
mvn test -Dcucumber.filter.tags="@dropdown"
mvn test -Dcucumber.filter.tags="@alerts"
```

### Run on Firefox
```bash
mvn test -Dbrowser=firefox
```

### Run headless (no browser window — ideal for CI)
```bash
mvn test -Dheadless=true
```

### Combine options
```bash
mvn test -Dbrowser=firefox -Dheadless=true -Dcucumber.filter.tags="@regression"
```

---

## 📝 Test Scenarios Covered

### 🔐 Login & Authentication (`@login`)
| Scenario | Tags |
|---|---|
| Successful login with valid credentials | `@smoke @regression` |
| Failed login with wrong password | `@smoke @regression` |
| Failed login with invalid username | `@regression` |
| Failed login with empty credentials | `@regression` |
| Successful logout after login | `@regression` |
| Data-driven login with Scenario Outline | `@regression` |

### ☑️ Checkboxes (`@checkboxes`)
| Scenario | Tags |
|---|---|
| Verify correct checkbox count on page load | `@smoke @regression` |
| Check an unchecked checkbox | `@regression` |
| Uncheck a checked checkbox | `@regression` |
| Check all checkboxes | `@regression` |
| Checkbox state persists after interaction | `@regression` |

### 📋 Dropdowns (`@dropdown`)
| Scenario | Tags |
|---|---|
| Dropdown loads with correct number of options | `@smoke @regression` |
| Select Option 1 | `@regression` |
| Select Option 2 | `@regression` |
| Verify all expected options are present | `@regression` |
| Data-driven option selection | `@regression` |

### ⚠️ JavaScript Alerts (`@alerts`)
| Scenario | Tags |
|---|---|
| Accept a JS alert | `@smoke @regression` |
| Accept a JS confirm dialog | `@regression` |
| Dismiss a JS confirm dialog | `@regression` |
| Type in a JS prompt and accept | `@regression` |

---

## 📊 Reports

After running tests, reports are generated in the `reports/` directory:

| Report | Path | Description |
|---|---|---|
| Extent HTML | `reports/extent/ExtentReport.html` | Rich dashboard with graphs, screenshots, steps |
| Cucumber HTML | `reports/cucumber/cucumber-report.html` | Feature/scenario breakdown |
| Cucumber JSON | `reports/cucumber/cucumber-report.json` | For CI integrations |
| JUnit XML | `reports/cucumber/cucumber-report.xml` | For GitHub Actions test reporter |
| Logs | `reports/logs/test-execution.log` | Full execution log |
| Screenshots | `reports/screenshots/` | Failure screenshots (auto-captured) |

---

## ⚙️ CI/CD Pipeline

The GitHub Actions pipeline (`.github/workflows/regression.yml`) runs automatically on:

- **Every push** to `main` or `develop`
- **Every pull request** to `main`
- **Nightly schedule** at 6:00 AM UTC
- **Manual trigger** from the GitHub Actions UI (choose browser + tags)

### Pipeline Steps

```
1. Checkout code
2. Set up Java 11
3. Install Chrome
4. Run tests (headless)
5. Upload HTML reports as artifacts
6. Upload failure screenshots
7. Publish TestNG results summary
```

---

## 🔧 Configuration

All settings live in `src/test/resources/config/framework.properties`:

```properties
browser=chrome            # chrome | firefox | edge
headless=false            # true for CI
env=staging
base.url=https://the-internet.herokuapp.com
implicit.wait=10          # seconds
explicit.wait=20          # seconds
screenshot.on.failure=true
retry.count=1
```

Any value can be overridden at runtime with `-D` flags:
```bash
mvn test -Dbrowser=firefox -Dheadless=true -Denv=production
```

---

## 🏗 Design Patterns

### Page Object Model (POM)
Each page has its own class extending `BasePage`. Locators and actions are encapsulated — tests never interact with Selenium directly.

### ThreadLocal WebDriver
`DriverManager` uses `ThreadLocal<WebDriver>` so each thread gets its own browser instance. This enables safe parallel test execution.

### BDD with Cucumber
Feature files are written in plain English (Gherkin), making them readable by non-technical stakeholders (Product, BA, QA Leads). Step definitions connect Gherkin to Java.

### Data-Driven via Scenario Outline
Scenario Outlines with `Examples` tables allow the same test logic to run across multiple data sets — reducing duplication and increasing coverage.

---

## 👩‍💻 Author

**Astha Priya Jha**  
Senior QA Engineer | AdTech & SaaS Automation Specialist  
📍 Atlanta, GA  
📧 asthapriya0625@gmail.com  
🔗 [LinkedIn](https://linkedin.com/in/https://www.linkedin.com/in/astha-jha-937a4810a/)

---

*Framework built using industry-standard patterns from 6+ years of QA experience in AdTech SaaS platforms.*
