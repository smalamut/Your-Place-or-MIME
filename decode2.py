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
	count = 0;
	char1 = -1;
	char2 = -1;
	char3 = -1;
	char4 = -1;
	
	while True: #reading in everything "QUFB" from stdin
		inputBase64 = sys.stdin.readline() #reads 1 byte at a time as string: ABCDEFGH
		if inputBase64 == '': #if empty... break
			break
		#sys.stdout.write(inputBase64)
		print
		inputsize = len(inputBase64);
		
		#filter out
		for i in range(0, inputsize):
			if inputBase64[i] == "=": #filter out padding
				break
			elif inputBase64[i] == " ":
				continue
			else:
				inputClean = inputClean + inputBase64[i]

		inputsize = len(inputClean)
		print "input: ", inputClean

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
		
		print decimals
		decimals[i]/2
		while (decimals[i] mod 2) == 0:
			count = count + 1;
		0 20
		#decimal to binary
		for i in range(0, inputsize):
			sixbit[i] = decimals[i] #convert from integer --> binary string
			while len(sixbit[i]) < 6:
				print "less than 6"
				sixbit[i] = 0 + sixbit[i]




    	

	#if inputByte2 == '': #handle = case
		

	
	#if inputByte3 == '': #handle AA case
		

main()


