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
Feature: Order Management

Scenario Outline: see order deatils
Given Buyer is on checkout page
When Buyer click "<orderdetail>" for ordered product
Then Buyer should see "<orderid>", "<orderimage>" , "<date>" , "<type>" and 1234 as result

Examples: 
| orderdetail | orderid | orderimage | date | type | amount|
| order1 | OID1234 | img1 | 10/02/22-15/02/22 | rent | 6000|
| order2 | OID5678 | img2 | 20/02/22 | buy | 60000|
      
