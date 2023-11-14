package Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class TestReadFile {
	public static void main(String[] args) throws IOException {
		// Scanner input = new Scanner(new File("data/hamlet.txt"));
		Scanner input = new Scanner(new File("data/fit.txt"));

		while (input.hasNext()) {
			String word = input.next();
			System.out.println(word);
		}
		MyWordCountApp wordCountApp = new MyWordCountApp();
		wordCountApp.loadData();
		System.out.println("Number of unique tokens: " + wordCountApp.countUnique());
        System.out.println("Word counts:");
        wordCountApp.printWordCounts();
        System.out.println("Word counts in alphabetical order:");
        wordCountApp.printWordCountsAlphabet();
        TextAnalyzer textAnalyzer = new TextAnalyzer();
        textAnalyzer.load("data/short.txt");
        System.out.println("Words and their positions:");
        textAnalyzer.displayWords();
        System.out.println("\nText content displayed by position:");
        textAnalyzer.displayText();
        System.out.println("\nMost frequent word: " + textAnalyzer.mostFrequentWord());
    }
	
}
