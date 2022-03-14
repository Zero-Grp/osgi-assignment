package billerpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Biller Publisher service started");
		BillerInterface billerInterface = new BillerServiceImpl();
		serviceRegistration = context.registerService(BillerInterface.class.getName(), billerInterface, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biller Publisher service stopped");
		serviceRegistration.unregister();
	}

}
