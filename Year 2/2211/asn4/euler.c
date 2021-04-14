#include <stdio.h>

 long double factorialMethod( int count){

     long double result = 1.0;

    for (int i = 1; i <= count; i++) {
        result = result * i;

    }

    result = 1/result;
    return (result);
}
long double euler(long double input){

    int count = 1;
    long double result = 1.0;

    while(factorialMethod(count) >= input){
        result += factorialMethod(count);
        count++;
    }

    return result;
}

int main() {

    long double input = 0.0;

    printf("Please enter a number: ");
    scanf("%Lf",&input);

    printf("The value is: %.15Lf", euler(input));

}