/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_project;

import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author SaidPolat
 */
public class UserLoginCheckView implements ViewInterface{

     @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {               
                    case "select":  return selectOperation(modelData);
                    case "check":   return selectGUI(modelData);	
		}
		
		return new ViewData("UserMenu", "");
	}
        
        ViewData selectOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
                
		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
				String tc = resultSet.getString("TC");
                                String name = resultSet.getString("Name");
                                String surname = resultSet.getString("Surname");
				String password = resultSet.getString("UserPassword");		
                                
                                PersonClass person = new PersonClass(tc, name, surname, password);
                                UserLogin2MenuView.personList.add(person);
                                TcAndPassword obj = UserLogin2MenuView.userBilgiler.get(ModelViewControllerConsole.counter);
                                ModelViewControllerConsole.counter++;
                                
                                String inputTc = obj.getTc();
                                String inputPassword = obj.getPassword();
                                
                                if(inputTc.equals(tc) && password.equals(inputPassword)){    
                                    System.out.println("Login Succesfull!!");
                                    System.out.println("Welcome " + person.getName() + " " + person.getSurname());
                                    return new ViewData("UserMenu", "");                   
                                }
                                else{           
                                    System.out.println("ID or Password is incorrect, Please try again!!");
                                    return new ViewData("UserLogin2Menu", "");
                                }
			}
			resultSet.close();	
		}
		return new ViewData("UserMenu", "");
	}
	
        Map<String, Object> getWhereParameters() throws Exception {
		Map<String, Object> whereParameters = new HashMap<>();
		if (UserLogin2MenuView.userBilgiler.get(ModelViewControllerConsole.counter).getTc()!= null) whereParameters.put("TC", UserLogin2MenuView.userBilgiler.get(ModelViewControllerConsole.counter).getTc());
		
		return whereParameters;
	}
        
        ViewData selectGUI(ModelData modelData) throws Exception {
            
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("UserLoginCheck", "select", parameters);
	}
    
}
