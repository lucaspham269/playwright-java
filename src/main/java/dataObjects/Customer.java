package dataObjects;

public class Customer {

    protected AddressData addressData;
    protected ContactPersonData contactPersonData;
    protected EmploymentData employmentData;
    protected PersonalData personalData;
    protected ConditionDto conditionDto;

    public AddressData getAddressData() {
        return addressData;
    }

    public void setAddressData(AddressData addressData) {
        this.addressData = addressData;
    }

    public ContactPersonData getContactPersonData() {
        return contactPersonData;
    }

    public void setContactPersonData(ContactPersonData contactPersonData) {
        this.contactPersonData = contactPersonData;
    }

    public EmploymentData getEmploymentData() {
        return employmentData;
    }

    public void setEmploymentData(EmploymentData employmentData) {
        this.employmentData = employmentData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public ConditionDto getConditionDto() {
        return conditionDto;
    }

    public void setConditionDto(ConditionDto conditionDto) {
        this.conditionDto = conditionDto;
    }

    public Customer() {
        addressData = new AddressData();
        contactPersonData = new ContactPersonData();
        employmentData = new EmploymentData();
        personalData = new PersonalData();
        conditionDto = new ConditionDto();
    }
}