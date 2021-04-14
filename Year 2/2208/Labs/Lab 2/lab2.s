		area program, code, readonly
		ENTRY
		LDR r0,=X1
loop 	B loop
		SPACE 4088
X1 		DCD 0x12345678
		END 