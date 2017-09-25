import databasemodel.CompanyCarBuilder;
import databasemodel.CompanyCarBuilder.CompanyCar;
import java.sql.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("");
		
		
		CompanyCar companyCar = new CompanyCarBuilder()
				.setReg_nr("ABC123")
				.setBrand("VOLVO").setModel("V70")
				.setPurchase_price(250000)
				.setPurchase_date(Date.valueOf("1978-03-03"))
				.setEmployee_id(1)
				.build();
		
				

	}

}
