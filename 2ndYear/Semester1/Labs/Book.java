/**
 * Write a description of class Book here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Book
{
    private String title;
    private int isbn;
    private String author;
    private double price;

    public Book(String thisTitle, int thisIsbn, String thisAuthor, double thisPrice)
    {
        setTitle(thisTitle);
        setIsbn(thisIsbn);
        setAuthor(thisAuthor);
        setPrice(thisPrice);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getIsbn()
    {
        return isbn;
    }

    public void setIsbn(int isbn)
    {
        this.isbn = isbn;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
 
    public String toString()
    {
        return "Title: " + getTitle() + "  ISBN: " + getIsbn() +
                "  Author: " + getAuthor() + "  Price: " + getPrice(); 
    }
    
    public void print()
    {
        System.out.println(toString());
    }
    
}
