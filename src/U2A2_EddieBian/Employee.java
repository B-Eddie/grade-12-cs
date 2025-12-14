package U2A2_EddieBian;
/**
 * Employee class that contains id number, name, salary, start date
*/

public class Employee {
    // instance variables
    private int employeeIdNumber;
    private String firstName;
    private String lastName;
    private double annualSalary;
    private String startDate;

    /**
     * Constructor
     * @param   employeeIdNumber    employee's unique ID
     * @param   firstName           employee's first name
     * @param   lastName            employee's last name
     * @param   annualSalary        their annual salary
     * @param   startDate           their date started
    */
    public Employee(int employeeIdNumber, String firstName, String lastName, double annualSalary, String startDate) {
        this.employeeIdNumber = employeeIdNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = annualSalary;
        this.startDate = startDate;
    }

    // ======Getters======
    /**
     * returns the employee id number
     * @return  employee id number (integer)
    */
    public int getEmployeeIdNumber() {
        return employeeIdNumber;
    }

    /**
     * returns the employee first name
     * @return  first name (string)
    */
    public String getFirstName() {
        return firstName;
    }

    /**
     * returns the employee last name
     * @return  last name (string)
    */
    public String getLastName() {
        return lastName;
    }

    /**
     * returns the employee annual salary
     * @return  annual salary (double)
    */
    public double getAnnualSalary() {
        return annualSalary;
    }

    /**
     * returns the employee start date
     * @return  start date (string)
    */
    public String getStartDate() {
        return startDate;
    }

    // ======Setters======
    /**
     * updates the employee id number
     * @param   idNum   employee id number (integer)
    */
    public void setEmployeeIdNumber(int idNum) {
        employeeIdNumber = idNum;
    }

    /**
     * sets the employee first name
     * @param   first   employee's first name (string)
    */
    public void setFirstName(String first) {
        firstName = first;
    }

    /**
     * sets the employee last name
     * @param   last    employee's last name (string)
    */
    public void setLastName(String last) {
        lastName = last;
    }

    /**
     * sets the employee salary
     * @param   salary  employee's salary (double)
    */
    public void setAnnualSalary(double salary) {
        annualSalary = salary;
    }

    /**
     * sets the employee start date
     * @param   date    employee's start date (string)
    */
    public void setStartDate(String date) {
        startDate = date;
    }


    /**
     * override of toString to display all variables
     * @return  formatted details as a string
    */
    @Override
    public String toString() {
        return "Id: " + employeeIdNumber + "\nName: " + firstName + " " + lastName + "\nAnnual Salary: " + annualSalary + "\nStart date: " + startDate;
    }
}