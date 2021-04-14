#include <stdio.h>
#include <stdlib.h>
#include "memory.h"

static unsigned char *memory;
static BStree bst;

// Initialize an unsigned char array of size elements and initialize a binary search tree.
void mem_ini(unsigned int size) {
    memory = (unsigned char *) malloc(size);
    bst = bstree_ini(size/5); // size/5 is large enough
    bstree_insert(bst, 0, size);
}

// Allocate size bytes and return a pointer to the first byte allocated.
void *simu_malloc(unsigned int size) {

    Key *newKey = bstree_data_search(bst, size + 4);
    Data *freeBlock = bstree_search(bst, *newKey);
    bstree_delete(bst,*newKey);
    unsigned char *memInt = &memory[*newKey];
    *memInt = size;

    if (freeBlock -(size + 4) > 0){
        bstree_insert(bst, *newKey + size + 4, *freeBlock - (size + 4));
    }
    return &memory[*newKey + 4];
}


// Put the allocated memory pointed by ptr back to be free memory.
void simu_free(void *ptr) {
    Key *k = (Key*)((unsigned char*)ptr - 4);
    Key newk = *bstree_data_search(bst,*k);
    Data d = *k+4;
    bstree_insert(bst,newk - (*k + 4), d);

}

// Print all the free memory blocks
void mem_print(void) {
    bstree_traversal(bst);
}

// Free memory used for the array and the binary search tree
void mem_free(void) {
    bstree_free(bst);
}
