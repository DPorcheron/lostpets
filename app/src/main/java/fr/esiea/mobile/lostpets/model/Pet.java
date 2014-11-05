package fr.esiea.mobile.lostpets.model;

import org.json.JSONObject;

/**
 * Created by david on 26/10/2014.
 */
public class Pet {

    private Integer m_petId;
    private String m_petName;
    private String m_petRace;
    private String m_petColour;
    private String m_petSex;
    private String m_petTatoo;
    private String m_petPicture;
    private String m_petLostAddress;
    private String m_petLostZipcode;
    private String m_petLostCity;
    private String m_petOwnerFirstName;
    private String m_petOwnerLastName;
    private String m_petOwnerAddress;
    private String m_petOwnerZipCode;
    private String m_petOwnerCity;
    private String m_petOwnerPhone;

    public Pet(Integer m_petId, String m_petName, String m_petRace, String m_petColour,
               String m_petSex, String m_petTatoo, String m_petPicture,
               String m_petLostAddress, String m_petLostZipcode, String m_petLostCity,
               String m_petOwnerFirstName, String m_petOwnerLastName, String m_petOwnerAddress,
               String m_petOwnerZipCode, String m_petOwnerCity, String m_petOwnerPhone) {
        this.m_petId = m_petId;
        this.m_petName = m_petName;
        this.m_petRace = m_petRace;
        this.m_petColour = m_petColour;
        this.m_petSex = m_petSex;
        this.m_petTatoo = m_petTatoo;
        this.m_petPicture = m_petPicture;
        this.m_petLostAddress = m_petLostAddress;
        this.m_petLostZipcode = m_petLostZipcode;
        this.m_petLostCity = m_petLostCity;
        this.m_petOwnerFirstName = m_petOwnerFirstName;
        this.m_petOwnerLastName = m_petOwnerLastName;
        this.m_petOwnerAddress = m_petOwnerAddress;
        this.m_petOwnerZipCode = m_petOwnerZipCode;
        this.m_petOwnerCity = m_petOwnerCity;
        this.m_petOwnerPhone = m_petOwnerPhone;
    }

    public Pet (JSONObject json) {
        if (json.has("id")) {
            this.m_petId = json.optInt("id");
        }
        if (json.has("name")){
            this.m_petName = json.optString("name");
        }
        if (json.has("race")){
            this.m_petRace = json.optString("race");
        }
        if (json.has("color")){
            this.m_petColour = json.optString("color");
        }
        if (json.has("sex")){
            this.m_petSex = json.optString("sex");
        }
        if (json.has("tatoo")){
            this.m_petTatoo = json.optString("tatoo");
        }
        if (json.has("picture")){
            this.m_petPicture = json.optString("picture");
        }
        if (json.has("address")){
            this.m_petLostAddress = json.optString("address");
        }
        if (json.has("zipcode")){
            this.m_petLostZipcode = json.optString("zipcode");
        }
        if (json.has("city")){
            this.m_petLostCity = json.optString("city");
        }
        if (json.has("ownerFirstName")){
            this.m_petOwnerFirstName = json.optString("ownerFirstName");
        }
        if (json.has("ownerLastName")){
            this.m_petOwnerLastName = json.optString("ownerLastName");
        }
        if (json.has("ownerAddress")){
            this.m_petOwnerAddress = json.optString("ownerAddress");
        }
        if (json.has("ownerZipcode")){
            this.m_petOwnerZipCode = json.optString("ownerZipcode");
        }
        if (json.has("ownerCity")){
            this.m_petOwnerCity = json.optString("ownerCity");
        }
        if (json.has("ownerPhone")){
            this.m_petOwnerPhone = json.optString("ownerPhone");
        }
    }

