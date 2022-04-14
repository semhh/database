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
public class UserLogin2MenuView implements ViewInterface{
    
    public static ArrayList<TcAndPassword> userBilgiler = new ArrayList();
    public static ArrayList<PersonClass> personList = new ArrayList<>();
    
     @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {          

            String tc = getString("Enter Your TC Number: ", false);
            String password = getString("Enter Your Password: ", false);
            
            operationName = "check";

            TcAndPassword userPerson = new TcAndPassword(tc, password);
            
            userPerson.setTc(tc);
            userPerson.setPassword(password);
            
            userBilgiler.add(userPerson);      

            return new ViewData("UserLoginCheck", operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "User Login 2 Menu View";
	}		
    
}
