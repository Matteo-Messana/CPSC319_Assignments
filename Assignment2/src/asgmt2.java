import java.io.BufferedReader;
import java.io.PrintStream;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Arrays;

/**
 * Description: This program will receive a text file of under 30000 words
 * and sort those words in alphabetical order. Once sorted, the program will 
 * try and find any anagrams for the words in the list. If found, all anagrams of one set of 
 * letters will be sorted into a linked list in alphabetical order. The linked
 * lists will also be sorted in alphabetical order and exported to a text file.
 * 
 * @author Matteo Messana, 30020933
 * @version 1.0
 * 
 */

public class asgmt2 {

    public static void main(String[] args) throws IOException {

	/*
	 * Read input, output file name from command line arguments
	 * 
	 * 
	 * Command line instruction of assignment:
	 * -----------------------------------------
	 * 
	 * java asgmt2 [input file name with valid path] [output file name]
	 * 
	 * As example:
	 * 
	 * java asgmt2 example_1--8_words.txt example_1--8_words-out.txt
	 * 
	 * Here, input file is considered to be in the same location/path as
	 * asgmt2.java. Output file will be generated in the same path too.
	 * 
	 * If you want to provide command line arguments in eclipse, check this
	 */
	// Quick check for valid input file name (Optional)
    String fileName = "src/Example2";
	File file = new File(fileName); 
	// if file does not exist or not a file
	if (!(file.exists() && file.isFile())) 
	{
	    System.err
		    .println(fileName + " does not exist. Program Terminated.");
	    System.exit(1);
	}
	
	

	// if everything is okay
	String inputFile = fileName;
	String outputFile = "Example1Out";

	//System.out.println("INPUT: " + inputFile + "\nOUTPUT: " + outputFile);

	// -------------------------------------------------------------------

	/*
	 * Usage of static array
	 */

	// create the 1-D array
	BufferedReader in = new BufferedReader(new FileReader(file));
	String line = null;
	
	StaticArray<String> listA = new StaticArray<String>(30000);


	// insert words into the array
	while((line = in.readLine()) != null)
	{
		listA.add(line);
	}
	in.close();
	listA.trimToSize(); // trim extra memory

	/*
	 * Organize the list of words
	 */
	
	listA.setData(organizeList(listA));
	
	
	/*
	 * listA -> listB
	 */
	
	// suppose, generateListB generates list B for given list A
	StaticArray<SinglyLinkedList<String>> listB = generateListB(listA);

	// get the correct format
	PrintStream fileOut = new PrintStream("./out.txt");
	System.setOut(fileOut);
	System.out.println(toString(listB));

	
    }

    /**
     * Create 1-D array storing each set of anagram in a singly-linked list
     * 
     * @param listA
     *            array of sorted words
     * 
     * @return array of list of anagrams (sorted)
     */
    public static StaticArray<SinglyLinkedList<String>> generateListB(StaticArray<String> listA) {

	// initialize listB as an array of singly linked list of Strings
	StaticArray<SinglyLinkedList<String>> listB = new StaticArray<SinglyLinkedList<String>>(listA.size());

	// A new LIST B should then be created from sorted LIST A

	// if a set of anagrams found, we have to insert them into the array as
	// singly linked list

	// for each set, create a new linked list	
	for(int i =0; i<listA.size();i++)
	{
		boolean repeatTest = false;
		SinglyLinkedList<String> linkedList = new SinglyLinkedList<String>();
		ArrayList<Integer> linkedListElements = findAnagrams(listA,listA.get(i),i);
		linkedList.addLast(listA.get(i));
		for(int j = 0; j < linkedListElements.size();j++)
		{
			linkedList.addLast(listA.get(linkedListElements.get(j)));
		}
		
		for(int j = 0; j<listB.size();j++)
		{
			for(int k = 0; k<listB.get(j).size(); k++)
			{
				if(listA.get(i) == listB.get(j).get(k))
				{
					repeatTest = true;
				}
			}
		}
		if(repeatTest == false)
		{
			listB.add(linkedList);
		}
	}
	
	return listB;

    }

    /**
     * Produces a string representation of the contents of the array.
     * 
     * @param arr
     *            StaticArray<SinglyLinkedList<String>>
     * @return string corresponds to the result
     */
    public static String toString(StaticArray<SinglyLinkedList<String>> arr) {
	StringBuilder sb = new StringBuilder("");
	for (int j = 0; j < arr.size(); j++) {
	    if (j > 0) // if new list is present
		sb.append("\n"); // go to new line
	    // if no value in the list then break
	    if (arr.get(j) == null)
		break;
	    else {
		// get all the element(s) in the list and append
		for (int k = 0; k < arr.get(j).size(); k++) {
		    sb.append(arr.get(j).get(k));
		    sb.append(" ");
		}
	    }
	}
	sb.append("");
	return sb.toString();
    }

    /**
     * Takes the list of words from the file and organizes it into alphabetical order. 
     * 
     * @param list: The list of words provided for organizing 
     * @return Array of organized strings.
     */
    public static String[] organizeList (StaticArray<String> list)
    {
    	Comparator<String> comparator = new Comparator<String>() {

    	    @Override
    	    public int compare(String arg0, String arg1) {
    	    // Compares strings to strings
    		return arg0.compareTo(arg1);
    	    }
    	};
    	String[] arrStrings = list.toStringArray();
    	//calls merge sort to sort the list
    	MergeSort.mergeSort(arrStrings,comparator);
    	return arrStrings;
    }

    /**
     * This program will search the list it is given and find any anagrams of the
     * source search word.
     * 
     * @param list: The list of words provided for the program to search through.
     * @param search: The word being used to search the list for anagrams.
     * @param index: The index of the search word to ensure it is skipped when searching for anagrams.
     * @return ArrayList<Integer>: Returns an ArrayList of type integer for all index values of anagrams for the search.
     */
    public static ArrayList<Integer> findAnagrams(StaticArray<String> list, String search, int index)
    {
    	Comparator<Character> comparator = new Comparator<Character>() {

    	    @Override
    	    public int compare(Character arg0, Character arg1) {
    	    //compares charachters.
    		return arg0.compareTo(arg1);
    	    }
    	};
    	String [] listArray = list.toStringArray();
    	ArrayList<Integer> indices = new ArrayList<Integer>();
    	Character[] searchCharacters = QuickSort.toCharacterArray(search.toLowerCase());
    	QuickSort.quickSortInPlace(searchCharacters, comparator);
    	for(int i = 0; i<listArray.length;i++)
    	{
    		Character[] indexCharacters = QuickSort.toCharacterArray(listArray[i].toLowerCase());
    		QuickSort.quickSortInPlace(indexCharacters, comparator);
    		for(int k = 0; k<indexCharacters.length; k++) {
    		}
    		
    		if(Arrays.equals(searchCharacters,indexCharacters) && i != index)
    		{
    			indices.add(i);
    		}
    	}
    	return indices;
    }
}


