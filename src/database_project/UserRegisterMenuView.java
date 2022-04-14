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
public class UserRegisterMenuView implements ViewInterface{
    
    public static ArrayList<PersonClass> personBilgiler1 = new ArrayList();
    
     @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {          
            
            String tc, name, surname, password;
            tc = getString("Enter Your Tc Number : ", false);
            name = getString("Enter Your Name : ", false);
            surname = getString("Enter Your Surname : ", false);
            password = getString("Enter Your New Password : ", false);
            System.out.println();

            operationName = "check";

            PersonClass person = new PersonClass(tc, name, surname, password);
            personBilgiler1.add(person);

            return new ViewData("UserRegisterInsert", operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "User Register Menu View";
	}		
    
}
