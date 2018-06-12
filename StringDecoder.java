/* 
 * Question 2 -- decodeString(s)
 * 
 * Written by Nicholas Shinn
 *
 */

/*
 * Given an encoded string, return its corresponding decoded string. 
 * The encoding rule is: k[encoded_string], where the encoded_string 
 * inside the square brackets is repeated exactly k times. Note: k is 
 * guaranteed to be a positive integer. 
 *
 * For s = "4[ab]", the output should be decodeString(s) = "abababab" 
 * For s = "2[b3[a]]", the output should be decodeString(s) = "baaabaaa"
 */

import java.util.*;

public class StringDecoder{

	static String decodeString(String s) {

		// Check for valid paramters 
		if (s == null || s.length() == 0) {
			return null;
		}

		// This stack keeps track of the last "k" value
		Stack<Integer> lastK = new Stack<Integer>();

		// This stack keeps track of the last strings
		Stack<String> decodedStrings = new Stack<String>();

		// Create new string to build decoded string
		StringBuilder res = new StringBuilder();

		// keep track of index
        int index = 0;
        while (index < s.length()) {
        	// If the current character is a number, then parse the number into the stack
            if (Character.isDigit(s.charAt(index))) {
                int count = 0;
                while (Character.isDigit(s.charAt(index))) {
                    count = (10*count)+(Character.getNumericValue(s.charAt(index++)));
                }
                lastK.push(count);
            }
            // If the character is an open bracket, then store the previous string in the stack
            else if (s.charAt(index) == '[') {
                decodedStrings.push(res.toString());
                res = new StringBuilder();
                index = index+1;
            }
            // If the character is a close bracket, then retrieve old decoded string
            else if (s.charAt(index) == ']') {
                StringBuilder temp = new StringBuilder(decodedStrings.pop());
              	// Retrieve count of last K value
                int currentK = lastK.pop();
                for (int i = 0; i < currentK; i++) {
                    temp.append(res);
                }
                res = temp;
                index = index+1;
            }
            // Else append the character to the result string
            else {
                res.append(s.charAt(index));
                index = index+1;
            }
        }

        return res.toString();
	}

	// Driver method to test simple examples
	public static void main(String[] args) {

		System.out.println(decodeString(""));
		System.out.println(decodeString("4[ab]"));
		System.out.println(decodeString("2[b3[a]]"));
		System.out.println(decodeString("10[a]"));
	
	}
}