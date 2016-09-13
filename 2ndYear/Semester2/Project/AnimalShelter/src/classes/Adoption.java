package classes;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */

public class Adoption extends Category{

	private static final long serialVersionUID = -5174414124406580187L;
	private boolean neutered, chipped, vaccinated, reserved;
	private String status;
	private ArrayList<Person> interested;
	private LocalDate adoptionDate;

	public Adoption(LocalDate date)
	{
		super(date);
		interested = new ArrayList<Person>();
	}

	public void setNeutered(boolean n) {
		this.neutered = n;
	}

	public void setAdoptionDate(LocalDate date) {
		this.adoptionDate = date;
	}

	public LocalDate getAdoptionDate() {
		return adoptionDate;
	}

	public boolean getNeutered() {
		return neutered;
	}

	public void setVaccinated(boolean n) {
		this.vaccinated = n;
	}

	public boolean getVaccinated() {
		return vaccinated;
	}

	public void setChipped(boolean n) {
		this.chipped = n;
	}

	public boolean getChipped() {
		return chipped;
	}

	public void setReservered(boolean n) {
		this.reserved = n;
	}

	public boolean getReserved() {
		return reserved;
	}

	public void setStatus(String status) {
		if(status.equalsIgnoreCase("Ready")){
			setChipped(true);
			setVaccinated(true);
			setNeutered(true);
		}
		this.status = status;
	}

	public String getStatus() {
		if(chipped == true && vaccinated == true && neutered == true){
			setStatus("Ready");
		}
		return status;
	}

	public void addPerson(Person a) {
		interested.add(a);
		System.out.println("add person interested");
	}

	public void removePerson(Person r) {
		interested.remove(r);
	}

	public Person getPerson(int g) {
		return interested.get(g);
	}
	
	public ArrayList<Person> getInterested(){
		return interested;
	}

	public String getPersonList() {
		String pIntereset = "";
		if(interested.size() != 0){
			for(int i = 0; i < interested.size(); i++){
				pIntereset += interested.get(i).toString() + ", ";
			}
		}
		if(pIntereset.length() != 0){
			pIntereset.substring(0,pIntereset.length()-2);
		}

		return pIntereset;
	}

	public String toString()
	{
		return "neutered: " +  getNeutered() + "\nvaccinated: " + getVaccinated() + "\nstatus: " + getStatus()  + "\nChipped: "+ getChipped() + "\nreservered: " + getReserved() + "\nadoption Date: " + getAdoptionDate() + "\ninterested people: \n"  +
				getPersonList();
	} 

	public void print()
	{
		System.out.print(toString());
	}
}
