package edu.smith.cs.csc212.adtr.real;

import edu.smith.cs.csc212.adtr.ListADT;
import edu.smith.cs.csc212.adtr.errors.BadIndexError;
import edu.smith.cs.csc212.adtr.errors.TODOErr;


public class DoublyLinkedList<T> extends ListADT<T> {
	private Node<T> start;
	private Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}
	

	@Override
	public T removeFront() {
		checkNotEmpty();
		
		
		
		Node<T> oldFront = this.start;
		
		 if(this.end == this.start) {
			this.start = null;
			this.end = null;
			return oldFront.value;
		}
		
		Node<T> newFront = this.start.after;
		this.start = newFront;
		
		return oldFront.value;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		
		Node<T> oldBack = this.end;
		
		if(this.end == this.start) {
			this.start = null;
			this.end = null;
			return oldBack.value;
		}
		
		Node<T> newBack = this.end.before;
		newBack.after = null;
		this.end = newBack;
		
		
		return oldBack.value;
		
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		
		if(index < 0) {
			throw new BadIndexError(index);
		}
		
		if(index == 0) {
			return removeFront();
		} 
		else {
		
			int count = 0;
			for(Node<T> node = this.start; node != null; node = node.after) {
				if(count++ == index) {
					Node<T> remove = node;
					Node<T> before = remove.before;
					Node<T> after = remove.after;
					
					before.after = after;
					if (after == null) {
						// at the end of the list:
						end = before;
					} else {
						after.before = before;
					}
					
					return node.value;
				}
			}
			throw new BadIndexError(index); 
		}
	}

	@Override
	public void addFront(T item) {
		if(start == null) {
			start = end = new Node<T>(item);
		} 
		else {
			Node<T> oldStart = start;
			start = new Node<T>(item);
			start.after = oldStart;
			oldStart.before = start;
		}
	}

	@Override
	public void addBack(T item) {
		if (end == null) {
			start = end = new Node<T>(item);
		} else {
			Node<T> secondLast = end;
			end = new Node<T>(item);
			end.before = secondLast;
			secondLast.after = end;
		}
	}

	@Override
	public void addIndex(int index, T item) {
		
		if(index < 0) {
			throw new BadIndexError(index);
		}
		
		if(index == 0) {
			this.addFront(item);
		}
		else {
			int count = 0;
			
			for(Node<T> node = this.start; node != null; node = node.after) {
				if(count ++ == index) {
					Node<T> three = node;
					Node<T> two = new Node<T>(item);
					Node<T> one = node.before;
					
					two.before = one;
					two.after = three;
					three.before = two;
					one.after = two;
					return;
				}
			}
			if(count == index) {
				this.addBack(item);
				return;
			}
			throw new BadIndexError(index);
		}
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return this.start.value;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return this.end.value;
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		
		if(index < 0) {
			throw new BadIndexError(index);
		}
		
		int count = 0;
		for(Node<T> node = this.start; node != null; node = node.after) {
			if(count++ == index) {
				return node.value;
			}
		}
		
		throw new BadIndexError(index);
	}
	
	public void setIndex(int index, T value) {
		checkNotEmpty();
		
		if(index < 0) {
			throw new BadIndexError(index);
		}
		
		if(index == 0) {
			this.removeFront();
			this.addFront(value);
		}
		else {
			int count = 0;
			for(Node<T> node = this.start; node != null; node = node.after) {
				if(count++ == index) {
					
					if(node == this.end) {
						this.removeBack();
						this.addBack(value);
						return;
					}
					
					Node<T> remove = node;
					Node<T> before = node.before;
					Node<T> after = node.after;
					
					Node<T> newGuy = new Node<T>(value);
					newGuy.before = before;
					newGuy.after = after;
					
					before.after = newGuy;
					after.before = newGuy;
					return;
				}
			}
			throw new BadIndexError(index);
		}
	}

	@Override
	public int size() {
		int count = 0;
		
		if(start == null) {
			return 0;
		}
		else {
			Node<T> node = this.start;
			while(node != null) {
				count += 1;
				node = node.after;
			}
			return count;
		}
	}

	@Override
	public boolean isEmpty() {
		if (start == null && end != null) {
			throw new AssertionError("end not updated");
		}
		if (start != null && end == null) {
			throw new AssertionError("start not updated");
		}
		if(start == null && end == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
