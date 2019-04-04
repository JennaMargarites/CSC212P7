package edu.smith.cs.csc212.p7;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.real.DoublyLinkedList;
import edu.smith.cs.csc212.adtr.real.JavaList;

public class MergeSort {

	public static ListADT<Integer> combineLists(ListADT<Integer> LHS, 
													ListADT<Integer> RHS){
		
		ListADT<Integer> combinedList = new JavaList<>();
		boolean done = false;
		
		while(!done) {

			if(LHS.size() == 0 || RHS.size() == 0) {
				if(LHS.size() == 0 && RHS.size() == 0) {
					done = true;
				}
				if(LHS.size() == 0 && RHS.size() != 0) {
					combinedList.addAll(RHS);
					done = true;
				}
				if(LHS.size() != 0 && RHS.size() == 0) {
					combinedList.addAll(LHS);
					done = true;
				}
			}
			else {
				Integer LHSfront = LHS.getFront();
				Integer RHSfront = RHS.getFront();
			
				if(LHSfront < RHSfront) {
					combinedList.addBack(LHSfront);
					LHS.removeFront();
				} else {
					combinedList.addBack(RHSfront);
					RHS.removeFront();
				}
			}
		}
		
		return combinedList;
	}
	
	public static ListADT<Integer> iterativeMergeSort(ListADT<Integer> input){
		
		int N = input.size();
		ListADT<ListADT<Integer>> ddList = new DoublyLinkedList<>();
		
		for(int i = 0; i < N; i++) {
			ListADT<Integer> tempList = new JavaList<>();
			tempList.addBack(input.getIndex(i));
			ddList.addBack(tempList);
		}
		
		while(ddList.getFront() != ddList.getBack()) {
			
			ListADT<Integer> LHS = ddList.getFront();
			ddList.removeFront();
			ListADT<Integer> RHS = ddList.getFront();
			ddList.removeFront();
			
			ListADT<Integer> newBoi = combineLists(LHS, RHS);
			
			ddList.addBack(newBoi);
		}
		
		ListADT<Integer> newList = ddList.getFront();
		
		return newList;
	}
	
	public static ListADT<Integer> recursiveMergeSort(ListADT<Integer> input){
		
		int N = input.size();
		
		if(N <= 1) {
			return input;
		}
		else {
		
			ListADT<Integer> LHS = recursiveMergeSort(input.slice(0, N/2));
			ListADT<Integer> RHS = recursiveMergeSort(input.slice((N/2), N));
		
			return combineLists(LHS, RHS);
		}
	}
}
