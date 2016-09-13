
/**
 * Write a description of class ObjectList here.
 * 
 * @author Alan Kelly
 * @version R00052131
 */
public class ObjectList
{
    // instance variables - replace the example below with your own
    private int total;
    private Object[] oList;

    /**
     * Constructor for objects of class ObjectList
     */
    public ObjectList(int size)
    {
        oList = new Object[size];
    }

    public int findSize() //this finds the total number of objects
    {
        int count =0; 
        for (int i= 0; i<oList.length; i++) {
            if (oList[i] != null) {
                count++;
            }
        }
        total = count;
        return count;
    }

    public boolean add(Object o) // this adds the objects to the object list
    {
        boolean empty = false;
        for (int i = 0; i<oList.length; i++) 
        {
            if (oList[i]!=null ) 
            {
                empty = false;
            } 
            else 
            {
                oList[i] = o;
                empty = true;
                break;
            }
        }
        findSize();
        return empty;
    }

    public boolean remove(int c) {//this removes elements from the array
        boolean empty = false;

        //printsize(); had problems with this method was using this for testing
        if(c<total){ //checks if the element to be deleted is in the range of the objectlist
            if (oList[c]!=null) //checks if the element is used
            {
                for ( int i = c; i < oList.length -1; i++ )
                {
                    if(oList[i]!=null){
                        if (i+1 == oList.length){
                            oList[ i ] = oList[i + 1] ; //moves all elements up the array
                            empty = true;
                            break;

                        }
                    }
                    //printsize(); had problems with this method was using this for testing
                }
                oList[oList.length - 1] = null; //deletes the last element
            }
        }
        else
        {
            System.out.println("cant not remove element as array has less elements");
        }
        return empty;
    }

    public boolean isFull() //checks if array is full
    {
        boolean full = false;
        /*int count =0;
        for (int i= 0; i<oList.length; i++) {
        if (oList[i] != null) {
        count++;
        }
        }
        if(count == oList.length){
        full = true;
        }*/

        if (total == oList.length)
        {
            full = true;
        }
        return full;
    }

    public boolean isEmpty() //checks if array is empty
    {
        boolean empty = true;
        for (int i=0; i<oList.length; i++) {
            if (oList[i] != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    public int getTotal() //returns the number of objects in the array
    {
        /*int count = 0;
        for (int i=0; i<oList.length; i++) {
        if (oList[i] != null) {
        count++;
        }
        }*/
        return total;
    }

    public Object getObject(int i)
    {
        return oList[i];
    }

    public String toString()
    {
        return "size = " +  findSize();
    }

    public void printsize()
    {
        System.out.println(toString());
    }
}
