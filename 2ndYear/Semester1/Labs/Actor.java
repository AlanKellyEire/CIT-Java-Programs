
/**
 * Write a description of class Actor here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Actor
{
    // instance variables - replace the example below with your own
    private int age;
    private String name, address;
    private Film[] myFilm;

    /**
     * Constructor for objects of class Actor
     */
    public Actor(String thisName, String thisAddress, int thisAge)
    {
        setName(thisName);
        setAddress(thisAddress);
        setAge(thisAge);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFilm(Film[] inFilm) {
        myFilm = new Film[inFilm.length];
        for(int i = 0; i < inFilm.length; i++) {
            myFilm[i] = inFilm[i];
        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public Film[] getFilm() {
        return myFilm;
    }

    public String toString()
    {
        return "Name: " + getName() + "\nAddress: " + getAddress() + "\nAge: " + getAge();
    }

    public void print()
    {
        System.out.println(toString());
        for(int i = 0; i < myFilm.length; i++) {
            myFilm[i].print();
        }
    }
}
