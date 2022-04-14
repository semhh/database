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
public class UserMenuView implements ViewInterface{
        
        public static Map<String, Integer> userMenuInput = new HashMap<>();
        
        @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		Integer choice;
		do {
			System.out.println("1. New Appeal");
			System.out.println("2. Show my past appeals");
                        System.out.println("3. Show my spesific past appeal");
			System.out.println("4. Delete my appeal");
			System.out.println("5. Quit");	
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 5);
                
                userMenuInput.put("UserMenuChoice", choice);
		
		switch (choice) {
		case 1: operationName = "insert.gui";	break;
		case 2: operationName = "select.gui";   break;
                case 3: operationName = "select.gui";   break;
		case 4: operationName = "delete.gui";	break;
		default: return new ViewData(null, null);
		}
		
		return new ViewData("User", operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "User Menu View";
	}		
    
}
