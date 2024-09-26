# Amazon Test Automation

This repository contains the test automation scripts for two scenarios on the Amazon website, using Java and Selenium WebDriver. The scripts perform actions such as searching for items, adding them to the cart, and applying filters to select specific deals.

## Project Overview

This project automates two test scenarios:

### Task 1

#### Scenario 1: Search and Add to Cart
1. Navigate to [Amazon](https://www.amazon.com/).
2. Search for "car accessories" using the search bar.
3. Select the first item from the search results.
4. Add the item to the cart.
5. Go to the cart and verify that the item was successfully added.

#### Scenario 2: Apply Deals Filter and Add to Cart
1. Open Amazon and navigate to **Today's Deals**.
2. From the left-side filters, select **Headphones** and **Grocery** categories.
3. From the discount section, select **10% off or more**.
4. Navigate to the fourth page of the filtered deals.
5. Select any item on this page and add it to the cart.

## Project Setup

### Prerequisites
- Java (version 8 or higher)
- Maven
- Selenium WebDriver
- ChromeDriver (or any other driver for your browser)
- TestNG

### Dependencies

This project uses Maven to manage dependencies. The key dependencies for the project include:

```xml
<dependencies>
    <!-- Selenium WebDriver -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.1.0</version>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.4.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Alternatively, you can run the test using TestNG in your IDE by right-clicking the test file and selecting "Run."

## Folder Structure
- **src/main/java**: Contains the main Java classes for test automation.
- **src/test/java**: Contains the test cases and TestNG configurations.

## Key Classes
- `AmazonTest.java`: Contains the test scenarios for searching items, applying filters, and adding them to the cart.

## Customizations
You can modify the search queries or filters by updating the test methods in `AmazonTest.java` to fit your needs.

## Reporting
Test results can be viewed in the terminal output or using any report generation plugin like **ExtentReports** or **Allure** (optional).

## Author
Michael Magdy Fekry
