#define FILENAME_OUTPUT "assignment_6_output_file.txt"
#define MAX 255
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include "dataHandling.h"

int main(int argc, char* argv[]) {

    // Check if the arguments provided contain the program and the file.
    if (argc != 2){
        printf("Usage: %s input.txt\n", argv[0]);
        exit(1);
    }

    // If the output file has already been created delete the output file
    if (access(FILENAME_OUTPUT, F_OK) != -1)
    {
        remove(FILENAME_OUTPUT);
    }

    FILE *fp_outfile = fopen(FILENAME_OUTPUT, "w");

    char **array = NULL;        /* array of pointers to char        */
    char *ln = NULL;            /* NULL forces getline to allocate  */
    size_t n = 0;               /* buf size, 0 use getline default  */
    ssize_t nchr = 0;           /* number of chars actually read    */
    size_t idx = 0;             /* array index for number of lines  */
    size_t lmax = MAX;         /* current array pointer allocation */
    FILE *fp = NULL;            /* file pointer                     */

    if (!(fp = fopen (argv[1], "r"))) { /* open file for reading    */
        fprintf (stderr, "error: file open failed '%s'.", argv[1]);
        return 1;
    }
    if (!(array = calloc (MAX, sizeof *array))) {
        fprintf (stderr, "error: memory allocation failed.");
        return 1;
    }
    while ((nchr = getline (&ln, &n, fp)) != -1)    /* read line    */
    {
        while (nchr > 0 && (ln[nchr-1] == '\n' || ln[nchr-1] == '\r'))
            ln[--nchr] = 0;     /* strip newline or carriage rtn    */

        /* allocate & copy ln to array - this will create a block of memory
           to hold each character in ln and copy the characters in ln to that
           memory address. The address will then be stored in array[idx].
           (idx++ just increases idx by 1 so it is ready for the next address)
           There is a lot going on in that simple: array[idx++] = strdup (ln);
        */
        array[idx++] = strdup (ln);

        if (idx == lmax) {      /* if lmax lines reached, realloc   */
            char **tmp = realloc (array, lmax * 2 * sizeof *array);
            if (!tmp)
                return -1;
            array = tmp;
            lmax *= 2;
        }
    }

    if (fp) fclose (fp);        /* close file */
    if (ln) free (ln);          /* free memory allocated to ln  */

    dataHandling(array,idx,fp_outfile);

    for (int it = 0; it < idx; it++)        /* free array memory    */
        free (array[it]);
    free (array);

    fclose(fp_outfile);
    return 0;
}