
Run ALL testcase: open src/test/java/runners and run "TestRunner"


**Example 1:**
- Project depends on POM structure, use BDD (Gherkin) to write scenario, user Data-Driven Testing to read 
- To run testcase
Case1: open src/test/resources/test-suites and run testcity.xml
Case2: run by cmd:
  mvn test -Dcucumber.features=src/test/resources/features/SearchCity.feature

**Example 2:**
Case1: open src/test/java/runners and run "TestRunner"
mvn test -Dcucumber.features=src/test/resources/features/GitHub.feature


**Example 3:**
Connect to Appium Inspector:
{
"platformName": "Android",
"appium:automationName": "UIAutomator2",
"appium:deviceName": "emulator-5554",
"appium:appPackage": "com.axlehire.drive.staging",
"appium:appActivity": "com.axlehire.drive.MainActivity"
}

- Have to setup JAVA_HOME and ANDROID_HOME in Appium Server
- host: localhost
- port: 4723
- path: /wd/hub
 
mvn test -Dcucumber.features=src/test/resources/features/JitsuAppTest.feature

some button cannot interact by Xpath
I cannot use wait in Appium becasue there are many locators cannot use xpath to work on it, have to use TouchAction in app.

