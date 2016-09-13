package classes;

import javafx.scene.image.ImageView;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class Animal implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private static int animalID; 

	private int ID, age;
	private String aType, description, name, breed;
	private boolean gender; 
	private ImageView picture;
	private Category animalCat, adoption;
	
	public Animal()
	{
		
	}
	
	public Animal(String t, boolean g, String breed, String des)
	{
		setID();
		setAType(t);
		setGender(g);
		setDescription(des);
		setBreed(breed);
	}
	
	public Animal(int age, String t, boolean g, String des, String breed)
	{
		this(t,g,des,breed);
		setAge(age);		
	}
	
	public Animal(int age, String t, boolean g, String des, String breed, String name){
		
		this(age,t,g,des,breed);
		setName(name);
	}
	
	public int getID() {
		return ID;
	}

	public void setID() {
		this.ID = Animal.animalID;
		Animal.animalID++;
	}
	
	public void setThisAnimalID(int i) {
		this.ID = i;
	}
	
	public static int getAnimalID() {
		return animalID;
	}
	
	public static void setAnimalID(int staticId) {
		animalID = staticId;
	}

	public String getAType() {
		return aType;
	}

	public void setAType(String aType) {
		this.aType = aType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public ImageView getPicture() {
		return picture;
	}

	public void setPicture(ImageView picture) {
		this.picture = picture;
	}

	public Category getAnimalCat() {
		return animalCat;
	}

	public boolean setAnimalCat(Category animalCat) {
		this.animalCat = animalCat;
		
		return true;
	}
	
    public void setAdoption(Category catergory) {
        this.adoption = catergory;
    }

    public Adoption getAdoption() {
        return (Adoption) adoption;
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
    
    public void setGender(boolean b)
    {
    	this.gender = b;
    }
    
    public boolean getGender()
    {
    	return gender;
    }
    
    public String toString()
    {
        return "Animal ID: " + getID() + "\tName: " + getName() + "\tMale:" + getGender() + "\tAge: " + getAge() +  "\tBreed: " + getBreed() + 
        		"\tDes: " + getDescription();
    }
    
    public void print()
    {
        System.out.println(toString());
    }   
	
}
