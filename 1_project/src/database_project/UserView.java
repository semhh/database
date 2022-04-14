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
public class UserView implements ViewInterface{

    @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
            
		switch(operationName) {
		case "insert.gui":      return insertGUI(modelData);	
		case "insert":          return insertOperation(modelData);	
		case "delete.gui":      return deleteGUI(modelData);	
                case "delete":          return deleteOperation(modelData);
		case "select.gui":      return selectGUI(modelData);
                case "select":          return selectOperation(modelData);
		}
		
		return new ViewData("UserMenu", "");
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
                        String appealTypeExp = UserModel.selectAppealTypeNumber(appealTypeNumber);// --> numarayı ve açıklamasını databaseden alıp UserModelın içinde
                        System.out.println("Appeal Type is: " + appealTypeExp);        //onu hashMap e atıp daha sonra burdan giden parametreyle hashmapteki
                                                                                       //değeri stringe atıp return olarak buraya gönderiyorum. Yani databasedeki                                                          
                        if (isTurkish == 1) {                                          //açıklamasını almış oluyorum.
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
		
		return new ViewData("UserMenu", "");
	}
	
	ViewData insertOperation(ModelData modelData) throws Exception {
		System.out.println("Number of inserted row is " + modelData.recordCount);
	
		return new ViewData("UserMenu", "");
	}
  
	ViewData deleteOperation(ModelData modelData) throws Exception {
		System.out.println("Number of deleted appeals is " + modelData.recordCount);
		
		return new ViewData("UserMenu", "");
	}	
	
	Map<String, Object> getWhereParameters() throws Exception {
		
                Map<String, Object> whereParameters = new HashMap<>();
                //her türlü where parameter olarak login yapan kişinin tc sini alacak. UserLogin2MenuView'de bilgilerini db den alıp personListe atmıştık
                //burda o arraylistten kişiyi counter vesilesiyle giriş yapmaya çalışma sayısıyla senkronize şekilde tc sini alıyoruz.
                //counter çalışma prensibi ise her giriş yapılmaya çalışıldığında 1 artıyor
                whereParameters.put("TC", UserLogin2MenuView.personList.get(ModelViewControllerConsole.counter - 1).getTc());
          
                int userChoice = UserMenuView.userMenuInput.get("UserMenuChoice");
                //Menüde 2 tane select olup ikisinde de en kötü tc yi where olarak ekleyeceğim için böyle çözüm buldum.
                if(userChoice == 3 || userChoice == 4){
                    System.out.println("Filter conditions:");
                    Integer appealNumber = getInteger("Write Appeal Number : ", true);
                    if (appealNumber != null) whereParameters.put("Appeal_Number", appealNumber);
                }
                
		return whereParameters;
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("User", "select", parameters);
	}

	ViewData insertGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fieldNames", "Appeal_Type_Number, Is_Turkish, TC, Is_Legal_Entity, Legal_Entity_Title, Address, Address_Type_Number, E_Posta, Phone_Number, Phone_Type_Number, Fax, Response_Type_Number, Institution_Number, Appeal_Explanation_And_Documents");
 
		List<Object> rows = new ArrayList<>();

		String legalEntityTitle, address, ePosta, fax, phoneNumber, tc, appealExplanation;
                Integer appealTypeNumber, isTurkish, isLegalEntity, addressTypeNumber, phoneTypeNumber, institutionNumber, responseTypeNumber;
                
                System.out.println("Fields to insert:");
                
                //tamamı bittikten sonra bir boşluk varsa bile her şeyi baştan tekrar yazmasın diye her inputa gerekli korumaları ekledim
                
                do{
                    appealTypeNumber = getInteger("Appeal Type Number(1 for Internet, 2 for Fax, 3 for E-mail, 4 for Yazili) : ", true);
                }while(appealTypeNumber == null || appealTypeNumber < 1 || appealTypeNumber > 4);
                
                do{
                    isTurkish = getInteger("Is Turkish(1 for Yes, 0 for No) : ", true);
                }while(isTurkish == null || isTurkish < 0 || isTurkish > 1);
                
