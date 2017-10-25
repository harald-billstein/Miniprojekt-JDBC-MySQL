package model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Class file used by Hibernete to fetch all info from database entity company_car.
 *
 * @author Harald & Christoffer
 */
@Entity(name = "company_car")
public class CompanyCar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reg_nr")
  private String carRegNr;

  @Column(name = "brand")
  private String carBrand;

  @Column(name = "model")
  private String carModel;

  @Column(name = "purchase_price")
  private Integer carPurchasePrice;

  @Column(name = "purchase_date")
  private Date carPurchaseDate;

  @Column(name = "employee_id")
  private Integer employeeId;

  @OneToOne()
  @JoinColumn(name = "employee_id", insertable = false, updatable = false)
  private Employee employee;

  public CompanyCar() {

  }

  /**
   * Constructor used by the CompanyCarBuilder to create a companyCar object.
   */
  public CompanyCar(String reg_nr, String brand, String model, Integer purchase_price,
      Date purchase_date, Integer employee_id) {
    super();
    this.carRegNr = reg_nr;
    this.carBrand = brand;
    this.carModel = model;
    this.carPurchasePrice = purchase_price;
    this.carPurchaseDate = purchase_date;
    this.employeeId = employee_id;
  }

  public String getCarRegNr() {
    return carRegNr;
  }

  public void setCarRegNr(String carRegNr) {
    this.carRegNr = carRegNr;
  }

  public String getCarBrand() {
    return carBrand;
  }

  public void setCarBrand(String carBrand) {
    this.carBrand = carBrand;
  }

  public String getCarModel() {
    return carModel;
  }

  public void setCarModel(String carModel) {
    this.carModel = carModel;
  }

  public Integer getCarPurchasePrice() {
    return carPurchasePrice;
  }

  public void setCarPurchasePrice(Integer carPurchasePrice) {
    this.carPurchasePrice = carPurchasePrice;
  }

  public Date getCarPurchaseDate() {
    return carPurchaseDate;
  }

  public void setCarPurchaseDate(Date carPurchaseDate) {
    this.carPurchaseDate = carPurchaseDate;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

}
