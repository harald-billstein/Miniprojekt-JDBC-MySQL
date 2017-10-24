package model;

import java.util.HashMap;

public class UserInputErrorText {

  private final int MINIMUM_SALARY;
  private HashMap<String, String> errorMessages;
  
  public UserInputErrorText(int minimumSalary) {
    this.MINIMUM_SALARY = minimumSalary;
    initObjects();
  }
  
  public void initObjects() {
    errorMessages = new HashMap<>();
    errorMessages.put("firstName", "First name can not be empty");
    errorMessages.put("lastName", "Last name can not be empty");
    errorMessages.put("salary", "Salary must be a number and minimum of " + MINIMUM_SALARY);
    errorMessages.put("department", "Must be a number, department Id not found");
    errorMessages.put("search", "Error. Empty search string");
  }
  
  public HashMap<String, String> getErrors() {
    return this.errorMessages;
  }

}