#include <stdio.h>

/*Case 1 converts kilometers to miles*/
float case1(float toConvert){

    float value = toConvert * 0.621371;
        printf("The value is: %f", value);
    return 0.0;
}
/*Case 1b converts miles to kilometers*/
float case1b(float toConvert){

    float value = toConvert * 1.60934;
        printf("The value is: %f", value);
    return 0.0;
}
/*Case 2 converts meters to feet*/
float case2(float toConvert){

    float value = toConvert * 3.280841;
        printf("The value is: %f", value);
    return 0.0;
}
/*Case 2b converts feet to meters*/
float case2b(float toConvert){

    float value = toConvert * 0.3048;
        printf("The value is: %f", value);
    return 0.0;
}
/*Case 3 converts centimetres to inches*/
float case3(float toConvert){

    float value = toConvert * 0.393701;
        printf("The value is: %f", value);
    return 0.0;
}
/*Case 3b converts inches to centimetres*/
float case3b(float toConvert){

    float value = toConvert * 2.54;
        printf("The value is: %f", value);
    return 0.0;
}
/*Case 4 converts Celsius to fahrenheit*/
float case4(float toConvert){

    float value = toConvert * (9/5) + 32;
        printf("The value is: %f", value);
    return 0.0;
}
/*Case 4b converts fahrenheit to celsius*/
float case4b(float toConvert){

    float value = (toConvert - 32) * 5/9 ;
        printf("The value is: %f", value);
    return 0.0;
}
int main() {

    int userI = 0;
    char userI2 = 0;
    float toConvert = 0.0;
    while(userI != 5) {

        /*Initial print statement*/
        printf("\nWould you like to convert"
               "\n1. Kilometers and miles"
               "\n2. meters and feet"
               "\n3. centimetres and inches"
               "\n4. celsius and fahrenheit"
               "\n5. Quit\n");
        scanf("%i", &userI);

        /*Switch case for the user input*/
        switch (userI){
            case 1:
              printf("K for conversion from Kilometer to Mile\n"
                     "M for conversion from Mile to Kilometer\n");
              scanf(" %c", &userI2);
              if(userI2 == 'K'){
                  printf("Provide your input: ");
                  scanf(" %f", &toConvert);
                  case1(toConvert);
              }
              else if (userI2  == 'M'){
                  printf("Provide your input: ");
                  scanf(" %f", &toConvert);
                  case1b(toConvert);
              }
              else{
                  printf("Please provide a correct input\n");
              }
                break;
            case 2:
                printf("M for conversion from Meter to Feet\n"
                       "F for conversion from Feet to Meter\n");
                scanf(" %c", &userI2);
                if(userI2  == 'M'){
                    printf("Provide your input: ");
                    scanf(" %f", &toConvert);
                    case2(toConvert);
                }
                else if(userI2 == 'F'){
                    printf("Provide your input: ");
                    scanf(" %f", &toConvert);
                    case2b(toConvert);
                }
                else{
                    printf("Please provide a correct input\n");
                }
                break;
            case 3:
                printf("C for conversion from Centimetre to Inch\n"
                       "I for conversion from Inch to Centimetre\n");
                scanf(" %c", &userI2);
                if(userI2 == 'C'){
                    printf("Provide your input: ");
                    scanf(" %f", &toConvert);
                    case3(toConvert);
                }
                else if (userI2  == 'I'){
                    printf("Provide your input: ");
                    scanf(" %f", &toConvert);
                    case3b(toConvert);
                }
                else{
                    printf("Please provide a correct input\n");
                }
                break;
            case 4:
                printf("C for conversion from Celsius to Fahrenheit\n"
                       "F for conversion from Fahrenheit to Celsius\n");
                scanf(" %c", &userI2);
                if(userI2 == 'C'){
                    printf("Provide your input: ");
                    scanf(" %f", &toConvert);
                    case4(toConvert);
                }
                else if (userI2  == 'F'){
                    printf("Provide your input: ");
                    scanf(" %f", &toConvert);
                    case4b(toConvert);
                }
                else{
                    printf("Please provide a correct input\n");
                }
                break;
            case 5:
                return(0);
            default: printf("Please choose a valid option\n");
            break;
        }
    }
    return 0;
}