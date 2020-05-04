package slee7.hw2;
/**
 * Building from Question 1, there are different questions to answer.
 * 
 * 1. What is the most frequently used word in the entire book? 2. What are the
 * top-ten most frequently used words in the entire book? 3. How many words
 * occur exactly once in the book?
 */
public class Q2 {

	static void mostFrequent() throws java.io.IOException {
		// TODO CHANGE TO MAKE THIS WORK...

		WordSymbolTable wst = new WordSymbolTable();
		int topTen = -1;

		for (int currChapter = 1; currChapter <= 45; currChapter++) {
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(currChapter);

			while (extractor.hasNext()) {
				wst.increment(extractor.next());
			}
		}
		int tot = wst.totalCounts();
		double perNum = (double) (wst.count(wst.mostFrequent())) / tot;

		System.out
				.println(String.format("\"%s\" is the most frequent word, used %d times out of %d total words (%.2f%%)",
						wst.mostFrequent(), wst.count(wst.mostFrequent()), tot,
						perNum * 100));

		System.out.println("The Top Ten words by frequency are:");

		int i = 1;
		while (i <= 10) {
			System.out.println(String.format("%2d. %s", i, wst.mostFrequent(),
					wst.count(wst.mostFrequent())));
			topTen += wst.count(wst.mostFrequent());
			wst.remove(wst.mostFrequent());
			i++;

		}
		double topTenPer = (double) (topTen) / tot;
		System.out.println(
				String.format("These ten words represent %.2f%% of the total words in the book", topTenPer));

	}

	static void wordsUsedOnce() throws java.io.IOException {
		int numSingle = 0;
		int longest = 999;

		WordSymbolTable mostFrequentWST = new WordSymbolTable();
		for (int currChapter = 1; currChapter <= 45; currChapter++) {
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(currChapter);

			while (extractor.hasNext()) {
				mostFrequentWST.increment(extractor.next());
			}
		}

		for (int currChapter = 1; currChapter <= 45; currChapter++) {
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(currChapter);

			while (extractor.hasNext()) {
				if (mostFrequentWST.count(extractor.next()) == 1) {
					numSingle++;
				}
			}
		}

		System.out.println(String.format("%d words are used exactly once (longest is \"%s\")", numSingle, longest));
	}

	public static void main(String[] args) throws java.io.IOException {
		mostFrequent();
		wordsUsedOnce();
	}
}