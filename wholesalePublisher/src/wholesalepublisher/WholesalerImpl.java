package wholesalepublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import myposdb.Database;
import myposdb.DatabaseImpl;

public class WholesalerImpl implements WholesalerInterface {
	
	private Connection connection = null;
	private Statement statement = null;
	private Database database;
	private ResultSet resultset;

	public WholesalerImpl() {
		super();
		database = new DatabaseImpl();
		connection = database.getDatabaseConnection();
	}
	
	Scanner sc = new Scanner(System.in);

	@Override
	public void insertWholesaler() {
	     Wholesaler wholesaler = new Wholesaler();
			
			System.out.println("Enter Wholesaler Name: ");
			wholesaler.setName(sc.nextLine().trim());
			
			System.out.println("Enter Phone Number: ");
			wholesaler.setMobile(Integer.parseInt(sc.nextLine().trim()));
			
			
			String sqlQueryWholesaler = "INSERT INTO wholesaler(name, mobile) "
					+ "VALUES('"+ wholesaler.getName() +"', '"+ wholesaler.getMobile() +"')";
			
			try {
				statement = connection.createStatement();
				statement.executeUpdate(sqlQueryWholesaler);
				System.out.println("Wholesaler successfully inserted ...");
			} catch (SQLException exc) {
				System.out.println("Error with insert Wholesaler");
				System.out.println(exc.getMessage());
			}
	}

	@Override
	public void searchWholesaler() {
		
		int id;
		
		System.out.println("Enter Wholesaler Id : ");
		id = (Integer.parseInt(sc.nextLine().trim()));
		
		String sqlQueryWholesaler = "SELECT * FROM wholesaler WHERE id = '"+ id +"'";
		
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQueryWholesaler);
			while (resultset.next()) {  
		    	  System.out.printf("%20d %20s %20d\n",resultset.getInt("id"),resultset.getString("name"),resultset.getInt("mobile"));		    	
		      }		    	

		} catch (SQLException exc) {
			System.out.println("Error with get Wholesaler by Id");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void getAllWholesalers() {
		
		String sqlQueryWholesaler = "SELECT * FROM wholesaler";

		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQueryWholesaler);
		      while (resultset.next()) {  
		    	  System.out.printf("%20d %20s %20d\n",resultset.getInt("id"),resultset.getString("name"),resultset.getInt("mobile"));		    	
		      }
		} catch (SQLException exc) {
			System.out.println("Error with get all Wholesalers");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void deleteWholesaler() {
		
		int id;
		
		System.out.println("Enter Wholesaler Id: ");
		id = (Integer.parseInt(sc.nextLine().trim()));
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM wholesaler WHERE id = '"+ id +"'"); 
		    	  System.out.printf("Successfully Deleted!");

		} catch (SQLException exc) {
			System.out.println("Error with get Wholesaler by Id");
			System.out.println(exc.getMessage());
		}
	}

}