                //giriş ekranında girdiği için bir daha girmesine gerek yok
                tc = UserLogin2MenuView.personList.get(ModelViewControllerConsole.counter - 1).getTc();
                do {
                    isLegalEntity = getInteger("is Legal Entity(1 for Yes, 0 for No) : ", true);
                }while (isLegalEntity == null || isLegalEntity < 0 || isLegalEntity > 1);

                if (isLegalEntity == 1) {
                    do {
                        Scanner scan = new Scanner(System.in);
                        System.out.print("Legal Entity Title: ");
                        legalEntityTitle = scan.nextLine(); //getString kullanarak bunu yapmaya çalıştığımda nullPointer hatası veriyordu
                    } while (legalEntityTitle.isEmpty());   //scanner ile yapmak zorunda kaldım
                } 
                else
                    legalEntityTitle = "";
                        
                do {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Address: ");
                    address = scan.nextLine();
                }while (address.isEmpty());

                do {
                    addressTypeNumber = getInteger("Address Type Number:(1 for İş, 2 for Ev) ", true);
                }while (addressTypeNumber == null || addressTypeNumber < 1 || addressTypeNumber > 2);

                do {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("E-mail: ");
                    ePosta = scan.nextLine();
                }while (ePosta.isEmpty());

                do {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Phone Number: ");
                    phoneNumber = scan.nextLine();
                }while (phoneNumber.isEmpty());

                do {
                    phoneTypeNumber = getInteger("Phone Type: (1 for İş, 2 for Ev, 3 for Mobil) ", true);
                } while (phoneTypeNumber == null || phoneTypeNumber < 1 || phoneTypeNumber > 3);

                Scanner scan = new Scanner(System.in);
                System.out.print("Fax: ");
                fax = scan.nextLine();
                if (fax.isEmpty()) {
                    fax = "";
                }

                do {
                    responseTypeNumber = getInteger("Response Type Number(1 for Yazılı, 2 for E-mail) : ", true);
                } while (responseTypeNumber == null || responseTypeNumber < 1 || responseTypeNumber > 2);
                        
                System.out.println("Select which Institution you are sending appeal to, List Below");
                System.out.println("1 for Cumhurbaşkanlığı");
                System.out.println("2 for Adalet Bakanlığı");
                System.out.println("3 for Aile, Çalışma ve Sosyal Hizmetler Bakanlığı");
                System.out.println("5 for Sağlık Bakanlığı");
                System.out.println("6 for Çevre ve Şehircilik Bakanlığı");
                System.out.println("7 for Dışişleri Bakanlığı");
                System.out.println("8 for Enerji ve Tabii Kaynaklar Bakanlığı");
                System.out.println("9 for Gençlik ve Spor Bakanlığı");
                System.out.println("10 for Hazine ve Maliye Bakanlığı");
                System.out.println("11 for İçişleri Bakanlığı");
                System.out.println("12 for Milli Eğitim Bakanlığı");
                System.out.println("13 for Ticaret Bakanlığı");
                System.out.println("14 for Sanayi ve Teknoloji Bakanlığı");
                System.out.println("15 for Tarım ve Orman Bakanlığı");
                System.out.println("16 for Milli Savunma Bakanlığı");
                System.out.println("17 for Ulaştırma ve Altyapı Bakanlığı");
                System.out.println("18 for Kültür ve Turizm Bakanlığı");
                System.out.println("19 for Ülker Bisküvi A.Ş.");
                System.out.println("20 for Mercedes Türk");
                System.out.println("21 for SpaceX A.Ş.");
                System.out.println("22 for Sabancı Holding");
                System.out.println("23 for Koç Holding");
                System.out.println("24 for Kardeşler Kundura A.Ş.");
                do {
                    institutionNumber = getInteger("Institution Number : ", true);
                } while (institutionNumber == null || institutionNumber < 1 || institutionNumber > 24);

                do {
                    System.out.print("Write Appeal Explanation and Desired Documents: ");
                    appealExplanation = scan.nextLine();
                } while (appealExplanation.isEmpty());
                
                System.out.println();
      
                rows.add(new AppealClass(appealTypeNumber, isTurkish, tc, isLegalEntity, legalEntityTitle, address, addressTypeNumber, ePosta, phoneNumber, phoneTypeNumber, fax, responseTypeNumber, institutionNumber, appealExplanation));
	
		parameters.put("rows", rows);
		return new ViewData("User", "insert", parameters);
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
