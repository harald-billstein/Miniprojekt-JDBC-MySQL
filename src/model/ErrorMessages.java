package model;

import java.util.HashMap;

/**
 * Container for all error messages
 * 
 * @author Harald & Cristoffer
 */

public class ErrorMessages {

  private final int MINIMUM_SALARY;
  private HashMap<String, String> errorMessages;

  public ErrorMessages(int minimumSalary) {
    this.MINIMUM_SALARY = minimumSalary;
    initObjects();
  }

  private void initObjects() {
    errorMessages = new HashMap<>();
    errorMessages.put("firstName", "First name can not be empty");
    errorMessages.put("lastName", "Last name can not be empty");
    errorMessages.put("salary", "Salary must be a number and minimum of " + MINIMUM_SALARY);
    errorMessages.put("department", "Must be a number, department Id not found");
    errorMessages.put("search", "Error. Empty search string");
  }

  public String getFirstNameError() {
    return errorMessages.get("firstName");
  }

  public String getLastNameError() {
    return errorMessages.get("lastName");
  }

  public String getSalaryError() {
    return errorMessages.get("salary");
  }

  public String getDepartmentError() {
    return errorMessages.get("department");
  }

  public String getSearchError() {
    return errorMessages.get("search");
  }

}
