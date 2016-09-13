package classes;
import java.time.LocalDate;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class Lost extends Category{
	
	private static final long serialVersionUID = -2822495969042896593L;
	private String location;
	
	public Lost (LocalDate date, String string, Person p)
	{
		super(date, p);
		setLocation(string);
	}

	public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
    
    public String toString()
    {
    	return "Location Lost:\t" + location + "\nDate Lost: \t" + super.getDate() + "\nContact:\n" + super.getContact(); 
    
    } 

    public void print()
    {
    	System.out.print(toString());
    }
}
