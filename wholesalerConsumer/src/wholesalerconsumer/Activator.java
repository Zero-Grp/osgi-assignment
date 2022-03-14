package wholesalerconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import wholesalepublisher.WholesalerInterface;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		serviceReference = context.getServiceReference(WholesalerInterface.class.getName());
		@SuppressWarnings("unchecked")
		WholesalerInterface wholesalerInterface = (WholesalerInterface) context.getService(serviceReference);	
		displayMainMenu(wholesalerInterface);
	}


	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(serviceReference);
	}
	
	public void displayMainMenu(WholesalerInterface wholesalerInterface) {
		
		int option;
		String suboption = "y";
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("----------MyPos - Wholesaler Manager----------");
		System.out.println("1  - Add Wholesaler");
		System.out.println("2  - Get all Wholesalers");
		System.out.println("3  - Search Wholesalers by Id");
		System.out.println("4  - Delete Wholesaler by Id");
		System.out.print("Choose an option : ");
		
		option = Integer.parseInt(sc.nextLine().trim());
		switch(option) {
			case 1:
				wholesalerInterface.insertWholesaler();
				
				while(suboption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Wholesaler (y/n)");
					suboption = sc.nextLine().trim();
		
					if(suboption.equals("y")) {
						wholesalerInterface.insertWholesaler();
					}
				}
				displayMainMenu(wholesalerInterface);
				break;
			case 2:
				wholesalerInterface.getAllWholesalers();
				displayMainMenu(wholesalerInterface);
				break;
			case 3:
				wholesalerInterface.searchWholesaler();
				displayMainMenu(wholesalerInterface);
				break;
			case 4:
				wholesalerInterface.deleteWholesaler();
				displayMainMenu(wholesalerInterface);
				break;
			default:
				System.out.println("Incorrect Input... Try Again");
				displayMainMenu(wholesalerInterface);
		}
		
		
	}

}
