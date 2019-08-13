import java.util.*;

/**
 * A class that calculates the performance of several SimpleSet types.
 */
public class SimpleSetPerformanceAnalyzer {

    /**
     * A variable that will contains which collections to examine
     */
    private final static LinkedList<Integer> collectionsToChoose = new LinkedList<>();

    /** Names of the examined collections      */
    private static String[] names = {"OpenHashSet", "ClosedHashSet", "TreeSet", "LinkedList", "HashSet"};

    /** An array of some SimpleSet types     */
    private static SimpleSet[] arr;

    /** A 2D array of strings of strings.      */
    private static String[][] datas = new String[2][];

    /** Fixed for use in software      */
    private final static int TO_MS = 1000000;
    private final static int REGULAR_CIRC = 70000;
    private final static int LINK_LIST_CIRC = 7000;

    /**
     * A main function that runs all tests
     * @param args
     */
    public static void main(String[] args){
        // Initializes the information into the appropriate array
        datas[0] = Ex4Utils.file2array("data1.txt");
        datas[1] = Ex4Utils.file2array("data2.txt");

        Scanner scan = new Scanner(System.in);


        System.out.println( "To run a test only on some collections:\n" +
                "Write the collection numbers that you want to check by the key-\n"+
                "('all' to run all)\n"+
                "1: OpenHashSet\n"+
                "2: ClosedHashSet\n"+
                "3: TreeSet\n"+
                "4: LinkedList\n"+
                "5: HashSet");
        String input = scan.nextLine();

        for (int i=1; i<6; i++)
            if ((input.contains(Integer.toString(i))) || (input.equals("all")))
                collectionsToChoose.add(i);


        System.out.println( "Choose which tests to run:\n" +
                "Write the test numbers that you want to check by the key-\n"+
                "('all' to run all)\n"+
                "1: add of data1\n"+
                "2: Checks whether the collection contains: 'hi' in data1\n"+
                "3: Checks whether the collection contains: '-13170890158' in data1\n"+
                "4: add of data2\n"+
                "5: Checks whether the collection contains: '23' in data2\n"+
                "6: Checks whether the collection contains: 'hi' in data2\n");
        input = scan.nextLine();


        /**
         * The appropriate measurements for data1
         */
        initialization();  // Initializing all collections

        if ((input.contains(Integer.toString(1))) || (input.equals("all")))
            addAll(0,true);
        else if ((input.contains(Integer.toString(2))) || (input.contains(Integer.toString(3))))
            addAll(0,false);

        if ((input.contains(Integer.toString(2))) || (input.equals("all")))
            containsChecks("hi");  // Checks if found, for the hashCod different from all the information
        if ((input.contains(Integer.toString(3))) || (input.equals("all")))
            containsChecks("-13170890158");  // Checks if found, for same hashCod

        /**
         * The appropriate measurements for data2
         */
        initialization();  // Initializing all collections

        if ((input.contains(Integer.toString(4))) || (input.equals("all")))
            addAll(1,true);
        else if ((input.contains(Integer.toString(5))) || (input.contains(Integer.toString(6))))
            addAll(1,false);

        if ((input.contains(Integer.toString(5))) || (input.equals("all")))
            containsChecks("23");  // Checks if found for information not found
        if ((input.contains(Integer.toString(6))) || (input.equals("all")))
            containsChecks("hi");  //Checks if found for information that found

    }

    /**
     * Initializing all collections
     */
    private static void initialization() {
        arr = new SimpleSet[5];
        arr[0] = new OpenHashSet();
        arr[1] = new ClosedHashSet();
        arr[2] = new CollectionFacadeSet(new TreeSet<String>());
        arr[3] = new CollectionFacadeSet(new LinkedList<String>());
        arr[4] = new CollectionFacadeSet(new HashSet<String>());
    }

    /**
     * Adds the appropriate information, by index, to all collections, and measures how long it takes
     * @param index What information to add
     * @param print Flag the pointer to print during the test
     */
    private static void addAll(int index, Boolean print){
        if (print) System.out.println("Add all data"+(index+1)+" to collections:");
        for (int i: collectionsToChoose){
            long timeBefore = System.nanoTime();

            for (int j=0; j<datas[index].length; j++)
                arr[i-1].add(datas[index][j]);

            long difference = System.nanoTime() - timeBefore;
            if (print)System.out.println(names[i-1]+": "+(difference/TO_MS)+" ms");
        }
        if (print) System.out.println("\n***********************************\n");
    }

    /**
     * Measures how long it takes (on average) to check whether the string exists in each collection.
     * @param strToSerch String search
     */
    private static void containsChecks(String strToSerch){
        System.out.println("Checks whether the collection contains: "+strToSerch);
        for (int i: collectionsToChoose){
            if (i != 4)
                oneContain(i-1, REGULAR_CIRC, strToSerch);
            else
                oneContain(i-1, LINK_LIST_CIRC, strToSerch);
        }
        System.out.println("\n***********************************\n");
    }

    /**
     * Measures and prints how long it takes to find a specific string in a specific collection
     * @param i The collection index to search for
     * @param numSirc How many times should I search (and do average)
     * @param strToSerch String to search
     */
    private static void oneContain(int i, int numSirc, String strToSerch){
        // "Heating" system
        for (int k = 0; k < numSirc; k++)
            arr[i].contains(strToSerch);
        // Measures the time for all searches together
        long timeBefore = System.nanoTime();
        for (int k = 0; k < numSirc; k++)
            arr[i].contains(strToSerch);
        long difference = System.nanoTime() - timeBefore;
        // Prints the time on average
        System.out.println(names[i] + ": " + difference/numSirc + " ns");
    }
}
