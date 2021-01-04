# Nordea Test Automation Framework
This project automates for checking Main and Careers pages via https://www.nordea.com/en/ test scenarios

## Table of Contents
* [Tech Stack](https://github.com/irookanji/nordea.com-en#tech-stack-used)
* [Getting Started](https://github.com/irookanji/nordea.com-en#getting-started)
* [Running from IntelliJ IDEA](https://github.com/irookanji/nordea.com-en#running-autotests-from-IntelliJ-IDEA-IDE)
* [Running from command line](https://github.com/irookanji/nordea.com-en#running-autotests-from-command-line-or-terminal)
* [Framework Structure](https://github.com/irookanji/nordea.com-en#framework-structure)
* [Test Layers](https://github.com/irookanji/nordea.com-en#test-layers)
* [Supported Browsers](https://github.com/irookanji/nordea.com-en#tests-support-cross-platform-browser-testing)
* [Jenkins job](https://github.com/irookanji/nordea.com-en#jenkins-job)
* [Results Reporting](https://github.com/irookanji/nordea.com-en#results-reporting)
* [Code Design](https://github.com/irookanji/nordea.com-en#code-design)
* [License](https://github.com/irookanji/nordea.com-en#license)

## Tech Stack used
* Java 11 - coding
* Gradle - project builder
* JUnit5 - runner
* Params - parameterized tests
* Selenide - Selenium WebDriver wrapper
* Allure - test reporting tool
* Selenoid - a robust implementation of the Selenium Hub using Docker containers to launch browsers
* Jenkins - CI/CD solution tool
* Logback - logger
  
## Getting Started  
Software to be pre-installed : 
* JDK 11 - https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
* Gradle - latest version here https://gradle.org/install
```
note that both tools should be set to environment variables      
```
## Running autotests from IntelliJ IDEA IDE
1. Clone repository
```
git clone https://github.com/irookanji/nordea.com-en
```
2. Open File -> New -> Project from Existing Source -> Select `../nordea.com-en/build.gradle` -> Click 'Next' every prompt
3. Go to `nordea.com-en/src/test/java/tests/NordeaTests.java` -> click rmb -> click 'Run...' in context menu to execute all existing tests

## Running autotests from command line or terminal
1. Clone repository
```
git clone https://github.com/irookanji/nordea.com-en
```
2. Open directory: `nordea.com-en/` 
3. In command line execute:
* ```gradle test``` - executes all tests inside `nordea.com-en/src/test/java/tests/` with default browser set in config file
* ```gradle test -Dremote.browser.url=selenoid.autotests.cloud``` - executes tests remotely in selenoid cloud can be seen [here](https://selenoid.autotests.cloud/#/) while running tests

## Framework Structure
* Programming language – Java 11
* Page Object – Separate class for every web-page, that hold all functionality and members of that web-page
* Test base class: Deals with all the common functions used by all the pages, responsible for test launch, for reports, pre-cond post-cond, web-driver init, loading configs etc 
* Params: Is parameterized tests. This feature enables us to execute a single test method multiple times with different parameters
* Packages: I have separate packages for Pages, Tests and any other framework layer
* Helpers: This code (AttachmentsHelper) helps to catch attachments for a more detailed reports(logs, images, page source, video)
* Selenoid: Is a powerful implementation of Selenium Hub using Docker containers to launch browsers. When the test starts, the required container is created, the test is executed and the container is removed. Tests executed in docker containers are more stable. It was installed remotely.
* Jenkins: I have configured Jenkins job to build tests in Selenoid and reporting with Allure report tool (Allure plugin was connected). Jenkins is also hosted remotely. It can be configured the tests execution on a schedule.
* VCS: Git

## Test Layers
* Pages(web-pages and elements)
* Tests (logic implementation)
* Test launch(runners)
* Test data(feature files)

## Tests support cross-platform browser testing
 * Windows: 
      * Chrome
      * FireFox
 * MacOS:
      * Chrome
      * FireFox

Default browser is Chrome. To change browser, go to TestBase file => @BeforeAll and set 'Configuration.browser = "firefox";'. Or override this property from cmd/terminal using:
```
gradle -Dbrowser=browser_name test
```
P.S. Didn't have a chance to configure and test Safari browser, however Chrome and Firefox are supported on both platforms

## Jenkins job
Jenkins job and the entire history of its launches can be found [here](https://jenkins.autotests.cloud/view/cohort_03/job/c03-g10-irookanji-nordea.com/)

## Results Reporting
Every step is logged by Allure. It generates all actions performed by scripts to a separate html document and saves the entire history of running Jenkins tests.
To browse Allure reports, go to:
```
https://jenkins.autotests.cloud/view/cohort_03/job/c03-g10-irookanji-nordea.com/7/allure/      
```

P.S. There are many opportunities to add any other convenient reporter tool to this project

## Code Design
Project follows [Google Java](https://google.github.io/styleguide/javaguide.html) code style guide

For more detailed information about code design, please refer to in-code documentation

## License
Project is licensed under [MIT](https://github.com/irookanji/nordea.com-en/blob/master/LICENSE.md)
