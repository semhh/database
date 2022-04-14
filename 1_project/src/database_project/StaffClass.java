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
public class StaffClass {
    
    private int staffId, institutionNumber;
    private String tc, password;

    public StaffClass(int staffId, String tc, int institutionNumber, String password) {
        this.staffId = staffId;
        this.tc = tc;
        this.institutionNumber = institutionNumber;     
        this.password = password;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getInstitutionNumber() {
        return institutionNumber;
    }

    public void setInstitutionNumber(int institutionNumber) {
        this.institutionNumber = institutionNumber;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "Staff_ID": return staffId;
		case "TC": return tc;
		case "Institution_Number": return institutionNumber;
                case "Password": return password;
		default: return null;
		}
	}
    
}
