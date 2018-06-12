/* 
 * Question 2 -- changePossibilities(amount,denominations)
 * 
 * Written by Nicholas Shinn
 *
 */

/*
 * Your quirky boss collects rare, old coins. They found out you're a programmer 
 * and asked you to solve something they've been wondering for a long time. 
 *
 * Write a function that, given an amount of money and an array of coin 
 * denominations, computes the number of ways to make the amount of money 
 * with coins of the available denominations. 
 *
 * Example: for amount=4 (4 cents) and denominations=[1,2,3] 
 * (1 cent, 2 cent and 3 cent), 
 * your program would output 4: the number of ways to make 4 with those 
 * denominations: 
 *
 * 1, 1, 1, 1
 * 1, 1, 2
 * 1, 3
 * 2, 2
 */

import java.util.*;

public class CoinPermuter{

	static int changePossibilities(int amount, int[] denominations) {

		// Check for valid paramters 
		if (denominations == null || denominations.length == 0) {
			return -1;
		}

		// 2D array int[i][j] that stores number of ways that i coints makes 
		// up the amount j
		int[][] amountValues = new int[denominations.length+1][amount+1];
        amountValues[0][0] = 1;
        
        // There is only 1 way to make up amount 0
        for (int i = 1; i <= denominations.length; i++) amountValues[i][0] = 1;

        // Bottom-up solution to find target amount
        // Traverse through denominations
        for (int i = 1; i <= denominations.length; i++) {
        	// Traverse towards target amount starting from 1
            for (int j = 1; j <= amount; j++) {
            	// Add value above
                amountValues[i][j] += amountValues[i-1][j];
                // Add the value coins[i-1] steps back if possible
                if (j >= denominations[i-1]) {
                	amountValues[i][j] += amountValues[i][j-denominations[i-1]];
                }
            }
        }

        // Final result stored at end of 2D array
        return amountValues[denominations.length][amount];
	}

	// Driver method to test simple examples
	public static void main(String[] args) {

		System.out.println(changePossibilities(4, null));
		System.out.println(changePossibilities(4, new int[]{1,2,3}));
		System.out.println(changePossibilities(5, new int[]{1,2,3}));

	}
}