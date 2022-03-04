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

  Scenario: User Login and Sign Up
    Given User is on Home Page when he apply for registration
    When user enter personal data "emailid" , "password" , "full name" and "mobile number"
    Then on users registered email id verification email will be sent

  Scenario: Reset Password and forgot password
    Given Buyer is on Login Page
    When Buyer clicks on forgot password button "emailid " option will be showed, buyer enter email id a link will be sent on registered email
    When Buyer proceeds buyer will enter "newPassword" and "confirmPassword"
    Then Password is changed to "newPassword" and password will reset

  Scenario: To See Filter Options
    Given : Buyer is on listing page
    When :  Buyer search for products various option to "filter" should be displayed
    Then :  Products related to selected filter will be displayed
