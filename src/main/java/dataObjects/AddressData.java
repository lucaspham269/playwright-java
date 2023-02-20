package dataObjects;

import org.json.JSONObject;

public class AddressData {
    
    // AddressData
    private String city;
    private int cityId;
    private String district;
    private int districtId;
    private String flat;
    private String houseStreet;
    private String ward;

    // AddressData
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getHouseStreet() {
        return houseStreet;
    }

    public void setHouseStreet(String houseStreet) {
        this.houseStreet = houseStreet;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public AddressData() {
        setCity("Hồ Chí Minh");
        setCityId(1);
        setDistrict("Quận 1");
        setDistrictId(102);
        setFlat("A.01.01");
        setHouseStreet("123 Random street");
        setWard("Phường Tân Định");
    }

    public AddressData(JSONObject jObject) {
        setCity(jObject.getString("city"));
        setCityId(jObject.getInt("cityId"));
        setDistrict(jObject.getString("district"));
        if(jObject.has("districtId"))
            setDistrictId(jObject.getInt("districtId"));
        if(jObject.has("flat"))
            setFlat(jObject.getString("flat"));
        if(jObject.has("houseStreet"))
            setHouseStreet(jObject.getString("houseStreet"));
        if(jObject.has("ward"))
            setWard(jObject.getString("ward"));
    }
}
