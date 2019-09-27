Feature: Random Name and Random Joke Concatenation API tests

  @regression
  Scenario: To test the GET call for /concat endpoint
    Given that I am connected to the test environment
    When I perform a GET request to the "/concat" endpoint
    Then I recieve a "200" status
    And I revieve the response type as "json"
    And the response contains the randomName, randomChuckNorrisJoke and combinedMessage fields

  @regression
  Scenario: To test the /concat api concatenates the random name and joke properly
    Given that I am connected to the test environment
    When I perform a GET request to the "/concat" endpoint
    And I compare the combinedMessage with the randomName and randomChuckNorrisJoke
    Then combinedMessage should replace randomName with John Doe in randomChuckNorrisJoke

  @regression
  Scenario: To test the POST call for /concat endpoint when the endpoint is not available for POST
    Given that I am connected to the test environment
    When I perform a POST request to the "/concat" endpoint
    Then I recieve a "405" status

  @regression
  Scenario: To test the DELETE call for /concat endpoint when the endpoint is not available for DELETE
    Given that I am connected to the test environment
    When I perform a DELETE request to the "/concat" endpoint
    Then I recieve a "405" status

  @regression
  Scenario: To test the PUT call for /concat endpoint when the endpoint is not available for PUT
    Given that I am connected to the test environment
    When I perform a PUT request to the "/concat" endpoint
    Then I recieve a "405" status
    