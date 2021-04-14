		AREA test, CODE, READWRITE
		ENTRY
		LDR r0, =STRING1 ; load register 0 with the entire string
		ADR r1, STRING2
		LDRB r4, [r0]
		
		str r4, [r1]
		

		
		
		
	
	
STRING1	DCB "and the man said they must go" ;String1
STRING2 space 0x7F 	

		END