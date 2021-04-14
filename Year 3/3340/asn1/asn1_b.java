// Matteo Tanzi; 251011979

public class asn1_b{


    private String val;

    // Big int constructor
    asn1_b(int val){
        this.val = Integer.toString(val);
    }
    asn1_b(String value){
        this.val = value;
    }

    public static void main(String[]args){
        for(int i = 0; i <= 25; i++){
                if(i == 0){
                    System.out.println(fib(i*20)[1].getValue());
                }
                else{
                    System.out.println(fib(i*20)[0].getValue());
                }
        }
    }

    //Get and Set Methods
    public String getValue(){
        return val;
    }
    public void setVal(int val){
        this.val = Integer.toString(val);
    }
    public void setValue(String value){this.val = value;}
    public static String add(asn1_b x, asn1_b y){
        y.plus(x);
        return y.getValue();
    }
    public int length(){ return val.length();}
    public void plus(asn1_b x)
    {
        String[] xy = equalLengths(x, this);
        char[] xChars = xy[0].toCharArray();
        char[] yChars = xy[1].toCharArray();

        int overflow = 0;//number being carried over when sum goes over 10

        StringBuilder solution = new StringBuilder();
        for(int i = xChars.length - 1; i >= 0 ; i--)
        {
            int xInt = Character.getNumericValue(xChars[i]);
            int yInt = Character.getNumericValue(yChars[i]);
            int digitSum = xInt + yInt + overflow;

            solution.append( digitSum % 10);
            overflow = digitSum / 10;
        }
        //append the number if overflow is nonzero (it can only be 1 or 0)
        //then reverse the string builder because they are added backwards
        this.val = (overflow == 0 ? "" : "1") + solution.reverse().toString();

    }
    private static String[] equalLengths(asn1_b x, asn1_b y){
        String yVal = y.getValue();
        String xVal = x.getValue();

        if(x.length() > y.length()) {
            yVal = "0";
            for (int i = 1; i < x.length() - y.length(); i++) {
                yVal = yVal + "0";
            }
            yVal = yVal + y.getValue();
        }
        else if (x.length() < y.length()) {
            xVal = "0";
            for (int i = 1; i < x.length() - y.length(); i++) {
                xVal = xVal + "0";
            }
            xVal = xVal + x.getValue();
        }

        return new String[] {xVal,yVal};
    }
    public static asn1_b[] fib(int n){

        asn1_b[] fib = new asn1_b[2];

        if (n<2) {
            fib[0] = new asn1_b(1);
            fib[1] = new asn1_b(0);
            return fib;
        }
        asn1_b[] prev_Fib = fib(n-1);
        fib[0] = new asn1_b(add(prev_Fib[0],prev_Fib[1]));
        fib[1] = prev_Fib[0];
        return fib;
    }

    //

}