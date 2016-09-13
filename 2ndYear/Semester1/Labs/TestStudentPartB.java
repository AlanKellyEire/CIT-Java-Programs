import java.util.Scanner;
/**
 * Write a description of class TestStudentPartB here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class TestStudentPartB
{

    static final int MAX = 8;

    public static void main(String[] args)
    {
        Student[] student1 = new Student[MAX];
        for(int i = 0; i < student1.length; i++) {

            Scanner keyboard = new Scanner(System.in);

            int mark;
            String fName, sName;
            System.out.println("please enter your first name");
            fName = keyboard.next();
            System.out.println("please enter your surname");
            sName = keyboard.next();
            System.out.println("please enter your mark");
            mark = keyboard.nextInt();

            student1[i] = new Student(fName , sName , mark);
        }

        for(int i = 0; i < student1.length; i++) {

            student1[i].print();
            System.out.println();

        }
    }
}

