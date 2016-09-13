
/**
 * Write a description of class Person here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Person
{
    static final int MAX = 20; // max number of cats
    private int age, total;
    private String name, address;
    private Cat[] myCat;

    /**
     * Constructor for objects of class Actor
     */
    public Person(String thisName, String thisAddress, int thisAge)
    {
        myCat = new Cat[MAX];
        setName(thisName);
        setAddress(thisAddress);
        setAge(thisAge);

    }

    public boolean add(Cat o)
    {
        boolean added = false;
        /*if(findTotal() < MAX){
        for (int i = 0; i<myCat.length; i++) 
        {
        if (myCat[i]==null ) 
        {
        myCat[i] = o;
        added = true; 
        break;
        } 
        }
        } */
        String owner = o.getOwner();
        if(owner == null && !added){
            int i = 0;
            while(i<myCat.length && !added){
                if (myCat[i] != null ) 
                {
                    i++;
                }
                else{
                    myCat[i] = o;
                    added = true;
                    o.setOwner(this);
                    break;

                }
                findTotal();
            } 
        }

        if(o.getOwner() != null && !added){     
            System.out.println("cat already has an owner please remove owner before adding another"); 
        }

        /*if(added == true)
        {
        o.setOwner(this);
        }*/

        else if(total == MAX && !added){
            System.out.println("Array full please remove a cat before u can add another");
        }
        //System.out.println(added); using this for tesing
        //System.out.println(findTotal());  using this for tesing
        findTotal();
        return added; 
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

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    /*public void setCat(Cat[] ownCat) { // cant even remember why i had this method
    myCat = new Cat[ownCat.length];
    for(int i = 0; i < ownCat.length; i++) {
    myCat[i] = ownCat[i];
    }
    }*/

    public Cat[] getCat() {
        return myCat;
    }

    public String toString()
    {
        return "Name: " + getName() + "\nAddress: " + getAddress() + "\nAge: " + getAge();
    }

    public int findTotal() //finds the total valid no of cats in the array.
    {
        int count =0;
        for (int i= 0; i<myCat.length; i++) {
            if (myCat[i] != null) {
                count++;
            }
        }
        total = count;
        return count;
    }

    public boolean removeCat(Cat cat) { //removes cat from the array
        findTotal();
        boolean removed = false;
        //System.out.println(cat.getOwner()); this was added for testing
        if(cat.getOwner() != null){
            for ( int i = 0; i < myCat.length; i++ ){
                if(myCat[i]!=null) //checks if element if empty
                {
                    if (cat.equals(myCat[i])) //checks if cat = cat in array
                    {
                        for ( int f = i; f < myCat.length; f++ )
                        {
                            if(myCat[f]!=null && f+1 < myCat.length){//moves the elements up one
                                myCat[f] = myCat[f + 1];
                            }
                        }
                        removed = true;
                        myCat[total-1] = null; //removes the last valid cat as it exists twice
                    }
                }
            }
        }

        if(removed == true){
            cat.removeOwner(this);
        }
        findTotal();
        return removed;
    }

    public void print()
    {
        System.out.println(toString());
        for(int i = 0; i < myCat.length; i++) {
            if(myCat[i]!=null){// goes through the array and prints out each cat
                System.out.println("Cat " + (i+1));
                myCat[i].print();
            }
            else
                break;
        }
    }
}