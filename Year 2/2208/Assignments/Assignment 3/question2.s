			AREA question2, CODE, READWRITE
			ENTRY 
			
			LDR r2, =STRING			; Load the String into R2
			ADR r4, EoS	-1			; Load the end of the string into r4
				
loopLetters	
PointR		LDRB r5, [r4], #-1		; Load r5 with the address of the end of the string pointer
			CMP r5, #0x00			; Check if the value is the end of the string
			BEQ palin				; if it is, it is a palindrome as all the values are equal ignoring the non chars, from left to right
			ORR r5, #0X20			; adds 20 hex value, puts in lower case
			CMP r5, #0X61			; Char lower bound
			BLT PointR				; Less than? not a letter: reset the loop
			CMP r5, #0X7A			; char upper bound
			BGT PointR				; Greater Than? not a letter: reset the loop
		
PointL		LDRB r3, [r2], #1		; load r3 with the address of the first character in the string
			ORR r3, #0x20			; adds 20 hex value, puts in lower case
			CMP r3, #0x61			; Char lower bound
			BLT PointL				; less than? not a letter: go back
			CMP r3, #0x7A			; char upper bound
			BGT	PointL				; Greater Than? not a letter: go back

			CMP r3,r5				; Compare the values for both registers and see if 
			BNE notPalin			; if not equal, then go to notPalin
			b loopLetters			; loop the letters until they're all checked

palin		LDR r0, =1				; Load the register r0 with 1
			b exit					; exit the program if it is a palindrome
notPalin	LDR r0, =2				; Load the register r0 with 2
exit		b exit					; Exit

STRING  	DCB "IN/TeeaeeT<<NI?"  ;STRING
EoS			DCB 0x00
			END