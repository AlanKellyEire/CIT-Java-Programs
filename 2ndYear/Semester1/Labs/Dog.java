/**
 * Write a description of class Dog here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Dog
{
    private int size;
    private String breed;
    private String name; 

    /**
     * Constructor for objects of class Dog
     */
    public Dog(int thisSize, String thisBreed, String thisName)
    {
        setSize(thisSize);
        setBreed(thisBreed);
        setName(thisName);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public void bark()
    {
        System.out.println("Ruff! Ruff!");
    }

    public void bark(int numBark)
    {
        for(int i = 1; i <= numBark; i++)
        {
            bark();
        }
    }

    public String toString()
    {
        return "Size: " + getSize() + "\nBreed: " + getBreed() + "\nName: " + getName();
    }

    public void print()
    {
        System.out.println(toString());
    }

}