package dataObjects;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.json.JSONObject;

public class PersonalData {
    
    // PersonalData
    private String birthDate;
    private String cardIssuingDate;
    private String email;
    private String fullName;
    private String loanPurpose;
    private String primaryId;
    private String sex;
    private String mobilePhone;

    // PersonalData
    public String getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getCardIssuingDate() {
        return cardIssuingDate;
    }
    
    public void setCardIssuingDate(String cardIssuingDate) {
        this.cardIssuingDate = cardIssuingDate;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getLoanPurpose() {
        return loanPurpose;
    }
    
    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }
    
    public String getPrimaryId() {
        return primaryId;
    }
    
    public void setPrimaryId(String primaryId) {
        this.primaryId = primaryId;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }
        
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    // utils methods
    private static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }

    private static String randomDOB() {
        LocalDate randomDOB = createRandomDate(1980, 2000);
        return String.valueOf(randomDOB);
    }

    private static String randomIDCardIssuedDate() {
        LocalDate randomCardIssuedDate = createRandomDate(2010, 2020);
        return String.valueOf(randomCardIssuedDate);
    }

    private static String generateRandomID()
    {
        // The UUID.randomUUID().toString() of length
        // consist of digits ,alphabets which will be handled
        // to get digits using BigInteger and "-" which needs
        // to be replaced with "". Inside  new
        // BigInteger("%010d", new
        // BigInteger(UUID.randomUUID().toString().replace("-",
        // ""), 16)) 16 represent radix .
        String generateUUIDNo = String.format("%010d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
 
        // To decide length of unique positive long number
        // generateUUIDNo.length() - uniqueNoSize is being
        // used
        String unique_no = generateUUIDNo.substring( generateUUIDNo.length() - 12);
        return unique_no;
    }

    public PersonalData() {
        setBirthDate(randomDOB());
        setCardIssuingDate(randomIDCardIssuedDate());
        Random random = new Random();
        int randomPhone = 10000000 + (int)(random.nextDouble()*90000000);
        setMobilePhone("09" + String.valueOf(randomPhone));
        setEmail("qa.hastech+" + getMobilePhone() + "@gmail.com");
        setPrimaryId(generateRandomID());
        setFullName("QA TESTING - " + String.valueOf(mobilePhone));
        setLoanPurpose(null);
        setSex("MALE");
    }

    public PersonalData(JSONObject jObject) {
        setBirthDate(jObject.getString("birthDate"));
        setCardIssuingDate(jObject.getString("cardIssuingDate"));
        setMobilePhone(jObject.getString("mobilePhone"));
        setEmail(jObject.getString("email"));
        setPrimaryId(jObject.getString("primaryId"));
        setFullName(jObject.getString("fullName"));
        setLoanPurpose(jObject.getString("loanPurpose"));
        setSex(jObject.getString("sex"));
    }
}
