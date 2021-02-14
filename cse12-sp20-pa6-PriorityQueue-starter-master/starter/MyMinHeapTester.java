/**
 * Your beautiful file header here! 
 * (Note, this file won't be graded for style). 
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*; 

/**
 * Your beautiful class header here! 
 * (Note, this file won't be graded for style). 
 */
public class MyMinHeapTester
{
    private MyMinHeap<Integer> integerHeap; 
    private MyMinHeap<Integer> sanityIntegerHeap; 
    private MyMinHeap<Character> characterHeap; 
    private MyMinHeap<Double> doubleHeap; 

    ArrayList<Integer> listIntsRegularHeap;
    ArrayList<Integer> listIntsSmallHeap;
    ArrayList<Integer> listIntsDuplicatesHeap;
    
    private static final Integer[] arrIntsRegular = {5, 8, 10, 9, 11}; 
    private static final Integer[] arrIntsSmall = {3, 4, 5}; 
    private static final Integer[] arrIntsDup = {5, 8, 9, 8, 10, 11, 11}; 

    /**
     * This method runs before every method tagged with @Test. 
     * Feel free to add your own MyMinHeap variables and their 
     * initializations here. 
     */
    @Before
    public void setUp() {
        integerHeap = new MyMinHeap<>(); 
        sanityIntegerHeap = new MyMinHeap<>(); 
        characterHeap = new MyMinHeap<>(); 
        doubleHeap = new MyMinHeap<>(); 
        listIntsRegularHeap = new ArrayList<>(Arrays.asList(arrIntsRegular));
        listIntsSmallHeap = new ArrayList<>(Arrays.asList(arrIntsSmall));
        listIntsDuplicatesHeap = new ArrayList<>(Arrays.asList(arrIntsDup));
    }

    /**
     * Runs the sanity test for swap() given in the write-up. 
     */
    @Test
    public void testSwapSanity() {
        List<Integer> startingList = Arrays.asList(new Integer[]{3,1,2}); 
        sanityIntegerHeap.list = new ArrayList<>(startingList);
        sanityIntegerHeap.swap(0,1); 
        Integer[] expected = {1,3,2};
        assertEquals(Arrays.asList(expected), sanityIntegerHeap.list);
    }

    /**
     * Runs the sanity test for getParentIdx() given in the write-up. 
     */
    @Test
    public void testGetParentIdxSanity() {
        assertEquals(0, characterHeap.getParentIdx(1)); 
        assertEquals(new ArrayList<>(), characterHeap.list);
    }

    /**
     * Runs the sanity test for getLeftChildIdx() given in the write-up. 
     */
    @Test
    public void testGetLeftChildIdxSanity() {
        assertEquals(1, characterHeap.getLeftChildIdx(0)); 
        assertEquals(new ArrayList<>(), characterHeap.list);
    }

    /**
     * Runs the sanity test for getRightChildIdx() given in the write-up. 
     */
    @Test
    public void testGetRightChildIdxSanity() {
        assertEquals(2, characterHeap.getRightChildIdx(0)); 
        assertEquals(new ArrayList<>(), characterHeap.list);
    }

    /**
     * Runs the sanity test for getMinChildIdx() given in the write-up. 
     */
    @Test
    public void testGetMinChildIdxSanity() {
        List<Integer> startingList = Arrays.asList(new Integer[]{5,3,4}); 
        sanityIntegerHeap.list = new ArrayList<>(startingList);
        assertEquals(1, sanityIntegerHeap.getMinChildIdx(0)); 
        assertEquals(new ArrayList<>(startingList), sanityIntegerHeap.list);
    }

    /**
     * Runs the sanity test for percolateUp() given in the write-up. 
     */
    @Test
    public void testPercolateUpSanity() {
        List<Integer> startingList = Arrays.asList(new Integer[]{1,4,4,2,2}); 
        sanityIntegerHeap.list = new ArrayList<>(startingList);
        sanityIntegerHeap.percolateUp(3); 
        Integer[] expected = {1,2,4,4,2};
        assertEquals(Arrays.asList(expected), sanityIntegerHeap.list);
    }

    /**
     * Runs the sanity test for percolateDown() given in the write-up. 
     */
    @Test
    public void testPercolateDownSanity() {
        List<Integer> startingList = Arrays.asList(new Integer[]{8,4,3,5,2}); 
        sanityIntegerHeap.list = new ArrayList<>(startingList);
        sanityIntegerHeap.percolateDown(0); 
        Integer[] expected = {3,4,8,5,2};
        assertEquals(Arrays.asList(expected), sanityIntegerHeap.list);
    }

