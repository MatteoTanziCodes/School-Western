#define FILENAME_INPUT "cpu_scheduling_input_file.txt"
#define FILENAME_OUTPUT "cpu_scheduling_output_file.txt"

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#include "io.h"
#include "scheduling.h"
#include "types.h"


int main(int argc, char* argv[])
{
	if (argc != 2){
		printf("Usage: %s input.txt\n", argv[0]);
		exit(1);
	}
	input_queues inp;
	if (access(FILENAME_OUTPUT, F_OK) != -1)
	{
		remove(FILENAME_OUTPUT);
	}
	int fp_outfile = open(FILENAME_OUTPUT, O_CREAT | O_WRONLY, S_IRUSR | S_IWUSR);
	read_input_file(argv[1], &inp);
	// read_input_file(FILENAME_INPUT, &inp);
	dup2(fp_outfile, STDOUT_FILENO);

	int **queues = inp.queues;
	int nq = inp.nq;
	int *np = inp.np;
	int *tq = inp.tq;

	for (int i = 0; i < nq; i++)
	{
		printf("Ready Queue %d Applying RR Scheduling:\n\n", i + 1);
		rr_result *rr_executed = rr(queues[i], np[i], tq[i]);
		print_rr_result(rr_executed);

		if (i < nq - 1)
			puts("");

		free_rr_result(rr_executed);

		free(queues[i]);
	}

	free(np);
	free(tq);
	free(queues);
	close(fp_outfile);

	return 0;
}
