package classes;

import java.util.ArrayList;

/**
 * @author Alan Kelly (R00052131)
 * Created 28 Mar 2016
 */
public class AnimalList implements java.io.Serializable {

	private static final long serialVersionUID = 7539097817798448905L;
	private ArrayList<Animal> List = new ArrayList<Animal>();

	public AnimalList()
	{
	}

	public void add(Animal a)
	{
		List.add(a);
	}

	public void remove(Animal r)
	{
		List.remove(r);
	}
	
	public ArrayList<Animal> getList()
	{
		return List;
	}

	public int size()
	{
		return List.size();
	}
	
	public Animal get(int i)
	{
		return List.get(i);
	}
	
	public ArrayList<Animal> getLostList()
	{
		ArrayList<Animal> lostList = new ArrayList<Animal>();
		
		for(int i = 0; i < List.size(); i++) { 
//			if (List.get(i).getCatergory().getClass().isInstance(Lost.class)){
				if (List.get(i).getAnimalCat() instanceof Lost){
				lostList.add(List.get(i));
			}
		} 
		return lostList;
	}
	
	public ArrayList<Animal> getFoundList()
	{
		ArrayList<Animal> foundList = new ArrayList<Animal>();
		
		for(int i = 0; i < List.size(); i++) { 
				if (List.get(i).getAnimalCat() instanceof Found){
				foundList.add(List.get(i));
			}
		} 
		return foundList;
	}
	
	public ArrayList<Animal> getAdoptionList()
	{
		ArrayList<Animal> adoptionList = new ArrayList<Animal>();
		
		for(int i = 0; i < List.size(); i++) { 
				if (List.get(i).getAdoption() instanceof Adoption || List.get(i).getAnimalCat() instanceof Adoption){
					adoptionList.add(List.get(i));
			}
		} 
		return adoptionList;
	}
	
	public ArrayList<Animal> getAdoptionReadyList()
	{
		ArrayList<Animal> adoptionReadyList = new ArrayList<Animal>();
		
		for(int i = 0; i < List.size(); i++) { 
				if ((List.get(i).getAdoption() instanceof Adoption && ((Adoption) List.get(i).getAdoption()).getStatus().equalsIgnoreCase("ready"))){
					adoptionReadyList.add(List.get(i));
			}
		} 
		return adoptionReadyList;
	}
	
	public ArrayList<Animal> getAdoptionTrainingList()
	{
		ArrayList<Animal> adoptionTrainingList = new ArrayList<Animal>();
		
		for(int i = 0; i < List.size(); i++) { 
				if (List.get(i).getAdoption() instanceof Adoption && ((Adoption) List.get(i).getAdoption()).getStatus().equalsIgnoreCase("in training")){
					adoptionTrainingList.add(List.get(i));
			}
		} 
		return adoptionTrainingList;
	}
	
	public ArrayList<Animal> getPuppiesAdoptionTrainingList()
	{
		ArrayList<Animal> adoptionTrainingList = new ArrayList<Animal>();
		
		for(int i = 0; i < List.size(); i++) { 
				if (List.get(i).getAdoption() instanceof Adoption && ((Adoption) List.get(i).getAdoption()).getStatus().equalsIgnoreCase("in training") && List.get(i).getAge() <= 2 && List.get(i).getAType().equalsIgnoreCase("Dog")){
					adoptionTrainingList.add(List.get(i));
			}
		} 
		return adoptionTrainingList;
	}

	public ArrayList<Category> getCategoryList(ArrayList<Animal> list)
	{
		ArrayList<Category> adoptionList = new ArrayList<Category>();
		
		for(int i = 0; i < list.size(); i++) { 			
				adoptionList.add(list.get(i).getAdoption());	
		} 
		return adoptionList;
	}
	
	public ArrayList<Category> getCategoryLostList(ArrayList<Animal> list)
	{
		ArrayList<Category> categoryLostList = new ArrayList<Category>();
		
		for(int i = 0; i < list.size(); i++) { 
			categoryLostList.add(list.get(i).getAdoption());	
			}
		
		return categoryLostList;
	
	}
	
	public void printAnimalList()
	{
		for(int i = 0; i < List.size(); i++) {
			System.out.println(List.get(i).toString());
			
		}
	}
	
}
