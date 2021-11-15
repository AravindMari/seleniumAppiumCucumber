#@web
# mvn clean test -P=weatherShopper '-Dcucumber.filter.tags="@WSsanity"' -Dbrowser="chrome" -Denv="prod"
# mvn clean test -P=weatherShopperParallel '-Dcucumber.filter.tags="@WSsanity"' -Dbrowser="chrome" -Denv="prod"

Feature: Weather Shopper Application

  @chrome @WSsanity
  Scenario: User should be able to order products based on current weather
    Given user is on Weather Shopper application Home screen
    When user choose a product type based on current weather
    And add products to the cart based on requirement
    And made a payment
    Then user should be able to order products successfully

#    below duplicate scenario is added to show parallel execution
  @firefox @WSsanity
  Scenario: User should be able to order products based on current weather
    Given user is on Weather Shopper application Home screen
    When user choose a product type based on current weather
    And add products to the cart based on requirement
    And made a payment
    Then user should be able to order products successfully