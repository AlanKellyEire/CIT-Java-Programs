
/**
 * Write a description of class Employee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Employee
{
    // instance variables - replace the example below with your own
    private String name;
    private int number;
    static int emNo = 0;

    /**
     * Constructor for objects of class Employee
     */
    public Employee(String thisName)
    {
        setName(thisName);
        emNo++;
        setNum(emNo);
    }

    public void setNum(int num) {
        this.number = num;
    }

    public int getNum() {
        return number;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public abstract String getStatus();
 
    public String toString()
    {
        return "Name: " + getName() + "\t  Number: " + getNum() +
                "\t  Status: " + getStatus();
    }

    public void print()
    {
        System.out.println(toString());
    }
}
