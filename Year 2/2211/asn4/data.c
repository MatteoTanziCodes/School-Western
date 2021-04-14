//
// Created by matte on 2019-11-12.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "data.h"
// Input: ’in_name’: a string ends with ’\0’
// ’in_id’: an integer
// Output: a pointer of type pointer to Key,
// pointing to an allocated memory containing a Key
// Effect: dynamically allocate memory to hold a Key
// set Key’s id to be in_id
// dynamically allocate memory for the Key’s name
// so that name will contain what is in ’in_name’.
// Note: do not use strdup(), use malloc(), strlen(), and strcpy()


Key *key_construct(char *in_name, int in_id) {

    //Constructs the key and all of it's components, setting the id and the name
    Key *key_data = (Key *) malloc(sizeof(Key));
    key_data->id = in_id;
    key_data->name = (char *) malloc(strlen(in_name)+1 * sizeof(char));
    strcpy(key_data->name, in_name);
    return key_data;

}
// Input: ’key1’ and ’key2’ are two pointers to Key
// Output: if return value < 0, then *key1 < *key2,
// if return value = 0, then *key1 = *key2,
// if return value > 0, then *key1 > *key2,
// Note: use strcmp() to compare key1->name and key2->name
// if key1->name == key2->name, then compare key1->id with key2->id

int key_comp(Key *key1, Key *key2) {

    int Compare = strcmp(key1->name,key2->name); // Uses str compare to determine where to insert the key in the tree
    if(Compare == 0){
        if(key1->id < key2->id){
            return -1;
        } else if(key1->id > key2->id){
            return 1;
        } else {
            return 0;
        }
    } else {
        return Compare;
    }

}
// Input: ’key’: a pointer to Key
// Effect: ( key->name key->id ) is printed
void print_key(Key *key) {
    int spaces,digits=0,datas = key->id;
    printf("( %s " ,key->name);
    // Manually configure the space between the last element of the string and the first in the digit
    while(datas!=0){
        datas = datas/10;
        digits++;
    }
    spaces = 14 - strlen(key->name) - digits;
    for (int j = 0; j < spaces; ++j) {
        printf(" ");
    }
    printf("%d )",key->id);
}
// Input: ’node’: a node
// Effect: node.key is printed and then the node.data is printed
void print_node(Node node) {
    int spaces,digits=0,datas = node.data;
    print_key(node.key);
    // Manually configure the space between the last element of the string and the first in the digit
    while (datas!=0){
        datas = datas/10;
                digits++;
    }
    spaces = 7 -digits;
    for (int i = 0; i < spaces; ++i) {
        printf(" ");
    }
    printf("%d\n", node.data);
}
