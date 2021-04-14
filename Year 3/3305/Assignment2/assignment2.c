#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

// Reading and writing end of the pipe
#define READ_END 0
#define WRITE_END 1

int main(int argc, char **argv)
{

    /*************************************** command line arguments ***************************************
        To compile assignment2.c: gcc assignment2.c -o assignment2
        To run your program: ./assignment2 "CS" "3305" " is fun!"
        argv[0] = "./assignment2"
        argv[1] = "CS"
        argv[2] = "3305"
        argv[3] = " is fun!"

        Number of arguments (argc) = 4

        In this assignment, the parent process reads argv[1] and the child process reads argv[2] and argv[3]
        e.g. In the parent process: char *x = argv[1];
    *******************************************************************************************************/

    // If the user does not pass X, Y and Z, the program will terminate
    if (argc != 4)
    {
        printf("Invalid arguments\n");
        exit(0);
    }

    // You must insert the following into your code (Replace zeros with the appropriate values/variables)
    // Note: You do not need to implement a function to concatenate two strings. "string.h" header file has been included

    /***************************************************************************************************
     * 										   YOUR CODE GOES HERE
     ***************************************************************************************************/


    pid_t child1;
    int fp[2];
    if (pipe(fp) == -1){
        printf("Pipe Error");
    }
    child1 = fork();

    if (child1 > 0){
        wait(NULL); // Begin Child Process

        close(fp[WRITE_END]);

        char yP[30];
        read(fp[READ_END],yP,sizeof(yP));

        printf("parent (PID %d) reads Y' from the pipe (Y' = \"%s\")\n", getpid(), yP);

        strcat(argv[1],yP);
        printf("parent (PID %d) concatenates X and Y' to generate the string: %s\n", getpid(), argv[1]);

    }
    else if (child1 == 0) {

        close(fp[READ_END]);

        printf("A pipe is created for communication between parent (PID %d) and child\n", getppid());
        printf("parent (PID %d) created a child (PID %d)\n", getppid(), getpid());

        printf("parent (PID %d) receives X = \"%s\" from the user\n", getppid(), argv[1]);
        printf("child (PID %d) receives Y = \"%s\" and Z = \"%s\" from the user\n", getpid(), argv[2], argv[3]);

        strcat(argv[2],argv[3]);
        printf("child (PID %d) concatenates Y and Z to generate Y'= %s\n", getpid(), argv[2]);

        char ptr[40] = "";
        strcpy(ptr,argv[2]);

        write(fp[WRITE_END],ptr,sizeof(ptr));
        printf("child (PID %d) writes Y'(%s) into the pipe\n", getpid(),ptr);

    }
    else{
        printf("Fork Failure\n");
        return 0;
    }

    return 0;
}