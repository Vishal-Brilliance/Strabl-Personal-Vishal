$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/com/strabl/service/user/feature/google.feature");
formatter.feature({
  "name": "User Management",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Sign-up will google",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "Buyer is on Sign-up page",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinition.Google.buyer_is_on_Sign_up_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Buyer will click on Google Sign-up option",
  "keyword": "And "
});
formatter.match({
  "location": "StepDefinition.Google.buyer_will_click_on_Google_Sign_up_option()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "google sign-up window will appear with all accounts of user",
  "keyword": "And "
});
formatter.match({
  "location": "StepDefinition.Google.google_sign_up_window_will_appear_with_all_accounts_of_user()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "when Buyer will select \"emailaccount\"",
  "keyword": "When "
});
formatter.match({
  "location": "StepDefinition.Google.when_Buyer_will_select(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Buyer will be signed-in through the selected account",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDefinition.Google.buyer_will_be_signed_in_through_the_selected_account()"
});
formatter.result({
  "error_message": "java.lang.NullPointerException: Cannot invoke \"java.util.List.contains(Object)\" because the return value of \"com.strabl.service.user.bdd.Account.googleAccount()\" is null\r\n\tat com.strabl.service.user.bdd.Display.showaccount(Display.java:7)\r\n\tat StepDefinition.Google.buyer_will_be_signed_in_through_the_selected_account(Google.java:48)\r\n\tat ✽.Buyer will be signed-in through the selected account(file:///C:/strabl-internal-main/user-service/src/test/java/com/strabl/service/user/feature/google.feature:28)\r\n",
  "status": "failed"
});
});