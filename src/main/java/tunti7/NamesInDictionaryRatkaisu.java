package tunti7;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wordplay.io.DictionaryReader;
import wordplay.io.NamesReader;

/**
 * NOTE: This class needs this repository https://github.com/ohjelmointi2/wordplay-exercise 
 * to actually work and run 
 */
public class NamesInDictionaryRatkaisu {

	private static List<String> finnishNames = NamesReader.readFirstNames();
    private static List<String> finnishWords = DictionaryReader.readFinnishWords();

    public static void main(String[] args) {
        /*
         * Implement a program that prints all Finnish names that are also
         * found in the Finnish dictionary.
         *
         * You can use the finnishNames and finnishWords variables defined above
         * as the data set you need to process. Notice that the words are in lower case
         * but the names are not. The names can contain duplicates, as there are several
         * unisex names in the data set.
         * 
         * Try to make your program as efficient as possible. A good goal is to have
         * the program run in tenths of a second.
         * 
         * NOTE: In this example implementation we have added the additional isAloneInDictionary
         * functionality to present some algorithmic concepts, it's not really needed according
         * to the task assignment.
         *
         */
    	
        // Create a HashSet for efficient lookup
        Set<String> dictionaryWords = new HashSet<>(finnishWords); //O(m)
        
        List<String> sortedFinnishWords = sortByLengthAscending(finnishWords);

        Set<String> processedNames = new HashSet<>(); // To store processed names

        int count = 0;
        
        // Capture start timestamp
        Instant startTime = Instant.now();
        // Iterate through Finnish names
        for (String name : finnishNames) { //O(n)
            // Convert name to lowercase for case-insensitive comparison
            String lowercaseName = name.toLowerCase();

            // Check if the lowercase name is in the dictionary
            //if (finnishWords.contains(lowercaseName)) { //O(m)
            if (dictionaryWords.contains(lowercaseName)) { //O(1)
            	if (!processedNames.contains(lowercaseName)) { // O(1) check that no duplicates 
                    // Check also if the name is not part of any longer word
                    if (isAloneInDictionary(lowercaseName, /*finnishWords*/ sortedFinnishWords)) {
	                    System.out.println(name);
	                    count++;
	                    processedNames.add(lowercaseName); //O(h)
                    }
                }
            }
        }
        
        // Capture end timestamp
        Instant endTime = Instant.now();

        // Calculate duration
        Duration duration = Duration.between(startTime, endTime);

        System.out.println("Yhteensä: " + count + " nimeä.");
        System.out.println("Suoritusaika: " + duration.toMillis() + " ms.");        
    }
    
    // Check if the given name is not part of any longer word in the dictionary
    private static boolean isAloneInDictionary(String name, List<String> dictionaryWordsAsc) {
    	int startIndex = binarySearch(dictionaryWordsAsc, name.length()); //can be used if list sorted;

        for (int i = startIndex; i < dictionaryWordsAsc.size(); i++) {
            String word = dictionaryWordsAsc.get(i);

            // Check if the word contains the name
            if (!word.equals(name) && word.contains(name)) {
                // Check if the name is not part of a longer word
                return false;
            }
        }
        return true;
    }

    // Binary search to find the index of the first word with a length equal to or shorter than targetLength
    // Binary search is O(log n), https://fi.wikipedia.org/wiki/Puolitushaku
    private static Integer binarySearch(List<String> list, int targetLength) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midLength = list.get(mid).length();

            if (midLength == targetLength) {
                return mid;
            } else if (targetLength < midLength) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    // Sort the list by length in ascending order
    private static List<String> sortByLengthAscending(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        sortedList.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

        return sortedList;
    }
    
    // Sort the list by length in ascending order with Collections.sort
    private static List<String> sortByLengthAscendingWithCollectionsSort(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, Comparator.comparingInt(String::length));
        return sortedList;
    }
}

