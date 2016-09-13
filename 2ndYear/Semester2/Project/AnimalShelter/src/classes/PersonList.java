package classes;

import java.util.ArrayList;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class PersonList implements  java.io.Serializable  {

	private static final long serialVersionUID = 7636655433071489463L;
	private ArrayList<Person> List = new ArrayList<Person>();

	public PersonList()
	{
	}

	public void add(Person a)
	{
		List.add(a);
	}

	public void remove(Person r)
	{
		List.remove(r);
	}
	
	public ArrayList<Person> getList()
	{
		return List;
	}

	public int size()
	{
		return List.size();
	}
	
	public Person get(int i)
	{
		return List.get(i);
	}

	public void printPersonList()
	{
		for(int i = 0; i < List.size(); i++) {
			System.out.println(List.get(i).toString());
			
		}
	}
	
	public void printPersonList2()
	{
		for(int i = 0; i < List.size(); i++) {
			if(List.get(i).getName().equalsIgnoreCase("tom")){
				System.out.println(List.get(i).toString() + "from personlist");
			}
			
		}
	}
	
}
