//
// Created by matteo on 2020-11-12.
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "scheduling.h"

/*
##  Round-Robin Scheduling Algorithm  ##

	Parameters:-

	queue: 1D int array containing the CPU burst time for every process in a ready queue
	np: number of processes (length of `queue` array)
	tq: time quantum for the RR algorithm


	Returns:-

	rr_result struct pointer, where
	rr_result -> order: 1D int array, containing the execution order of processes (i.e. `(rr_result -> order)[0]` is the process number of the first process)
		** hint: p1 is always the first item in order of selection in round-robin and FCFS
    rr_result -> order_n: int, representing the size of the `order` array, described above ^
	rr_result -> turnarounds: 1D int array, containing the turnaround time for each process (i.e. `(rr_result -> turnarounds)[0]` is the turnaround time for p1)
*/
rr_result *rr(int *queue, int np, int tq)
{

    rr_result *result = malloc(sizeof(rr_result));
    result->np = np;
    result->turnarounds = malloc(sizeof(int) * np);
    result->order = malloc(sizeof(int) * (100));
    result->order_n = 0;

    // code here to assign values to result->turnarounds, result->order, and result->order_n


    //Declare Variables
    int task = 0;
    int time = 0;
    int flag[np];
    bool complete = false;
    int startTime[np];
    int endTime[np];
    int loop = 0;


    // Set all flags marking the completion of a task
    for (int i = 0; i < np; i++) {
        flag[i] = 0;
        startTime[i] = 0;
        endTime[i] = 0;
    }


    while(!complete){


        // Test to see if complete
        for (int i = 0; i < np; i++) {
            if (flag[i]==0){
                complete = false;
                i = np;
            }
            else{
                complete = true;
            }
        }

        if (queue[task] != 0){

            // add task to new order
            (result->order)[result->order_n] = task;

            //increment new orderSize
            result->order_n++;

            //if burst time is greater than time quantum
            if (queue[task] > tq) {


                //if startTime [task] == 0
                if (startTime[task] == 0 && task != 0) {
                    //set startTime[task] = time
                    startTime[task] = time;

                }
                //remove time quantum from burst time
                queue[task] = queue[task] - tq;
                //add time quantum to time
                time = time + tq;
            }
                //else
            else {

                if (startTime[task] == 0 && task != 0){
                    startTime[task] = time;
                }//
// Created by matteo on 2020-11-12.
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "scheduling.h"

/*
##  Round-Robin Scheduling Algorithm  ##

	Parameters:-

	queue: 1D int array containing the CPU burst time for every process in a ready queue
	np: number of processes (length of `queue` array)
	tq: time quantum for the RR algorithm


	Returns:-

	rr_result struct pointer, where
	rr_result -> order: 1D int array, containing the execution order of processes (i.e. `(rr_result -> order)[0]` is the process number of the first process)
		** hint: p1 is always the first item in order of selection in round-robin and FCFS
    rr_result -> order_n: int, representing the size of the `order` array, described above ^
	rr_result -> turnarounds: 1D int array, containing the turnaround time for each process (i.e. `(rr_result -> turnarounds)[0]` is the turnaround time for p1)
*/
                rr_result *rr(int *queue, int np, int tq)
                {

                    rr_result *result = malloc(sizeof(rr_result));
                    result->np = np;
                    result->turnarounds = malloc(sizeof(int) * np);
                    result->order = malloc(sizeof(int) * (100));
                    result->order_n = 0;

                    // code here to assign values to result->turnarounds, result->order, and result->order_n


                    //Declare Variables
                    int task = 0;
                    int time = 0;
                    int flag[np];
                    bool complete = false;
                    int startTime[np];
                    int endTime[np];
                    int loop = 0;


                    // Set all flags marking the completion of a task
                    for (int i = 0; i < np; i++) {
                        flag[i] = 0;
                        startTime[i] = 0;
                        endTime[i] = 0;
                    }


                    while(!complete){


                        // Test to see if complete
                        for (int i = 0; i < np; i++) {
                            if (flag[i]==0){
                                complete = false;
                                i = np;
                            }
                            else{
                                complete = true;
                            }
                        }

                        if (queue[task] != 0){

                            // add task to new order
                            (result->order)[result->order_n] = task;

                            //increment new orderSize
                            result->order_n++;

                            //if burst time is greater than time quantum
                            if (queue[task] > tq) {


                                //if startTime [task] == 0
                                if (startTime[task] == 0 && task != 0) {
                                    //set startTime[task] = time
                                    startTime[task] = time;

                                }
                                //remove time quantum from burst time
                                queue[task] = queue[task] - tq;
                                //add time quantum to time
                                time = time + tq;
                            }
                                //else
                            else {

                                if (startTime[task] == 0 && task != 0){
                                    startTime[task] = time;
                                }
                                //add q[task] to time
                                time = time + queue[task];
                                //set endTime[task] to time
                                endTime[task] = time;
                                result->turnarounds[task] = endTime[task] - startTime[task];


                                //set q at task to 0
                                queue[task] = 0;
                            }


                            //if task+1 == np
                            if (task + 1 == np){
                                //set task to 0
                                task = 0;
                            }
                            else{//else increment task
                                task++;
                            }


                        }
                        else{
                            flag[task] = 1;

                            //if task+1 == np
                            if (task + 1 == np){
                                //set task to 0
                                task = 0;
                            }
                            else{//else increment task
                                task++;
                            }
                        }

                    }

                    return result;

                }


                //add q[task] to time
                time = time + queue[task];
                //set endTime[task] to time
                endTime[task] = time;
                result->turnarounds[task] = endTime[task] - startTime[task];


                //set q at task to 0
                queue[task] = 0;
            }


            //if task+1 == np
            if (task + 1 == np){
                //set task to 0
                task = 0;
            }
            else{//else increment task
                task++;
            }


        }
        else{
            flag[task] = 1;

            //if task+1 == np
            if (task + 1 == np){
                //set task to 0
                task = 0;
            }
            else{//else increment task
                task++;
            }
        }

    }

    return result;

}

