//
// Created by matteo on 2020-12-05.
//

#include <stdio.h>
#include "dataPrinting.h"

void dataPrinting(int bankVals[],int bankLength , FILE *fp){

    for (int i = 0; i < bankLength; i++){
        printf("a%d b %d\n", (i+1), bankVals[i]);
        fprintf(fp,"a%d b %d\n", (i+1), bankVals[i] );
    }
}