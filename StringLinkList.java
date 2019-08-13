import java.util.*;

/**
 * A class that surrounds the linked class of a string to use in the array.
 */
public class StringLinkList implements SimpleSet{

    /** Linked List of strings            */
    private LinkedList<String> arr;

    /**
     * constructor. Initializes a new linked list.
     */
    StringLinkList(){
        arr = new LinkedList<>();
    }

    /**
     * Look for a specified value in the linkList.
     * @param val Value to search for
     * @return True if val is found in the linkList
     */
    public boolean contains(String val){
        return arr.contains(val);
    }

    /**
     * @return  the iterator of the link list
     */
    public Iterator<String> toIter(){
        return arr.iterator();
    }

    /**
     * Add a specified element to the linkList
     * @param val New value to add to the linkList
     * @return true
     */
    public boolean add(String val){
        arr.add(val);
        return true;
    }

    /**
     * Remove the input element from the LinkList.
     * @param val value to Remove from the linkList
     * @return True iff val is found and deleted
     */
    public boolean delete(String val){
        return arr.remove(val);
    }

    /**
     *
     * @return The number of elements currently in the list
     */
    public int size(){
        return arr.size();
    }

}
