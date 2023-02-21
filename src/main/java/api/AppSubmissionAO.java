package api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;

import dataObjects.*;
import java.util.HashMap;
import java.util.Map;

import dataProviders.*;

public class AppSubmissionAO {

    private String API_TOKEN;
    
    public String getAPI_TOKEN() {
        return API_TOKEN;
    }

    public void setAPI_TOKEN(String api_token) {
        API_TOKEN = api_token;
    }

    private static String HOST;
    private static String EXT;
    private static String collectApplyStats;
    private static String sendAcceptanceCode;
    private static String token;
    private static String getFormType;
    private static String updatePersonalData;
    private static String updateRegistrationAddressData;
    private static String updateEmploymentData;
    private static String updateConditionData;
    private static String updateContactPersonData;
    private static String create;

    private static Playwright playwright;
    private static APIRequestContext request;

    // New instance for each test method.
    BrowserContext context;
    Page page;
    ConfigFileReader configFileReader;
    private static Customer customer;

    void createPlaywright() {
        playwright = Playwright.create();
    }

    void createTestContext() {
        configFileReader = new ConfigFileReader();
        customer = new Customer();
        HOST = configFileReader.getApplicationConfig().get("host").toString();
        EXT = configFileReader.getApplicationConfig().get("ext").toString();
        collectApplyStats = configFileReader.getApplicationConfig().get("collect-apply-stats").toString();
        sendAcceptanceCode = configFileReader.getApplicationConfig().get("sendAcceptanceCode").toString();
        token = configFileReader.getApplicationConfig().get("token").toString();
        getFormType = configFileReader.getApplicationConfig().get("getFormType").toString();
        updatePersonalData = configFileReader.getApplicationConfig().get("updatePersonalData").toString();
        updateRegistrationAddressData = configFileReader.getApplicationConfig().get("updateRegistrationAddressData").toString();
        updateEmploymentData = configFileReader.getApplicationConfig().get("updateEmploymentData").toString();
        updateConditionData = configFileReader.getApplicationConfig().get("updateConditionData").toString();
        updateContactPersonData = configFileReader.getApplicationConfig().get("updateContactPersonData").toString();
        create = configFileReader.getApplicationConfig().get("create").toString();
    }

