package classes;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class Person implements java.io.Serializable 
{
	
	private static final long serialVersionUID = -5634027284639386629L;

	private static int personID; 

	private String name, address, email, phoneNumber;
	private int ID;

	public Person()
	{
		
	}

	public Person(String name, String add,  String email, String phone ){
		setName( name );
		setAddress( add );
		setEmail( email );
		setPhoneNumber( phone );
		setID();
	}

	public int getID() {
		return ID;
	}

	public void setID() {
		ID = personID;
		personID++;
	}

	public void setPersonId(int i){
		this.ID = i;
	}
	
	public static void setStaticID(int staticID) {
		personID = staticID;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setAddress( String add )
	{
		this.address = add;
	}

	public String getAddress()
	{
		return address;
	}

	public void setEmail( String emailAddress )
	{
		this.email = emailAddress;
	}

	public String getEmail()
	{
		return email;
	}

	public void setPhoneNumber(String phone )
	{
		this.phoneNumber = phone;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public String toString()
	{
		return "ID" + getID() + "\nName: " + getName() + "\nAddress: " + getAddress() + "\nphone number: " + getPhoneNumber() + "\nEmail: " + getEmail();
	}

	public void print()
	{
		System.out.print(toString());
	}
}