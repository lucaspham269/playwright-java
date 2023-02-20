package step_definitions.ll;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;

import com.microsoft.playwright.APIResponse;
import api.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LLSteps {
    
    private static String API_TOKEN;

    // New API Response for each request.
    APIResponse response;
    String actionIdentifier;

    AppSubmissionLL testAppSubmissionLL;
    
    @Before
    public void setUp() {
        testAppSubmissionLL = new AppSubmissionLL();
    }

    @After
    public void tearDown() {
        testAppSubmissionLL.disposeAPIRequestContext();
        testAppSubmissionLL.closeContext();
        testAppSubmissionLL.closePlaywright();
    }
    
    @Given("^I send the \"(.*)\" request in LL$")
    public void iSendTheRequest(String nameOfRequest) throws Throwable {
        JSONObject jsonResponse;
        switch (nameOfRequest) {
            case "sendNewPhone":
                response = testAppSubmissionLL.sendRequestSendNewPhone();
                break;
            case "sendAcceptanceCode":
                response = testAppSubmissionLL.sendRequestSendAcceptanceCode();
                actionIdentifier = response.text();
                break;
            case "verifyAcceptanceCode":
                response = testAppSubmissionLL.sendRequestVerifyAcceptanceCode(actionIdentifier);
                break;
            case "token":
                response = testAppSubmissionLL.sendRequestToken(actionIdentifier);
                jsonResponse = new JSONObject(response.text());
                API_TOKEN = jsonResponse.get("access_token").toString();
                testAppSubmissionLL.setAPI_TOKEN(API_TOKEN);
                break;
            case "updatePersonalData":
                response = testAppSubmissionLL.sendRequestUpdatePersonalData();
                break;
            case "updateRegistrationAddressData":
                response = testAppSubmissionLL.sendRequestUpdatePersonalData();
                break;
            case "updateEmploymentData":
                response = testAppSubmissionLL.sendRequestUpdateEmploymentData();
                break;
            case "updateBankAndContactData":
                response = testAppSubmissionLL.sendRequestUpdateBankAndContactData();
                break;
            case "create":
                response = testAppSubmissionLL.sendRequestCreate();
                break;
            default:
                System.out.println("Invalid request: " + nameOfRequest);
                break;
        }
    }

    @When("^I check if the response is successful in LL$")
    public void iCheckIfTheResponseCodeIs() throws Throwable{
        assertTrue(response.ok(), response.text());
    }

    @When("^I check if the response body contains key \"(.*)\" in LL$")
    public void iCheckIfTheResponseBodyContains(String key) throws Throwable{
        JSONObject jsonResponse = new JSONObject(response.text());
        assertTrue(jsonResponse.has(key), response.text());
    }

    @When("^I check if the response body different from null in LL$")
    public void iCheckIfTheResponseBodyDifferentFromNull() throws Throwable{
        String responseBody = response.text();
        assertFalse(responseBody.equalsIgnoreCase(""), "Response body is null.");
    }
}
