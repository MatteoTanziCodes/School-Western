#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "dataHandling.h"
#include "dataPrinting.h"

void dataHandling(char **array, int idx, FILE *fp) {

    char **dataArray = array;
    int arrayLength = idx, bankValue = 0, bankLength = 0, counter = 0;
    const char *delim = " ";

    for (int i = 0; i < idx; i++) {
        char *checkForBank = dataArray[i];
        if (checkForBank[0] == 'a') {
            bankLength++;
        }
    }

    int bankValues[bankLength];
    for (int i = 0; i < arrayLength; i++) {
        char *checkForBank = dataArray[i];
        if (checkForBank[0] == 'a') {
            char *ptr = strtok(checkForBank, delim);
            for (int j = 0; j < 2; j++) {
                ptr = strtok(NULL, delim);
            }
            bankValue = (int) strtol(ptr, NULL, 10);
            bankValues[counter] = bankValue;
            counter++;
        }
    }
    counter = 0;

    char index, transferTo, transferFrom;
    int amount;

    for (int i = bankLength; i < arrayLength; i++) {

        char *ptr = strtok(dataArray[i], delim);
        while (ptr != NULL) {
            if (ptr[0] == 'd') {
                ptr = strtok(NULL, delim);
                index = ptr[1];
                ptr = strtok(NULL, delim);
                amount = (int) strtol(ptr, NULL, 10);
                bankValues[index - '0' - 1] += amount;
            } else if (ptr[0] == 'w') {
                ptr = strtok(NULL, delim);
                index = ptr[1];
                ptr = strtok(NULL, delim);
                amount = (int) strtol(ptr, NULL, 10);
                if ((bankValues[index - '0' - 1] - amount) >= 0){
                    bankValues[index - '0'- 1] = bankValues[index - '0'- 1] - amount;
                }
            } else if (ptr[0] == 't') {
                ptr = strtok(NULL, delim);
                transferFrom = ptr[1];
                ptr = strtok(NULL, delim);
                transferTo = ptr[1];
                ptr = strtok(NULL, delim);
                amount = (int) strtol(ptr, NULL, 10);
                if ((bankValues[transferFrom - '0'- 1] - amount) >= 0){
                    bankValues[transferFrom - '0'- 1] = bankValues[transferFrom - '0'- 1] - amount;
                    bankValues[transferTo - '0'- 1] += amount;
                }

            }
            ptr = strtok(NULL, delim);
        }

    }

    dataPrinting(bankValues, bankLength, fp);
}