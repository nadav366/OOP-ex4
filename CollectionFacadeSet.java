import java.util.Collection;

/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a
 * common type with the implemented SimpleHashSets
 */
public class CollectionFacadeSet implements SimpleSet {

    /**
     * The collection we wrap
     */
    private Collection<String> col;

    /**
     * Creates a new facade wrapping the specified collection.
     * It is assumed that there are no duplicates in the collection.
     * @param collection The Collection to wrap
     */
    CollectionFacadeSet(Collection<String> collection){
        this.col = collection;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if (col.contains(newValue))
            return false;
        return col.add(newValue);
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        return col.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        return col.remove(toDelete);
    }

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return col.size();
    }
}