    @Override
    public String toString() {
        return "Pet{" +
                "m_petId=" + m_petId +
                ", m_petName='" + m_petName + '\'' +
                ", m_petRace='" + m_petRace + '\'' +
                ", m_petColour='" + m_petColour + '\'' +
                ", m_petSex='" + m_petSex + '\'' +
                ", m_petTatoo='" + m_petTatoo + '\'' +
                ", m_petPicture='" + m_petPicture + '\'' +
                ", m_petLostAddress='" + m_petLostAddress + '\'' +
                ", m_petLostZipcode='" + m_petLostZipcode + '\'' +
                ", m_petLostCity='" + m_petLostCity + '\'' +
                ", m_petOwnerFirstName='" + m_petOwnerFirstName + '\'' +
                ", m_petOwnerLastName='" + m_petOwnerLastName + '\'' +
                ", m_petOwnerAddress='" + m_petOwnerAddress + '\'' +
                ", m_petOwnerZipCode='" + m_petOwnerZipCode + '\'' +
                ", m_petOwnerCity='" + m_petOwnerCity + '\'' +
                ", m_petOwnerPhone='" + m_petOwnerPhone + '\'' +
                '}';
    }

    public Integer getM_petId() {
        return m_petId;
    }

    public void setM_petId(Integer m_petId) {
        this.m_petId = m_petId;
    }

    public String getM_petName() {
        return m_petName;
    }

    public void setM_petName(String m_petName) {
        this.m_petName = m_petName;
    }

    public String getM_petRace() {
        return m_petRace;
    }

    public void setM_petRace(String m_petRace) {
        this.m_petRace = m_petRace;
    }

    public String getM_petColour() {
        return m_petColour;
    }

    public void setM_petColour(String m_petColour) {
        this.m_petColour = m_petColour;
    }

    public String getM_petSex() {
        return m_petSex;
    }

    public void setM_petSex(String m_petSex) {
        this.m_petSex = m_petSex;
    }

    public String getM_petTatoo() {
        return m_petTatoo;
    }

    public void setM_petTatoo(String m_petTatoo) {
        this.m_petTatoo = m_petTatoo;
    }

    public String getM_petPicture() {
        return m_petPicture;
    }

    public void setM_petPicture(String m_petPicture) {
        this.m_petPicture = m_petPicture;
    }

    public String getM_petLostAddress() {
        return m_petLostAddress;
    }

    public void setM_petLostAddress(String m_petLostAddress) {
        this.m_petLostAddress = m_petLostAddress;
    }

    public String getM_petLostZipcode() {
        return m_petLostZipcode;
    }

    public void setM_petLostZipcode(String m_petLostZipcode) {
        this.m_petLostZipcode = m_petLostZipcode;
    }

    public String getM_petLostCity() {
        return m_petLostCity;
    }

    public void setM_petLostCity(String m_petLostCity) {
        this.m_petLostCity = m_petLostCity;
    }

    public String getM_petOwnerFirstName() {
        return m_petOwnerFirstName;
    }

    public void setM_petOwnerFirstName(String m_petOwnerFirstName) {
        this.m_petOwnerFirstName = m_petOwnerFirstName;
    }

    public String getM_petOwnerLastName() {
        return m_petOwnerLastName;
    }

    public void setM_petOwnerLastName(String m_petOwnerLastName) {
        this.m_petOwnerLastName = m_petOwnerLastName;
    }

    public String getM_petOwnerAddress() {
        return m_petOwnerAddress;
    }

    public void setM_petOwnerAddress(String m_petOwnerAddress) {
        this.m_petOwnerAddress = m_petOwnerAddress;
    }

    public String getM_petOwnerZipCode() {
        return m_petOwnerZipCode;
    }

    public void setM_petOwnerZipCode(String m_petOwnerZipCode) {
        this.m_petOwnerZipCode = m_petOwnerZipCode;
    }

    public String getM_petOwnerCity() {
        return m_petOwnerCity;
    }

    public void setM_petOwnerCity(String m_petOwnerCity) {
        this.m_petOwnerCity = m_petOwnerCity;
    }

    public String getM_petOwnerPhone() {
        return m_petOwnerPhone;
    }

    public void setM_petOwnerPhone(String m_petOwnerPhone) {
        this.m_petOwnerPhone = m_petOwnerPhone;
    }
}
