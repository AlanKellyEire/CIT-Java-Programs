
/**
 * Write a description of class BookList here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class BookList extends ObjectList
{
    public BookList(int size)
    {
        super(size);
    }

    public Book getBook(int i)
    {
        return (Book)getObject(i); 
    }

    public Book search(int isbn)
    {
        for (int i = 0; i < getTotal(); i++)
        {
            Book book = getBook(i);
            if (book.getIsbn() == isbn)
                return book;
        }
        return null;
    }

    public boolean removeBook(int isbn)
    {
        for (int i = 0; i < getTotal(); i++)
        {
            Book book = getBook(i);
            if (book.getIsbn() == isbn)
            {
                remove(i);
                return true;
            }
        }
        return false;

    }
    
    public double calculateYearlyBookPayment()
    {
        double total = 0;

        for (int i = 0; i < getTotal(); i++)
        {
            Book b = getBook(i);
            total += b.getPrice();
        }
        return total;
    }

}
