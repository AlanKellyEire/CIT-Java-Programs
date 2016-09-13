
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Write a description of class TestStudentPartC here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class TestLecturer
{
    static ArrayList<Lecturer> lecturer1 = new ArrayList<Lecturer>();

    public static void main(String[] args)
    {
        int choice = -1;
        do
        {
            System.out.println("1.  Add Lecturer");
            System.out.println("2.  Find a Lecturer");
            System.out.println("3.  Add a book to the lecturer's Booklist");
            System.out.println("4.  Remove a book from the lecturer's Booklist");
            System.out.println("5.  Search for a book using ISBN number");
            System.out.println("6.  Calculate the yearly book payment");
            System.out.println("7.  Output all of the book details in the system to a file");
            System.out.println("8.  Exit");

            Scanner kb = new Scanner(System.in);
            while (!kb.hasNextInt())
            {
                System.out.println("Enter an number from 1 - 8");
                kb.nextLine();
            }
            choice = kb.nextInt();

            switch (choice)
            {
                case 1:

                int ID;
                String name;

                name = getName();
                ID = getId();
                lecturer1.add(new Lecturer (name, ID));

                break;
                case 2:
                ID = getId();
                boolean found = false;
                int i = 0;
                if (lecturer1.isEmpty())
                    System.out.println("\nThere is currently no Lecturers!!");
                else
                {
                    while (found != true && i < lecturer1.size()){
                        if (lecturer1.get(i).getId() == ID ){
                            found = true;
                            do
                            {
                                System.out.println("1.  Add Book");
                                System.out.println("2.  Remove a Booklist");
                                System.out.println("3.  Exit");

                            }while (choice != 3);
                        }

                        else{
                            i++;
                        }
                    }

                    if (found == false)
                    {
                        System.out.println("\nNo lecturer by that name");
                    }
                }
                break;
                case 3:
                for(int c = 0; c < lecturer1.size(); c++) {

                    lecturer1.get(c).print();
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

        } while (choice != 8);
        if (choice == 4)
        { System.out.println("\nThanks for using the program");
        }
    }

    public static String getName()
    {
        Scanner kb = new Scanner(System.in);
        String fName;
        System.out.println("please enter your first name");
        fName = kb.next();

        return fName;

    }

    public static int getId()
    {
        int mark;
        Scanner kb = new Scanner(System.in);
        System.out.println("please enter your mark");
        mark = kb.nextInt();

        return mark;
    } 

}

