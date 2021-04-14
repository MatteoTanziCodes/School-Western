#include <stdio.h>

/*Recursive func for even exponents*/
float even(float base, int exponent){

    if(exponent == 0){
        return 1;
    } else{
        return (base * even(base, exponent-1));
    }

}
/*Recursive func for odd exponents*/
float odd(float base, int exponent){

    if(exponent == 0){
        return 1;
    } else{
        return (base* odd(base, exponent-1));
    }
}

int main() {

    /*Declare vars*/
    float base = 0;
    int exponent = 0;
    float result = 0;

    /*while loop to check incompatibility*/
    while(base<= 0 && exponent<=0) {

        printf("Please provide the base: ");
        scanf(" %f", &base);
        printf("Please provide the exponent: ");
        scanf(" %i", &exponent);

    }

    /*calls functions*/
    if (exponent%2 == 0){
        result = even(base, exponent);
    }
    else{
        result = odd(base, exponent);
    }

    printf("%f", result);
    return 0;
}
