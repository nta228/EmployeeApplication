package com.example.employeeapplication;

public class Employee {
    private  int id;
    private String employeeName;
    private String designation;
    private String salary;

    public Employee() {
    }

    public Employee(int id, String employeeName, String designation, String salary) {
        this.id = id;
        this.employeeName = employeeName;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
