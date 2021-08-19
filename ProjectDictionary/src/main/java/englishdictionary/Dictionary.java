package englishdictionary;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Dictionary {

	static List<String> lowercase;
	static Set<String> Words = new HashSet<>();
	public static List<Character> alphabet = null;
	static Set<String> Dictionarywords = new HashSet<>();

	public static void loadDictionary() {
		String path = System.getProperty("user.dir") + File.separator + "EnglishWords" + File.separator
				+ "englishwords.txt";
		List<String> list;
		try {
			list = Files.readAllLines(new File(path).toPath(), Charset.defaultCharset());
			lowercase = list.stream().map(s -> s.toLowerCase()).filter(s -> s.chars().allMatch(Character::isLetter))
					.collect(Collectors.toList());
			System.out.println("Loading English Dictionary with" + lowercase.size() + " words");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isEnglishWord(String word) {
		if (lowercase.contains(word)) {
			return true;
		}
		return false;
	}

	public static List<Character> StringtoCharArray(String Input) {
		return Input.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
	}

	public static void generate(StringBuilder sb, int n) {

		if (n == sb.length()) {
			Words.add(sb.toString());
			return;
		}
		for (char letter : alphabet) {
			sb.setCharAt(n, letter);
			generate(sb, n + 1);
		}
	}

	public static void EnglishWordscreatedonInput() {
		StringBuilder sb = new StringBuilder();

		for (int length = 2; length <= alphabet.size(); length++) {
			sb.setLength(length);
			generate(sb, 0);
		}
	}

	public static Set<String> EnglishWordsonMatch() {
		for (String stock : Words) {
			if (isEnglishWord(stock)) {
				Dictionarywords.add(stock);
			}
		}
		return Dictionarywords;
	}

	public static Set<String> EnglishWordsonDemand(String input) {
		Set<String> matches = new HashSet<>();

		for (String word : lowercase) {
			Boolean VerifyDictionary = true;
			for (char chW : word.toCharArray()) {
				String w = Character.toString(chW);
				if (word.length() - word.replace(w, "").length() != input.length() - input.replace(w, "").length()) {
					VerifyDictionary = false;
					break;
				}
			}
			if (VerifyDictionary) {
				matches.add(word);
			}
		}
		return matches;
	}
}
