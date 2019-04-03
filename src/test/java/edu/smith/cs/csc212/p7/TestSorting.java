package edu.smith.cs.csc212.p7;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.real.JavaList;

public class TestSorting {
	/**
	 * This is useful for testing whether sort algorithms work!
	 * @param items - the list of integers.
	 * @return true if it is sorted, false if not.
	 */
	private static boolean checkSorted(ListADT<Integer> items) {
		for (int i=0; i<items.size()-1; i++) {
			if (items.getIndex(i) > items.getIndex(i+1)) {
				System.err.println("items out of order: "+items.getIndex(i)+", "+items.getIndex(i+1) + " at index="+i);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Here's some data!
	 */
	private static int[] data = {9,8,7,6,5,4,3,2,1};
	
	@Test
	public void testBubbleSort() {
		// See if the data can be reversed:
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		int size = sortMe.size();
		
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		System.out.println("Bubble Sort: " + sortMe.toJava());
		
		Random rand = new Random(13);
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		sortMe.shuffle(rand);
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		Assert.assertEquals(sortMe.size(), size);
	}
	
	@Test
	public void testClassBubbleSort() {
		// See if the data can be reversed:
		ListADT<Integer> sortMe = new JavaList<>(Arrays.asList(35, 88, 11, 47, 14, 24, 41, 62, 27));
		
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testSelectionSort() {
		// See if the data can be reversed:
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		System.out.println("Selection Sort: " + sortMe.toJava());
				
		Random rand = new Random(13);
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		sortMe.shuffle(rand);
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testClassSelectionSort() {
		// See if the data can be reversed:
		ListADT<Integer> sortMe = new JavaList<>(Arrays.asList(35, 88, 11, 47, 14, 24, 41, 62, 27));
				
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testInsertionSort() {
		// See if the data can be reversed:
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
				
		ListADT<Integer> newList = InsertionSort.insertionSort(sortMe);
		Assert.assertTrue(checkSorted(newList));
		System.out.println("Insertion Sort: " + newList.toJava());
				
		Random rand = new Random(13);
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		sortMe.shuffle(rand);
		Assert.assertTrue(checkSorted(InsertionSort.insertionSort(sortMe)));
	}
	
	@Test
	public void testClassInsertionSort() {
		// See if the data can be reversed:
		ListADT<Integer> sortMe = new JavaList<>(Arrays.asList(35, 88, 11, 47, 14, 24, 41, 62, 27));
				
		Assert.assertTrue(checkSorted(InsertionSort.insertionSort(sortMe)));
	}
	
	@Test
	public void testCombineHelper() {
		
		ListADT<Integer> sortMe = new JavaList<>();
		for (int y : data) {
			sortMe.addBack(y);
		}
		
		ListADT<Integer> listOne = InsertionSort.insertionSort(sortMe);
		ListADT<Integer> listTwo = InsertionSort.insertionSort(sortMe);
		
		ListADT<Integer> newList = MergeSort.combineLists(listOne, listTwo);
		Assert.assertTrue(checkSorted(newList));
		System.out.println("Combine Helper: " + newList.toJava());
		
	}

}
