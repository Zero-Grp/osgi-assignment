package itemspublisher;

public interface ItemInterface {
	
	public void insertItem();
	public void getAllItems();
	public void getItemDetailsById();
	public void getItemDetailsByName();
	public void deleteItem();
	public double getItemPriceById(int id);
	public String getCompanyNameByID(int id);

}
