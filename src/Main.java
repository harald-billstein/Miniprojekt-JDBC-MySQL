import databasemodel.CompanyCarBuilder;
import databasemodel.CompanyCarBuilder.CompanyCar;
import java.sql.Date;
import java.util.logging.Level;

public class Main {

	public static void main(String[] args) {
		// TURN OFF HIBERNATE LOGGER
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		TheFirm theFirm = new TheFirm();
		theFirm.start();

	}
}
