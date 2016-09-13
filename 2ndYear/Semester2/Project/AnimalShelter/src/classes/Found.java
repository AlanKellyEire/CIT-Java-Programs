package classes;
import java.time.LocalDate;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class Found extends Category{

	private static final long serialVersionUID = -8079347837970029640L;
	private String location;
	private Person owner;
	
	public Found (LocalDate date, String string, Person p)
	{
		super(date, p);
		setLocation(string);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
	public String toString()
    {
    	return "Date Found: \t" + super.getDate()  +"\nLocation Found:\t" + location +"\nOwner:\n" + owner; 
    
    } 
	
}