    /**
     * Runs a simple test for insert(). 
     */
    @Test
    public void testInsertSmall() {
        integerHeap.list = listIntsSmallHeap;
        integerHeap.insert(1);
        Integer[] expected = {1, 3, 5, 4};
        assertEquals(Arrays.asList(expected), integerHeap.list);
        integerHeap.insert(2);
        integerHeap.insert(10); 
        Integer[] expected2 = {1,2,5,4,3, 10};
        assertEquals(Arrays.asList(expected2), integerHeap.list);

    }

    /**
     * Runs another simple test for insert(). 
     */
    @Test
    public void testInsertRegular() {
        integerHeap.list = listIntsRegularHeap;
        integerHeap.insert(1);
        Integer[] expected = {1, 8, 5, 9, 11, 10};
        assertEquals(Arrays.asList(expected), integerHeap.list);
        integerHeap.insert(-3);
        integerHeap.insert(20);
        integerHeap.insert(5);
        Integer[] expected2 = {-3,5,1,8,11,10,5,20,9};
        assertEquals(Arrays.asList(expected2), integerHeap.list);
    }

    /**
     * Runs a simple test for getMin(). 
     */
    @Test
    public void testGetMinSmall() {
        integerHeap.list = listIntsSmallHeap;
        assertEquals(Integer.valueOf(3), integerHeap.getMin());
        integerHeap.insert(1);
        integerHeap.insert(0);
        integerHeap.insert(10);
        assertEquals(Integer.valueOf(0), integerHeap.getMin());

    }

    /**
     * Runs another simple test for getMin(). 
     */
    @Test
    public void testGetMinRegular() {
        integerHeap.list = listIntsRegularHeap;
        assertEquals(Integer.valueOf(5), integerHeap.getMin());
        integerHeap.remove();
        assertEquals(Integer.valueOf(8), integerHeap.getMin());
        integerHeap.insert(0);
        integerHeap.insert(-1);
        integerHeap.insert(-100);
        assertEquals(Integer.valueOf(-100), integerHeap.getMin());

    }

    /**
     * Runs a simple test for remove(). 
     */
    @Test
    public void testRemoveSmall() {
        integerHeap.list = listIntsSmallHeap;
        assertEquals(Integer.valueOf(3), integerHeap.remove());
        Integer[] expected = {4, 5};
        assertEquals(Arrays.asList(expected), integerHeap.list);
        integerHeap.remove();
        Integer[] expected2 = {5};
        assertEquals(Arrays.asList(expected2), integerHeap.list);

    }

    /**
     * Runs another simple test for remove(). 
     */
    @Test
    public void testRemoveRegular() {
        integerHeap.list = listIntsRegularHeap;
        assertEquals(Integer.valueOf(5), integerHeap.remove());
        Integer[] expected = {8, 9, 10, 11};
        assertEquals(Arrays.asList(expected), integerHeap.list);
        integerHeap.insert(8);
        integerHeap.remove();
        assertEquals(Arrays.asList(expected), integerHeap.list);
    }

    /**
     * Runs a simple test for size(). 
     */
    @Test
    public void testSizeSmall() {
        integerHeap.list = listIntsSmallHeap;
        assertEquals(3, integerHeap.size());
        integerHeap.insert(3);
        integerHeap.insert(10);
        integerHeap.insert(20);
        integerHeap.remove();
        assertEquals(5, integerHeap.size());
      
    }

    /**
     * Runs another simple test for size(). 
     */
    @Test
    public void testSizeRegular() {
        integerHeap.list = listIntsRegularHeap;
        assertEquals(5, integerHeap.size());
        integerHeap.remove();
        integerHeap.remove();
        assertEquals(3, integerHeap.size());

    }

    /**
     * Runs a simple test for clear(). 
     */
    @Test
    public void testClear() {
        integerHeap.list = listIntsSmallHeap;
        integerHeap.clear();
        assertNotNull(integerHeap.list);
        assertEquals(0, integerHeap.size());

        integerHeap.list = listIntsRegularHeap;
        assertEquals(5, integerHeap.size());
        integerHeap.clear();
        assertEquals(0, integerHeap.size());
        integerHeap.list = listIntsDuplicatesHeap; 
        assertEquals(7, integerHeap.size());
        integerHeap.clear();
        assertEquals(0, integerHeap.size());
    }

    @Test
    public void testNullPointer(){
      try{
        ArrayList<Integer> alist = new ArrayList<>();
        alist.add(2);
        alist.add(null);
        MyMinHeap<Integer> tester = new MyMinHeap<Integer>(alist);
        fail("was supposed to throw dummy");
      }catch (NullPointerException e){
        assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
      }

      try{
        MyMinHeap<Integer> tester2 = new MyMinHeap<Integer>(null);
      }catch (NullPointerException e){
        assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
      }

      try{
        integerHeap.list = listIntsRegularHeap;
        integerHeap.insert(null);
        fail("was supposed to catch");
      }catch (NullPointerException e){
        assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
      }

    }
}
