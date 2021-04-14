;--------------------------------------------------------------------------------
		AREA power, CODE, READWRITE
		ENTRY
Main 	ADR sp,stack      			; define the stack
		MOV r2, #x        			; prepare the parameter n
		MOV r0, #0x1				; prepare the default param r0
		MOV r1, #n					; prepare the parameter x
		BL Power					; Calculate the power of x
		ADR r1,Result     			; Get result var address
		STR r0,[r1]       			; Store the final calculation in the result variable
Loop 	B     Loop          		; infinite loop
;--------------------------------------------------------------------------------
		AREA power, CODE, READWRITE
Power 	STMEA sp!,{r0,r1,r2,fp,lr} 	; Push the general register, frame pointer register, and link register onto the stack
		MOV fp,sp         			; Set frame pointer for function call
		LDR r1,[fp,#-0x10] 			; Get the parameter n from stack
		
Base	CMP r1,#0        			; Base case - when exponent n is 0
		MOVEQ r1,#1        			; If the base case is true then set return value to 1
		BLEQ Return  	     		; Branch to the return section		
		TST r1,#1					; test for odd or even
		BEQ	Even					; Branch to even section
		BNE	Odd						; Otherwise, branch to odd section
		
Odd		SUB r1,#1					; Subtract 1 from exponent n	 	
		BL Power					; Recursive call to Power function with new n value
		LDR r1,[sp,#0]				; Load result value into r0 before popping it from stack ( x * power(x,n-1))
		MUL r0,r1,r2				; Calculate the result value ( x * power(x,n-1))
		STR r0,[fp,#-0x14]			; Push the value onto stack ( x * power(x,n-1))
		BL Return					; Branch to return section ( x * power(x,n-1))
		
Even	LSR r1,r1,#1				; Divide the exponent by 2
		STMEA sp!,{r0,r1,r2,fp,lr}	; store the stack in memory (y * y)
		ADD r11,#0x14				; change the frame pointer
		BL Power					; Recursive call to Power function with new n value
		LDR r1,[sp,#0]				; Load result value into r0 before popping it from stack
		MUL r0,r1,r2				; Calculate the result value
		STR r0,[fp,#-0x14]			; Push the value onto stack
		BL Return					; Branch to return section
		
Return 	MOV sp,fp         			; Collapse working spaces for function call
		LDMEA sp!,{r0,r1,r2,fp,pc} 	; Restore/reload initial settings of registers; return to caller
;--------------------------------------------------------------------------------
		AREA power, DATA, READWRITE
Result 	DCD 0x00        			; the final result
stack  	DCD 0x00        			; The initial stack position
n 		EQU 2						; Set the value for n
x 		EQU 2 						; set the value for x
;--------------------------------------------------------------------------------
		END