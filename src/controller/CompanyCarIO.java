package controller;

import model.CompanyCar;

public class CompanyCarIO extends DatabaseIO<CompanyCar> {

	public CompanyCarIO(HibernateSessionManager hibernateSessionManager) {
		super(hibernateSessionManager, CompanyCar.class);
	}
}
