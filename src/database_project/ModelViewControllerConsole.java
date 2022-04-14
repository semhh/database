package database_project;

import java.util.*;


public class ModelViewControllerConsole {
    
        public static int counter = 0;

	public static void main(String[] args) throws Exception {
		// Connect to database
		connectToDatabase();
               
		// Model View Controller (MVC)
		// Router knows all the controllers
		Map<String, Controller> router = new HashMap<>();			
                router.put("LoginMenu", new Controller(new LoginMenuView(), new NopModel()));
                router.put("UserMenu", new Controller(new UserMenuView(), new NopModel()));
                router.put("StaffLoginMenu", new Controller(new StaffLoginMenuView(), new NopModel()));
                router.put("StaffLoginCheck", new Controller(new StaffLoginCheckView(), new StaffLoginCheckModel()));
                router.put("StaffMenu", new Controller(new StaffMenuView(), new NopModel()));
                router.put("Staff", new Controller(new StaffView(), new StaffModel()));
                router.put("User", new Controller(new UserView(), new UserModel()));
                router.put("UserLogin1Menu", new Controller(new UserLogin1MenuView(), new NopModel()));
                router.put("UserLogin2Menu", new Controller(new UserLogin2MenuView(), new NopModel()));
                router.put("UserLoginCheck", new Controller(new UserLoginCheckView(), new UserLoginCheckModel()));
                router.put("UserRegisterMenu", new Controller(new UserRegisterMenuView(), new NopModel()));
                router.put("UserRegisterInsert", new Controller(new UserRegisterInsertView(), new UserRegisterInsertModel()));
                
		ViewData viewData = new ViewData("LoginMenu", "");		
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			ModelData modelData = controller.executeModel(viewData);
			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);
			
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		while (viewData.functionName != null);
		
		System.out.println();
		System.out.println();
		System.out.println("Program is ended...");

		// Disconnect from database
		disconnectFromDatabase();
	}

	
	public static void connectToDatabase() {
		DatabaseUtilities.host = "localhost:53617";
		DatabaseUtilities.databaseName = "Group17_Project";
		
		try {
			DatabaseUtilities.getConnection();
		}
		catch(Exception e) {
			System.out.println("Exception occured : " + e);
			return;
		}		
	}
	
	public static void disconnectFromDatabase() {
		try {
			DatabaseUtilities.disconnect();
		}
		catch(Exception e) {
			System.out.println("Error disconnecting from database : " + e);
			return;
		}		
	}
	
}
