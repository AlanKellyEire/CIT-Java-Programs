package classes;

import java.time.LocalDate;

/**
 * @author Alan Kelly R00052131
 * Created 13 Apr 2016
 *
 */
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private LocalDate date;
	private Person contact;

	public Category(LocalDate date) {
		setDate(date);
	}

	public Category(LocalDate date, Person p) {
		this(date);
		setContact(p);
	}

	@Override
	public String toString() {
		if (this instanceof Lost){
			return "Catergory [date=" + date + ", contact=" + contact + "Location";
		}
		return "Catergory [date=" + date + ", contact=" + contact + "]";
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Person getContact() {
		return contact;
	}

	public void setContact(Person contact) {
		this.contact = contact;
	}

}


