
/**
 * Write a description of class Student here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class Student
{

    private String sName, fName;
    private int mark;   

    public Student(String thisfName, String thissName, int thisMark)
    {
        setfName(thisfName);
        setsName(thissName);
        setMark(thisMark);

    }

    public void setfName(String fName) 
    {
        this.fName = fName;
    }

    public void setsName(String sName) 
    {
        this.sName = sName;
    }

    public String getfName() {
        return fName;
    }

    public String getsName() {
        return sName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) 
    {
        this.mark = mark;
    }

    public String toGrade()
    {
        /*return "Film: " + getName() + "\tCode: " + getCode();*/

        if(mark >= 85)
        {
            return "distinction";
        }
        else if(mark >= 65)
        {
            return "merit";
        }
        else if(mark >= 40)
        {
            return "pass";
        }
        else
        {
            return "fail";
        }

    }

    /*public String buildCode(String name)
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
    }*/

    public String toString()
    {
        return fName + " " + sName + " received a " + toGrade() +" for his mark of " + mark;
    }

    public void print()
    {
        System.out.println(toString());
    }
}

