package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.response.Response;
import org.pmw.tinylog.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class AssignmentApi {

    private BaseStepDefinitions baseStepDefinitions;
    private Response response;

    public AssignmentApi(BaseStepDefinitions baseStepDefinitions){
        this.baseStepDefinitions = baseStepDefinitions;
    }

    @Given("^that I am connected to the test environment$")
    public void thatIAmConnectedToTheTestEnvironment() throws Throwable {
        Logger.info("Given Condition");
        baseStepDefinitions.requestSpecification
                .baseUri(baseStepDefinitions.envData.get("base-uri").asText())
                .port(baseStepDefinitions.envData.get("base-uri-port").asInt());
    }

    @When("^I perform a GET request to the \"([^\"]*)\" endpoint$")
    public void iPerformAGETRequestToTheEndpoint(String endPoint) throws Throwable {
        Logger.info("GET Request made to endpoint " + endPoint);
        response = baseStepDefinitions.requestSpecification.get(endPoint);
    }

    @Then("^I recieve a \"([^\"]*)\" status$")
    public void iRecieveAStatus(String statusCode) throws Throwable {
        assertThat(response.statusCode()).isEqualTo(Integer.parseInt(statusCode));
    }

    @Then("^I revieve the response type as \"([^\"]*)\"$")
    public void iRecieveTheResponseTypeAs(String responseType) throws Throwable {
        assertThat(response.getContentType()).contains("application/"+ responseType);
    }

    @And("^the response contains the randomName, randomChuckNorrisJoke and combinedMessage fields$")
    public void theResponseContainsTheRandomNameRandomChuckNorrisJokeAndCombinedMessageFields() throws Throwable {
        assertThat(response.body().asString()).contains("\"randomName\":", "\"randomChuckNorrisJoke\":", "\"combinedMessage\":");
    }

    @And("^I compare the combinedMessage with the randomName and randomChuckNorrisJoke$")
    public void iCompareTheCombinedMessageWithTheRandomNameAndRandomChuckNorrisJoke() throws Throwable {
        Logger.info("Compare Combined Message step placeholder");   
    }

    @Then("^combinedMessage should replace randomName with John Doe in randomChuckNorrisJoke$")
    public void combinedMessageShouldReplaceRandomNameWithJohnDoeInChuckNorrisJoke() throws Throwable {
        assertThat(response.getBody().jsonPath().get("combinedMessage").toString()).isEqualTo(
            response.getBody().jsonPath().get("randomChuckNorrisJoke").toString().replace("John Doe", response.getBody().jsonPath().get("randomName").toString()));
    }

    @When("^I perform a POST request to the \"([^\"]*)\" endpoint$")
    public void iPerformAPOSTRequestToTheEndpoint(String endPoint) throws Throwable {
        Logger.info("GET Request made to endpoint " + endPoint);
        response = baseStepDefinitions.requestSpecification.post(endPoint);
    }

    @When("^I perform a DELETE request to the \"([^\"]*)\" endpoint$")
    public void iPerformADELETERequestToTheEndpoint(String endPoint) throws Throwable {
        Logger.info("GET Request made to endpoint " + endPoint);
        response = baseStepDefinitions.requestSpecification.delete(endPoint);
    }

    @When("^I perform a PUT request to the \"([^\"]*)\" endpoint$")
    public void iPerformAPUTRequestToTheEndpoint(String endPoint) throws Throwable {
        Logger.info("GET Request made to endpoint " + endPoint);
        response = baseStepDefinitions.requestSpecification.put(endPoint);
    }
}
