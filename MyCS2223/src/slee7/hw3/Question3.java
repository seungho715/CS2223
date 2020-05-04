package slee7.hw3;

/** 
 * Copy this class into your USERID.hw3 package and complete
 */
public class Question3 {
	
	public static void main(String[] args) throws java.io.IOException {

		// First Construct the Binary Search Tree from these Strings where
		// the associated value is the total number of times the key appeared
		// in "The Tale Of Two Cities".
		BST bt = new BST();

		for (int i = 1; i <= 45; i++) {
			TaleOfTwoCitiesExtractor ex = new TaleOfTwoCitiesExtractor(i);
			while (ex.hasNext()) {
				String word = ex.next();
				if (bt.contains(bt.root, word)) {
					int count = bt.get(word);
					bt.put(word, count + 1);

				} else {
					bt.put(word, 1);
				}
			}
		}

		System.out.println("Top ten most frequent words");
		for (int i = 0; i < 10; i++) {
			System.out.println(bt.mostFrequent() + " 	" + bt.get(bt.mostFrequent()));
			bt.delete(bt.mostFrequent());
		}

		System.out.println();

		System.out.println("Number of words that appear once");
		int n = bt.printUnique();
		System.out.println(n + " unique words.");

	}
	
}
