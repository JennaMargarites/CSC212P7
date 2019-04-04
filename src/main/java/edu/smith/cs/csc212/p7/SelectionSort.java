package edu.smith.cs.csc212.p7;

import edu.smith.cs.csc212.adtr.ListADT;

public class SelectionSort {

	public static void selectionSort(ListADT<Integer> input) {
		int N = input.size();
		int unsorted = N;
		
		while(unsorted != 0) {
			Integer min = input.getIndex((N-unsorted));
			int indexMin = (N-unsorted);
			for(int i = (N-unsorted); i < N; i++) {
				if(input.getIndex(i) < min) {
					min = input.getIndex(i);
					indexMin = i;
				}
			}
			input.swap(N-unsorted, indexMin);
			unsorted --;
		}
		
	}
}
