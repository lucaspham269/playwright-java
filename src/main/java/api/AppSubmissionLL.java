package api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;

import dataObjects.*;
import java.util.HashMap;
import java.util.Map;

import org.json.*;

import dataProviders.*;

public class AppSubmissionLL {
    private static final String BASIC_API_TOKEN = "bG90dXMtZnJvbnRlbmQ6OTM5YzMxMTUtNjk4NC00M2FhLTk4YTktNWE2ZDI2MGZkMzc5";
    private String API_TOKEN;
    
    public String getAPI_TOKEN() {
        return API_TOKEN;
    }

    public void setAPI_TOKEN(String aPI_TOKEN) {
        API_TOKEN = aPI_TOKEN;
    }

    private static String HOST;
    private static String EXT;
    private static String sendNewPhone;
    private static String sendAcceptanceCode;
    private static String verifyAcceptanceCode;
    private static String token;
    private static String updatePersonalData;
    private static String updateRegistrationAddressData;
    private static String updateEmploymentData;
    private static String updateBankAndContactData;
    private static String create;

    private Playwright playwright;
    private APIRequestContext request;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;
    ConfigFileReader configFileReader;
    Customer customer;

    void createPlaywright() {
        playwright = Playwright.create();
    }

    void createTestContext() {
        configFileReader = new ConfigFileReader();
        configFileReader.setCountry("ll");
        configFileReader.setEnvironment("preprod");
        customer = new CustomerLL();

        HOST = configFileReader.getApplicationConfig().get("host").toString();
        EXT = configFileReader.getApplicationConfig().get("ext").toString();
        sendNewPhone = configFileReader.getApplicationConfig().get("sendNewPhone").toString();
        sendAcceptanceCode = configFileReader.getApplicationConfig().get("sendAcceptanceCode").toString();
        verifyAcceptanceCode = configFileReader.getApplicationConfig().get("verifyAcceptanceCode").toString();
        token = configFileReader.getApplicationConfig().get("token").toString();
        updatePersonalData = configFileReader.getApplicationConfig().get("updatePersonalData").toString();
        updateRegistrationAddressData = configFileReader.getApplicationConfig().get("updateRegistrationAddressData").toString();
        updateEmploymentData = configFileReader.getApplicationConfig().get("updateEmploymentData").toString();
        updateBankAndContactData = configFileReader.getApplicationConfig().get("updateBankAndContactData").toString();
        create = configFileReader.getApplicationConfig().get("create").toString();
    }

