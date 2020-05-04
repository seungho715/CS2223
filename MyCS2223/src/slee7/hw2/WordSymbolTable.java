package slee7.hw2;

/**
 * Once you copy this file into your USERID.hw2 package, you must complete this
 * implementation.
 * 
 * This class will be used by Question Q2 on Homework2.
 */
public class WordSymbolTable {

	Node head = null;
	int count = 0;
	int N;

	/**
	 * Leave this Node class as is. While you don't need to make changes to this
	 * class, it is acceptable if you would like to add methods to this class.
	 */
	class Node {
		String word;
		int count;
		Node next;

		Node(String w, int count) {
			this.word = w;
			this.count = count;
		}
	}

	/**
	 * Increase the count for given word.
	 * 
	 * Note: this might need to add the word in the head place.
	 * 
	 * Returns TRUE if the word was newly added, otherwise FALSE
	 * 
	 * @param elt element whose count has increased by 1.
	 */
	public boolean increment(String elt) {

		String searchWord;
		for (Node currNode = head; currNode != null; currNode = currNode.next) {
			searchWord = currNode.word;
			if (searchWord.equals(elt)) {
				currNode.count++;
				return false;
			}
		}
		if (head == null) {
			head = new Node(elt, 1);
			count = 1;
			return true;
		}
		Node tempHead = head;
		head = new Node(elt, 1);
		head.next = tempHead;
		count++;
		return true;
		}
	/**
	 * Decrease the count for given word.
	 * 
	 * Note: this might need to remove the word once the count reaches zero. Returns
	 * TRUE if the word was removed, otherwise FALSE
	 * 
	 * @param elt element whose count is to decrease by 1.
	 */
	public boolean decrement(String elt) {
		Node curr = head;

		for (Node newNode = curr; newNode.next != null; newNode = newNode.next) {

			if (newNode.word.contentEquals(elt)) {

				newNode = new Node(elt, newNode.count--);

				return true;

			}

		}
		return false;
	}

	/** Return number of words in the symbol table. */
	public int size() {
// TODO
		return count;
	}

	/** Return the accumulated counts of all words in the word table. */
	public int totalCounts() {
		int totalC = 0;
		Node temp = head;
		while (temp != null) {
			if (temp.word != "" || temp.word != null) {
				totalC += temp.count;
			}
			temp = temp.next;
		}
		return totalC;
	}

	/** Remove entire word from the word table. */
	public boolean remove(String elt) {
		boolean result = false;
		if (head == null || head.word == "") {
			return false;
		} else {
			Node prev = null;
			Node after = head;
			if (after != null && after.word.equals(elt)) {
				head.count = 0;
				head = after.next;
				return true;
			}
			while (after != null && !after.word.equals(elt)) {
				prev = after;
				after = after.next;
			}
			if (after == null) {
				return false;
			}
			after.count = 0;
			prev.next = after.next;
			result = true;
		}

		return result;
	}

	/**
	 * Returns true if word exists in the WordSymbolTable; false otherwise.
	 * 
	 * @param elt target element to seek.
	 */
	public boolean contains(String elt) {
		String searchWord;
		for (Node currNode = head; currNode != null; currNode = currNode.next) {
			searchWord = currNode.word;
			if (searchWord.equals(elt)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Returns the count for the word (or 0 if the word doesn't exist in the symbol
	 * table).
	 * 
	 * @param elt target element to seek.
	 */
	public int count(String elt) {
		Node n = head;

		while (n != null) {
			if (n.word.equals(elt)) {
				return n.count;
			}
			n = n.next;
		}
		return 0;
	}

	/**
	 * Return a word whose repetition count is equal to the maximum in the Symbol
	 * table.
	 * 
	 * Note that there may be multiple words that have the maximum count, so this
	 * method only needs to return one of them.
	 */
	public String mostFrequent() {
		Node n = head;
		String word = null;

		int max = -1;

		while (n != null) {
			if (max < n.count) {
				max = n.count;
				word = n.word;
			}
			n = n.next;
		}
		return word;
	}

	/** For debugging, return semicolon-separated string of (word,count) pairs. */
	public String elements() {
		StringBuffer str = new StringBuffer();
		Node n = head;
		while (n != null) {
			str.append("(" + n.word + "," + n.count + ")");
			n = n.next;

			if (n != null) {
				str.append(";");
			}
		}
		return str.toString();
	}

// you should not have to modify anything below. These are testing routines for you to check your work.
// ----------------------------------------------------------------------------------------------------
	static void validate(Object o1, Object o2) {
		if (o1.equals(o2)) {
			return;
		}
		throw new RuntimeException(o1 + " doesn't equal " + o2);
	}

// Once you have completed the implementation, you should be able to run this method and have
// it complete without any runtime exceptions. While not an exhaustive test, this should be 
// sufficient to help you uncover many of the boundary cases.
	public static void main(String[] args) {

		WordSymbolTable wl = new WordSymbolTable();
		validate(0, wl.size());
		validate("", wl.elements()); // empty word list must return ""
		validate(0, wl.count("nothing"));
		validate(false, wl.contains("this"));
		validate(true, wl.increment("test"));

		validate("(test,1)", wl.elements()); // no trailing or pre comma.
		validate(1, wl.count("test"));
		validate(false, wl.contains("this"));
		validate(true, wl.contains("test"));

		validate(1, wl.count("test"));
		validate(false, wl.increment("test")); // when add TWICE, false is returned
		validate(2, wl.totalCounts());
		validate(2, wl.count("test"));

		validate(true, wl.remove("test")); // can remove head element
		validate(0, wl.totalCounts());
		validate(false, wl.remove("test")); // can't remove head empty

		validate(true, wl.increment("test"));
		validate(1, wl.totalCounts());

		validate(true, wl.increment("that"));
		validate(2, wl.totalCounts());
		validate(1, wl.count("that"));
		validate(1, wl.count("test"));
		validate(false, wl.increment("that"));
		validate(3, wl.totalCounts());
		validate(2, wl.count("that"));
		validate(3, wl.totalCounts());
		validate(2, wl.size());

		validate(false, wl.remove("not"));
		validate(true, wl.remove("test"));
		validate(2, wl.totalCounts());

		validate("(that,2)", wl.elements()); // no trailing or pre comma.
		validate(true, wl.remove("that"));
	}
}