package StepDefinitions;

import HelperLibrary.DataReader;
import com.fasterxml.jackson.databind.JsonNode;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.pmw.tinylog.Logger;

public class BaseStepDefinitions {

    public RequestSpecification requestSpecification;
    public JsonNode envData;

    @Before
    public void beforeScenario() throws Exception{
        Logger.info("Hook for Setup invoked");
        DataReader dataReader = new DataReader();
        envData = dataReader.getEnvironmentData().get("qa");
        requestSpecification = RestAssured.with().given()
                .relaxedHTTPSValidation();
    }

    @After
    public void afterScenario() throws Exception{
        Logger.info("Hook for Teardown invoked");
        requestSpecification = null;
        envData = null;
    }

}
