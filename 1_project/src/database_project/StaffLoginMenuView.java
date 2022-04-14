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
public class StaffLoginMenuView implements ViewInterface{
    
    public static ArrayList<IdAndPassword> bilgiler = new ArrayList<>();
    public static ArrayList<StaffClass> staffList = new ArrayList<>();
    
     @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {          

            Integer id = getInteger("Enter Your Staff ID: ", false);
            String password = getString("Enter Your Password: ", false);
            
            operationName = "check";
            
            IdAndPassword staffperson = new IdAndPassword(id, password);
            
            staffperson.setId(id);
            staffperson.setPassword(password);
            
            bilgiler.add(staffperson);      

            return new ViewData("StaffLoginCheck", operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "Staff Login Menu View";
	}		
    
}
