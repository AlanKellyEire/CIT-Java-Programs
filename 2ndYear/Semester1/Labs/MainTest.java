import java.util.Scanner;
import java.util.ArrayList;

/**
 * Write a description of class MainTest here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class MainTest
{
    private String name;

    public static void main(String[] args)
    {

        int size;
        /*ArrayList<Actor> actor1 = new ArrayList<Actor>();//unlimited amount of actors can be added to this

        actor1.add(new Actor ("Jack Nicholson" , "Miami" , 74));
        actor1.add(new Actor ("Violante Placido" , "Bologna" , 38));*/

        Actor[] actor1 = new Actor[2];
        actor1[0] = new Actor("Jack Nicholson" , "Miami" , 74);
        actor1[1] = new Actor("Violante Placido" , "Bologna" , 38);

        /*ArrayList<Film> film1 = new ArrayList<Film>();//unlimited amount of films can be added to this
        film1.add(new Film ("wolf"));
        film1.add(new Film ("As good as it gets"));
        film1.add(new Film ("One flew over the cuckoos nest"));*/
        Film[] film1 = new Film[3];
        film1[0] = new Film("wolf"); 
        film1[1] = new Film("As good as it gets");
        film1[2] = new Film("One flew over the cuckoos nest");
        //actor1.get(0).setFilm(film1); // uses the arraylist 
        actor1[0].setFilm(film1);

        film1[0] = new Film("The american");
        film1[1] = new Film("Ghost rider Spirit of vengence");
        film1[2] = new Film("Barah Aanan");
        actor1[1].setFilm(film1);
        //actor1.get(1).setFilm(film1); // uses the arraylist 

        for(int i = 0; i < actor1.length; i++) {

            actor1[i].print();
            System.out.println();

        }
        // ArrayList can only contain Objects,
        // ArrayList is an variable length Collection class and array is an fixed length data structure.
        // methods use with arraylist are different e.g size() instead of length.
    }
}
