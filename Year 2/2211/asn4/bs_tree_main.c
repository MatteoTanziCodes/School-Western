//
// Created by matte on 2019-11-10.
//

#include <stdio.h>
#include "bst.h"
int main(void) {
    BStree bst;
    bst = bstree_ini(256);

    char in_name[100];
    int val=0,val2=0;
    printf("Please enter data in triples format:");

    while(scanf("%s %d %d",&in_name, &val, &val2 ) == 3){
        bstree_insert(bst, key_construct(in_name,val),val2);
    }
    bstree_traversal(bst);
    bstree_free(bst);
}
