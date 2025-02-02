import java.util.NoSuchElementException;

/** Implements the interface <code>FrequencyTable</code> using linked
 *  elements. The linked structure is circular and uses a dummy node.
 *
 * @author Marcel Turcotte (turcott@eecs.uottawa.ca)
 */

public class LinearFrequencyTable implements FrequencyTable {

    // Linked elements

    private static class Elem {

	private String key;
	private long count;
	private Elem previous;
	private Elem next;

	private Elem(String key, Elem previous, Elem next) {
	    this.key = key;
	    this.count = 0;
	    this.previous = previous;
	    this.next = next;
	}

    }

    private Elem head;
    private int size;

    /** Constructs and empty <strong>FrequencyTable</strong>.
     */

    public LinearFrequencyTable() {
	head = new Elem(null, null, null); // dummy node
	head.previous = head; // making the dummy node circular
	head.next = head; // making the dummy node circular
	size = 0;
    }

    /** The size of the frequency table.
     *
     * @return the size of the frequency table
     */

    public int size() {
	return size;
    }
  
    /** Returns the frequency value associated with this key.
     *
     *  @param key key whose frequency value is to be returned
     *  @return the frequency associated with this key
     *  @throws NoSuchElementException if the key is not found
     */

    public long get(String key) {

        Elem elem = head;
        long toReturn = 0;

        while (elem.next != head){
            if(! elem.next.key.equals(key)){
                elem = elem.next;
            } else {
                toReturn = elem.next.count;
            }
        }

        if (elem.next == head){
            throw new NoSuchElementException();
        } else {
            return toReturn;
        }

	//throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");
	
    }

    /** Creates an entry in the frequency table and initializes its
     *  count to zero. The keys are kept in order (according to their
     *  method <strong>compareTo</strong>).
     *
     *  @param key key with which the specified value is to be associated
     *  @throws IllegalArgumentException if the key was alreaddy present
     */

    public void init(String key) {

        Elem e = head;
        Elem toInsert;
        boolean notInserted = true;
        
        if (size == 0){
            toInsert = new Elem(key, head, head);;
            head.next = toInsert;
            head.previous = toInsert;
            size++;
            notInserted = false;

        }

        while (e.next != head && notInserted){
            if (e.next.key.compareTo(key) < 0){
                e = e.next;
            } else if (e.next.key.compareTo(key) > 0){
                toInsert = new Elem(key, e, e.next);
                e.next.previous = toInsert;
                e.next = toInsert;
                notInserted = false;
                size++;
            } else {
                notInserted = false;
                throw new IllegalArgumentException();
            }
            
        }
        
        if (notInserted && e.next == head){
            toInsert = new Elem(key, e, head);
            e.next = toInsert;
            head.previous = toInsert;
            size++;

        }

	//throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");

    }

    /** The method updates the frequency associed with the key by one.
     *
     *  @param key key with which the specified value is to be associated
     *  @throws NoSuchElementException if the key is not found
     */

    public void update(String key) {
	   Elem elem = head;
       boolean countUpdated = false;
       

        while (elem.next != head && !countUpdated){
            if(! elem.next.key.equals(key)){
                elem = elem.next;
            } else {
                elem.next.count++;
                //System.out.println("HERE'S MY COUNT BITCH " + elem.next.count);
                countUpdated = true;
            }
        }

        if (elem.next == head && !countUpdated){
            throw new NoSuchElementException();
        }
	

    //throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");

    }

    /** Returns the list of keys in order, according to the method
     *  <strong>compareTo</strong> of the key objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<String> keys() {

        LinkedList<String> keysList = new LinkedList<String>();

        Elem elem = head;
        int i = 0;

        while (elem.next != head){

            keysList.add(i, elem.next.key);
            i++;
        }

        return keysList;

	//throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");

    }

    /** Returns an array containing the frequencies of the keys in the
     *  order specified by the method <strong>compareTo</strong> of
     *  the key objects.
     *
     *  @return an array of frequency counts
     */

    public long[] values() {

        long[] arr = new long[size];

        Elem elem = head;

        for (int i = 0; i < size; i++){
            arr[i] = elem.next.count;
            elem = elem.next;
        }

        return arr;

	//throw new UnsupportedOperationException("IMPLEMENT THIS METHOD");

    }

    /** Returns a <code>String</code> representations of the elements
     * of the frequency table.
     *  
     *  @return the string representation
     */

    public String toString() {

	StringBuffer str = new StringBuffer("{");
	Elem p = head.next;

	while (p != head) {
	    str.append("{key="+p.key+", count="+p.count+"}");
	    if (p.next != head) {
		str.append(",");
	    }
	    p = p.next;
	}
	str.append("}");
	return str.toString();
    }

}
