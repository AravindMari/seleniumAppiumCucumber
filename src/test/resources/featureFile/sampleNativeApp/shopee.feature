@mobileNative
  #CLI Command: mvn clean test -P=sampleNativeApp '-Dcucumber.filter.tags="@sampleApp"' -Dplatform="android"
Feature: Appium Practice

  @changeAddress @sampleApp
  Scenario: User should be able to choose a product, place an order and changes his address before payment
    Given User launch the Shopee app
    When user add a product to cart
    And tries to change address before payment
    Then user is able to save his new address

  @validateNumField
  Scenario Outline: User should be allowed only to enter digit in Mobile number field during address change
    Given User launch the Shopee app
    When user add a product to cart
    And enter mobile number during change address "<mobileNumber>"
    Then user should be able to only enter digit
    Examples:
      |mobileNumber|
      |9876543210|