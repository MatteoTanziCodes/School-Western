//
// Created by matte on 2019-10-13.
//
#include <stdio.h>
#include <stdlib.h>

float s_exp();
char get_op();
float get_num();
float m_exp(float sub_exp, char op);

int main() {
    char cont = 'Y';
    while (cont == 'Y' || cont == 'y') {
        printf("Enter a simple arithmetic expression: ");
        float result = s_exp();

        printf(" The result is %f\n", result);

        printf("Do you want to continue? Y/N\n");
        scanf(" %c", &cont);
        //Enter Y or y to continue, otherwise N or n to quit
    }
}
// Input: none, the s_expression will be read in from stdin
// Effect: the s_expression is evaluated using
// a while loop or a do while loop:
// repeatedly get num with m_exp() and then get op with
// get_op() while op is ’+’ or ’-’; when op is ’\n’, exit loop.
// Output: this function returns the value of the s_expression
float s_exp(void) {

    float num = get_num();

    char op = get_op();

    if(op == '\n'){
        return num;
    }

    float sub_exp = m_exp(num,op);

    op = get_op();


    while (op != '\n' ){


        if(op == '+'){
            num = get_num();
            op = get_op();
            sub_exp = sub_exp + m_exp(num,op);

        }
        else if(op == '-'){
            num = get_num();
            op = get_op();
            sub_exp = sub_exp - m_exp(num,op);

        } else{
            sub_exp = m_exp(num,op);
        }

        op = get_op();
    }


    return sub_exp;
}
// Input: ’sub_exp’: the value of the current sub m_expression
// to the left of ’op’ location in stdin.
// ’op’ : an operator, ’*’ or ’/’. ’op’ could also be
// ’+’, ’-’, or ’\n’ indicating the end of the m_expression.
// "+’, ’-’, or ’\n’ should be pushed back to stdin.
// the rest of the m_expression will be read in from stdin
// Effect: the m_expression is evaluated using recursion:
// get next_num with get_num() and then get next_op with get_op()
// use ’sub_exp op next_num’ and ’next_op’ to do recursive call
// Output: this function returns the value of the current m_expression
float m_exp(float sub_exp, char op) {

    float next_num, next_sub_exp;
    char next_op;

    if(op == '\n' || op == '+' || op == '-'){

        ungetc(op, stdin);
        return sub_exp;
    } else{

        next_num = get_num();
        next_op = get_op();


        if(op == '*'){
            next_sub_exp = sub_exp * next_num;

        } else if (op == '/'){
            if(next_num == 0.0){
                printf("Division by 0 is ILLEGAL");
		exit(8);
            }else {
                next_sub_exp = sub_exp / next_num;
            }
        }
        else{
            printf("This is an invalid operator");
	    exit(8);
        }
        return m_exp(next_sub_exp, next_op);

    }
}
// Input: none, read from stdin
// Effect: get the next operator of the expression
// this operator can be +, -, *, /, or ’\n’
// ’\n’ indicates the end of the expression input
// leading spaces should skipped
// Output: return the next operator of the expression.
char get_op() {
    char op = ' ';
    while (op == ' ')
        scanf("%c", &op);
    return op;
}
// Input: none, read from stdin
// Effect: get the next numeric value of the expression
// Output: return the next numeric value of the expression.
float get_num() {
    float num = ' ';
    while (num == ' ')
     scanf("%f", &num);
    return num;
}
