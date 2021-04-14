#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <errno.h>

int main(int argc, char **argv)
{
    /************************************************** command line arguments ***********************************************
        For the purposes of this explanation, assume that "external_program.out" is located in /home/usr/A1/external_program.out

        When you are testing your program, replace "/home/usr/A1/external_program.out" with the path to the executable file
        generated when you compile "external_program.c"

        To compile assignment1.c: gcc assignment1.c -o assignment1
        To run your program: ./assignment1 /home/usr/A1/external_program.out
        argv[0] = "./assignment1"
        argv[1] = "/home/usr/A1/external_program.out"

        Number of arguments (argc) = 2

        In this assignment, the path to the external program is in argv[1]
    *************************************************************************************************************************/


    /***************************************************************************************************
     * 										 YOUR CODE GOES HERE
     ***************************************************************************************************/


    if (argc != 2){
        printf("INVALID ARG\n");
        exit(0);
    }


    pid_t child1, child2, child1_1;


    child1 = fork();
    wait(NULL);
    child2 = fork();
    if (child1 > 0) {
        if(child2 > 0) {
            wait(NULL);
        }

        if (child2 == 0) {

            printf("parent (PID %d) created child_2 (PID %d)\n", getppid(), getpid());
            printf("child_2 (PID %d) is calling an external program external_program.out and leaving child_2..\n", getpid());

            char pid_str[20];
            int c = getpid();
            snprintf(pid_str, 19, "%d", c);
            execl(argv[1],"external_program.out", pid_str, NULL);

        }
        else {
            wait(NULL);
        }
    }
    else if (child1 == 0) {
        printf("parent process (PID %d) created child_1 (PID %d) \n", getppid(), getpid());
        printf("parent (PID %d) is waiting for child_1 (PID %d) to complete before creating child_2\n", getppid(), getpid());

        child1_1 = fork();
        if (child1_1 == 0) {
            printf("child_1 (PID %d) created child_1.1 (PID %d)\n", getppid(), getpid());
            printf("child_1 (PID %d) is now complete\n", getpid());
            exit(1);
        }
        else {
            wait(NULL);
        }
    }
    return 0;
}