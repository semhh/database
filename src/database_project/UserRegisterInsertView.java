/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_project;

import java.util.*;
/**
 *
 * @author SaidPolat
 */
public class UserRegisterInsertView implements ViewInterface{
        
     @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {   
            
            switch(operationName) {
		case "check":           return insertGUI(modelData);	
		case "insert":          return insertOperation(modelData);			
		}
		
		return new ViewData("UserMenu", "");   
	}
        
        ViewData insertOperation(ModelData modelData) throws Exception {
		System.out.println("Number of inserted user is " + modelData.recordCount);
                
		return new ViewData("UserMenu", "");
	}
        
        ViewData insertGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fieldNames", "TC, Name, Surname, UserPassword");

		List<Object> rows = new ArrayList<>();
		
                rows.add(UserRegisterMenuView.personBilgiler1.get(ModelViewControllerConsole.counter++));
                
		parameters.put("rows", rows);
		
		return new ViewData("UserRegisterInsert", "insert", parameters);
	}

	@Override
	public String toString() {
		return "User Register Insert View";
	}		
            
    
}
