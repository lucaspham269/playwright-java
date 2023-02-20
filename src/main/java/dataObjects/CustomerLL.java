package dataObjects;

import java.util.Random;

import org.json.JSONObject;

public class CustomerLL extends Customer {
    public CustomerLL() {
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("city", "WESTERN");
        jsonObject.put("district", "COLOMBO");
        jsonObject.put("cityId", 28);
        jsonObject.put("houseStreet", "123 ABC");
        jsonObject.put("districtId", 1);

        addressData = new AddressData(jsonObject);

        contactPersonData = new ContactPersonData();
        contactPersonData.setRelationType("MOTHER_FATHER");

        employmentData = new EmploymentData();
        Random rand = new Random();
        employmentData.setIncome(rand.nextInt(999999));
        employmentData.setWorkingIndustry("IT_AND_TELECOMMUNICATION");

        personalData = new PersonalData();
        String randomNIC = generateRandomNIC();
        personalData.setPrimaryId(randomNIC);
        // int birthOfDate = 19000000 + Integer.valueOf(randomNIC.substring(0, 5));
        // personalData.setBirthDate(String.valueOf(birthOfDate));
        // personalData.setFullName("QA " + "TEST " + personalData.getMobilePhone());

        conditionDto = new ConditionDto();
        conditionDto.setBankCode(13);
        conditionDto.setBankBranchId(1143);
    }

    public String generateRandomNIC() {
        Random random = new Random();
        int year = random.nextInt(100) + 1900;
        int dateOfBirth = random.nextInt(366);
        int sequence = random.nextInt(10000);
        int checkDigit = random.nextInt(9);
        String nic = String.format("%04d%03d%04d%d", year, dateOfBirth, sequence, checkDigit);
        return nic;
    }
}
