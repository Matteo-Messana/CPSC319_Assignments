import java.util.Arrays;
import java.util.Comparator;

/*
 * **************************
 * 	MergeSort.java 
 * 
 * ************************** 
 */

/**
 * Merge Sort is a divide and conquer algorithm. It divides input array into two
 * halves, calls itself into halves recursively until there is only one element
 * in the array. Finally, the two sorted arrays are merged together to
 * reconstruct the sorted array.
 * <p>
 * 
 * Reference: 12.1 Merge-Sort :Data Structures and Algorithms in Java, Sixth
 * Edition
 * <p>
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * <p>
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p>
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see http://www.gnu.org/licenses/.
 * <p>
 * 
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * 
 * 
 */
public class MergeSort {

    // -------- support for top-down merge-sort of arrays --------
    /**
     * Merge contents of arrays S1 and S2 into properly sized array S.
     * Reference: 12.1.2 Array-Based Implementation of Merge-Sort
     * 
     * @param S1
     *            left sub-array
     * @param S2
     *            right sub-array
     * @param S
     *            merged sorted array
     * @param comp
     *            comparator
     */
    public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
	int i = 0, j = 0;
	while (i + j < S.length) {
	    if (j == S2.length
		    || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
		S[i + j] = S1[i++]; // copy ith element of S1 and increment i
	    else
		S[i + j] = S2[j++]; // copy jth element of S2 and increment j
	}
    }

    /**
     * Merge-sort contents of array S.
     * 
     * @param S
     *            unsorted array
     * @param comp
     *            comparator
     */
    public static <K> void mergeSort(K[] S, Comparator<K> comp) {
	int n = S.length;
	if (n < 2)
	    return; // array is trivially sorted
	// divide
	int mid = n / 2;
	K[] S1 = Arrays.copyOfRange(S, 0, mid); // copy of first half
	K[] S2 = Arrays.copyOfRange(S, mid, n); // copy of second half
	// conquer (with recursion)
	mergeSort(S1, comp); // sort copy of first half
	mergeSort(S2, comp); // sort copy of second half
	// merge results
	merge(S1, S2, S, comp); // merge sorted halves back into original
    }

    // example test
    /*public static void main(String[] args) {

	StaticArray<String> listA = new StaticArray<String>(15000);

	listA.add("Hello");
	listA.add("World");
	listA.add("Abc");
	listA.add("Xyz");

	listA.trimToSize(); // trim extra memory

	System.out.println(listA.toString()); // unsorted

	// define the comparator
	Comparator<String> comparator = new Comparator<String>() {

	    @Override
	    public int compare(String arg0, String arg1) {

		return arg0.compareTo(arg1);
	    }
	};

	// create an array with String
	String[] arrStrings = listA.toStringArray();

	mergeSort(arrStrings, comparator); // sort the array

	// set the values of the sorted array into list A
	for (int i = 0; i < listA.size(); i++)
	    listA.set(i, arrStrings[i]);

	System.out.println(listA.toString()); // sorted listA

    }*/

}

