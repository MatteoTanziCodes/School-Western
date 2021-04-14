#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "io.h"

int num_queues(FILE *fp)
{
	int count = 0; // Line counter (result)
	char c;		   // To store a character read from file

	// Extract characters from file and store in character c
	for (c = getc(fp); c != EOF; c = getc(fp))
		if (c == '\n') // Increment count if this character is newline
			count++;

	fseek(fp, 0, SEEK_SET);
	return count;
}

int num_p(char *line)
{
	const char delim[2] = " ";
	char *tok;
	int p = -4;

	tok = strtok(line, delim);
	while (tok != NULL)
	{
		p++;
		tok = strtok(NULL, delim);
	}

	return p / 2;
}

int read_input_file(char *filename, input_queues *inp)
{
	FILE *fp = fopen(filename, "r");
	if (fp == NULL)
	{
		perror(filename);
		exit(1);
	}
	int nq = num_queues(fp);

	char *buffer = NULL;
	size_t len = 0;
	char *tok;
	const char delim[2] = " ";
	ssize_t nread;

	int **queues = malloc(sizeof(int *) * nq);
	int *np = malloc(sizeof(int) * nq);
	int *tq = malloc(sizeof(int) * nq);
	int p;

	int i = 0;
	while (nread = getline(&buffer, &len, fp) != -1)
	{
		p = num_p(buffer);
		queues[i++] = malloc(sizeof(int) * p);
	}

	fseek(fp, 0, SEEK_SET);
	i = -1;
	int j;
	while (getline(&buffer, &len, fp) != -1)
	{
		j = 0;
		tok = strtok(buffer, delim);
		while (tok != NULL)
		{
			switch (tok[0])
			{
			case 't':
				tok = strtok(NULL, delim);
				// Save time quantum for the i'th ready queue
				tq[++i] = atoi(tok);
				break;
			case 'p':
				tok = strtok(NULL, delim);
				char *buf = strdup(tok);
				queues[i][j++] = atoi(buf);
				free(buf);
				break;
			default:
				tok = strtok(NULL, delim);
				break;
			}
		}
		np[i] = j;
	}

	fclose(fp);
	free(buffer);

	inp->nq = nq;
	inp->queues = queues;
	inp->np = np;
	inp->tq = tq;

	return 0;
}

void free_rr_result(rr_result *result)
{
	free(result->order);
	free(result->turnarounds);
	free(result);
}

void print_rr_result(rr_result *result)
{
	puts("Order of selection by CPU: ");
	for (int i = 0; i < result->order_n; i++)
	{
		printf("p%d ", (result->order)[i] + 1);
	}

	// Get order of process completion
	int completion[result->np];
	int k = (result -> np) - 1;

	int seen[result -> np];
	for (int i = 0; i < result -> np; i++) {
		seen[i] = 0;
	}

	for (int i = (result->order_n)-1; k >= 0 && i >= 0; i--) {
		int p = (result->order)[i];
		if (!seen[p]) {
			seen[p] = 1;
			completion[k] = p;
			k--;
		}
	}

	puts("");
	puts("");

	puts("Turnaround times for each process: ");
	for (int i = 0; i < result->np; i++)
	{
		int p = completion[i];
		printf("p%d = %d\n", p + 1, (result->turnarounds)[p]);
	}
}
