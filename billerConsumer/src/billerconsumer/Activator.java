package billerconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import billerpublisher.BillerInterface;

public class Activator implements BundleActivator {

	private ServiceReference serviceReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		serviceReference = context.getServiceReference(BillerInterface.class.getName());
		@SuppressWarnings("unchecked")
		BillerInterface billerInterface = (BillerInterface)context.getService(serviceReference);	
		displayMainMenu(billerInterface);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(serviceReference);
	}
	
	public void displayMainMenu(BillerInterface billerInterface) {
		
		int option;
		String suboption = "y";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("----------MyPos - Biller Manager ----------");
		System.out.println("1  - Add Bill");
		System.out.println("2  - Get all Bills");
		System.out.println("3  - Search Bill by Id");
		System.out.println("4  - Delete a Bill");
		System.out.print("Choose an option : ");
		
		option = Integer.parseInt(sc.nextLine().trim());
		
		switch(option) {
			case 1:
				billerInterface.insertBiller();
				
				while(suboption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Bill (y/n)");
					suboption = sc.nextLine().trim();
		
					if(suboption.equals("y")) {
						billerInterface.insertBiller();
					}
				}
				displayMainMenu(billerInterface);
				break;
			case 2:
				billerInterface.getAllBillers();
				displayMainMenu(billerInterface);
				break;
			case 3:
				billerInterface.searchBiller();
				displayMainMenu(billerInterface);
				break;
			case 4:
				billerInterface.deleteBiller();
				displayMainMenu(billerInterface);
				break;
			default:
				System.out.println("Incorrect Input. Try Again...");
				displayMainMenu(billerInterface);
		}
		
		
	}

}
