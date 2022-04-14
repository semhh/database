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
public class UserLogin1MenuView implements ViewInterface{
    
     @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {    
            
            Integer choice;
		do {
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Quit");	
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 3);
	
		Map<String, Object> userInput = new HashMap<>();
		userInput.put("UserMenuChoice", choice);
		
		switch (choice) {
		case 1: return new ViewData("UserLogin2Menu", ""); 
                case 2: return new ViewData("UserRegisterMenu", "");                
		default: return new ViewData(null, null);
                }
        }

	@Override
	public String toString() {
		return "User Login 1 Menu View";
	}		
     
}
