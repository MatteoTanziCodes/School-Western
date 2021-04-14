			AREA assign4, CODE, READWRITE
			ENTRY
			
Pointers	ADR r0, STRING1 					; r0 stores the address of the beginning of string1
			ADR r2, STRING2 					; r2 stores the address of the beginning of string2
			
baseCase	LDRB r4, [r0]						; r4 hold the value for the first character byte in the string
			CMP r4, #t 							; compare the ascii values of the first byte and the ascii value for t
			BEQ regStr 							; if the ascii values are equal, call regStr to set r1 to 0 and save the current pointer address
			
findSpc		LDRB r4, [r0], #1					; load the next char into r4
			STRB r4, [r2], #1 					; store the char into the address that r2 points to (beginning of string2)
			CMP r4, #0x00 						; Check to see if the char is the end of the string
			BEQ exit 							; if it is the end of the string, exit the program
			CMP r4, #gap 						; Check to see if the char is a space
			BNE findSpc 						; continue to loop until you find a space
			
regStr 		MOV r1, #0 							; set register 1 to 0
			MOV r5, r0 							; store the current pointer address
			
cmpTxt 		ADD r7, #1							; Increment teh counter for r7
			LDR r3, =0x74686520 				; set the comparitor for "the " which the value in r1 will compare to
			LDRB r4, [r0], #1 					; load the next char into r4
			CMP r4, #0x00 						; check to see if it is a null value if so, change the comparitor to "the0\" 
			LDREQ r3, =0x74686500 				; change the comparitor to "the0\"
			ADD r1, r4 							; append the value in r4, the current char into the comparitor
			CMP r7, #4							; check to see if the comparitor has 4 bytes in it yet
			LSLNE r1, #8 						; if it has less than 4 bytes shift the value so that there is 1 byte at the end to store a new char
			BNE cmpTxt 							; loop back to top if the comparitor isn't full
			SUBEQ r7,#4							; clear the counter
			CMP r1, r3 							; compare the values in the comparitors when they are full
			SUBEQ r0, #1						; if they are equal move the pointer to the next value
			MOVNE r0, r5 						; if they are not equal, move to pointer back to the position after the space
			B findSpc							; loop until the null character is reached
exit		b exit								; END

STRING1		DCB "the the the 123 the" 			; String1
EoS			DCB 0x00							; null space at the end of the string
STRING2 	space 0x7F 							; just allocating 127 bytes 
gap			EQU 0x20							; Ascii value for a space
t			EQU 0x74							; ascii value for a t
			END