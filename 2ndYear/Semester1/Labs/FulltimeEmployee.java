
/**
 * Write a description of class FulltimeEmployee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FulltimeEmployee extends Employee
{
    // instance variables - replace the example below with your own
    private double annualSalary;

    /**
     * Constructor for objects of class FulltimeEmployee
     */
    public FulltimeEmployee(String name, double salary)
    {
        super(name);
        setAnnSal(salary);
    }

    public void setAnnSal(double salary) {
        this.annualSalary = salary;
    }

    public double getAnnSal() {
        return annualSalary;
    }

    public String getStatus(){
        return "Fulltime";
    }

    public double monthlyPay()
    {
        return Math.round((annualSalary / 12) * 100d) / 100d;
    }

    public String toString()
    {
        
        return super.toString() + "\t Monthly Pay = €" + monthlyPay();
    }

    public void print()
    {
        System.out.println(toString());
    }
}
