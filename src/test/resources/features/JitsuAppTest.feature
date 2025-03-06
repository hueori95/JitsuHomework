Feature: Test Jitsu app use appium
  Scenario: Driver logins the apps
    Given I access to the Jitsu app
    When Login to app with valid information username "auto_244332" and password "Testing1!"
    And He opens the Profile screen and taps on the Tutorias
    Then He sees 3 sections: Assigned Route, Direct Booking, Ticket Booking
    And He taps on Assigned Route
    Then He is in Active Assignment screen and sees the starting of tutorial which is chosen in step 4