import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class TestCol {


    public static void main(String[] args) {
        String[] data1 = Ex4Utils.file2array("C:\\Users\\NADAV\\IdeaProjects\\ex4\\src\\data1.txt");
        String[] data2 = Ex4Utils.file2array("C:\\Users\\NADAV\\IdeaProjects\\ex4\\src\\data2.txt");


        System.out.println("data2");
        SimpleSet[] arr = initialization();
        System.out.println("add:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 0; j < data2.length; j++) {
                assertTrue(arr[i].add(data2[j]));
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }
        /*
        System.out.println("not contains:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 2000; j < 2050; j++) {
                assertFalse(arr[i].contains(data2[j]));
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }
        System.out.println("contains:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 50; j < 100; j++) {
                assertTrue(arr[i].contains(data2[j]));
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }*/
        System.out.println("add contains:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 50; j < 100; j++) {
                assertFalse(arr[i].add(data2[j]));
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }
        System.out.println("delete:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = data2.length-1; j >= 0; j--) {
                assertTrue(arr[i].delete(data2[j]));
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }


        System.out.println("data1");
        arr = initialization();
        System.out.println("add:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 0; j < data1.length; j++) {
                arr[i].add(data1[j]);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }
        /*
        assertEquals(1000, arr[0].size());
        assertEquals(1000, arr[1].size());
        System.out.println("not contains:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 2000; j < 2050; j++) {
                assertFalse(arr[i].contains(data1[j]));
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }
        System.out.println("contains:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 50; j < 100; j++) {
                assertTrue(arr[i].contains(data1[j]));
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }*/
        System.out.println("add contains:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 50; j < 100; j++) {
                assertFalse(arr[i].add(data1[j]));
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }
        System.out.println("delete:");
        for (int i = 0; i < 2; i++) {
            long timeBefore = System.nanoTime();
            for (int j = data1.length-1; j >= 0; j--) {
                //assertTrue(arr[i].delete(data1[j]));
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(i + 1 + ": " + (difference / Math.pow(10, 6)) + " ms");
        }


    }

    private static SimpleSet[] initialization() {
        SimpleSet[] arr = new SimpleSet[5];
        arr[0] = new OpenHashSet();
        arr[1] = new ClosedHashSet();
        arr[2] = new CollectionFacadeSet(new TreeSet<String>());
        arr[3] = new CollectionFacadeSet(new LinkedList<String>());
        arr[4] = new CollectionFacadeSet(new HashSet<String>());

        return arr;
    }
}
