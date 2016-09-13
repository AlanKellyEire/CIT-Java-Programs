import java.util.Scanner;
import java.util.ArrayList;

/**
 * Write a description of class MainTest here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Maintestperson
{
    private String name;

    public static void main(String[] args)
    {

        int size;
        Person[] person1 = new Person[2];
        person1[0] = new Person("Alan" , "Cork" , 28);
        person1[1] = new Person("Billy" , "Kerry" , 38);

        
        Cat cat1 = new Cat("Sleepy", 2); 
        Cat cat2 = new Cat("Smelly", 3);
        Cat cat3 = new Cat("Brownie", 4);
        
        person1[0].add(cat1);
        person1[0].add(cat2);
        person1[0].add(cat3);
        person1[0].print();
        
        person1[0].removeCat(cat2);
        person1[0].print();
        

        /*cat1[0] = new Cat("Coco", 8);
        cat1[1] = new Cat("Fuffly", 5);
        cat1[2] = new Cat("Scratchy", 88);
        cat1[3] = new Cat("Coco", 8);
        cat1[4] = new Cat("Fuffly", 5);
        cat1[5] = new Cat("Scratchy", 88);
        cat1[6] = new Cat("Sleepy", 2); 
        cat1[7] = new Cat("Smelly", 3);
        cat1[8] = new Cat("Brownie", 4);*/

       /*(person1[1].setCat(cat1);
        for(int i = 0; i < person1.length; i++) {

            person1[i].print();
            System.out.println();

        }*/
    }
}
