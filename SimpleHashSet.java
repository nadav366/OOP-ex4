/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 */
public abstract class SimpleHashSet implements SimpleSet {

    /**
     * Constants that define defaults values to the tables.
     */
    protected static final int INITIAL_CAPACITY = 16;
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;

    /**   Load Factor variables for the collection.   */
    private float lowerLoadFactor;
    private float upperLoadFactor;

    /**     The current size of the table      */
    protected int curCapacity;

    /**    The number of current values in the table       */
    protected int curItems;


    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     * @param upperLoadFactor the upper load factor before rehashing
     * @param lowerLoadFactor the lower load factor before rehashing
     */
    SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;

        curCapacity = INITIAL_CAPACITY;
        curItems = 0;
    }

    /**
     * Constructs a new hash set with the default capacities given in
     * DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY
     */
    SimpleHashSet(){
        this(DEFAULT_HIGHER_CAPACITY, DEFAULT_LOWER_CAPACITY);
    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    protected int capacity(){
        return curCapacity;
    }
    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return curItems;
    }

    /**
     * Clamps hashing indices to fit within the current table capacity
     * @param index  the index before clamping
     * @return an index properly clamped
     */

    int clamp(int index) {
        return (index & (capacity()-1));
    }

    /**
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor(){
        return lowerLoadFactor;

    }

    /**
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor(){
        return upperLoadFactor;
    }
}
