package controller;

import java.util.HashMap;
import model.UserInputErrorText;

public class UserInputChecker {

  private int MINIMUM_SALARY;
  private int numberOfDepartments;
  private UserInputErrorText userInputErrorText;

  public UserInputChecker(int minimumSalart, int numberOfDepartments) {
    this.MINIMUM_SALARY = minimumSalart;
    this.numberOfDepartments = numberOfDepartments;
    userInputErrorText = new UserInputErrorText(MINIMUM_SALARY);
  }

  public boolean isValidFirstName(String firstName) {
    boolean success = true;

    if (firstName.isEmpty()) {
      success = false;
    }
    return success;
  }

  public boolean isValidLastName(String lastName) {
    boolean success = true;

    if (lastName.isEmpty()) {
      success = false;
    }
    return success;
  }

  public boolean isValidSalary(String salary) {
    boolean success = true;

    if (!isParasble(salary)) {
      success = false;
    } else if (Integer.parseInt(salary) < MINIMUM_SALARY) {
      success = false;
    }

    return success;
  }

  public boolean isValidDepartmentId(String departmentId) {
    boolean success = true;

    if (!isParasble(departmentId)) {

      success = false;
    } else if (Integer.parseInt(departmentId) < 1
        || Integer.parseInt(departmentId) > numberOfDepartments) {

      success = false;
    }

    return success;
  }

  public boolean isParasble(String s) {
    boolean success;

    try {
      Integer.parseInt(s);
      success = true;
    } catch (NumberFormatException e) {
      success = false;
    }
    return success;
  }

  public UserInputErrorText getUserInputErrorText() {
    return userInputErrorText;
  }

  
}
