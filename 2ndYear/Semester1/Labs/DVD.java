/**
 * Write a description of class DVD here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class DVD
{
    private String title;
    private String genre;
    private String artist;     

    public DVD()
    {
    }

    public DVD(String thisTitle, String thisArtist)
    {
        setTitle(thisTitle);
        setArtist(thisArtist);

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void print()
    {
        System.out.println(toString());
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getArtist() {
        return artist;
    }

    public String toString()
    {
        return ("Title: " + getTitle() + "\nGenre: " + getGenre() + "\nArtist: " + getArtist());
    }

}