package model;

import java.sql.Date;

public class CompanyCarBuilder {

  private String carRegNr;
  private String carBrand;
  private String carModel;
  private int carPurchasePrice;
  private Date carPurchaseDate;
  private int employeeId;

  public CompanyCarBuilder setCarRegNr(String carRegNr) {
    this.carRegNr = carRegNr;
    return this;
  }

  public CompanyCarBuilder setCarBrand(String carBrand) {
    this.carBrand = carBrand;
    return this;
  }

  public CompanyCarBuilder setCarModel(String carModel) {
    this.carModel = carModel;
    return this;
  }

  public CompanyCarBuilder setCarPurchasePrice(int carPurchasePrice) {
    this.carPurchasePrice = carPurchasePrice;
    return this;
  }

  public CompanyCarBuilder setCarPurchaseDate(Date carPurchaseDate) {
    this.carPurchaseDate = carPurchaseDate;
    return this;
  }

  public CompanyCarBuilder setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  public CompanyCar build() {
    return new CompanyCar(this.carRegNr, this.carBrand, this.carModel, this.carPurchasePrice,
        this.carPurchaseDate, this.employeeId);
  }

}
