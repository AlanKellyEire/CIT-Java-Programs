
import java.util.Scanner;
/**
 * Write a description of class Films here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Film
{

    private String name;
    private String code;   

    public Film(String thisName)
    {
        setName(thisName);   
    }

    public void setName(String name) 
    {
        this.name = name;
        buildCode(name);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String toString()
    {
        return "Film: " + getName() + "\tCode: " + getCode();
    }

    public String buildCode(String name)
    {
        code = "" + name.charAt(0);
        //System.out.println(name); // A
        int x = name.indexOf(" ");
        while(x != -1)
        {
            code += name.substring(x+1,x+2);//g
            name = name.substring(x+1);
            x = name.indexOf(" ");
        }
        return code = code.toUpperCase();
    }

    public void print()
    {
        System.out.println(toString());
    }
}
