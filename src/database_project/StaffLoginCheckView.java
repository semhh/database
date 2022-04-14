/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_project;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SaidPolat
 */
public class StaffLoginCheckView implements ViewInterface{
    
    @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {               
                    case "select":  return selectOperation(modelData);
                    case "check":   return selectGUI(modelData);	
		}
		return new ViewData("StaffMenu", "");
	}
        
        ViewData selectOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;

		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
				int staffID = resultSet.getInt("Staff_ID");
                                String tc = resultSet.getString("TC");
                                int institutionNumber = resultSet.getInt("Institution_Number");
				String password = resultSet.getString("Password");		
                                
                                StaffClass staff = new StaffClass(staffID, tc, institutionNumber, password);
                                StaffLoginMenuView.staffList.add(staff);
                                IdAndPassword obj = StaffLoginMenuView.bilgiler.get(ModelViewControllerConsole.counter++);
                                
                                Integer inputId = obj.getId();
                                String inputPassword = obj.getPassword();
                                
                                if(inputId == staffID && password.equals(inputPassword)){    
                                    System.out.println("Login Succesfull!!");
                                    System.out.println("You are loged into appeal system of " + UserModel.selectInstitutionNumber(institutionNumber));
                                    
                                    return new ViewData("StaffMenu", "");                                  
                                }
                                else{           
                                    System.out.println("ID or Password is incorrect, Please try again!!");
                                    return new ViewData("StaffLoginMenu", "");
                                }
			}
			resultSet.close();	
		}
		
		return new ViewData("StaffMenu", "");
	}
	
        Map<String, Object> getWhereParameters() throws Exception {
                Integer id = StaffLoginMenuView.bilgiler.get(ModelViewControllerConsole.counter).getId();
                
		Map<String, Object> whereParameters = new HashMap<>();
		if (id != null) whereParameters.put("Staff_ID", id);

		return whereParameters;
	}
        
        ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("StaffLoginCheck", "select", parameters);
	}
}
