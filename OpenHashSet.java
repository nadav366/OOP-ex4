import java.util.Iterator;

/**
 * Collection realized by Open Hash, Kind of SimpleHashSet.
 */
public class OpenHashSet extends SimpleHashSet {
    private StringLinkList[] table;

    /**
     * A default constructor. Constructs a new, empty table
     * with default initial capacity (16), upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        super();
        table = newTable();
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor  The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        table = newTable();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * The new table has the default values.
     * @param data Values to add to the set.
     */
    public OpenHashSet(String[] data){
        this(); // Builds a new collection with default values

        // Adds all values from array.
        for (String str : data){
            add(str);
        }
    }

    /**
     * Initializes a new blank table, the size of curCapacity
     * @return new blank table.
     */
    private StringLinkList[] newTable(){
        StringLinkList[] table = new StringLinkList[capacity()];

        for (int i=0; i<capacity(); i++)
            table[i] = new StringLinkList(); // Initializes each cell to an empty linked list

        return table;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        // Checks whether the string is already in the collection
        if (contains(newValue))
            return false;

        // Adds to list-
        curItems++;
        table[clamp(newValue.hashCode())].add(newValue);

        // Resizes the table if needed-
        if ((double)size()/capacity() > getUpperLoadFactor()){
            curCapacity *= 2;
            resizing();
        }
        return true;
    }

    /**
     * Changes the table size to the curCapacity and copying all the values in the old table into the new table
     */
    private void resizing() {
        StringLinkList[] fillTable = newTable();

        for (StringLinkList cell : table){  // Passes all cells in the table
            Iterator<String> iter = cell.toIter();
            while (iter.hasNext()){  // Passes the entire linked list in the cell
                String str = iter.next();
                fillTable[clamp(str.hashCode())].add(str);
            }
        }
        table = fillTable; // Saves the new table
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        return table[clamp(searchVal.hashCode())].contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        // Trying to delete the entry from the linked list in the corresponding cell-
        if (!table[clamp(toDelete.hashCode())].delete(toDelete))
            return false;  // Could not delete, meaning value does not exist
        curItems--;

        // Resizes the table if needed-
        if (((double)size()/capacity() < getLowerLoadFactor())  && (capacity()*0.5 >= 1)) {
            curCapacity /= 2;
            resizing();
        }
        return true;
    }
}
