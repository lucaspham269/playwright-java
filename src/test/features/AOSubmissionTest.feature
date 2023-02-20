@ao
Feature: AO App submission

Scenario: Submit IL app successfully
    Given I send the "collectApplyStats" request in AO
    Then I check if the response is successful in AO
    When I send the "sendAcceptanceCode" request in AO
    Then I check if the response is successful in AO
    When I send the "token" request in AO
    Then I check if the response is successful in AO
    When I send the "getFormType" request in AO
    Then I check if the response is successful in AO
    When I send the "updatePersonalData" request in AO
    Then I check if the response is successful in AO
    When I send the "updateRegistrationAddressData" request in AO
    Then I check if the response is successful in AO
    When I send the "updateEmploymentData" request in AO
    Then I check if the response is successful in AO
    When I send the "updateConditionData" request in AO
    Then I check if the response is successful in AO
    When I send the "updateContactPersonData" request in AO
    Then I check if the response is successful in AO
    When I send the "create" request in AO
    Then I check if the response is successful in AO
    And I check if the response body contains key "id" in AO
    And I check if the response body contains key "borrowerId" in AO