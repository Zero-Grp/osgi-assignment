package itemsconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import itemspublisher.ItemInterface;

public class Activator implements BundleActivator {

	private ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		serviceReference = context.getServiceReference(ItemInterface.class.getName());
		@SuppressWarnings("unchecked")
		ItemInterface itemInterface = (ItemInterface)context.getService(serviceReference);	
		displayMainMenu(itemInterface);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(serviceReference);
	}
	
	public void displayMainMenu(ItemInterface itemInterface) {
		
		int option;
		String suboption = "y";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("----------MyPos - Item Manager ----------");
		System.out.println("1  - Add Items");
		System.out.println("2  - Get all Items");
		System.out.println("3  - Search Item by Id");
		System.out.println("4  - Search Item by name");
		System.out.println("5  - Delete Item");
		System.out.print("Choose an option : ");
		
		option = Integer.parseInt(sc.nextLine().trim());
		
		switch(option) {
			case 1:
				itemInterface.insertItem();
				
				while(suboption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another item (y/n)");
					suboption = sc.nextLine().trim();
		
					if(suboption.equals("y")) {
						itemInterface.insertItem();
					}
				}
				displayMainMenu(itemInterface);
				break;
			case 2:
				itemInterface.getAllItems();
				displayMainMenu(itemInterface);
				break;
			case 3:
				itemInterface.getItemDetailsById();
				displayMainMenu(itemInterface);
				break;
			case 4:
				itemInterface.getItemDetailsByName();
				displayMainMenu(itemInterface);
				break;
			case 5:
				itemInterface.deleteItem();
				displayMainMenu(itemInterface);
				break;
			default:
				System.out.println("Incorrect Input. Try Again...");
				displayMainMenu(itemInterface);
		}
		
		
	}

}
