//
// Created by matte on 2019-10-13.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {

/*Declare Vars*/
    int input = 1;
    int num_length = 0;
    int digits[100];
    int counter = 0;
    char Neg = ' ';
    char decision = ' ';
    char result[100];
    const char segment [10][3][3] =
            {{{' ', '_',' '}, {'|', ' ', '|'}, {'|', '_', '|'}},
             {{' ',' ' ,' ' },{' ', '|', ' '},{' ', '|', ' '}},
             {{' ', '_', ' '},{' ', '_', '|'},{'|', '_', ' '}},
             {{' ', '_', ' '},{' ', '_', '|'},{' ', '_', '|'}},
             {{' ', ' ', ' '},{'|', '_', '|'},{' ', ' ', '|'}},
             {{' ', '_', ' '},{'|', '_', ' '},{' ', '_', '|'}},
             {{' ', '_', ' '},{'|', '_', ' '},{'|', '_', '|'}},
             {{' ', '_', ' '},{' ', ' ', '|'},{' ', ' ', '|'}},
             {{' ', '_', ' '},{'|', '_', '|'},{'|', '_', '|'}},
             {{' ', '_', ' '},{'|', '_', '|'},{' ', '_', '|'}}};

    do {
        printf("Please input an integer : ");
        scanf(" %i", &input);

        /*Find if negative is required*/
        if (input < 0){
            Neg = 'Y';
            input = abs(input);
        }
        else{
            Neg = 'N';
        }

        /*gets num length*/
        sprintf(result, "%i", input);
        getchar();
        num_length = strlen(result);

        /*inputs vals into the array*/
        for (int i = num_length; i >= 0; i--) {
            digits[i-1] = input%10;
            input /= 10;
        }

        /*While loop segment prints the values*/
        printf("  ");
        counter = 0;
        while (counter<num_length){
            for (int i = 0; i < 3; ++i) {
                printf("%c", segment[digits[counter]][0][i]);

            }
            printf(" ");
            counter++;
        }
        counter = 0;
        printf("\n");
        if (Neg == 'Y'){
            printf("--");
        } else {
            printf("  ");
        }
        while (counter<num_length){
            for (int i = 0; i < 3; ++i) {
                printf("%c", segment[digits[counter]][1][i]);

            }
            printf(" ");
            counter++;
        }
        counter = 0;
        printf("\n");
        printf("  ");
        while (counter<num_length){
            for (int i = 0; i < 3; ++i) {
                printf("%c", segment[digits[counter]][2][i]);

            }
            printf(" ");
            counter++;
        }

        /*Repeat function*/
        printf("\nWould you like to continue? (Y for yes/ N for no): ");
        scanf(" %c", &decision);
    }
    while(decision == 'Y');

    return 0;
}
