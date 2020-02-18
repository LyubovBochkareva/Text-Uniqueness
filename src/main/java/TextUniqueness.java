import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TextUniqueness {
	private static final String STOP_SYMBOLS[] = {".", ",", "!", "?", ":", ";", "-", "\\", "/", "*", "(", ")", "+", "@",
			"#", "$", "%", "^", "&", "=", "'", "\"", "[", "]", "{", "}", "|"};
	private static final String STOP_WORDS_RU[] = {"это", "как", "так", "и", "в", "над", "к", "до", "не", "на", "но", "за",
			"то", "с", "ли", "а", "во", "от", "со", "для", "о", "же", "ну", "вы",
			"бы", "что", "кто", "он", "она", "у", "г"};

	/**
	 * Returns string object without STOP_SYMBOLS and STOP_WORDS_RU.
	 *
	 * @param text source text
	 * @return modified text
	 */
	private String canonize(String text) {
		for (String stopSymbol : STOP_SYMBOLS) {
			text = text.replace(stopSymbol, "");
		}

		for (String stopWord : STOP_WORDS_RU) {
			text = text.replace(" " + stopWord + " ", " ");
		}

		return text;
	}

	/**
	 * Returns % text uniqueness.
	 *
	 * @param textShingles1New first source text
	 * @param textShingles2New second source text
	 * @return % text uniqueness
	 */
	public double compare(String textShingles1New, String textShingles2New) {

		if (textShingles1New == null || textShingles2New == null) {
			return 0;
		}

		String str1 = canonize(textShingles1New.toLowerCase());
		String str2 = canonize(textShingles2New.toLowerCase());
		String[] words = str1.split(" ");
		String[] words2 = str2.split(" ");
		Collection coincidencesArrays = getCoincidences(words, words2);
		Set<String> unicArray = unicArray(words, words2);
		int textShingles1Number = coincidencesArrays.size();
		int textShingles2Number = unicArray.size();
		return (double) textShingles1Number / textShingles2Number * 100;
	}

	/**
	 * Returns intersection of two arrays.
	 *
	 * @param oneArray first source text
	 * @param twoArray second source text
	 * @return intersection of two arrays
	 */
	public Collection getCoincidences(String[] oneArray, String[] twoArray) {
		Collection<String> listOne = new ArrayList<>(Arrays.asList(oneArray));
		Collection<String> listTwo = new ArrayList<>(Arrays.asList(twoArray));
		listOne.retainAll(listTwo);
		return listOne;
	}

	/**
	 * Returns concatenated text from unical words.
	 *
	 * @param oneArray first source text
	 * @param twoArray second source text
	 * @return concatenated text from unical words
	 */
	public Set<String> unicArray(String[] oneArray, String[] twoArray) {
		Set<String> result = new HashSet<>();
		result.addAll(Arrays.asList(oneArray));
		result.addAll(Arrays.asList(twoArray));
		return result;
	}

	public static void main(String[] args) {
		TextUniqueness solution = new TextUniqueness();
		String text1 = "";
		String text2 = "";
		double result = solution.compare(text1, text2);
		System.out.println(result);
	}
}
