		AREA question1, CODE, READWRITE
		ENTRY
		
		LDR r1, =UPC 				; load r1 with the UPC
		
sumLoop	LDRB r3, [r1], #1 			; start a loop that sums the first digit plus every other digit, this line gets the first digit 
		CMP r3, 0x00 				; check if it is the end of the string
		BEQ addVals 				; if it is, go to add vals
		LDRB r4, [r1], #1 			; this line gets the second digit in the string
		
		SUB r4, #48 				; convert the character hex values to decimal values
		SUB r3, #48 				; convert the character hex values to decimal values
		
		ADD r5, r5, r3 				; add the current number to the previous number and store (this adds 1,3,5,7,9 and 11)
		ADD r2, r2, r4 				; add the current number to the previous number and store (this adds 2,4,6,8,10 and 12)
		b sumLoop 					; return to the top of the while loop
		
addVals	ADD r5, r5, LSL#1 			; multiply the first sum by 3
		ADD r2, r5 					; add the sums together and store the value in r2
		
sumDiv	SUB r2, #10 				; start a loop that subtracts the value by 10
		CMP r2, #0 					; compare the value to 0
		
		BLT invalid 				; if r2 isn't a multiple of 10, then the upc code is invalid
		BNE sumDiv 					; loop until it is either equal to or less than 0. if it is equal to zero, continue
		
		MOV r0, #1 					; if the upc is valid, store the value 1 in r0
		b exit 						; skip lines until exit
		
invalid	MOV r0,#2 					; if the upc is invalid store 2 in the register r0

exit 	b exit 						; end of program

UPC		DCB "060383755577" 			; store the upc string with the label UPC
		END