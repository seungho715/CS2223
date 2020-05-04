package slee7.hw3;

import edu.princeton.cs.algs4.StdRandom;

/**
 * This is the template code for question 1.
 *
 * Be sure to Explain whether the empirical results support the proposition.
 *
 */
public class Question1 {
	public static void main(String[] args) {

		System.out.println("N" + "\t" + "MaxComp" + "\t" + "MaxExch");
		
		for (int i = 16; i <= 512; i = i*2) {
			int maxComp = 0;
			int maxExch = 0;
			Comparable [] array = new Comparable[i];
			
			for (int j = 0; j < 100; j++) {
				for (int k = 0; k < i; k++) {
					array[k] = StdRandom.uniform();
				}
				Heap.constructHeap(array);
				
				maxComp = Heap.numComp;
				maxExch = Heap.numExch;
				
			}
			System.out.println(i + "\t" + maxComp + "\t" + maxExch);
		}
	}
}
