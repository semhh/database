/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_project;

/**
 *
 * @author SaidPolat
 */
public class AppealClass {
    
    private String legalEntityTitle, address, ePosta, tc, fax, phoneNumber, appealExplanation;
    private int appealTypeNumber, isTurkish, isLegalEntity, addressTypeNumber, phoneTypeNumber, institutionNumber, responseTypeNumber;

    public AppealClass(int appealTypeNumber, int isTurkish, String tc, int isLegalEntity, String legalEntityTitle, String address, int addressTypeNumber, String ePosta, String phoneNumber, int phoneTypeNumber, String fax, int responseTypeNumber, int institutionNumber, String appealExplanation) {
        this.appealTypeNumber = appealTypeNumber;
        this.isTurkish = isTurkish;
        this.tc = tc;
        this.isLegalEntity = isLegalEntity;
        this.legalEntityTitle = legalEntityTitle;
        this.address = address;
        this.addressTypeNumber = addressTypeNumber;
        this.ePosta = ePosta;
        this.phoneNumber = phoneNumber;
        this.phoneTypeNumber = phoneTypeNumber;
        this.fax = fax;
        this.responseTypeNumber = responseTypeNumber;
        this.institutionNumber = institutionNumber;  
        this.appealExplanation = appealExplanation;
    }

    public String getLegalEntityTitle() {
        return legalEntityTitle;
    }

    public void setLegalEntityTitle(String legalEntityTitle) {
        this.legalEntityTitle = legalEntityTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getePosta() {
        return ePosta;
    }

    public void setePosta(String ePosta) {
        this.ePosta = ePosta;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public int getAppealTypeNumber() {
        return appealTypeNumber;
    }

    public void setAppealTypeNumber(int appealTypeNumber) {
        this.appealTypeNumber = appealTypeNumber;
    }

    public int getIsTurkish() {
        return isTurkish;
    }

    public void setIsTurkish(int isTurkish) {
        this.isTurkish = isTurkish;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public int getIsLegalEntity() {
        return isLegalEntity;
    }

    public void setIsLegalEntity(int isLegalEntity) {
        this.isLegalEntity = isLegalEntity;
    }

    public int getAddressTypeNumber() {
        return addressTypeNumber;
    }

    public void setAddressTypeNumber(int addressTypeNumber) {
        this.addressTypeNumber = addressTypeNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneTypeNumber() {
        return phoneTypeNumber;
    }

    public void setPhoneTypeNumber(int phoneTypeNumber) {
        this.phoneTypeNumber = phoneTypeNumber;
    }

    public int getResponseTypeNumber() {
        return responseTypeNumber;
    }

    public void setResponseTypeNumber(int responseTypeNumber) {
        this.responseTypeNumber = responseTypeNumber;
    }
    
    public int getInstitutionNumber() {
        return institutionNumber;
    }

    public void setInstitutionNumber(int institutionNumber) {
        this.institutionNumber = institutionNumber;
    }

    public String getAppealExplanation() {
        return appealExplanation;
    }

    public void setAppealExplanation(String appealExplanation) {
        this.appealExplanation = appealExplanation;
    }
    
    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "Legal_Entity_Title": return legalEntityTitle;
                case "Appeal_Type_Number": return appealTypeNumber;
		case "Is_Turkish": return isTurkish;
		case "TC": return tc;
                case "Is_Legal_Entity": return isLegalEntity;
                case "Address": return address;
                case "Address_Type_Number": return addressTypeNumber;
                case "E_Posta": return ePosta;
                case "Phone_Number": return phoneNumber;
                case "Phone_Type_Number": return phoneTypeNumber;
                case "Fax": return fax;
                case "Institution_Number": return institutionNumber;
                case "Response_Type_Number": return responseTypeNumber;
                case "Appeal_Explanation_And_Documents": return appealExplanation;
		default: return null;
		}
    }

    @Override
    public String toString() {
        return appealTypeNumber + ", " + isTurkish + ", " + tc + ", " + isLegalEntity + ", " + legalEntityTitle + ", " + address + ", " + addressTypeNumber + ", " + ePosta + ", " + phoneNumber + ", " + phoneTypeNumber + ", " + fax + ", " + responseTypeNumber + ", " + institutionNumber + ", " + appealExplanation;
    }
    
    

}
