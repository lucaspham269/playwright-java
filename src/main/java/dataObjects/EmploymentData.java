package dataObjects;

import java.util.Random;

import org.json.JSONObject;

public class EmploymentData {
    
    // EmploymentData
    private String employerName;
    private String employerPhone;
    private int income;
    private String occupationType;
    private String workingIndustry;

    // EmploymentData
    public String getEmployerName() {
        return employerName;
    }
    
    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }
    
    public String getEmployerPhone() {
        return employerPhone;
    }
    
    public void setEmployerPhone(String employerPhone) {
        this.employerPhone = employerPhone;
    }
    
    public int getIncome() {
        return income;
    }
    
    public void setIncome(int income) {
        this.income = income;
    }
    
    public String getOccupationType() {
        return occupationType;
    }
    
    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }
    
    public String getWorkingIndustry() {
        return workingIndustry;
    }
    
    public void setWorkingIndustry(String workingIndustry) {
        this.workingIndustry = workingIndustry;
    }

    public EmploymentData() {
        setEmployerName("H.A.S Tech");
        setEmployerPhone("02800000000");
        setWorkingIndustry("IT_TELECOM");
        setOccupationType("WORKING_WITH_CONTRACT");
        Random rand = new Random();
        int randomIncome = rand.nextInt(100000000);
        setIncome(randomIncome);
    }

    public EmploymentData(JSONObject jObject) {
        setEmployerName(jObject.getString("employerName"));
        setEmployerPhone(jObject.getString("employerPhone"));
        setWorkingIndustry(jObject.getString("workingIndustry"));
        setOccupationType(jObject.getString("occupationType"));
        setIncome(jObject.getInt("income"));
    }
}
