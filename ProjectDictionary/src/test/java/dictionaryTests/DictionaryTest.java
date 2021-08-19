package dictionaryTests;

import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

import englishdictionary.Dictionary;

public class DictionaryTest {

	static String inputString = null;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void CollectInputandLoadDictionary() {
		System.out.println("Write Input String in the console and hit enter");
		Scanner scanner = new Scanner(System.in);
		StringBuffer sb = new StringBuffer(scanner.nextLine());
		inputString = sb.toString().toLowerCase();

		Dictionary.loadDictionary();

	}

	@Test
	public void DictionarySearch() {
		Dictionary.alphabet = Dictionary.StringtoCharArray(inputString);
		Dictionary.EnglishWordscreatedonInput();
		System.out.println("word to word" + Dictionary.EnglishWordsonMatch());
	}

	@Test
	public void DictionarySearchonDemand() {
		System.out.println("On Demand" + Dictionary.EnglishWordsonDemand(inputString));
	}
}
