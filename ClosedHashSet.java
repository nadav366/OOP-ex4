/**
 * Collection realized by close Hash, Kind of SimpleHashSet.
 */
public class ClosedHashSet extends SimpleHashSet {

    /**   An array of strings is a Hash table    */
    private String[] table;

    /**   A variable that serves as a flag that a value has been deleted    */
    private final static String DELETED_FLAG = new String();

    /**
     * A default constructor. Constructs a new, empty table
     * with default initial capacity (16), upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        super();
        table = new String[INITIAL_CAPACITY];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        table = new String[INITIAL_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values ignored. The new table has the default value.
     * @param data Values to add to the set.
     */
    public ClosedHashSet(String[] data){
        this();
        for (String str : data){
            add(str);
        }
    }

    /**
     * A function that returns for the hash cod its corresponding place in the table.
     * @param hashCod The code matches the word you want to insert
     * @return An index matching the word in the table
     */
    private int searchFreeCell(int hashCod) {
        int i = 0;
        int newIndex;
        while (true) {
            newIndex = clamp(hashCod + (i + i * i) / 2);  // Calculates a suitable index for a table
            if ((table[newIndex] == null) || (table[newIndex] == DELETED_FLAG))  //If the cell is empty
                return newIndex;
            i++;
        }
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

        // Resizes the table if needed-
        curItems++;
        if ((double)size()/capacity() > getUpperLoadFactor()) {
            curCapacity *= 2;
            resizing();
        }

        // Adds to list-
        int index = searchFreeCell(newValue.hashCode());
        table[index] = newValue;
        return true;
    }

    /**
     * Changes the table size to the curCapacity and copying all the values in the old table into the new table
     */
    private void resizing() {
        String[] oldTable = table;
        table = new String[capacity()];
        for (String cell : oldTable){  // Passes the whole old table-
            if ((cell != null) && (cell != DELETED_FLAG)) // Checks for value-
                table[searchFreeCell(cell.hashCode())] = cell;   //Add to list-
        }
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        if (findStr(searchVal) == -1)
            return false;
        return true;
    }

    /**
     * A function that searches for a table string
     * @param searchVal Value to search for
     * @return  Table position index, -1 if not found
     */
    private int findStr(String searchVal) {
        int newIndex;
        int hash = searchVal.hashCode();
        for (int i=0; i< capacity(); i++){
            newIndex = clamp( hash+ (i + i * i) / 2);  // Calculates the appropriate index in the table
            if (table[newIndex] == null)   // If the cell is empty
                return -1;  // An empty cell is found (which is not erased) the value is not found
            if (table[newIndex] == DELETED_FLAG) // If the value is deleted, continue to search
                continue;
            if(table[newIndex].equals(searchVal))   // If the cell contains the value itself
                return newIndex;
        }
        return -1;  // Value not found
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        int index = findStr(toDelete);  // Searching for the value in the table
        if (index == -1)
            return false;

        // Deletes the value, Indicates that it has been deleted
        table[index] = DELETED_FLAG;
        curItems--;

        // Resizes the table if needed-
        if (((double)size()/capacity() < getLowerLoadFactor()) && (capacity()*0.5 >= 1)) {
            curCapacity /= 2;
            resizing();
        }
        return true;
    }
}
