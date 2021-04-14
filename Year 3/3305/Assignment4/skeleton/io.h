#ifndef IO_H
#define IO_H

#include <stdio.h>
#include "types.h"

int num_queues(FILE *fp);
int num_p(char *line);
int read_input_file(char *filename, input_queues *inp);

void print_rr_result(rr_result *);
void free_rr_result(rr_result *);

#endif
