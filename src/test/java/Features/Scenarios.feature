Feature: Trendyol


  Scenario: User logins successfully
    Given a web browser is at the homepage
    And User clicks gender choice
    Then User clicks login button on homepage
    Then User fill username info
    And User fill password info
    Then User clicks login button on login page
    And User login successfully
    Then Driver close

  Scenario: Add product to Basket
    Given a web browser page
    Then User logins
    Then Write brand on search area
    Then Filter brand
    Then Enter min price
    And Enter max price
    Then Filter price
    Then Add product to basket
    Then check product on basket
    Then Close Driver

  Scenario: Add fav'd product to Basket
    Given a web page
    Then User login
    Then Write product on search area
    Then Favorite product
    Then Click favorites
    Then Add fav'd product to basket
    Then Driver Quit

  Scenario: Check Images
    Given web page
    Then Click first tab
    And Click first component
    Then Check Product Images
    Then Control other tabs
    Then Quit Driver



