import java.lang.Object;
import java.lang.Number;
import java.lang.Integer;
import java.lang.Character;
import java.io.*;
import java.lang.*;
import java.io.FileInputStream;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.lang.Throwable;
import java.lang.Exception;
import java.io.IOException;


public class encode {
	

	private static String[] inputBytes;
	private static String[] eightbitgroups;
	private static String[] sixbitgroups;
	private static int[] decimals;
	private static char[] base64alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
	'P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g',
	'h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
	'w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'};

    private String output;

    //reads input and makes encoder object
	public static void main(String args[])  throws IOException
	{
		

		int reading = 0;
		boolean broke = false;
		
		//if (System.in.read() == -1)
			//return;

		do  {
			//System.out.println("do loop");
			inputBytes = new String[3]; //reading 3 bytes at once
			
			//System.out.println("input bytes at start = " + Arrays.toString(inputBytes));
			
			for (int i = 0; i < 3; i++) { //first 3 bytes
				reading = System.in.read();
				if (reading == -1)
					{ //	System.out.println("reading = -1");
						broke = true;
						break; }
				if (!broke) {
				inputBytes[i] = Integer.toBinaryString(reading);
				
				while (inputBytes[i].length() < 8)
					inputBytes[i] = "0" + inputBytes[i]; }

				//System.out.println("after converting " + inputBytes[i]);
			}
			

			if (broke && System.in.read() == -1) //not a multiple of 3
			{
				/*System.out.println("it broke in hereeeee!!!");
				System.out.println("and inputBytes: " + Arrays.toString(inputBytes));*/

				if (inputBytes[0] != null) {
				//	System.out.println("inputBytes not null");
				encode n = new encode();
				
				//System.out.println("and inputBytes: " + Arrays.toString(inputBytes));

				//System.out.println("Bytes after reading = " + Arrays.toString(n.inputBytes));
				//sixbitgroups = n.eightTosixbits(n.inputBytes));
				/*System.out.println("6bit groups = " + Arrays.toString(n.eightTosixbits(n.inputBytes)));
				System.out.println("decimals = " + Arrays.toString(n.decimalVal(n.sixbitgroups)));
				System.out.println("Final output = " + n.base64Digit(n.decimals));*/
				sixbitgroups = n.eightTosixbits(n.inputBytes);
				decimals = n.decimalVal(n.sixbitgroups);
				System.out.print(n.base64Digit(n.decimals));
				//n.base64Digit(n.decimals);
				}
				System.out.println();
				return;
			} 

			encode n = new encode();
			//System.out.println("Bytes after reading = " + Arrays.toString(n.inputBytes));
			//sixbitgroups = n.eightTosixbits(n.inputBytes));
			//System.out.println("6bit groups = " + Arrays.toString(n.eightTosixbits(n.inputBytes)));
			//System.out.println("decimals = " + Arrays.toString(n.decimalVal(n.sixbitgroups)));
			//System.out.println("Final output = " + n.base64Digit(n.decimals));
			
			sixbitgroups = n.eightTosixbits(n.inputBytes);
			decimals = n.decimalVal(n.sixbitgroups);
			System.out.print( n.base64Digit(n.decimals));
			//n.base64Digit(n.decimals);

			


		} while (reading != -1);


		System.out.println();
	}
	


	public void encode(){ //constructor
		

	}


	public String[] eightTosixbits (String s[]){ //concatenating into 4 6-bit groups

		int totalbits = (s.length * 8);
		//System.out.println("s*8 = " + totalbits);
		String allbits = "";
		boolean broke = false;
		sixbitgroups = new String[4];
	//	System.out.println("and s: " + Arrays.toString(s));


 
		for (int j = 0; j < s.length; j++) //concat everything into one string
		{
			//if (s[j] == null) break;
			allbits += s[j];
		}
			//System.out.println("allbits = " + allbits);
		//if (allbits.length() == 0) return s;
	
		for (int i = 0; i < totalbits; i += 6) //dividing into string of groups of 6bits
		{
			//System.out.println("i = " + (i));
			sixbitgroups[i/6] = "";
			for (int x = 0; x < 6; x++)
			{	

				//System.out.println("x = " + (x));
				//System.out.println("charAt.. " + x + allbits.charAt(x));
				if (allbits.charAt(x+i)== 'n') 
				{ //System.out.println("found a null char in allbits");
					while (sixbitgroups[i/6].length() < 6)
						sixbitgroups[i/6] = sixbitgroups[i/6] + "0"; //add 0's
					broke = true;
					break; }

				sixbitgroups[i/6] += allbits.charAt(x+i);

			}
			if (broke) {break;};
			//sixbitgroups[i/6] = allbits.substring(i , i+6); 
 			//System.out.println("sixbitgroups #" + (i/6) + " = " + sixbitgroups[i/6]);

 			//System.out.println("line 176");
		}
		//System.out.println("sixbitgroups in method= " + Arrays.toString(sixbitgroups));
		return sixbitgroups;

	}


	public int[] decimalVal (String[] s){ //finds decimal value of each 6bit group

		decimals = new int[s.length];
		//System.out.println("s.length " + s.length);
		//System.out.println("s " + Arrays.toString(s));

		for (int i = 0; i < s.length; i++) {
			if (s[i] == null) { 
				decimals[i] = -1;}
			else {
			decimals[i] = Integer.parseInt(s[i], 2); }
		//	System.out.println("decimals of " + i + decimals[i]);

		}


		return decimals;
	}


	public String base64Digit (int[] dec){ //each 6bit group translated into a single digit in the base64 alphabet
		
		String s = "";
		int result;

		for (int i = 0; i < dec.length; i++){
			//System.out.println(Integer.toString(dec[i]));
				if (dec[i] == -1)
				{
				//	System.out.println("in here line 196");
					s += "=";
					//break;
				}
				else
					s += base64alphabet[dec[i]];
			
			}

		return s;
	/*	for (int i = 0; i < s.length(); i++)
		{
		//	System.out.println("line 199");
			//System.out.println((int)s.charAt(i));
			System.out.write((int)s.charAt(i));

		}*/
		

}
}


