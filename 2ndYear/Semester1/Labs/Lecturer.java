/**
 * Write a description of class BookList here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Lecturer
{
    static final int MAXBOOKS = 15;

    private String name;
    private int id;
    private BookList books;

    public Lecturer(String thisName, int thisId)
    {
        setName(thisName);
        setId(thisId);
        books = new BookList(MAXBOOKS);
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }    

    public int getId ()
    {
        return id;
    }

    public boolean addBook(Book b)
    {
        return books.add(b);
    }

    public BookList getBookList()
    {
        return books;
    }

    public String toString()
    {
        return "Name: " + getName() + "  ID: " + getId();
    }

    public void print()
    {
        System.out.println(toString());
    }
}
