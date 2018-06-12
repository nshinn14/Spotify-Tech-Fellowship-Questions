/* 
 * Question 1 -- sortByStrings(s,t)
 * 
 * Written by Nicholas Shinn
 *
 */

/*
 * Sort the letters in the string s by the order they occur in the string t.
 * 
 * You can assume t will not have repetitive characters. For s = "weather" 
 * and t = "therapyw", the output should be sortByString(s, t) = "theeraw". 
 * For s = "good" and t = "odg", the output should be sortByString(s, t) = "oodg".
 */

import java.util.*;

public class StringSort{

	static String sortByStrings(String s, String t) {

		// Check for valid paramters 
		if (s == null || s.length() == 0 || t == null || t.length() == 0) {
			return null;
		}

		// Create string builder to create sorted String
		StringBuilder sortedString = new StringBuilder();

		// HashMap is used to store counts of the characters
		HashMap<Character, Integer> counts = new HashMap<Character, Integer>();

		// Store counts of each charater in s to hash map
		for (char c : s.toCharArray()) {
			if (counts.containsKey(c)) {
				counts.put(c, counts.get(c)+1);
			} else {
				counts.put(c, 1);
			}
		}

		// Create string sorted by order in t
		for (char c : t.toCharArray()) {
			if (counts.containsKey(c)) {
				for (int i = 0; i < counts.get(c); i++) {
					sortedString.append(c);
				}
			}
		}

		return sortedString.toString();
	}

	// Driver method to test simple examples
	public static void main(String[] args) {

		System.out.println(sortByStrings("weather", "therapyw"));
		System.out.println(sortByStrings("good", "odg"));
		System.out.println(sortByStrings(null, "notNull"));
		System.out.println(sortByStrings("notNull", null));

	}
}