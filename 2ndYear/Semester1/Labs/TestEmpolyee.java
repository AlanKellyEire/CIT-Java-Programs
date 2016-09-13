
/**
 * Write a description of class TestEmpolyee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestEmpolyee
{
    public static void main(String[] args)
    {

        int size;
        Employee[] employees = new Employee[4];
        employees[0] = new FulltimeEmployee("Alan", 28000);
        employees[1] = new PartTimeEmployee("Billy", 18);
        employees[2] = new FulltimeEmployee("Paddy", 63000);
        employees[3] = new PartTimeEmployee("JJ Murphy", 12);

        for(int i = 0; i < employees.length; i++) {

            employees[i].print();

        }
    }

}
