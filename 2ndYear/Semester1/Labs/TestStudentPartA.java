import java.util.Scanner;
/**
 * Write a description of class TestStudentPartA here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class TestStudentPartA
{
    static final int MAX = 8;

    public static void main(String[] args)
    {
        String [] firstName = new String[MAX];
        String[] surName = new String[MAX];
        int [] mark = new int[MAX];

        for(int i = 0; i <MAX; i++)
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("please enter your first name");
            firstName[i] = keyboard.next();
            System.out.println("please enter your surname");
            surName[i] = keyboard.next();
            System.out.print("Enter the mark: ");
            mark[i] = keyboard.nextInt();
        } 

        display(firstName, surName, mark);
    }

    public static void display(String[] firstName, String[] surName, int[] mark) 
    {
        for(int i = 0; i < MAX; i++)
        {
            if(mark[i] >= 85)
            {
                System.out.println(firstName[i] + " " + surName[i] + " received a distinction for the mark of " + mark[i]);
            }
            else if(mark[i] >= 65)
            {
                System.out.println(firstName[i] + " " + surName[i] + " received a merit for t mark of " + mark[i]);
            }
            else if(mark[i] >= 40)
            {
                System.out.println(firstName[i] + " " + surName[i] + " received a pass for his mark of " + mark[i]);
            }
            else
            {
                System.out.println(firstName[i] + " " + surName[i] + " received a fail for his mark of " + mark[i]);
            }
        }
    }

}
