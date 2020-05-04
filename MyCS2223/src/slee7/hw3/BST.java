package slee7.hw3;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

/**
 * Minimum implementation of Binary Search Tree (BST) as a Symbol Table<String,
 * Integer>
 *
 * You need to copy this class into your USERID.hw3 and add methods to the end
 * of this class.
 */
public class BST {

	Node root; // root of the tree

	class Node {
		String key;
		Integer count;
		Node left, right; // left and right subtrees
		int N; // number of nodes in subtree

		public Node(String key, int ct, int N) {
			this.key = key;
			this.count = ct;
			this.N = N;
		}

		public String toString() {
			return "[" + key + "]";
		}

	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/** Return number of key-value pairs in ST. */
	public int size() {
		return size(root);
	}

	// Helper method that deals with "empty nodes"
	private int size(Node node) {
		if (node == null)
			return 0;

		return node.N;
	}

	/** Search for key in BST. */
	public Integer get(String key) {
		return get(root, key);
	}

	/** Helper method to search for key in BST rooted at parent. */
	private Integer get(Node parent, String key) {
		if (parent == null)
			return null;

		int cmp = key.compareTo(parent.key);

		if (cmp < 0)
			return get(parent.left, key);
		else if (cmp > 0)
			return get(parent.right, key);
		else
			return parent.count;
	}

	/** Invoke put on parent, should it exist. */
	public void put(String key, Integer val) {
		root = put(root, key, val);
	}

	/** Helper method to put (key, ct) pair into BST rooted at parent. */
	private Node put(Node parent, String key, Integer ct) {
		if (parent == null)
			return new Node(key, ct, 1);

		int cmp = key.compareTo(parent.key);
		if (cmp < 0)
			parent.left = put(parent.left, key, ct);
		else if (cmp > 0)
			parent.right = put(parent.right, key, ct);
		else
			parent.count = ct;

		parent.N = 1 + size(parent.left) + size(parent.right);
		return parent;
	}

	// traversal ideas
	// invoke an inorder traversal of the tree
	public void inorder() {
		inorder(root);
	}

	private void inorder(Node n) {
		if (n == null) {
			return;
		}

		inorder(n.left);
		StdOut.println(n.key);
		inorder(n.right);
	}

	/**
	 * Removes the specified key and its associated value from this symbol table (if
	 * the key is in this symbol table).
	 *
	 * @param key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(String key) {
		if (key == null)
			throw new IllegalArgumentException("calls delete() with a null key");
		root = delete(root, key);
	}

	/** Taken from Sedgewick algo. */
	private Node delete(Node x, String key) {
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		return x;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	/**
	 * Removes the smallest key and associated value from the symbol table.
	 *
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public void deleteMin() {
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		return x;
	}

	// ------------------------------------------------------------------------------------------------
	// YOU WILL ADD METHODS BELOW. THERE IS NO NEED TO MODIFY CODE ABOVE.
	// ------------------------------------------------------------------------------------------------

	/**
	 * Returns the count of nodes at a given depth. Key is depth, Value is count.
	 * 
	 * For the following tree
	 * 
	 * G -- depth is 0 / \ D H -- depth is 1 \ F -- depth is 2
	 * 
	 * The returned hash table should be { (0, 1), (1, 2), (2, 1) } where each (key,
	 * value) is (depth, count).
	 * 
	 * Note: you will need a helper method, much like you have seen in other
	 * recursive methods.
	 * 
	 * Returns a edu.princeton.cs.algs4.SeparateChainingHashST object representing
	 * the symbol table.
	 */

	SeparateChainingHashST<Integer, Integer> htOne = new SeparateChainingHashST<Integer, Integer>();
	SeparateChainingHashST<String, Integer> htTwo = new SeparateChainingHashST<String, Integer>();

	public SeparateChainingHashST<Integer, Integer> collect() {
		// TODO

    	collect(root,0);
    	return htOne;
	}
	
	//Helper function
	public void collect(Node n, int depth) {
		int count = 0;
		if (n == null) {
			return;
		} else {
			if (htOne.contains(depth)) {
				count = htOne.get(depth);
			}
			count++;
			htOne.put(depth, count);
			depth++;
			collect(n.left, depth);
			collect(n.right, depth);
		}
	}
	/**
	 * Returns the height of this binary tree.
	 */
	public int height() {
		// TODO
		return height(root);

	}

	/**
	 * Returns the height of a given node.
	 * 
	 * For the following tree
	 * 
	 * G -- height of G is 2 / \ D H -- height of D is 1, height of H is 0 \ F --
	 * height of F is 0
	 */
	
	public int height(Node n) {
		// TODO
		if (n == null) {
			return -1;
		} else {
			return 1 + Math.max(height(n.left), height(n.right));
		}
	}

	/**
	 * Return the key whose count is the greatest (that is, has the most occurrences
	 * in the BST).
	 * 
	 */
	public String mostFrequent() {
		// TODO
		if (root == null) {
			return null;
		}
		String mostFrequent = root.key;
		Node left = mostFrequent(root.left, root);
		Node right = mostFrequent(root.right, root);

		if (left.count > right.count) {
			mostFrequent = left.key;
		} else {
			mostFrequent = right.key;
		}
		return mostFrequent;
	}

	public Node mostFrequent(Node n, Node max) {
		if (n == null) {
			return max;
		}
		if (n.count > max.count) {
			max = n;
		}

		Node left = mostFrequent(n.left, max);
		Node right = mostFrequent(n.right, max);

		if (left.count > right.count) {
			return left;
		} else {
			return right;
		}

	}

	/**
	 * Print in ascending order the keys whose count is 1 (that is, only occur once)
	 * and return total.
	 */
	
	int num = 0;
	public int printUnique() {
		// TODO
		helpPrintUnique(root);
		return num;
	}

	public void helpPrintUnique(Node n) {
		
		
		if (n == null) {
			return;
		}
		if (n.count == 1) {
			
			System.out.println(n.key);
			num++;
		}
		
			helpPrintUnique(n.left);
			helpPrintUnique(n.right);
	}
	
	
	
	boolean contains(Node n, String word) {
		if (n == null) {
			return false;
		}
		if (n.key.equals(word)) {
			return true;
		}
		return contains(n.left,word) || contains(n.right,word);
	}
	
	void increment(Node n, String key) {
		if (n == null) {
			return;
		}
		if (n.key.contentEquals(key)) {
			n.count++;
		}
		else {
			increment(n.left,key);
			increment(n.right,key);
		}
	}
	

}