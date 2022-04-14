/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_project;

import java.sql.*;
import java.util.*;


/**
 *
 * @author SaidPolat
 */
class UserModel implements ModelInterface{
    
    @Override
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("	Appeal_Number, Appeal_Date, Appeal_Response_Date, Appeal_Type_Number, Is_Turkish, TC, Is_Legal_Entity, Legal_Entity_Title, Address, Address_Type_Number, E_Posta, Phone_Number, Phone_Type_Number, Fax, Response_Type_Number, Appeal_Response_Explanation, Rejection_Reason_Number, Appeal_Situation_Number, Institution_Number, Appeal_Explanation_And_Documents");
		sql.append(" FROM APPEAL ");

		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		
		sql.append("ORDER BY Appeal_Number");		
		//System.out.println(sql.toString() + "\n");

		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
		ResultSet result = preparedStatement.executeQuery();
		
		return result;
	}
		
	@Override
	public int insert(String fieldNames, List<Object> rows) throws Exception
	{
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO APPEAL (" + fieldNames + ") " );
		sql.append(" VALUES ");
                
		String[] fieldList = fieldNames.split(",");

		int rowCount = 0;
		for (int i=0; i<rows.size(); i++) {
			if (rows.get(i) instanceof AppealClass) {
				rowCount++;
				
                                AppealClass appeal = (AppealClass)rows.get(i);
                                
				sql.append("(");
				for (int j=0; j<fieldList.length; j++) {
					String fieldName = fieldList[j].trim();
					sql.append(DatabaseUtilities.formatField(appeal.getByName(fieldName)));
					if (j < fieldList.length - 1) {
						sql.append(", ");
					}
				}
				sql.append(")");
				if (i < rows.size() - 1) {
					sql.append(", ");
				}
			}
		}	
		//System.out.println(sql.toString());
		
		// execute constructed SQL statement
		if (rowCount > 0) {
			Connection connection = DatabaseUtilities.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			rowCount = preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		return rowCount;
	}

	@Override
	public int delete(Map<String,Object> whereParameters) throws Exception
	{
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM APPEAL ");

		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		//System.out.println(sql.toString());

	
		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);		
		int rowCount = preparedStatement.executeUpdate();
		preparedStatement.close();
		
		return rowCount;
	}
   
        public static String selectAppealSituation(Integer input) throws Exception{
            
            String res;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("     Appeal_Situation_Number, Explanation ");
            sql.append(" FROM APPEAL_SITUATION ");
            sql.append(" ORDER BY Appeal_Situation_Number");
            
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet result = preparedStatement.executeQuery();
            
            Map<Integer, String> appealSituationNumberMap = new HashMap<>();
            
            if(result != null){
                while(result.next()){
                    int appealSituationNumber = result.getInt("Appeal_Situation_Number");
                    String explanation = result.getString("Explanation");     
                    appealSituationNumberMap.put(appealSituationNumber, explanation);
                }
                result.close();
            }
            
            res = appealSituationNumberMap.get(input);
		
            return res;
        }
        
        public static String selectAppealTypeNumber(Integer input) throws Exception{
            
            String res;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("     Appeal_Type_Number, Explanation ");
            sql.append(" FROM APPEAL_TYPE ");
            sql.append(" ORDER BY Appeal_Type_Number");
            
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet result = preparedStatement.executeQuery();
            
            Map<Integer, String> appealTypeNumberMap = new HashMap<>();
            
            if(result != null){
                while(result.next()){
                    int appealTypeNumber = result.getInt("Appeal_Type_Number");
                    String explanation = result.getString("Explanation");     
                    appealTypeNumberMap.put(appealTypeNumber, explanation);
                }
                result.close();
            }
            
            res = appealTypeNumberMap.get(input);
		
            return res;
        }
        
        public static String selectAddressTypeNumber(Integer input) throws Exception{
            
            String res;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("     Address_Type_Number, Explanation ");
            sql.append(" FROM ADDRESS_TYPE ");
            sql.append(" ORDER BY Address_Type_Number");
            
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet result = preparedStatement.executeQuery();
            
            Map<Integer, String> addressTypeNumberMap = new HashMap<>();
            
            if(result != null){
                while(result.next()){
                    int addressTypeNumber = result.getInt("Address_Type_Number");
                    String explanation = result.getString("Explanation");     
                    addressTypeNumberMap.put(addressTypeNumber, explanation);
                }
                result.close();
            }
            
            res = addressTypeNumberMap.get(input);
		
            return res;
        }
        
        public static String selectPhoneTypeNumber(Integer input) throws Exception{
            
            String res;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("     Phone_Type_Number, Explanation ");
            sql.append(" FROM PHONE_TYPE ");
            sql.append(" ORDER BY Phone_Type_Number");
            
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet result = preparedStatement.executeQuery();
            
            Map<Integer, String> phoneTypeNumberMap = new HashMap<>();
            
            if(result != null){
                while(result.next()){
                    int phoneTypeNumber = result.getInt("Phone_Type_Number");
                    String explanation = result.getString("Explanation");     
                    phoneTypeNumberMap.put(phoneTypeNumber, explanation);
                }
                result.close();
            }
            
            res = phoneTypeNumberMap.get(input);
		
            return res;
        }
        
        public static String selectResponseTypeNumber(Integer input) throws Exception{
            
            String res;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("     Response_Type_Number, Explanation ");
            sql.append(" FROM RESPONSE_TYPE ");
            sql.append(" ORDER BY Response_Type_Number");
            
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet result = preparedStatement.executeQuery();
            
            Map<Integer, String> responseTypeNumberMap = new HashMap<>();
            
            if(result != null){
                while(result.next()){
                    int responseTypeNumber = result.getInt("Response_Type_Number");
                    String explanation = result.getString("Explanation");     
                    responseTypeNumberMap.put(responseTypeNumber, explanation);
                }
                result.close();
            }
            
            res = responseTypeNumberMap.get(input);
		
            return res;
        }
        
        public static String selectRejectReasonNumber(Integer input) throws Exception{
            
            String res;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("     Rejection_Reason_Number, Explanation, Secret_Information ");
            sql.append(" FROM REJECTION_REASON ");
            sql.append(" ORDER BY Rejection_Reason_Number");
            
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet result = preparedStatement.executeQuery();
            
            Map<Integer, String> rejectReasonNumberMap = new HashMap<>();
            
            if(result != null){
                while(result.next()){
                    int rejectReasonNumber = result.getInt("Rejection_Reason_Number");
                    String explanation = result.getString("Explanation");   
                    int secretInfo = result.getInt("Secret_Information");
                    
                    if(secretInfo == 1){
                        explanation += "  ( Gizli Bilgidir!! )  ";
                    }
                    
                    rejectReasonNumberMap.put(rejectReasonNumber, explanation);
                }
                result.close();
            }
            
            res = rejectReasonNumberMap.get(input);
		
            return res;
        }
        
        public static String selectInstitutionTypeNumber(Integer input) throws Exception{
            
            String res;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("     Institution_Type_Number, Explanation ");
            sql.append(" FROM INSTITUTION_TYPE ");
            sql.append(" ORDER BY Institution_Type_Number");
            
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet result = preparedStatement.executeQuery();
            
            Map<Integer, String> institutionTypeNumberMap = new HashMap<>();
            
            if(result != null){
                while(result.next()){
                    int institutionTypeNumber = result.getInt("Institution_Type_Number");
                    String explanation = result.getString("Explanation");     
                    institutionTypeNumberMap.put(institutionTypeNumber, explanation);
                }
                result.close();
            }
            
            res = institutionTypeNumberMap.get(input);
		
            return res;
        }
        
        public static String selectInstitutionNumber(Integer input) throws Exception{
            
            String res;
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ");
            sql.append("     Institution_Number, Institution_Name, Institution_Type_Number ");
            sql.append(" FROM INSTITUTION ");
            sql.append(" ORDER BY Institution_Number");
            
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            ResultSet result = preparedStatement.executeQuery();
            
            Map<Integer, String> institutionNumberMap = new HashMap<>();
            
            Integer institutionTypeNumber = 1;
            
            if(result != null){
                while(result.next()){
                    int institutionNumber = result.getInt("Institution_Number");
                    String institutionName = result.getString("Institution_Name");
                    institutionTypeNumber = result.getInt("Institution_Type_Number");
                    String typeRes = selectInstitutionTypeNumber(institutionTypeNumber);
                    institutionName += ",  Bu kurum " + typeRes + "dur.";
                    institutionNumberMap.put(institutionNumber, institutionName);
                }
                result.close();
            }
            res = institutionNumberMap.get(input);
	
            return res;
        }

	@Override
	public String toString() {
		return "User Model";
	}		

    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
