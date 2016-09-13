import java.util.Scanner;
/**
 * Write a description of class Song here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Song
{
    public static void main(String[] args)
    {
        String s = "bottles ";
        int num;

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the number of bottles: ");
        num= keyboard.nextInt();

        while(num > 0)
        {

            System.out.println(num + " " + s +"of beer on the wall");
            System.out.println(num + " " + s +"of beer");
            System.out.println("Take one down");
            System.out.println("Pass it around");
            System.out.println("");
            num--;
            if(num == 1)
            {
                s = "bottle ";
            }

            if (num != 0)
            {
                System.out.println(num + " " + s +"of beer on the wall");
                System.out.println("");
            }
            else
            {  
                System.out.println("No more bottles of beer on the wall");
            }
        }    
    }
}
