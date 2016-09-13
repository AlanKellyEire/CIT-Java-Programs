import java.util.Scanner;
/**
 * Write a description of class billy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class billy
     */

    static Scanner kb = new Scanner(System.in);
    public static void main(String[] args){
        String s;

        System.out.print("Type in some shit: ");
        s = kb.nextLine();

        String[] strgs = s.split(" ");

        for (int i=0; i<strgs.length; i++){
            System.out.println(strgs[i] + "\t" + strgs[i].charAt(0));
        }
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
