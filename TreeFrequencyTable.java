import java.util.NoSuchElementException;

/** Implements the interface <code>FrequencyTable</code> using a
 *  binary search tree.
 *
 * @author Marcel Turcotte (turcott@eecs.uottawa.ca)
 */

public class TreeFrequencyTable implements FrequencyTable {

    // Stores the elements of this binary search tree (frequency
    // table)
    
    private static class Elem {
    
        private String key;
        private long count;
    
        private Elem left;
        private Elem right;
    
        private Elem(String key) {
            this.key = key;
            this.count = 0;
            left = null;
            right = null;
        }
    }

    private Elem root = null; // A reference to the root element
    private int size = 0; // The size of the tree

    /** The size of the frequency table.
     *
     * @return the size of the frequency table
     */
    
    public int size() {
        return size;
    }
  
    /** Creates an entry in the frequency table and initializes its
     *  count to zero.
     *
     * @param key key with which the specified value is to be associated
     */
  
    public void init(String key) {
    	
	    
	    if (key == null){
	    	throw new NullPointerException();
	    }
	    
	    if (root == null){
	    	root = new Elem(key);
	    	size++;
	    } else {
	    
		    Elem current = root;
		    boolean notInserted = true;
		    
		    while (notInserted){
		    	if (current.key.compareTo(key) < 0){
		    		if (current.right != null){
		    			current = current.right;
		    		} else {
		    			current.right = new Elem(key);
		    			size++;
		    			notInserted = false;
		    		}
		            
		    	} else if (current.key.compareTo(key) > 0){
		    		if (current.left != null){
		    			current = current.left;
		    		} else {
		    			current.left = new Elem(key);
		    			size++;
		    			notInserted = false;
		    		}
		    		
		    	} else {
		    		notInserted = false;
		            throw new IllegalArgumentException();
		    	}
		    }
    	}
    }
  
    /** The method updates the frequency associed with the key by one.
     *
     * @param key key with which the specified value is to be associated
     */
  
    public void update(String key) {
    
    	Elem current = root;
	    boolean notUpdated = true;
	    
	    while (notUpdated){
	    	if (current.key.compareTo(key) < 0){
	    		if (current.right != null){
	    			current = current.right;
	    		} else {
	    			notUpdated = false;
	    			throw new IllegalArgumentException();
	    		}
	            
	    	} else if (current.key.compareTo(key) > 0){
	    		if (current.left != null){
	    			current = current.left;
	    		} else {
	    			notUpdated = false;
	    			throw new IllegalArgumentException();
	    		}
	    		
	    	} else {
	    		current.count++;
	    		notUpdated = false;
	    	}
	    }

    }
  
    /**
     * Looks up for key in this TreeFrequencyTable, returns associated value.
     *
     * @param key value to look for
     * @return value the value associated with this key
     * @throws NoSuchElementException if the key is not found
     */
  
    public long get(String key) {

        boolean found = false;
        long toReturn = 0;
        Elem current = root;
        while (!found && current != null){
            if (key.compareTo(current.key) == 0){
                toReturn = current.count;
                found = true;
            } else if (key.compareTo(current.key) < 0){
                current = current.left;
            } else {
                current = current.right;
            }

        }

        if (!found){
            throw new NoSuchElementException();
        }

        return toReturn;

    
	//throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");

    }
  
    /** Returns the list of keys in order, according to the method compareTo of the key
     *  objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<String> keys() {

	//throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");
    	
    	LinkedList<String> keysList = new LinkedList<String>();
    	
    	return keys(root, keysList, 0);

    }
    
    private LinkedList<String> keys (Elem current, LinkedList<String> keysList, int pos){
    	
    	
    	
    	if (current != null){
    		keys(current.left, keysList, pos);
    		keysList.add(pos++, current.key);
    		keys(current.right,keysList, pos);	
    	}
    	
    	return keysList;
    }

    /** Returns the values in the order specified by the method compareTo of the key
     *  objects.
     *
     *  @return the values
     */

    public long[] values() {
    	
    	long[] valuesArray = new long [size];

    	return values(root, valuesArray, 0);

    }
    
    private long[] values(Elem current, long[] valuesArray, int index){
    	
    	if (current != null){
    		values(current.left, valuesArray, index);
    		valuesArray[index++] = current.count;
    		values(current.right, valuesArray, index);
    	}
    	
    	return valuesArray;
    }

    /** Returns a String representation of the tree.
     *
     * @return a String representation of the tree.
     */

    public String toString() {
        return toString( root );
    }

    // Helper method.
  
    private String toString(Elem current) {
    
        if (current == null) {
            return "{}";
        }
    
        return "{" + toString(current.left) + "[" + current.key + "," + current.count + "]" + toString(current.right) + "}";
    }
  
}