    void createAPIRequestContext() {
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

    public AppSubmissionAO() {
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

    public APIResponse sendRequestCollectApplyStats() {
        String url = HOST + EXT + collectApplyStats;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("sourceType", "Landing");
        data.put("phoneNumber", customer.getPersonalData().getMobilePhone());
        createAPIRequestContext();
        APIResponse collectApplyStatsResponse = request.post(url,
                RequestOptions.create().setData(data));
        return collectApplyStatsResponse;
    }

    public APIResponse sendRequestSendAcceptanceCode() {
        String url = HOST + EXT + sendAcceptanceCode;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("source", "ONLINE");
        data.put("mobilePhone", customer.getPersonalData().getMobilePhone());
        createAPIRequestContext();
        APIResponse sendAcceptanceCodeResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendAcceptanceCodeResponse;
    }

    public APIResponse sendRequestToken(String actionIdentifier) {
        String url = HOST + EXT + token;
        createAPIRequestContext();
        APIResponse sendRequestTokenResponse = request.post(url,
                RequestOptions.create().setForm(
                    FormData.create().set("client_id", "atm-online-frontend")
                                    .set("action_identifier", actionIdentifier)
                                    .set("grant_type", "password")
                                    .set("client_secret", "939c3115-6984-43aa-98a9-5a6d260fd379")
                                    .set("design_type", "NEW")
                                    .set("username", customer.getPersonalData().getMobilePhone())
                                    .set("password", "1111")));
        return sendRequestTokenResponse;
    }

    public APIResponse sendRequestUpdateRegistrationAddressData() {
        String url = HOST + EXT + updateRegistrationAddressData;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("city", customer.getAddressData().getCity());
        data.put("cityId", customer.getAddressData().getCityId());
        data.put("district", customer.getAddressData().getDistrict());
        data.put("districtId", customer.getAddressData().getDistrictId());
        data.put("flat", customer.getAddressData().getFlat());
        data.put("houseStreet", customer.getAddressData().getHouseStreet());
        data.put("ward", customer.getAddressData().getWard());
        createAPIRequestContext();
        APIResponse sendRequestUpdateRegistrationAddressDataResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendRequestUpdateRegistrationAddressDataResponse;
    }

    public APIResponse sendRequestGetFormType() {
        String url = HOST + EXT + getFormType;
        createAPIRequestContext();
        APIResponse sendRequestGetFormTypeResponse = request.get(url);
        return sendRequestGetFormTypeResponse;
    }

    public APIResponse sendRequestUpdateEmploymentData() {
        String url = HOST + EXT + updateEmploymentData;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("employerName", customer.getEmploymentData().getEmployerName());
        data.put("employerPhone", customer.getEmploymentData().getEmployerPhone());
        data.put("income", customer.getEmploymentData().getIncome());
        data.put("occupationType", customer.getEmploymentData().getOccupationType());
        data.put("workingIndustry", customer.getEmploymentData().getWorkingIndustry());
        createAPIRequestContext();
        APIResponse sendRequestUpdateEmploymentDataResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendRequestUpdateEmploymentDataResponse;
    }

    public APIResponse sendRequestUpdateConditionData() {
        String url = HOST + EXT + updateConditionData;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("bankCode", customer.getConditionDto().getBankCode());
        data.put("accountNumber", customer.getConditionDto().getAccountNumber());
        data.put("provideBankDetailsLater", customer.getConditionDto().getProvideBankDetailsLater());
        data.put("disburseType", customer.getConditionDto().getDisburseType());
        data.put("portalAccountNumberMandatory", customer.getConditionDto().getPortalAccountNumberMandatory());
        data.put("phone", customer.getPersonalData().getMobilePhone());
        data.put("relationType", customer.getConditionDto().getRelationType());
        data.put("name", customer.getPersonalData().getFullName());
        data.put("bankName", customer.getConditionDto().getBankName());
        createAPIRequestContext();
        APIResponse sendRequestUpdateConditionDataResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendRequestUpdateConditionDataResponse;
    }

    public APIResponse sendRequestUpdateContactPersonData() {
        String url = HOST + EXT + updateContactPersonData;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("bankCode", customer.getConditionDto().getBankCode());
        data.put("accountNumber", customer.getConditionDto().getAccountNumber());
        data.put("provideBankDetailsLater", customer.getConditionDto().getProvideBankDetailsLater());
        data.put("disburseType", customer.getConditionDto().getDisburseType());
        data.put("portalAccountNumberMandatory", customer.getConditionDto().getPortalAccountNumberMandatory());
        data.put("phone", customer.getPersonalData().getMobilePhone());
        data.put("relationType", customer.getConditionDto().getRelationType());
        data.put("name", customer.getPersonalData().getFullName());
        data.put("bankName", customer.getConditionDto().getBankName());
        createAPIRequestContext();
        APIResponse sendRequestUpdateContactPersonDataResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendRequestUpdateContactPersonDataResponse;
    }

    public APIResponse sendRequestUpdatePersonalData() {
        String url = HOST + EXT + updatePersonalData;
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("primaryId", customer.getPersonalData().getPrimaryId());
        data.put("fullName", customer.getPersonalData().getFullName());
        data.put("cardIssuingDate", customer.getPersonalData().getCardIssuingDate());
        data.put("birthDate", customer.getPersonalData().getBirthDate());
        data.put("sex", customer.getPersonalData().getSex());
        data.put("email", customer.getPersonalData().getEmail());
        createAPIRequestContext();
        APIResponse sendRequestUpdatePersonalDataResponse = request.post(url,
                RequestOptions.create().setData(data));
        return sendRequestUpdatePersonalDataResponse;
    }

    public APIResponse sendRequestCreate() {
        String url = HOST + EXT + create;
        String json = "{\"expressProcessing\": true,\"requestedAmount\": 3000000,\"requestedTerm\": 3,\"utmParametersData\": {\"affSid\": \"\",\"campaign\": \"\",\"content\": \"\",\"medium\": \"direct\",\"referrer\": \"\",\"source\": \"direct\",\"term\": \"\"}}";
        createAPIRequestContext("application/json");
        APIResponse sendRequestCreateResponse = request.post(url,
                RequestOptions.create().setData(json));
        return sendRequestCreateResponse;
    }
}