package fr.esiea.mobile.lostpets.model;

/**
 * Created by david on 03/11/2014.
 */
//This class is the User model
public class User {

    private Integer m_userId;
    private String m_userLastName;
    private String m_userFirstName;
    private String m_userAddress;
    private String m_userZipCode;
    private String m_userCity;
    private String m_userPhone;

    public User() {

    }

    public User(Integer m_userId, String m_userLastName, String m_userFirstName, String m_userAddress, String m_userZipCode, String m_userCity, String m_userPhone) {
        this.m_userId = m_userId;
        this.m_userLastName = m_userLastName;
        this.m_userFirstName = m_userFirstName;
        this.m_userAddress = m_userAddress;
        this.m_userZipCode = m_userZipCode;
        this.m_userCity = m_userCity;
        this.m_userPhone = m_userPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "m_userId=" + m_userId +
                ", m_userLastName='" + m_userLastName + '\'' +
                ", m_userFirstName='" + m_userFirstName + '\'' +
                ", m_userAddress='" + m_userAddress + '\'' +
                ", m_userZipCode='" + m_userZipCode + '\'' +
                ", m_userCity='" + m_userCity + '\'' +
                ", m_userPhone='" + m_userPhone + '\'' +
                '}';
    }

    public Integer getM_userId() {
        return m_userId;
    }

    public void setM_userId(Integer m_userId) {
        this.m_userId = m_userId;
    }

    public String getM_userLastName() {
        return m_userLastName;
    }

    public void setM_userLastName(String m_userLastName) {
        this.m_userLastName = m_userLastName;
    }

    public String getM_userFirstName() {
        return m_userFirstName;
    }

    public void setM_userFirstName(String m_userFirstName) {
        this.m_userFirstName = m_userFirstName;
    }

    public String getM_userAddress() {
        return m_userAddress;
    }

    public void setM_userAddress(String m_userAddress) {
        this.m_userAddress = m_userAddress;
    }

    public String getM_userZipCode() {
        return m_userZipCode;
    }

    public void setM_userZipCode(String m_userZipCode) {
        this.m_userZipCode = m_userZipCode;
    }

    public String getM_userCity() {
        return m_userCity;
    }

    public void setM_userCity(String m_userCity) {
        this.m_userCity = m_userCity;
    }

    public String getM_userPhone() {
        return m_userPhone;
    }

    public void setM_userPhone(String m_userPhone) {
        this.m_userPhone = m_userPhone;
    }
}
