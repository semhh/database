/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_project;

import java.util.*;
import java.sql.ResultSet;

/**
 *
 * @author SaidPolat
 */
public class StaffView implements ViewInterface{

    @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		switch(operationName) {
		case "select.gui":      return selectGUI(modelData);
                case "select":          return selectOperation(modelData);
		case "update.gui":      return updateGUI(modelData);
                case "update":          return updateOperation(modelData);
		}
		
		return new ViewData("StaffMenu", "");
	}
	
	ViewData selectOperation(ModelData modelData) throws Exception {
            ResultSet resultSet = modelData.resultSet;

            if (resultSet != null) {
                while (resultSet.next()) {
                    // Retrieve by column name
                    int appealNumber = resultSet.getInt("Appeal_Number");
                    Date appealDate = resultSet.getDate("Appeal_Date");
                    Date appealResponseDate = resultSet.getDate("Appeal_Response_Date");
                    int appealTypeNumber = resultSet.getInt("Appeal_Type_Number");
                    int isTurkish = resultSet.getInt("Is_Turkish");
                    String tc = resultSet.getString("TC");
                    int isLegalEntity = resultSet.getInt("Is_Legal_Entity");
                    String legalEntityTitle = resultSet.getString("Legal_Entity_Title");
                    String address = resultSet.getString("Address");
                    int addressTypeNumber = resultSet.getInt("Address_Type_Number");
                    String ePosta = resultSet.getString("E_Posta");
                    String phoneNumber = resultSet.getString("Phone_Number");
                    int phoneTypeNumber = resultSet.getInt("Phone_Type_Number");
                    String fax = resultSet.getString("Fax");
                    int responseTypeNumber = resultSet.getInt("Response_Type_Number");
                    String appealResponseExplanation = resultSet.getString("Appeal_Response_Explanation");
                    int rejectionReasonNumber = resultSet.getInt("Rejection_Reason_Number");
                    int appealSituationNumber = resultSet.getInt("Appeal_Situation_Number");
                    int institutionNumber = resultSet.getInt("Institution_Number");
                    String appealExplanation = resultSet.getString("Appeal_Explanation_And_Documents");

                    // Display values
                    System.out.println("Appeal Number is: " + appealNumber);
                    System.out.println("Appeal Date is: " + appealDate);
                    System.out.println("Appeal Response Date is: " + appealResponseDate);
                    String appealTypeExp = UserModel.selectAppealTypeNumber(appealTypeNumber);
                    System.out.println("Appeal Type is: " + appealTypeExp);
                    if (isTurkish == 1) {
                        System.out.println("Is Turkish is: Yes");
                    } else {
                        System.out.println("Is Turkish is: No");
                    }
                    System.out.println("TC Number is: " + tc);
                    if (isLegalEntity == 1) {
                        System.out.println("Are you Legal Entity: Yes");
                    } else {
                        System.out.println("Are you Legal Entity: No");
                    }
                    System.out.println("Legal Entity Title is: " + legalEntityTitle);
                    System.out.println("Address is: " + address);

                    String addressTypeNumberExp = UserModel.selectAddressTypeNumber(addressTypeNumber);
                    System.out.println("Address Type is: " + addressTypeNumberExp);
                    System.out.println("E-mail is: " + ePosta);
                    System.out.println("Phone Number is: " + phoneNumber);

                    String phoneTypeNumberExp = UserModel.selectPhoneTypeNumber(phoneTypeNumber);
                    System.out.println("Phone Type is: " + phoneTypeNumberExp);
                    System.out.println("Fax Number is: " + fax);

                    String responseTypeNumberExp = UserModel.selectResponseTypeNumber(responseTypeNumber);
                    System.out.println("Response Type is: " + responseTypeNumberExp);
                    System.out.println("Appeal Response Explanation is: " + appealResponseExplanation);

                    String rejectionReasonNumberExp = UserModel.selectRejectReasonNumber(rejectionReasonNumber);
                    System.out.println("Rejection Reason is: " + rejectionReasonNumberExp);

                    String appealSituationExp = UserModel.selectAppealSituation(appealSituationNumber);
                    System.out.println("Appeal Situation is: " + appealSituationExp);

                    String institutionNumberExp = UserModel.selectInstitutionNumber(institutionNumber);
                    System.out.println("Institution is: " + institutionNumberExp);
                    System.out.println("Appeal Explanation and Desired Documents is: " + appealExplanation);
                    System.out.println("\n\n----------------------------------------\n\n");
                }
                resultSet.close();
            }

            return new ViewData("StaffMenu", "");
        }

	ViewData updateOperation(ModelData modelData) throws Exception {
		System.out.println("Number of updated rows is " + modelData.recordCount);
		
		return new ViewData("StaffMenu", "");
	}
	
	Map<String, Object> getWhereParameters() throws Exception {
                Map<String, Object> whereParameters = new HashMap<>();
                whereParameters.put("Institution_Number", StaffLoginMenuView.staffList.get(ModelViewControllerConsole.counter - 1).getInstitutionNumber());

                int userChoice = StaffMenuView.userInput.get("StaffMenuChoice");
                if(userChoice == 2 || userChoice == 3 || userChoice == 4 || userChoice == 5 || userChoice == 6){
                    System.out.println("Filter conditions:");
                    Integer appealNumber = getInteger("Write Appeal Number : ", true);
                    if (appealNumber != null) whereParameters.put("Appeal_Number", appealNumber);
                }

		return whereParameters;
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Staff", "select", parameters);
	}

	ViewData updateGUI(ModelData modelData) throws Exception {
                
                Map<String, Object> updateParameters = new HashMap<>();
            
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("whereParameters", getWhereParameters());
		parameters.put("updateParameters", updateParameters);

                Integer userChoice = StaffMenuView.userInput.get("StaffMenuChoice");
                
                switch(userChoice){
                    case 3:
                        String responseExplanation = getString("Write Response Explanation : ", true);
                        if (responseExplanation != null) updateParameters.put("Appeal_Response_Explanation", responseExplanation);
                        break;
                    case 4: 
                        Integer rejectionReasonNumber = getInteger("Write Rejection Reason Number: ", true);
                        if (rejectionReasonNumber != null) updateParameters.put("Rejection_Reason_Number", rejectionReasonNumber);
                        break;
                    case 5: 
                        Integer appealSituationNumber = getInteger("Write Appeal Situation Number: ", true);
                        if (appealSituationNumber != null) updateParameters.put("Appeal_Situation_Number", appealSituationNumber);
                        break;
                    case 6: 
                        Integer institutionNumber = getInteger("Forward Institution to: ", true);
                        if (institutionNumber != null) updateParameters.put("Institution_Number", institutionNumber);
                        break;
                }
	
		return new ViewData("Staff", "update", parameters);
	}

	ViewData deleteGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("User", "delete", parameters);
	}

	@Override
	public String toString() {
		return "User View";
	}		
    
}
