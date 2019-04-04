package edu.smith.cs.csc212.p7;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.real.JavaList;

public class InsertionSort {

	public static ListADT<Integer> insertionSort(ListADT<Integer> input) {
		ListADT<Integer> newList = new JavaList<>();
		
		for(int i = 0; i < input.size(); i++) {
				
			Integer value = input.getIndex(i);
			
			if(i == 0) {
				newList.addFront(value);
			}
			
			else {
				int size = i;
				boolean done = false;
				
				while(!done && size > 0) {
					if(newList.getIndex(size-1) < value) {
						newList.addIndex(size, value);
						done = true;
					}
					size--;
				}
				if(!done) {
					newList.addFront(value);
				}
				
			}
		}
		return newList;
	}
	
}
