CC = gcc
H = dataPrinting.h dataHandling.h
C = main.c dataHandling.c dataPrinting.c
EXEC = a6

build: ${C} ${H}
	${CC} ${C} -o ${EXEC}

test:
	./${EXEC} assignment_6_input_file.txt

clean:
	@echo "Cleaning up..."
	rm -f ${EXEC} assignment_6_output_file.txt