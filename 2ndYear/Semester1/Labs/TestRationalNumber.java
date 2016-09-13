
/**
 * Write a description of class TestRationalNumber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestRationalNumber
{
    public static void main(String[] args)
    {
        RationalNumber rat1 = new RationalNumber(3,4);
        RationalNumber rat2 = new RationalNumber(7,8);
        
        rat1.add(rat2).print();
        rat1.sub(rat2).print();
        rat1.multi(rat2).print();
        rat1.divide(rat2).print();
    }

}
