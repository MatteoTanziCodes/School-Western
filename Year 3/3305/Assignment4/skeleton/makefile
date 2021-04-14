CC = gcc
H = io.h types.h scheduling.h
C = main.c io.c scheduling.c
EXEC = a4

build: ${C} ${H}
	${CC} ${C} -o ${EXEC}

test:
	@echo "Running a4 on cpu_scheduling_input_file.txt"
	./${EXEC} cpu_scheduling_input_file.txt
	diff cpu_scheduling_output_file.txt correct_output.txt
	@echo "Passed!"

clean:
	@echo "Cleaning up..."
	rm -f ${EXEC} cpu_scheduling_output_file.txt

