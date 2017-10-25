package model;

import java.sql.Date;

/**
 * A builder class building companyCar objects.
 *
 * @author Harald & Cristoffer
 */
public class CompanyCarBuilder {

  private String carRegNr;
  private String carBrand;
  private String carModel;
  private Integer carPurchasePrice;
  private Date carPurchaseDate;
  private Integer employeeId;

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

  public CompanyCarBuilder setCarPurchasePrice(Integer carPurchasePrice) {
    this.carPurchasePrice = carPurchasePrice;
    return this;
  }

  public CompanyCarBuilder setCarPurchaseDate(Date carPurchaseDate) {
    this.carPurchaseDate = carPurchaseDate;
    return this;
  }

  public CompanyCarBuilder setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
    return this;
  }

  /**
   * Method takes all parameters from this class and sets them on the companyCar object.
   *
   * @return a complete companyCar object.
   */
  public CompanyCar build() {
    return new CompanyCar(this.carRegNr, this.carBrand, this.carModel, this.carPurchasePrice,
        this.carPurchaseDate, this.employeeId);
  }

}
