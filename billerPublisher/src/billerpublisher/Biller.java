package billerpublisher;

public class Biller {
	
	private int id;
	private double total;
	private String employee;
	
	public Biller() {
		super();
	}

	public Biller(int id, double total, String employee) {
		super();
		this.id = id;
		this.total = total;
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

}
