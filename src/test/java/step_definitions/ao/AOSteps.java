package step_definitions.ao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;

import com.microsoft.playwright.APIResponse;
import api.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class AOSteps {
    
    private static String API_TOKEN;

    // New API Response for each request.
    APIResponse response;
    String actionIdentifier;

    AppSubmissionAO testAppSubmissionAO;
    
    @Before
    public void setUp() {
        testAppSubmissionAO = new AppSubmissionAO();
    }

    @After
    public void tearDown() {
        testAppSubmissionAO.disposeAPIRequestContext();
        testAppSubmissionAO.closeContext();
        testAppSubmissionAO.closePlaywright();
    }
    
    @Given("^I send the \"(.*)\" request in AO$")
    public void iSendTheRequest(String nameOfRequest) throws Throwable {
        JSONObject jsonResponse;
        switch (nameOfRequest) {
            case "collectApplyStats":
                response = testAppSubmissionAO.sendRequestCollectApplyStats();
                break;
            case "sendAcceptanceCode":
                response = testAppSubmissionAO.sendRequestSendAcceptanceCode();
                jsonResponse = new JSONObject(response.text());
                actionIdentifier = jsonResponse.get("actionIdentifier").toString();
                break;
            case "token":
                response = testAppSubmissionAO.sendRequestToken(actionIdentifier);
                jsonResponse = new JSONObject(response.text());
                API_TOKEN = jsonResponse.get("access_token").toString();
                testAppSubmissionAO.setAPI_TOKEN(API_TOKEN);
                break;
            case "getFormType":
                response = testAppSubmissionAO.sendRequestGetFormType();
                break;
            case "updatePersonalData":
                response = testAppSubmissionAO.sendRequestUpdatePersonalData();
                break;
            case "updateRegistrationAddressData":
                response = testAppSubmissionAO.sendRequestUpdatePersonalData();
                break;
            case "updateEmploymentData":
                response = testAppSubmissionAO.sendRequestUpdateEmploymentData();
                break;
            case "updateConditionData":
                response = testAppSubmissionAO.sendRequestUpdateConditionData();
                break;
            case "updateContactPersonData":
                response = testAppSubmissionAO.sendRequestUpdateContactPersonData();
                break;
            case "create":
                response = testAppSubmissionAO.sendRequestCreate();
                break;
            default:
                System.out.println("Invalid request: " + nameOfRequest);
                break;
        }
    }

    @When("^I check if the response is successful in AO$")
    public void iCheckIfTheResponseCodeIs() throws Throwable{
        assertTrue(response.ok(), response.text());
    }

    @When("^I check if the response body contains key \"(.*)\" in AO$")
    public void iCheckIfTheResponseBodyContains(String key) throws Throwable{
        JSONObject jsonResponse = new JSONObject(response.text());
        assertTrue(jsonResponse.has(key), response.text());
    }
}