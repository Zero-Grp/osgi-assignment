package billerpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import myposdb.Database;
import myposdb.DatabaseImpl;

public class BillerServiceImpl implements BillerInterface {

	private Connection connection = null;
	private Statement statement = null;
	private Database database;
	private ResultSet resultSet;

	public BillerServiceImpl() {
		super();
		database = new DatabaseImpl();
		connection = database.getDatabaseConnection();
	}

	Scanner sc = new Scanner(System.in);

	@Override
	public void insertBiller() {

		Biller biller = new Biller();

		String empName = "";
		while (empName == "") {
			System.out.println("Enter Employee Id : ");
			empName = getEmployeeNameById(Integer.parseInt(sc.nextLine().trim()));
		}
		biller.setEmployee(empName);

		double tot = 0.0;
		String answer = "y";
		while (answer.equals("y")) {
			System.out.println("Enter Item Id : ");
			double price = 0.0;
			while (price == 0.0) {
				price = getItemPriceById(Integer.parseInt(sc.nextLine().trim()));
			}
			tot += price;
			System.out.println("Do you want to add more item? (Y/N): ");
			answer = sc.nextLine().trim();
		}
		biller.setTotal(tot);

		String sqlQueryBiller = "INSERT INTO biller(total, employee) " + "VALUES('" + biller.getTotal()
				+ "', '" + biller.getEmployee() + "')";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sqlQueryBiller);
			System.out.println("Biller successfully inserted ...");
		} catch (SQLException exc) {
			System.out.println("Error with insert Biller");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void searchBiller() {
		
		int id;
		
		System.out.println("Enter Biller Id : ");
		id = (Integer.parseInt(sc.nextLine().trim()));
		
		String sqlQueryBiller = "SELECT * FROM biller WHERE id = '"+ id +"'";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryBiller);
			while (resultSet.next()) {  
		    	  System.out.printf("%20d %20.2f %20s\n",resultSet.getInt("id"),resultSet.getDouble("total"),resultSet.getString("employee"));		    	
		      }		    	

		} catch (SQLException exc) {
			System.out.println("Error with get Biller by Id");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void getAllBillers() {
		
		String sqlQueryBiller = "SELECT * FROM biller";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryBiller);
		      while (resultSet.next()) {  
		    	  System.out.printf("%20d %20.2f %20s\n",resultSet.getInt("id"),resultSet.getDouble("total"),resultSet.getString("employee"));		    	
		      }
		} catch (SQLException exc) {
			System.out.println("Error with get all Billers");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void deleteBiller() {
		
		int id;
		
		System.out.println("Enter Id: ");
		id = (Integer.parseInt(sc.nextLine().trim()));
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM biller WHERE id = '"+ id +"'"); 
		    	  System.out.printf("Successfully Deleted!");

		} catch (SQLException exc) {
			System.out.println("Error with delete Biller by Id");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public double getItemPriceById(int id) {

		double itemPrice = 0.0;
		String sqlQueryItemPrice = "SELECT price FROM items WHERE id = '" + id + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryItemPrice);

			while (resultSet.next()) {
				itemPrice = resultSet.getDouble("price");
			}

		} catch (SQLException exc) {
			System.out.println("Error with get Item Price by Id");
			System.out.println(exc.getMessage());
			itemPrice = 0.0;
		}

		return itemPrice;
	}

	@Override
	public String getEmployeeNameById(int id) {

		String name = "";
		String sqlQueryEmployeeName = "SELECT name FROM employee WHERE id = '" + id + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryEmployeeName);

			while (resultSet.next()) {
				name = resultSet.getString("name");
			}

		} catch (SQLException exc) {
			System.out.println("Error with get Employee Name by Id");
			System.out.println(exc.getMessage());
			name = "";
		}

		return name;
	}

}
