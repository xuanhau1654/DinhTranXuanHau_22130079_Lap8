package Task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextAnalyzer {
	// <word, its positions>
	private Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

	// load words in the text file given by fileName and store into map by using add
	// method in Task 2.1.
	// Using BufferedReader reffered in file TextFileUtils.java
	public void load(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		int index = 1;
		String line = null;
		while(true) {
			line = reader.readLine();
			if(line == null)
				break;
			StringTokenizer tokens = new StringTokenizer(line, " ");
			while(tokens.hasMoreTokens()) {
				String w = tokens.nextToken();
				if(!tokens.hasMoreTokens())
					add(w, -index);
				else
					add(w, index);
				index++;
			}
		}
		reader.close();

	}
	// In the following method, if the word is not in the map, then adding that word
	// to the map containing the position of the word in the file. If the word is
	// already in the map, then its word position is added to the list of word
	// positions for this word.
	// Remember to negate the word position if the word is at the end of a line in
	// the text file

	public void add(String word, int position) {
		if (map.containsKey(word)) {
			ArrayList<Integer> values = map.get(word);
			values.add(position);
		} else {
			ArrayList<Integer> values = new ArrayList<>();
			values.add(position);
			map.put(word, values);
		}
	}

	// This method should display the words of the text file along with the
	// positions of each word, one word per line, in alphabetical order
	public void displayWords() {
		TreeMap<String, ArrayList<Integer>> re = new TreeMap<>(new Comparator<String>() {
			public int compare(String c1, String c2) {
				return c1.compareToIgnoreCase(c2);
			}

		});
		re.putAll(this.map);
		//In ra từng từ và vị trí
		for (Map.Entry<String, ArrayList<Integer>> entry : re.entrySet()) {
	        System.out.print(entry.getKey() + ": ");
	        entry.getValue().forEach(pos -> System.out.print(pos + " "));
	        System.out.println();
	    }
	}

	// This method will display the content of the text file stored in the map
	public void displayText() {
		// Tạo một TreeMap để sắp xếp theo thứ tự chữ cái không phân biệt chữ hoa và chữ thường
	    TreeMap<String, ArrayList<Integer>> sortedMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	    // Đưa toàn bộ dữ liệu từ map vào sortedMap
	    sortedMap.putAll(map);

	    // In ra nội dung văn bản theo thứ tự vị trí xuất hiện đầu tiên của từ
	    sortedMap.entrySet().stream()
	            .sorted(Comparator.comparingInt(entry -> entry.getValue().get(0)))
	            .forEach(entry -> {
	                entry.getValue().forEach(pos -> System.out.print(pos + " "));
	                System.out.println(entry.getKey());
	            });
	}

	// This method will return the word that occurs most frequently in the text file
	public String mostFrequentWord() {
		// Tạo một biến để lưu trữ từ xuất hiện nhiều nhất
	    String mostFrequent = null;

	    // Tạo một biến để lưu trữ số lần xuất hiện nhiều nhất
	    int maxFrequency = 0;

	    // Duyệt qua các entry trong map
	    for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
	        // Lấy số lần xuất hiện của từ hiện tại
	        int currentFrequency = entry.getValue().size();

	        // So sánh với số lần xuất hiện nhiều nhất hiện tại
	        if (currentFrequency > maxFrequency) {
	            maxFrequency = currentFrequency;
	            mostFrequent = entry.getKey();
	        }
	    }
	    return mostFrequent;
	}


}
