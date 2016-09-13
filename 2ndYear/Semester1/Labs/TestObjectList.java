/**
 * Write a description of class TestObjectList here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class TestObjectList
{
    public static void main(String[] args)
    {
        ObjectList ol = new ObjectList(3); //creates an objectlist thats accepts objects and its size is 3 it implies that we are using an arrray

        String s = "Im Happy";
        Dog d = new Dog(1, "terrier", "billy"); // creates a new dog object called d
        DVD v = new DVD("Johnny B. Goode", "Chuck Berry"); // creates a new dvd object called v
        Integer i = 1234;

        System.out.println(ol.add(s)); // a boolean of true
        System.out.println(ol.add(d)); // a boolean of true
        System.out.println(ol.add(v)); // a boolean of true
        //System.out.println("Is the list full? "+ ol.isFull()); testing was total working.
        System.out.println(ol.add(i)); // a boolean of false as object list aready full

        ol.remove(0); // removes element 0 of arraylist which is string s "im happy". all other elements move up one place
        System.out.println(ol.add(i)); // a boolean of true due to element 0 being deleted.

        System.out.println("Is the list full? "+ ol.isFull()); // returns true or false depending if the arrray list is full or not
        System.out.println("Is the list empty? "+ ol.isEmpty()); // returns true or false depending if the arrray list is empty or not

        System.out.println("Total number of objects in the list: " + ol.getTotal());//prints out total number of objects in array
        Object g = ol.getObject(1);//creates another object from element1 in  arraylist
        ((Dog)g).bark(); //prints out .bark method from object g
    }
}
