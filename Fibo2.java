package algoTP1;

import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

//A REMPLIR OBLIGATOIREMENT

//Nom : Hadjazi
//Prénom: Mohammed Hisham
//Spécialité: RSSI         Groupe: 01

//Nom : Amuer
//Prénom: Wassim Malik
//Spécialité: RSSI         Groupe: 01

public class Fibo2 {
	static long cpt1 = 0;
	static long cpt2 = 0;
	static long cpt3 = 0;
// Creating cache array to store values inside it. with first 2 values initialized to 1.
	private static final BigInteger[] arrayCache = new BigInteger[100000];
	static {
		arrayCache[0] = BigInteger.ONE;
		arrayCache[1] = BigInteger.ONE;
	}
// Hash Table For Memorization technique
	private static Map<Integer, BigInteger> memoizeTable = new HashMap<>();

// Declaring starting matrecies	
	static BigInteger[] matrix1 = { BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO };

	static BigInteger[] matrix2 = { BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE };

	public static void main(String[] args) {
//le main ne doit contenir que les appels des méthodes, l'affichage des résultats et des temps d'exécution
//le code des méthodes est développé à l'extérieur du main
		long startTime, endTime;
		float res;

		Scanner NomduScanner = new Scanner(System.in);
		System.out.print("Donner la valeur de n:");
		int n = NomduScanner.nextInt();

//      to verify if we get a negative input 

		if (n < 0)
			throw new IllegalArgumentException("Number must be a positive integer");

		System.out.println("*****Résultat avec 2 appels récursifs*****:");

		startTime = System.nanoTime();
		System.out.println("F(" + n + ") = " + methode4(n));
		endTime = System.nanoTime();
		res = (float) (endTime - startTime) / 1000000;
		System.out.println("Temps du calcul :" + res + "  ms");
		System.out.print("  Nombre de tests = " + cpt1);

		System.out.print("\n");
		System.out.println("  ");

		System.out.println("*****Résultat avec récursivité terminale*****:");

		startTime = System.nanoTime();
		System.out.println("F(" + n + ") = " + methode5(n, BigInteger.ONE, BigInteger.ONE));
		endTime = System.nanoTime();
		res = (float) (endTime - startTime) / 1000000;
		System.out.println("Temps du calcul :" + res + "  ms");
		System.out.print("  Nombre de tests = " + cpt2);

		System.out.print("\n");
		System.out.println("  ");

		System.out.println("*****Résultat avec une approche récursive matricielle *****:");
		startTime = System.nanoTime();
		System.out.println("F(" + n + ") = " + methode6(matrix1, matrix2, n)[1]);
		endTime = System.nanoTime();
		res = (float) (endTime - startTime) / 1000000;
		System.out.println("Temps du calcul :" + res + "  ms");
		System.out.print("  Nombre de tests = " + cpt3);

		System.out.print("\n");
		System.out.println("  ");

	} // fin du main

// Method 4
// Standard Recursive Fibonacci function

//	public static BigInteger methode4(int n) {
//		cpt1++;
//		if (n == 0)
//			return BigInteger.ZERO;
//		if (n == 1)
//			return BigInteger.ONE;
//		return methode4(n - 1).add(methode4(n - 2));
//
//	}

// Method 4.1
//To improve recursion performance we will implement array memorization technique

	public static BigInteger methode4(int n) {
		cpt1++;
		BigInteger result = BigInteger.ZERO;
		
		if (n == 0) 
			return BigInteger.ZERO;
	    if (n == 1)
			return BigInteger.ONE;

	    if (arrayCache[(int) n] != null) {
			return arrayCache[(int) n];
		} else {
			
			result = methode4(n - 1).add(methode4(n - 2));
			arrayCache[(int) n] = result;
			return result;
		}
	}

// // Method 4.2	
// Recursion with memorization technique using hashmaps in java

//    public static BigInteger methode4(int n){
//    	cpt1++;
//		if (n == 0) 
//			return BigInteger.ZERO;
//	    if (n == 1)
//			return BigInteger.ONE;
// 
//        if( memoizeTable.containsKey(n) ) 
//        {
//         
//            return memoizeTable.get(n);
//        }
// 
//        BigInteger result = methode4(n-1).add(methode4(n-2));
//        
//        memoizeTable.put(n, result);
// 
//        return result;
// 
//    }

//Method 5

	static BigInteger methode5(int n, BigInteger f0, BigInteger f1) {
		cpt2++;
		if (n == 0)
			return BigInteger.ZERO;
		if (n == 1 || n == 2) {
			return f0;
		}
		return methode5(n - 1, f0.add(f1), f0);
	}

// Method 6

	// Calculates the power of a matrix.
	private static BigInteger[] methode6(BigInteger[] matrix1, BigInteger[] matrix2, int n) {
		cpt3++;
		if (n == 0)
			return matrix2;
		if (n % 2 != 0) {
			return methode6(matrixMultiply(matrix1, matrix1), matrixMultiply(matrix2, matrix1), n / 2);
		} else {
			return methode6(matrixMultiply(matrix1, matrix1), matrix2, n / 2);
		}
	}

	// Multiplies 2 matrices.
	private static BigInteger[] matrixMultiply(BigInteger[] x, BigInteger[] y) {

		return new BigInteger[] { multiply(x[0], y[0]).add(multiply(x[1], y[2])),
				multiply(x[0], y[1]).add(multiply(x[1], y[3])), 
				multiply(x[2], y[0]).add(multiply(x[3], y[2])),
				multiply(x[2], y[1]).add(multiply(x[3], y[3])) };
	}

	// Multiplies two BigIntegers.
	private static BigInteger multiply(BigInteger x, BigInteger y) {
		return x.multiply(y);
	}

}

// fin de la classe
