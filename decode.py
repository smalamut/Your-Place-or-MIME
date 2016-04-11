#!/usr/bin/python

import fileinput, sys, string

eightbit = {};
sixbit = {};
decimals = {};
output = {};
base64alphabet = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
	'P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g',
	'h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
	'w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'];

def main():
	inputBase64 = -1;
	inputClean = "";
	inputsize = -1;
	inputByte1 = -1;
	inputByte2 = -1;
	inputByte3 = -1;
	count = 0;
	char1 = -1;
	char2 = -1;
	char3 = -1;
	char4 = -1;
	
	while True: #reading in everything "QUFB" from stdin
		inputBase64 = sys.stdin.readline() #reads 1 byte at a time as string: ABCDEFGH
		if inputBase64 == '': #if empty... break
			break
		inputsize = len(inputBase64);
		
		#filter out
		for i in range(0, inputsize):
			if inputBase64[i] == "=": #filter out padding
				break
			elif inputBase64[i] == " ":
				continue
			else:
				inputClean = inputClean + inputBase64[i]

		inputsize = len(inputClean);
		#print "input: ", inputClean
		#print "size: ", inputsize
		#find decimal value in b64 Alphabet by for loop
		for i in range(0, inputsize):
			for j in range(0, 64):
				#print inputBase64[i]
				if(inputBase64[i] == base64alphabet[j]):
					#print "match", i, j
					decimals[i] = j
					#print "decimals[i]: ", decimals[i]
				#else: fix THIS
					#print "not found"
					#return
		#print decimals
		#print len(decimals)
		
		#handles AAA case
		if (len(decimals) == 4):
			char1 = decimals[0];
			char2 = decimals[1];
			char3 = decimals[2];
			char4 = decimals[3];	
			inputByte1 = ( ((char1 << 2) & 252) | ((char2 >> 4) & 3)) #ABCDEF00 | 000000GH
			inputByte2 = ( ((char2 << 4) & 240) | ((char3 >> 2) & 15)) #ABCD0000 | 0000EFGH
			inputByte3 = ( ((char3 << 6) & 192) | (char4 & 63)) #AB000000 | 00CDEFGH
			sys.stdout.write(chr(inputByte1))
			sys.stdout.write(chr(inputByte2))
			sys.stdout.write(chr(inputByte3))
			#print str(inputByte1)
			#print str(inputByte2)
			#print inputByte3
			print


		#handles AA case
		if (len(decimals) == 3):
			char1 = decimals[0];
			char2 = decimals[1];
			char3 = decimals[2];
			inputByte1 = ( ((char1 << 2) & 252) | ((char2 >> 4) & 3)) #ABCDEF00 | 000000GH
			inputByte2 = ( ((char2 << 4) & 240) | ((char3 >> 2) & 15)) #ABCD0000 | 0000EFGH
			sys.stdout.write(chr(inputByte1))
			sys.stdout.write(chr(inputByte2))
			print

		#handles A case
		if (len(decimals) == 2):
			char1 = decimals[0];
			char2 = decimals[1];
			inputByte1 = ( ((char1 << 2) & 252) | ((char2 >> 4) & 3)) #ABCDEF00 | 000000GH
			sys.stdout.write(chr(inputByte1))
			print


main()


