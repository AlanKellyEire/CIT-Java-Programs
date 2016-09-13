
/**
 * Write a description of class Cat here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Cat
{
    private Person myOwner; //owner of the cat
    private String name;
    private int age;

    /**
     * Constructor for objects of class Cat
     */
    public Cat(String thisName, int thisAge)
    {
        setName(thisName);
        setAge(thisAge);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString()
    {
        return "Name: " + getName() + "\tAge: " + getAge();
    }

    public void print()
    {
        System.out.println(toString());
    }

    public void setOwner(Person owner){ //sets the owner of the cat
        //this.myOwner = owner;
        //owner.add(this);
        if (getOwner() == null){
            //owner.add(this);
            this.myOwner = owner;
        }
        
        else if(getOwner() != null){
             System.out.println("cat already has an owner please remove owner before adding another");
        }
    }

    public String getOwner() {// returns the owners name
        if(myOwner != null){
            return myOwner.getName();
        }

        else{
            return null;
        }
    }

    public boolean removeOwner(Person owner){ //removes the owner from cat
        boolean remove = false;
        if(this.myOwner == owner){
            owner.removeCat(this);
            this.myOwner = null;
            remove = true;
        }
        else if(remove == false)
        {
            System.out.println(owner.getName() + " is not the owner please enter the correct Person!!!");
        }
        return remove;
    }
}
