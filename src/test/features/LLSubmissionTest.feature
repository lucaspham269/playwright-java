@ll
Feature: LL App submission

Scenario: Submit IL app successfully
    Given I send the "sendNewPhone" request in LL
    Then I check if the response is successful in LL
    When I send the "sendAcceptanceCode" request in LL
    Then I check if the response is successful in LL
    When I send the "verifyAcceptanceCode" request in LL
    Then I check if the response is successful in LL
    When I send the "token" request in LL
    Then I check if the response is successful in LL
    When I send the "updatePersonalData" request in LL
    Then I check if the response is successful in LL
    When I send the "updateRegistrationAddressData" request in LL
    Then I check if the response is successful in LL
    When I send the "updateEmploymentData" request in LL
    Then I check if the response is successful in LL
    When I send the "updateBankAndContactData" request in LL
    Then I check if the response is successful in LL
    When I send the "create" request in LL
    Then I check if the response is successful in LL
    And I check if the response body different from null in LL