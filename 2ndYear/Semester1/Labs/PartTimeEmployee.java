
/**
 * Write a description of class PartTimeEmployee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PartTimeEmployee extends Employee
{
    // instance variables - replace the example below with your own
    private double noHours, hourlyRate = 9.90;

    /**
     * Constructor for objects of class PartTimeEmployee
     */
    public PartTimeEmployee(String thisName, double thisNoHours)
    {
        super(thisName);
        setHours(thisNoHours);
    }

    public String getStatus(){
        return "Part-time";
    }

    public double weeklyPay()
    {
        return (double)Math.round((hourlyRate * noHours) * 100d) / 100d;
    }

    public String toString()
    {
        return super.toString() + "\t weekly Pay = €" + weeklyPay();
    }
    
    public void setHours(double noHours) {
        this.noHours = noHours;
    }

    public double getHours() {
        return noHours;
    }

    public void print()
    {
        System.out.println(toString());
    }
}
