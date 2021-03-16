 /*
 * **************************
 * 	ArrayList.java 
 * 
 * ************************** 
 */

/**
 * An array-backed implementation of a list by means of a dynamic array.
 * Guarantees that this list will have at least enough capacity to hold all
 * elements. This implementation will grow the list to max(current * 2,
 * capacity) if (capacity &gt; current).
 * <p>
 * 
 * Adapted from: Chapter: 7 (List and Iterator ADTs) :Data Structures and
 * Algorithms in Java, Sixth Edition Michael T. Goodrich, Roberto Tamassia, and
 * Michael H. Goldwasser
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
 * @author Md Mamunur Rashid (mmncit@gmail.com)
 * 
 * 
 */
public class ArrayList<E> extends StaticArray<E> {

    // constructors
    /** Creates an array list with default initial capacity. */
    public ArrayList() {
	super();
    } // constructs list with default capacity

    /**
     * Constructs list with default capacity.
     * 
     * @param capacity
     *            default capacity
     */
    public ArrayList(int capacity) { // constructs list with given capacity
	super(capacity); // safe cast; compiler may give
			 // warning
    }

    /**
     * Inserts the given element at the specified index of the list, shifting
     * all subsequent elements in the list one position further to make room.
     * 
     * @param i
     *            the index at which the new element should be stored
     * @param e
     *            the new element to be stored
     * @throws IndexOutOfBoundsException
     *             if index &lt; 0 || index &gt; size()
     */
    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
	int size = super.size();
	int length = super.capacity();
	if (size == length) // not enough capacity
	    super.resize(2 * length); // so double the current capacity
	super.add(i, e);
    }

    /**
     * Appends the supplied element to the end of this list. The element, e, can
     * be an object of any type or null.
     * 
     * @param e
     *            the element to be appended to this list
     * @return true, the add will always succeed
     */

    @Override
    public boolean add(E e) {
	int size = super.size();
	int length = super.capacity();
	if (size == length)
	    // double the current capacity
	    super.resize(2 * length);
	super.add(e);
	return true;
    }

}

