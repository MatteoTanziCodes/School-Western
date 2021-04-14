//
// Created by matte on 2019-11-12.
//
#include <stdio.h>
#include <stdlib.h>
#include "bst.h"
// This function creates a new node
// input: Tree, key, data
// Set the tree nodes at the index to the construct data
// increase the pointer set to top
// change the free nodes index to 0
int newNode(BStree bst, Key *key, Data data) {

    int index = bst->top;

    if(index > bst->size){
        printf("ARRAY FULL\n");
        return 0;
    }

    bst->tree_nodes[index].key = key;
    bst->tree_nodes[index].data = data;
    bst->tree_nodes[index].left = 0;
    bst->tree_nodes[index].right = 0;
    bst->top++;
    bst->free_nodes[index] = 0;

    return index;

}
// Input: ’size’: size of an array
// Output: a pointer of type BStree,
// i.e. a pointer to an allocated memory of BStree_struct type
// Effect: dynamically allocate memory of type BStree_struct
// allocate memory for a Node array of size+1 for member tree_nodes
// allocate memory for an unsigned int array of size+1 for member free_nodes
// set member size to ’size’;
// set entry free_nodes[i] to i
// set member top to 1;
// set member root to 0;
BStree bstree_ini(int size) {

    //initialize the tree using malloc to dynamically allocate the size
    BStree tree = (BStree) malloc(sizeof(BStree_struct));
    tree->tree_nodes = (Node *) malloc((size + 1) * sizeof(Node));
    tree->free_nodes = (unsigned int *) malloc((size + 1) * sizeof(int));
    tree->size = size;
    tree->top = 1; // set the top to one
    tree->root = 0; // set the root to zero

    for(int i = 0; i < size + 1; i++){
        tree->free_nodes[i] = i; // set all the free-nodes
    }
    return tree;

}
//insert helper
void bst_insert(BStree bst,  int index,  Key *key, Data data) {

    // Check if the node index is greater than the allocated size of the tree, if so exit as it is full
    if(index >= bst->size ){
        printf("Full");
        return;
    }
    //Check is the top of the array is greater than the allocated size if so it will give OOB error.
    if(bst->top > bst->size){
        printf("Index OOB");
        return;
    }
    else{
        if(index == 0 && bst->root == 0){ // If it is the first being inserted make a new node
            bst->root = newNode(bst,key,data); // set the index of the root to the return of the new node
            return;
        }
    }
    int Compare = key_comp(key,bst->tree_nodes[index].key);
            if(Compare == 0){
                return;
            }else if(Compare < 0 ){
                if(bst->tree_nodes[index].left == 0){
                    bst->tree_nodes[index].left = newNode(bst,key,data);
                }else{
                    bst_insert(bst,bst->tree_nodes[index].left, key, data);
                }
            }else {
                if(bst->tree_nodes[index].right == 0){
                    bst->tree_nodes[index].right = newNode(bst,key,data);
                }else{
                    bst_insert(bst,bst->tree_nodes[index].right, key, data);
                }
            }

}
// Input: ’bst’: a binary search tree
// ’key’: a pointer to Key
// ’data’: an integer
// Effect: ’data’ with ’key’ is inserted into ’bst’
// if ’key’ is already in ’bst’, do nothing
void bstree_insert(BStree bst, Key *key, Data data) {

    if(bst->root == 0){
        bst->root = newNode(bst,key,data);
    } else{
        bst_insert(bst,bst->root,key,data);
    }

}
// Traversal helper
void bst_traversal(BStree bst, int i) {

    if (i==0){
        return;
    }

    //inOrder traversal
    if(bst->tree_nodes[i].left != 0 ){
        bst_traversal(bst, bst->tree_nodes[i].left);
    }

    print_node(bst->tree_nodes[i]); // print node

    if(bst->tree_nodes[i].right != 0){
        bst_traversal(bst, bst->tree_nodes[i].right);
    }

}

// Input: ’bst’: a binary search tree
// Effect: print all the nodes in bst using in order traversal
void bstree_traversal(BStree bst) {

    int index = bst->root;

    //if the tree has  values
    if(index < bst->size) {
        bst_traversal(bst, bst->root);
        }
    }

    //Free helper
void freeHelper(BStree bst, int i){

    Node node = bst->tree_nodes[i];


    if(bst->root == 0){
        free(bst);
    }
    else{
        if(node.left != 0){
            freeHelper(bst,node.left);
        }
        free(node.key);
        if(node.right != 0){
            freeHelper(bst,node.right);
        }

    }
}
// Input: ’bst’: a binary search tree
// Effect: all dynamic memory used by bst are freed
    void bstree_free(BStree bst) {
    int index = bst->root;
    freeHelper(bst,index);
}


