package itemspublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import myposdb.Database;
import myposdb.DatabaseImpl;

public class ItemServiceImpl implements ItemInterface {

	private Connection connection = null;
	private Statement statement = null;
	private Database database;
	private ResultSet resultSet;

	Scanner sc = new Scanner(System.in);

	public ItemServiceImpl() {
		super();
		database = new DatabaseImpl();
		connection = database.getDatabaseConnection();
	}

	@Override
	public void insertItem() {

		Item item = new Item();

		System.out.println("Enter Item Name : ");
		item.setItemName(sc.nextLine().trim());

		String cpName = "";
		while (cpName == "") {
			System.out.println("Enter Item Company Id : ");
			cpName = getCompanyNameByID(Integer.parseInt(sc.nextLine().trim()));
		}
		item.setCompany(cpName);

		System.out.println("Enter Item Price : ");
		item.setPrice(Double.parseDouble(sc.nextLine().trim()));

		String sqlQueryItem = "INSERT INTO items(itemName, company, price) " + "VALUES('" + item.getItemName()
				+ "', '" + item.getCompany() + "', '" + item.getPrice() + "')";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sqlQueryItem);
			System.out.println("Item successfully inserted ...");
		} catch (SQLException exc) {
			System.out.println("Error with insert Item");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public String getCompanyNameByID(int id) {

		String companyName = "";
		String sqlQueryCompany = "SELECT name FROM wholesaler WHERE id = '" + id + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryCompany);

			while (resultSet.next()) {
				companyName = resultSet.getString("name");
			}

		} catch (SQLException exc) {
			System.out.println("Error with get Company Name by Id");
			System.out.println(exc.getMessage());
			companyName = "";
		}

		return companyName;
	}

	@SuppressWarnings("finally")
	@Override
	public void getAllItems() {

		List<Item> itemList = new ArrayList<>();

		String sqlQueryItem = "SELECT * FROM items";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryItem);

			while (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getInt("id"));
				item.setItemName(resultSet.getString("itemName"));
				item.setCompany(resultSet.getString("company"));
				item.setPrice(resultSet.getDouble("price"));

				itemList.add(item);
			}

			System.out.println("\n\n\t\t------- Item -------\n");
			if (itemList.size() > 0) {
				for (Item item : itemList) {
					System.out.printf("%20d %20s %20s %20.2f\n", item.getId(), item.getItemName(), item.getCompany(),
							item.getPrice());
				}
			} else {
				System.out.println("\t\tNo Items Available\n\n");
			}

		} catch (SQLException exc) {
			System.out.println("Error with get all Food Item");
			System.out.println(exc.getMessage());
		}

	}

	@SuppressWarnings("finally")
	@Override
	public void getItemDetailsById() {

		int id;

		System.out.print("Enter Item ID : ");
		id = Integer.parseInt(sc.nextLine().trim());

		String sqlQueryItem = "SELECT * FROM items WHERE id = '" + id + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryItem);
			while (resultSet.next()) {
				System.out.printf("\n%20d %20s %20s %20.2f\n", resultSet.getInt("id"), resultSet.getString("itemName"),
						resultSet.getString("company"), resultSet.getDouble("price"));
			}
		} catch (SQLException exc) {
			System.out.println("Error with get Item by Id");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void getItemDetailsByName() {

		String itemName;

		System.out.print("Enter Item Name : ");
		itemName = sc.nextLine().trim();

		String sqlQueryItem = "SELECT * FROM items WHERE itemName = '" + itemName + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryItem);

			while (resultSet.next()) {
				System.out.printf("\n%20d %20s %20s %20.2f\n", resultSet.getInt("id"), resultSet.getString("itemName"),
						resultSet.getString("company"), resultSet.getDouble("price"));
			}

		} catch (SQLException exc) {
			System.out.println("Error with get Item by Name");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public double getItemPriceById(int id) {
		double price = 0;
		String sqlQueryItem = "SELECT * FROM items WHERE id = '" + id + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryItem);

			while (resultSet.next()) {
				price = resultSet.getDouble("price");
			}

		} catch (SQLException exc) {
			System.out.println("Error with get Item price by Id");
			System.out.println(exc.getMessage());
			price = 0.0;
		}

		return price;
	}

	@Override
	public void deleteItem() {

		int id;

		System.out.print("Enter Item ID To Delete : ");
		id = Integer.parseInt(sc.nextLine().trim());

		try {

			statement = connection.createStatement();
			statement.executeUpdate("\ndelete from items where id='" + id + "' ");
			System.out.println("Successfully deleted Item " + id);

		} catch (SQLException exc) {
			System.out.println("Error with delete Item by Id");
			System.out.println(exc.getMessage());
		}

	}
}
