#include <stdio.h>
#include <stdlib.h>
#include "list.h"
#include "memory.h"

// Return a pointer to a dynamically allocated and initialized List.
List list_ini(void) {
    List_node *head= (List_node *) simu_malloc( sizeof(List_node) );
    head->next = NULL;
    return head;
}

// If key is in list, return the address of key’s associated data. If key is not in list, return NULL.
Data *list_search(List list, Key key) {
    List_node* head = list->next;

    for(; head != NULL; head = head->next)
        if ( head -> key == key)
            return &head->data;
    return NULL;


}

// Add key with data into the front of list. If key is in list, then do nothing.
void list_add(List list, Key key, Data data) {
    Data *inlist;
    inlist = list_search(list,  key);

    if (inlist == NULL) {
        List_node* newHead;
        newHead = list_ini();
        newHead->key = key;
        newHead->data = data;
        newHead->next = list->next;
        list->next = newHead;
    }
}

// Delete the node in list with its key equals to key. If no such node in list, do nothing.
void list_delete(List list, Key key) {
    if (list_search(list, key) != NULL) {
        List prev = list;

        while (list->next != NULL) {
            if (list->key == key) {
                List next = list->next;
                prev->next=next;
            }
            prev = list;
            list = list->next;
        }
    }
}

// print each node of the list
void list_print(List list) {
    List_node* current = list->next;

    while (current != NULL) {
        print_list_node(current);
        current = current->next;
    }
}

// completely frees the list of nodes
void list_free(List list) {
    simu_free(list);
}