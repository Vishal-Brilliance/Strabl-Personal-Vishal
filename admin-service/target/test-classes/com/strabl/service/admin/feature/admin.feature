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
Feature: Admin Management

  Scenario: Admin want to see option to add/view/edit/delete user
    Given Admin is on admin Dashboard
    When Admin will navigate to "addUser" he will be able to add user and when Admin will navigate to "viewUser" he will able to edit or delete user
    Then The add/view/edit/delete user operations would be performed
 
Scenario: admin will have option to approve and disapprove products
    Given Admin is on Admin Dashboard
    When  Admin will navigate all products
    And Admin will able to see "publish" and "unpublish" options
    Then Operation will be performed as per Admin
    