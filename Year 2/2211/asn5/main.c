#include <stdio.h>
#include "bst.h"
#include "list.h"
#include "memory.h"

int main(void) {

    int memory;
    int num;

    scanf("%i",&memory); // Read in the memory

    mem_ini(memory);
    bstree_ini(memory); // Initialize the allocation system

    List list = list_ini(); // initialize a linked list


    while(scanf("%d", &num) == 1) {
        if(list_search(list, num) == NULL) {
            list_add(list, num, 1);
        } else {
            list_add(list, num, ++(*list_search(list, num)));
        }
    }
    printf("\n");

    printf("Integer\tOccurrence\n");
    list_print(list); // Print the list


    list_free(list); // Free the memory
    mem_free();

    return 0;

}
