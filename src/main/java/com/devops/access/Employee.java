package com.devops.access;

public class Employee {
    private String id;
    private String name;
    private int age;
    private String department;
    private String employmentStatus; // "ACTIVE" or "INACTIVE"
    private int securityClearanceLevel;
    private boolean isIdValid;

    public Employee(String id, String name, int age, String department, 
                    String employmentStatus, int securityClearanceLevel, boolean isIdValid) 
                    throws InvalidEmployeeDataException {
        
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("Employee ID cannot be empty.");
        }
        if (age < 0) {
            throw new InvalidEmployeeDataException("Age cannot be negative.");
        }
        if (securityClearanceLevel < 0) {
            throw new InvalidEmployeeDataException("Security clearance level cannot be negative.");
        }

        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.employmentStatus = employmentStatus;
        this.securityClearanceLevel = securityClearanceLevel;
        this.isIdValid = isIdValid;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public String getEmploymentStatus() { return employmentStatus; }
    public int getSecurityClearanceLevel() { return securityClearanceLevel; }
    public boolean isIdValid() { return isIdValid; }
}
