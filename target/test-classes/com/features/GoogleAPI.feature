Feature: Validating the Google APIs

  Scenario Outline: Verify if the place is added using the AddPlaceAPI
    Given User has the payload for AddPlace "<name>" "<language>" "<address>"
    When User call the "AddPlaceApi" "POST" http request
    Then API call is success with the status code 200
    And "status" in the response body is "OK"
    And "scope" in the response body is "APP" 
    And Verify the "<name>" using "GetPlaceApi"
  Examples:
 |name|language|address|
 |AAhouse|English|Shantinagar|
# |BBHouse|French|Keshwapur|
 

  