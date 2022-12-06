Steps for reporting
1. Get the code from github damianszczepanik
/
maven-cucumber-reporting

2. Update the latest version
3. create the folders in the target as mentioned in the code
4. update the test runner file with the  plugin = {"json:target/jsonReports/CucumberReport.json"}
5. Run the test with mvn test verify