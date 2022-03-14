package billerpublisher;

public interface BillerInterface {
	
	public void insertBiller();
	public void searchBiller();
	public void getAllBillers();
	public void deleteBiller();
	public double getItemPriceById(int id);
	public String getEmployeeNameById(int id);

}
