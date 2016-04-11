
#!/usr/bin/python

import fileinput, sys, string

eightbitgroups = {};
sixbitgroups = {};
decimals = {};
output = {};
base64alphabet = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
	'P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g',
	'h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
	'w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'];

def main():
	inputByte1 = -1;
	inputByte2 = -1;
	inputByte3 = -1;
	char1 = -1;
	char2 = -1;
	char3 = -1;
	char4 = -1;
	
	while True: #reading in from stdin
		inputByte1 = sys.stdin.read(1) #reads 1 byte at a time: ABCDEFGH
		if inputByte1 == '': #if empty... break
			break
		#sys.stdout.write(inputByte1)
		
		inputByte2 = sys.stdin.read(1) #reads 1 byte at a time
		if inputByte2 == '': #if empty... break
			break

		inputByte3 = sys.stdin.read(1) #reads 1 byte at a time
		if inputByte3 == '': #if empty... break
			break
		
		#handle AAA case
		char1 = (ord(inputByte1) >> 2) & 63 #ABCDEF
		char2 = (ord(inputByte1) & 3) << 4 | ((ord(inputByte2) >> 4) & 15) #GH0000 | 0000ABCD
		char3 = (ord(inputByte2) & 15) << 2| ((ord(inputByte3) >> 6) & 3) #EFGH00 | 0000AB
		char4 = (ord(inputByte3) & 63)  #CDEFGH
		sys.stdout.write(base64alphabet[char1])
		sys.stdout.write(base64alphabet[char2])
		sys.stdout.write(base64alphabet[char3])
		sys.stdout.write(base64alphabet[char4])
		

    	

	if inputByte2 == '': #handle A case
		char1 = (ord(inputByte1) >> 2) & 63 #ABCDEF
		char2 = (ord(inputByte1) & 3) << 4 #GH0000
		sys.stdout.write(base64alphabet[char1])
		sys.stdout.write(base64alphabet[char2])
		sys.stdout.write("=")
		sys.stdout.write("=")
		

	
	if inputByte3 == '': #handle AA case
		char1 = (ord(inputByte1) >> 2) & 63 #00ABCDEF
		char2 = (ord(inputByte1) & 3) << 4 | ((ord(inputByte2) >> 4) & 15) #GH0000 | 0000ABCD
		char3 = (ord(inputByte2) & 15) << 2 
		sys.stdout.write(base64alphabet[char1])
		sys.stdout.write(base64alphabet[char2])
		sys.stdout.write(base64alphabet[char3])
		sys.stdout.write("=")
		
	print
main()


