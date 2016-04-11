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


public class decode {
	
	private static String[] eightbit;
	private static String[] sixbit;
	private static int[] decimals;
	private static char[] base64alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
	'P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g',
	'h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
	'w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'};


	public static void main(String args[])  throws IOException
	{
		
		Scanner s = new Scanner(System.in);
		String input = "";
		if (s.hasNext())
			input = s.nextLine();
		else
			return;
		
		//System.out.println("input = " + input);
		String sample = "";
		int baseSize = input.length();

		for (int i = 0; i < baseSize; i++)
		{
			//System.out.println("in here");
			if (input.charAt(i) == '='){
				baseSize = (baseSize - (input.length()-i));
				break;}
			else if (input.charAt(i) == '\0' || input.charAt(i) == ' ') {;}
			else {
				sample += input.charAt(i);
			}
		}
		/*System.out.println("baseSize " + baseSize);
		System.out.println("sample length " + sample.length());*/
		baseSize = sample.length();
		String allbits = "";


		decimals = new int[baseSize];
		sixbit = new String[baseSize];
		
		
		
		int checking = 0;
		for (int i = 0; i < baseSize; i++) //looking up in Base64 Alphabet
		{
			for (int j = 0; j < 64; j++)
				if (base64alphabet[j] == sample.charAt(i))
					{
						decimals[i] = j;
						checking++;
					}

		}
		
		if (checking < sample.length()) {return;}

		//System.out.println("decimals = " + Arrays.toString(decimals));
		
		for (int i = 0; i < baseSize; i++) //decimal to binary string
		{
			sixbit[i] = Integer.toBinaryString(decimals[i]);
			while (sixbit[i].length() < 6)
					sixbit[i] = "0" + sixbit[i];
		}
		
		//System.out.println("sixbits = " + Arrays.toString(sixbit));
		
		for (int i = 0; i < baseSize; i++) //combine into one string
		{
			allbits += sixbit[i];
		}
		//allbits += " ";
		int num8bits = allbits.length()/8;
		eightbit = new String[num8bits];

		/*System.out.println("allbits  = " + allbits);
		System.out.println("allbits length = " + allbits.length());
		System.out.println("num8bits = " + num8bits);*/

		for (int i = 0; i < (num8bits*8); i += 8) //split into 8bits
		{
			///System.out.println(i + " substring = " + allbits.substring(i, i+8));
			eightbit[i/8] = allbits.substring(i, i+8);
		}
		//System.out.println("eightbit = " + Arrays.toString(eightbit));

		for (int i = 0; i < (allbits.length()/8); i++) //split into 8bits
		{
			System.out.print((char)Integer.parseInt(eightbit[i], 2));
			
		}
		
		System.out.println();

		s.close();
	}


}




