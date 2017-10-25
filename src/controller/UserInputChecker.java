package controller;

/**
 * Checks if user inputs are valid.
 *
 * @author Harald & Cristoffer
 */
public class UserInputChecker {

  private int MINIMUM_SALARY;
  private int numberOfDepartments;

  public UserInputChecker(int minimumSalart, int numberOfDepartments) {
    this.MINIMUM_SALARY = minimumSalart;
    this.numberOfDepartments = numberOfDepartments;
  }

  public boolean isFirstNameValid(String firstName) {
    boolean success = true;

    if (firstName.isEmpty()) {
      success = false;
    }
    return success;
  }

  public boolean isLastNameValid(String lastName) {
    boolean success = true;

    if (lastName.isEmpty()) {
      success = false;
    }
    return success;
  }

  public boolean isSalaryValid(String salary) {
    boolean success = true;

    if (!isParsable(salary)) {
      success = false;
    } else if (Integer.parseInt(salary) < MINIMUM_SALARY) {
      success = false;
    }

    return success;
  }

  public boolean isDepartmentIdValid(String departmentId) {
    boolean success = true;

    if (!isParsable(departmentId)) {

      success = false;
    } else if (Integer.parseInt(departmentId) < 1
        || Integer.parseInt(departmentId) > numberOfDepartments) {

      success = false;
    }

    return success;
  }

  public boolean isParsable(String s) {
    boolean success;

    try {
      Integer.parseInt(s);
      success = true;
    } catch (NumberFormatException e) {
      success = false;
    }
    return success;
  }

}
