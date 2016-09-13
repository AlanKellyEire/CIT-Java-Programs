
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Write a description of class TestStudentPartC here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class testStudentPartC
{
    static ArrayList<Student> student1 = new ArrayList<Student>();

    public static void main(String[] args)
    {
        int choice = -1;
        do
        {

            /*for(int i = 0; i < MAX; i++) {
            Scanner keyboard = new Scanner(System.in);

            int mark;
            String fName, sName;
            System.out.println("please enter your first name");
            fName = keyboard.next();

            System.out.println("please enter your surname");
            sName = keyboard.next();

            System.out.println("please enter your mark");
            mark = keyboard.nextInt();

            student1.add(new Student(fName , sName , mark));
            }*/

            System.out.println("1.  Add Student");
            System.out.println("2.  Remove Student");
            System.out.println("3.  Display Student Results");
            System.out.println("4.  Exit");

            Scanner kb = new Scanner(System.in);
            while (!kb.hasNextInt())
            {
                System.out.println("Enter an number from 1 - 4");
                kb.nextLine();
            }
            choice = kb.nextInt();

            switch (choice)
            {

                case 1:

                int mark;
                String fName, sName;

                fName = getFirstName();
                sName = getSurName();
                mark = getMark();
                student1.add(new Student (fName , sName , mark));
                

                break;
                case 2:
                fName = getFirstName();
                sName = getSurName();
                boolean found = false;
                int i = 0;
                while (found != true && i < student1.size()){
                    if (student1.get(i).getfName().equalsIgnoreCase(fName) && student1.get(i).getsName().equalsIgnoreCase(sName)){
                        student1.remove(i);
                        found = true;    
                    }
                    else{
                        i++;
                    }
                }

                if (found == false)
                {
                    System.out.println("\nNo student by that name");
                }
                else{
                    System.out.println("\nStudent Removed");
                }
                break;
                case 3:
                for(int c = 0; c < student1.size(); c++) {

                    student1.get(c).print();
                    System.out.println();

                }

                if (student1.isEmpty())
                    System.out.println("\nThere is currently no students!!");
                break;

            }
            if ((choice >= 1) && (choice <= 3))
            {
                kb.nextLine();
                System.out.println("\nPress Return to continue");
                kb.nextLine();
            } else if (choice != 4)
            {
                kb.nextLine();
                System.out.println("\nPress Return to continue");
                kb.nextLine();
            }

        } while (choice != 4);
        if (choice == 4)
        { System.out.println("\nThanks for using the program");
        }
    }

    public static String getFirstName()
    {
        Scanner kb = new Scanner(System.in);
        String fName;
        System.out.println("please enter your first name");
        fName = kb.next();

        return fName;

    }

    public static String getSurName()
    {
        Scanner kb = new Scanner(System.in);
        String sName;
        System.out.println("please enter your surname");
        sName = kb.next();

        return sName;
    }

    public static int getMark()
    {
        int mark;
        Scanner kb = new Scanner(System.in);
        System.out.println("please enter your mark");
        mark = kb.nextInt();

        return mark;
    } 

}

