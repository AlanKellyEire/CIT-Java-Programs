
/**
 * Write a description of class TestDVD here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */

public class TestDVD
{
    public static void main(String[] args)
    {
        DVD dvd1 = new DVD(); // creating scanner
        dvd1.setTitle("Song Bird"); // setting setTitle "Song Bird"
        dvd1.setGenre("Blues"); // setting setGenre to "Blues"
        dvd1.setArtist("Eva Cassidy"); // setting setArtist to Eva Cassidy
        dvd1.print(); // printing to the screen

        DVD dvd2 = new DVD("Johnny B. Goode", "Chuck Berry"); // setting the first string paramete 
        //to Johnny B. Goode and second string parameter to Chuck Berry.
        dvd2.print();//
    }

}

