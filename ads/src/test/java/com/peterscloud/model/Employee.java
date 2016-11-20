package com.peterscloud.model;

public class Employee extends Person{
  
  private int departmentId;
  private double salary;
  private int countryId;
  
  public Employee() {
    
  }

  
  public Employee(int age, int departmentId, double salary, int countryId) {
    super(age);
    this.salary = salary;
    this.departmentId = departmentId;
    this.countryId = countryId;
  }

  
  public int getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(int departmentId) {
    this.departmentId = departmentId; 
  }

  public double getSalary() {
    return salary; 
  }
  
  public void setSalary(double salary) {
    this.salary = salary; 
  }

  public int getCountryId() {
    return countryId;
  }

  public void setCountryId(int countryId) {
    this.countryId = countryId; 
  }
  
  public String toString() {
    return "Employee: {age : " + getAge() 
      + ", salary: " + salary 
      + ", departmentId: " + departmentId 
      + ", countryId: " + countryId + "}"; 
  }

}
