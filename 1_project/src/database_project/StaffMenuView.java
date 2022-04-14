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
public class StaffMenuView implements ViewInterface{
    
    public static Map<String, Integer> userInput = new HashMap<>();
    
    @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		Integer choice;
		do {
			System.out.println("1. Show My Institutions Appeals");
                        System.out.println("2. Show Appeals With Filter");
			System.out.println("3. Add Appeal Response Explanation");
			System.out.println("4. Write Rejection Reason Number");
                        System.out.println("5. Write Appeal Situation Number");
                        System.out.println("6. Forward Institution Number of Appeal");
                        System.out.println("7. Quit");
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 7);
	
		userInput.put("StaffMenuChoice", choice);
		
		switch (choice) {
		case 1: operationName = "select.gui";                   break;
		case 2: operationName = "select.gui";                   break;
		case 3: operationName = "update.gui";                   break;
		case 4: operationName = "update.gui";                   break;
                case 5: operationName = "update.gui";                   break;
                case 6: operationName = "update.gui";                   break;
                default: return new ViewData(null, null);
		}
		
		return new ViewData("Staff", operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "Staff Menu View";
	}		
    
}
