package dataObjects;

import java.util.Random;

import org.json.JSONObject;

public class ContactPersonData {
    
    // ContactPersonData
    private String name;
    private String phone;
    private String relationType;

    // ContactPersonData
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getRelationType() {
        return relationType;
    }
    
    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public ContactPersonData() {
        setName("NGUYỄN VĂN A");
        Random rand = new Random();
        int randomPhone = rand.nextInt(100000000);
        setPhone("09" + String.valueOf(randomPhone));
        setRelationType("RELATIVE");
    }

    public ContactPersonData(JSONObject jObject) {
        setName(jObject.getString("name"));
        setPhone(jObject.getString("phone"));
        setRelationType(jObject.getString("relationType"));
    }
}
