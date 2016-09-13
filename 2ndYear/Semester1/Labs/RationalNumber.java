
/**
 * Write a description of class RationalNumber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RationalNumber
{
    private int numer, denom, ans;
    
    //constructors
    public RationalNumber(){
        setNumer(1);
        setDenom(1);
    }

    public RationalNumber(int thisNum, int thisDen){
        setNumer(thisNum);
        setDenom(thisDen);
    }

    public void setNumer(int num){
        this.numer = num;
    }

    public void setDenom(int den){
        this.denom = den;
        chDenom();
    }

    private double getFloating()
    {
        return numer * 1.0 / denom;
    }

    public boolean chDenom(){
        if(denom == 0){
            System.out.print("denominator can not be equals to 0!!!!!");
            return true;
        } 

        else
        {
            return false;
        }
    }

    public int getNumer(){
        return numer;
    }

    public int getDenom(){
        return denom;
    }

    public RationalNumber add(RationalNumber o){
        int resultNumer, resultDenom;

        resultNumer = (this.numer * o.denom) + (o.numer * this.denom);
        resultDenom = (this.denom * o.denom);
        RationalNumber addResult = new RationalNumber(resultNumer, resultDenom);

        return addResult;
    }

    public RationalNumber sub(RationalNumber o){
        int resultNumer, resultDenom;
        
        resultNumer = (this.numer * o.denom) - (o.numer * this.denom);
        resultDenom = (this.denom * o.denom);
        RationalNumber subResult = new RationalNumber(resultNumer, resultDenom);

        return subResult;
    }

    public RationalNumber multi(RationalNumber o){
        int resultNumer, resultDenom;

        resultNumer = (this.numer * o.numer);
        resultDenom = (this.denom * o.denom);
        RationalNumber multiResult = new RationalNumber(resultNumer, resultDenom);

        return multiResult;
    }

    static int commonDenom(int x, int y){
        int r;
        while (y != 0) {
            r = x % y;
            x = y;
            y = r;
        }
        return x;
    }

    private void simplify(){
        int divisor;
        if(numer == denom){
           ans = 1;
        }
        
        else
        {
            divisor = commonDenom(numer, denom);
            numer = numer / divisor;
            denom = denom / divisor;
        }
    }

    public RationalNumber divide(RationalNumber o){
        int resultNumer, resultDenom;

        resultNumer = (this.numer * o.denom);
        resultDenom = (this.denom * o.numer);
        RationalNumber divideResult = new RationalNumber(resultNumer, resultDenom);

        return divideResult;
    }

    public String toString(){
        simplify();
        if(ans == 1){
           return "" + ans; 
        }
        else
        return numer + " / " + denom;
    }

    public void print()
    {
        System.out.println("Fraction Result\tFloating Point");
        System.out.print(toString());
        System.out.print("\t\t");
        System.out.printf("%.3f", this.getFloating());
        System.out.println();
    }
}