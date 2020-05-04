package slee7.hw2;

/**
 * Once you copy this file into your USERID.hw2 package, you must complete this implementation.
 * 
 * This class will be used by Question Q1 on Homework2.
 */
public class WordList {
	Node head = null;
	int size = 0;
	/** 
	 * Leave this Node class as is. While you don't need to make changes to this class,
	 * it is acceptable if you would like to add methods to this class.
	 */
	class Node {
		String     word;
		Node       next;

		Node(String w) {
			this.word = w;
		}
	}

	/**
	 * If the given element doesn't exist in the set then update 
	 * the set and return true; otherwise return false. This means that
	 * adding a duplicate element to a set must return false.
	 * 
	 * @param elt      element to be added.
	 */
	public boolean add(String elt) {
		// TODO

		if  (contains(elt)) {
			return false;
		}
		else if (head == null) {
			head = new Node(elt);
			size++;
			return true;
		}
		else {
			Node current = head;
			while (current.next!=null) {
				if (!current.word.contentEquals(elt)) {
					current = current.next;
				}
				else {
					if (current.word.contentEquals(elt)) {
						return false;
					}
				}
			}
			Node oldhead = head;
			head = new Node(elt);
			head.word = elt;
			head.next = oldhead;
			size++;
			return true;
		}
			

	}

	/** Returns the number of elements in the set. */ 
	public int size() {
		// TODO

		return size;
	}

	/**
	 * Returns true if the given element was in the set (and was removed) or 
	 * false if the given element did not belong to the set.
	 * @param elt      element to be removed.
	 */
	public boolean remove (String elt) {
		// TODO
		boolean result = false;
		if (head == null || head.word == "") {
			return false;
		}
		else {
			
			Node prev = null;
			Node after = head;
			
			if (after!=null && after.word.contentEquals(elt)) {
				head = after.next;
				size--;
				return true;
			}
			while (after!=null && !after.word.contentEquals(elt)) {
				prev = after;
				after = after.next;
			}
			if (after == null) {
				return false;
			}
			prev.next = after.next;
			size--;
			result = true;
		}
		return result;
	}

	/**
	 * Returns true if the element exists within the collection.
	 * @param elt      target element to be searched.
	 */
	public boolean contains(String elt) {
		// TODO

		String element;
		for (Node current = head; current != null; current = current.next) {
			element = current.word;
			if (element.contentEquals(elt)) {
				return true;
			}
		}
		return false;
	}

	/** For debugging, return comma-separated string of elements. */
	public String elements() {
		Node current = head;
		String result = "";
		// TODO
		if (current == null) {
			return result;
		}
		else {
			result = current.word;
			current = current.next;
			//for (Node x = head.next; x.next != null; x = x.next) {
			while(current!=null) {
				result += ", " + current.word;
				current = current.next;
			}
		}
		return result;
		}
	
	
	// you should not have to modify anything below. These are testing routines for you to check your work.
	// ----------------------------------------------------------------------------------------------------
	static void validate(Object o1, Object o2) {
		if (o1.equals(o2)) { return; }
		throw new RuntimeException(o1 + " doesn't equal " + o2);
	}

	// Once you have completed the implementation, you should be able to run this method and have
	// it complete without any runtime exceptions. While not an exhaustive test, this should be 
	// sufficient to help you uncover many of the boundary cases.
	public static void main(String[] args) {

		WordList wl = new WordList();
		validate(0, wl.size());
		validate("", wl.elements());           // empty word list must return ""
		validate(false, wl.contains("this"));
		validate(true, wl.add("test"));
		validate("test", wl.elements());       // no trailing or pre comma.
		validate(false, wl.contains("this"));
		validate(true, wl.contains("test"));

		validate(false, wl.add("test"));       // can't add twice
		validate(true, wl.remove("test"));     // can remove first element
		validate(false, wl.remove("test"));    // can't remove first empty
		validate(true, wl.add("test"));
		validate(true, wl.add("that"));
		validate(false, wl.remove("not"));
		validate(true, wl.remove("test"));
		validate("that", wl.elements());       // no trailing or pre comma.
		validate(true, wl.remove("that"));
	} 
}