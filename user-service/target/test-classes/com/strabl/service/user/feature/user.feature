#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: User Management
Scenario: add multiple card for payment

Given Buyer is on checkout page
When Buyer will click on add new card
And buyer will enter card details 1234 , "expdate" , 345 and "cardholdername"
Then card should be added

Scenario: Get related Products 
    Given Buyer is on Product detail page
    When Buyer will "search" for a Product
    And  If no Product is Found 
    Then  The "RelatedProduct" will be Displayed
    
Scenario: Sign-up will google
    Given Buyer is on Sign-up page
    And Buyer will click on Google Sign-up option 
    And google sign-up window will appear with all accounts of user
    When when Buyer will select "emailaccount" 
    Then Buyer will be signed-in through the selected account
    





