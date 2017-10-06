package databasecontroller;

import databasemodel.CompanyCar;

public class CompanyCarIO extends DatabaseIO<CompanyCar> {
	
	public CompanyCarIO(HibernateSessionManager hibernateSessionManager) {
		super(hibernateSessionManager, CompanyCar.class);
	}

}
