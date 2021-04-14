// Matteo Tanzi; 251011979

public class asn1_a {

    public static long fib_sequence(long sequence_length){

        if(sequence_length < 2){
            return sequence_length;
        }
        else{
            return fib_sequence(sequence_length - 1) + fib_sequence(sequence_length - 2);
        }

    }

    public static void main(String[]args){
        for (long i = 0; i <= 10; i++){
        System.out.println(fib_sequence(i*5));
        }

    }
}
