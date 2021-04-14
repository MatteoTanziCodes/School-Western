#include "data.h"

//
// Created by matte on 2019-11-10.
//
typedef struct {Node *tree_nodes; unsigned int *free_nodes; int size; int top; int root;} BStree_struct;
typedef BStree_struct* BStree;
BStree bstree_ini(int size);
void bstree_insert(BStree bst, Key *key, Data data);
void bstree_traversal(BStree bst);
void bstree_free(BStree bst);
