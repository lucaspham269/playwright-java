package dataObjects;

import java.util.Random;

import org.json.JSONObject;

public class ConditionDto {
    
    // ConditionDto
    private String accountNumber;
    private int bankCode;
    private String bankName;
    private String cardNumber;
    private String disburseType;
    private String license;
    private String relationType;
    private Boolean portalAccountNumberMandatory;
    private Boolean provideBankDetailsLater;
    private int bankBranchId;

    public int getBankBranchId() {
        return bankBranchId;
    }

    public void setBankBranchId(int bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    // ConditionDto
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBankCode() {
        return bankCode;
    }

    public void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDisburseType() {
        return disburseType;
    }

    public void setDisburseType(String disburseType) {
        this.disburseType = disburseType;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Boolean getPortalAccountNumberMandatory() {
        return portalAccountNumberMandatory;
    }

    public void setPortalAccountNumberMandatory(Boolean portalAccountNumberMandatory) {
        this.portalAccountNumberMandatory = portalAccountNumberMandatory;
    }

    public Boolean getProvideBankDetailsLater() {
        return provideBankDetailsLater;
    }

    public void setProvideBankDetailsLater(Boolean provideBankDetailsLater) {
        this.provideBankDetailsLater = provideBankDetailsLater;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public ConditionDto() {
        Random rand = new Random();
        int randomAccountNo = rand.nextInt(1000000000);
        setAccountNumber(String.valueOf(randomAccountNo));
        setBankCode(17);
        setBankName("SCB - NH TMCP Sài Gòn");
        setCardNumber(null);
        setDisburseType("BANK_ACCOUNT");
        setLicense(null);
        setRelationType("RELATIVE");
        setPortalAccountNumberMandatory(false);
        setProvideBankDetailsLater(false);
    }

    public ConditionDto(JSONObject jObject) {
        setAccountNumber(jObject.getString(accountNumber));
        setBankCode(jObject.getInt("bankCode"));
        setBankName(jObject.getString("bankName"));
        setCardNumber(jObject.getString("cardNumber"));
        setDisburseType(jObject.getString("disburseType"));
        setLicense(jObject.getString("license"));
        setRelationType(jObject.getString("relationType"));
        setPortalAccountNumberMandatory(jObject.getBoolean("portalAccountNumberMandatory"));
        setProvideBankDetailsLater(jObject.getBoolean("provideBankDetailsLater"));
        setBankBranchId(jObject.getInt("bankBranchId"));
    }
}