    void createAPIRequestContext() {
        disposeAPIRequestContext();
        Map<String, String> headers = new HashMap<>();
        // We set this header per GitHub guidelines.
        headers.put("Accept", "*/*");
        // Add authorization token to all requests.
        // Assuming personal access token available in the environment.
        if(API_TOKEN != null)
            headers.put("Authorization", "Bearer " + API_TOKEN);

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setExtraHTTPHeaders(headers));
    }

    void createAPIRequestContext(String contentType) {
        Map<String, String> headers = new HashMap<>();
        // We set this header per GitHub guidelines.
        headers.put("Accept", "*/*");
        // Add authorization token to all requests.
        // Assuming personal access token available in the environment.
        headers.put("Content-Type", contentType);
        if(API_TOKEN != null)
            headers.put("Authorization", "Bearer " + API_TOKEN);

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setExtraHTTPHeaders(headers));
    }

    public AppSubmissionLL() {
        createPlaywright();
        createTestContext();
    }

    public void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }

    public void closeContext() {
        if (context != null) {
            context.close();
            context = null;
        }
    }

    public void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }

    public APIResponse sendRequestSendNewPhone() {
        String url = HOST + EXT + sendNewPhone;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("phone", customer.getPersonalData().getMobilePhone());
        createAPIRequestContext();
        APIResponse sendRequestSendNewPhoneResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendRequestSendNewPhoneResponse;
    }

    public APIResponse sendRequestSendAcceptanceCode() {
        String url = HOST + EXT + sendAcceptanceCode;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("mobilePhone", customer.getPersonalData().getMobilePhone());
        createAPIRequestContext();
        APIResponse sendAcceptanceCodeResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendAcceptanceCodeResponse;
    }

    public APIResponse sendRequestVerifyAcceptanceCode(String actionIdentifier) {
        String url = HOST + EXT + verifyAcceptanceCode;
        JSONObject acceptanceCode = new JSONObject();
        acceptanceCode.put("actionIdentifier", actionIdentifier);
        acceptanceCode.put("code", "1111");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("acceptanceCode", acceptanceCode);
        jsonObject.put("phone", customer.getPersonalData().getMobilePhone());
        jsonObject.put("utmParametersData", new JSONObject("{\"affiliateID\":\"\",\"clickHash\":\"\",\"campaign\":\"\",\"content\":\"\",\"medium\":\"\",\"source\":\"\",\"term\":\"\"}"));
        createAPIRequestContext();
        APIResponse sendRequestVerifyAcceptanceCodeResponse = request.post(url,
                RequestOptions.create().setData(jsonObject.toMap()));
        return sendRequestVerifyAcceptanceCodeResponse;
    }

    public APIResponse sendRequestToken(String actionIdentifier) {
        String url = HOST + EXT + token;
        request = playwright.request().newContext(new APIRequest.NewContextOptions());
        APIResponse sendRequestTokenResponse = request.post(url,
                RequestOptions.create().setMultipart(
                    FormData.create().set("action_identifier", actionIdentifier)
                                    .set("grant_type", "password")
                                    .set("design_type", "NEW")
                                    .set("username", customer.getPersonalData().getMobilePhone())
                                    .set("password", "1111"))
                                    .setHeader("Authorization", "Basic " + BASIC_API_TOKEN).setHeader("Accept", "*/*"));
        return sendRequestTokenResponse;
    }

    public APIResponse sendRequestUpdateRegistrationAddressData() {
        String url = HOST + EXT + updateRegistrationAddressData;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("province", customer.getAddressData().getCity());
        data.put("cityId", customer.getAddressData().getCityId());
        data.put("district", customer.getAddressData().getDistrict());
        data.put("houseStreet", customer.getAddressData().getHouseStreet());
        createAPIRequestContext("application/json");
        APIResponse sendRequestUpdateRegistrationAddressDataResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendRequestUpdateRegistrationAddressDataResponse;
    }

    public APIResponse sendRequestUpdateEmploymentData() {
        String url = HOST + EXT + updateEmploymentData;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("employerName", customer.getEmploymentData().getEmployerName());
        data.put("employerPhone", customer.getEmploymentData().getEmployerPhone());
        data.put("income", customer.getEmploymentData().getIncome());
        data.put("occupationType", customer.getEmploymentData().getOccupationType());
        data.put("workingIndustry", customer.getEmploymentData().getWorkingIndustry());
        createAPIRequestContext("application/json");
        APIResponse sendRequestUpdateEmploymentDataResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendRequestUpdateEmploymentDataResponse;
    }

    public APIResponse sendRequestUpdateBankAndContactData() {
        String url = HOST + EXT + updateBankAndContactData;
        JSONObject jsonBankDetails = new JSONObject();
        jsonBankDetails.put("bankId", customer.getConditionDto().getBankCode());
        jsonBankDetails.put("bankBranchId", customer.getConditionDto().getBankBranchId());
        jsonBankDetails.put("accountNumber", customer.getConditionDto().getAccountNumber());
        jsonBankDetails.put("provideBankDetailsLater", customer.getConditionDto().getProvideBankDetailsLater());

        JSONObject jsonContactPersonDetails = new JSONObject();
        jsonContactPersonDetails.put("relationType", customer.getContactPersonData().getRelationType());
        jsonContactPersonDetails.put("name", customer.getContactPersonData().getName());
        jsonContactPersonDetails.put("phone", customer.getContactPersonData().getPhone());
        jsonContactPersonDetails.put("conditions", true);
        jsonContactPersonDetails.put("validationConcent", true);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bankDetails", jsonBankDetails);
        jsonObject.put("contactPersonDetails", jsonContactPersonDetails);
        createAPIRequestContext("application/json");
        APIResponse sendRequestUpdateBankAndContactDataResponse = request.post(url,
                RequestOptions.create().setData(jsonObject.toMap()));
        return sendRequestUpdateBankAndContactDataResponse;
    }

    public APIResponse sendRequestUpdatePersonalData() {
        String url = HOST + EXT + updatePersonalData;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("firstName", "QA");
        data.put("secondName", "TEST");
        data.put("lastName", "LK - " + String.valueOf(customer.getPersonalData().getPrimaryId()));
        data.put("nic", customer.getPersonalData().getPrimaryId());
        createAPIRequestContext("application/json");
        APIResponse sendRequestUpdatePersonalDataResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendRequestUpdatePersonalDataResponse;
    }

    public APIResponse sendRequestCreate() {
        String url = HOST + EXT + create;
        String json = "{\"requestedAmount\":30000,\"requestedTerm\":92,\"utmParametersData\":{\"affiliateID\":\"\",\"clickHash\":\"\",\"campaign\":\"\",\"content\":\"\",\"medium\":\"\",\"source\":\"\",\"term\":\"\"},\"termUnit\":\"DAYS\",\"expressProcessing\":false}";
        createAPIRequestContext("application/json");
        APIResponse sendRequestCreateResponse = request.post(url,
                RequestOptions.create().setData(json));
        return sendRequestCreateResponse;
    }
}
