
/**
 * Name: Darren Yeung
 * ID: A15943292
 * EMAIL: dyeung@ucsd.edu 
 * This file contains the Tester class for MyArrayList.java. Using junit, 
 * it makes sures that the methods in MyArrayList are working as expected. 
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Arrays;

import org.junit.*;

import jdk.jfr.Timestamp;

/**
 * This is the class MyArrayListTester which Tests all functions in MyArrayList.
 * There are six instances of MyArrayList which are formed using one of the
 * three different constructors and are tested using the methods.
 */
public class MyArrayListTester {

  static final int DEFAULT_CAPACITY = 10;
  static final int MY_CAPACITY = 3;

  Object[] arr = new Object[10];
  Integer[] arrInts = { 1, 2, 3 };

  private MyArrayList list1, list2, list3, list4, list5, listzero;

  /**
   * This method sets up the six MyArrayLists using one of the three differenet
   * constructors. Some lists have a different starting capacity and a different
   * size because it takes in an array with elements
   * @throws Exception Thrown when in invalid input is passed into the
   * constructor
   */
  @Before
  public void setUp() throws Exception {
    list1 = new MyArrayList();
    list2 = new MyArrayList(DEFAULT_CAPACITY);
    list3 = new MyArrayList(MY_CAPACITY);
    list4 = new MyArrayList(arr);
    list5 = new MyArrayList<Integer>(arrInts);
    listzero = new MyArrayList(0);
  }

  /**
   * This method tests whether the MyArrayList class does it job when an invalid
   * input is put into the constructor.
   */
  @Test
  public void testInvalidConstructor() {
    try {
      MyArrayList<Integer> invalid = new MyArrayList<Integer>(-1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Pass
    }
  }

  /**
   * This method tests whether all the sizes are current upon creation of the
   * MyArrayList instances.
   */
  @Test
  public void testDefaultSize() {
    assertEquals("Check size for default constructor", 0, 
    list1.size());
    assertEquals("Check size for constructor with given capacity of 10", 0,
     list2.size());
    assertEquals("Check size for constructor with given capacity of 3", 0, 
    list3.size());
    assertEquals("Check size for constructor with given array", 10, list4.size());
    assertEquals("Check size for constructor with given int array", 3, 
    list5.size());
  }

  /**
   * This method tests whether the initial capacities are correct upon creating
   * the MyArrayList instances.
   */
  @Test
  public void testInitialCapacity() {
    assertEquals("Check default capacity", DEFAULT_CAPACITY, 
    list1.getCapacity());
    assertEquals("Check capacity for list2", DEFAULT_CAPACITY, 
    list2.getCapacity());
    assertEquals("Check given capacity", MY_CAPACITY,
     list3.getCapacity());
    assertEquals("Check capacity for list4", DEFAULT_CAPACITY,
     list4.getCapacity());
    assertEquals("Check capacity for list5", MY_CAPACITY, 
    list5.getCapacity());
  }

  /**
   * This method tests whether the capacity gets updated correctly upon
   * manupilation
   */
  @Test
  public void testCheckCapacity() {
    list3.checkCapacity(4);
    assertEquals("make sure capacity doubles", 6,
     list3.getCapacity());
    // should be six now
    list3.checkCapacity(13);
    assertEquals("Make sure capacity is 13 because doubling did not meet requirement", 13,
     list3.getCapacity());
    list3.checkCapacity(0);
    assertEquals("Make sure method did not do anything if requirement was met", 13, 
    list3.getCapacity());
    listzero.checkCapacity(9);
    assertEquals("Make sure capacity is now 10 because the double 0 makes zero", 
    10, listzero.getCapacity());
    listzero = new MyArrayList(0);
    listzero.checkCapacity(12);
    assertEquals("make sure capacity is now required because 10 was not enough",
     12, listzero.getCapacity());
  }

  /**
   * This method tests whether the append method works correctly
   */
  @Test
  public void testAppend() {
    int[] nums = { 2, 4 };
    list1.append(nums[0]);
    assertEquals("Check that append increments size",
     1, list1.size());
    list1.append(nums[1]);
    assertEquals("Check that capacity is unchanged", DEFAULT_CAPACITY, 
    list1.getCapacity());
    assertEquals("Check that value is added", 
    2, list1.get(0));
    assertEquals("Check that value is added",
     4, list1.get(1));
    list1.append(1);
    list1.append(10);
    assertEquals("Check that values are preserved when updating capacity", 
    2, list1.get(0));
    list3.append(1);
    list3.append(1);
    list3.append(1);
    assertEquals("Check that size of array is three", 3,
     list3.size());
    assertEquals("Check that capacity is not yet updated", 3,
     list3.getCapacity());
    list3.append(1);
    assertEquals("Check that fourth element is added", 1, 
    list3.get(3));
    assertEquals("Check that size is updated", 4, 
    list3.size());
    assertEquals("check that capacity is doubled", 6,
     list3.getCapacity());
    listzero.append(1);
    assertEquals("Check size is updated", 1,
     listzero.size());
    assertEquals("Check capacity is updated to 10", 10, 
    listzero.getCapacity());
    assertEquals("Check element is added", 1,
     listzero.get(0));
    listzero.append(null);
    assertEquals("Check null is valid", 2, 
    listzero.size());

  }

  /**
   * This method tests whether or not the insert method works correctly
   */
  @Test
  public void testInsert() {
    list1.append(1);
    list1.append(5);
    list1.append(6);
    list1.insert(2, 3);
    assertEquals("Check index contains inserted value", 3,
     list1.get(2));
    // (1,5,6) becomes (1,5,3,6)
    assertEquals("Check elements were shifted down", 6,
     list1.get(3));
    assertEquals("Check that elements in the front remain the same", 1, list1.get(0));
    assertEquals("Check that elements in the front remain the same", 5,
     list1.get(1));
    assertEquals("Check that size is updated", 4, list1.size());
    list1.insert(4, 10);
    assertEquals("Checl that insert works when index is not in the middle", 10,
     list1.get(4));
    list3.append(1);
    list3.append(2);
    list3.append(3);
    list3.insert(2, 4);
    assertEquals("Check that 4 was inserted", 4, list3.get(2));
    // (1,2,3) becomes (1,2,4,3)
    assertEquals("Check size was updated", 4, list3.size());
    assertEquals("Check capa was doubled", 6, list3.getCapacity());
    list3.insert(0, 10);
    assertEquals("Check can insert in the beginning", 10, list3.get(0));
    assertEquals("Check elements are shifted down", 3, list3.get(4));
    list3 = new MyArrayList(2);
    list3.insert(0, 1);
    list3.insert(1, 2);
    list3.insert(2, 3);
    assertEquals("Check that size is updated", 3, list3.size());
    assertEquals("Check that capa is doubled to four", 4, list3.getCapacity());
    assertEquals("Check third element is added", 3, list3.get(2));
    list3.insert(1, null);
    assertEquals("Check that null is valid", null, list3.get(1));
    assertEquals("Check size", 4, list3.size());
    // (1,2,3) becomes (1,null,2,3)
    assertEquals("Check elements are shifted down", 2, list3.get(2));
    assertEquals("Check elements ar shifted down", 3, list3.get(3));
    assertEquals("Check first element remains the same", 1, list3.get(0));

  }

  /**
   * This method testse whether or not the prepend method works correctly
   */
  @Test
  public void testPrepend() {
    String[] strings = { "hello", "darren", "gerald", "cao", "gary", "logitech" };
    list1.append(strings[0]);
    list1.append(strings[1]);
    list1.append(strings[2]);
    list1.prepend(strings[3]);
    assertEquals("Check that size is updated correctly", 4, 
    list1.size());
    assertEquals("Check that fourth element is prepended", "cao", list1.get(0));
    assertEquals("Check that elements are shifted downward correctly",
     "hello", list1.get(1));
    assertEquals("Check that elements are shifted downward correctly", 
    "darren", list1.get(2));
    assertEquals("Check that elements are shifted downward correctly",
     "gerald", list1.get(3));
    list3.prepend(1);
    list3.prepend(2);
    list3.prepend(3);
    // expeceted (3,2,1)
    assertEquals("Check if first element is three", 3, list3.get(0));
    assertEquals("Check if second element is two", 2, list3.get(1));
    assertEquals("Check if third element is one", 1, list3.get(2));
    list3.prepend(null);
    assertEquals("Check if capa was  updated to 6", 6, list3.getCapacity());
    assertEquals("Check if null is valid element", 4, list3.size());
    assertEquals("Check if first element is null", null, list3.get(0));
    assertEquals("check if elements are preserved", 3, list3.get(1));
    assertEquals("check if elements are preserved", 2, list3.get(2));
    assertEquals("check if elements are preserved", 1, list3.get(3));
  }

  /**
   * This method tests whether or not the get method works properly
   */
  @Test
  public void testGet() {
    list3.append(1);
    list3.append(2);
    list3.append(3);
    list3.append(10);
    list3.append(null);
    assertEquals("Check if get works index with element", 1, 
    list3.get(0));
    assertEquals("Check if get works with index with valid null", 
    null, list4.get(3));
    assertEquals("Check if get works with index with actual blank space",
     null, list3.get(4));
  }

  /**
   * This method tests whether or not the set method works properly
   */
  @Test
  public void testSet() {
    listzero.append(1);
    listzero.append(2);
    listzero.append(3);
    listzero.append(4);
    listzero.append(5);
    listzero.append(6);
    assertEquals("Make sure current element is there", 4, listzero.get(3));
    listzero.set(3, 100);
    assertEquals("Make sure element was changed", 100, 
    listzero.get(3));
    listzero.set(3, null);
    assertEquals("Make sure size does not get changed",
     6, listzero.size());
    assertEquals("Make sure element is null", null, listzero.get(3));
    list3.append(1);
    list3.append(2);
    list3.append(3);
    Integer expected = new Integer(2);
    Integer override = (Integer) list3.set(1, 10);
    assertEquals("Make sure method returns correct overridden value", expected, override);
  }

  /**
   * This method tests whether or not the remove method works properly
   */
  @Test
  public void testRemove() {
    list3.append(1);
    list3.append(2);
    list3.append(3);
    list3.append(4);
    list3.append(5);
    list3.append(6);
    list3.append(7);
    list3.remove(3);
    // (1,2,3,4,5,6,null,null,null,null) becomes
    // (1,2,3,5,6,null,null,null,null,null);
    assertEquals("Test if fourth element is changed to five", 5, 
    list3.get(3));
    assertEquals("Test if elements are shifted", 6,
     list3.get(4));
    assertEquals("Test if elements are shifted", 7,
     list3.get(5));
    list3.remove(5);
    assertEquals("Check if last element got removed", 6, list3.get(4));
    list3.remove(0);
    // should be this 2,3,5,6
    assertEquals("Check if first element gets removed properly", 2, 
    list3.get(0));
    assertEquals("Check if size is updated correctly", 4, list3.size());
    assertEquals("Check if rest of elements remain the same",
     3, list3.get(1));
    assertEquals("Check if rest of elements remain the same",
     5, list3.get(2));
    assertEquals("Check if rest of elements remain the same",
     6, list3.get(3));
    listzero.append(null);
    listzero.append(1);
    listzero.append(2);
    Integer removed = (Integer) listzero.remove(0);
    assertEquals("Make sure removed element gets returned", null, removed);

  }

  /**
   * This method tests whether or not the size method works properly
   */
  @Test
  public void testSize() {
    list1.append(1);
    list1.append(1);
    list1.append(null);
    assertEquals("Make sure null is valid", 3, list1.size());
  }

  /**
   * This method tests whehter or not the trimtosize method works properly
   */
  @Test
  public void testTrimToSize() {
    list1.append(1);
    list1.append(2);
    list1.append(null);
    assertEquals("Make sure capacity is 10", 10,
     list1.getCapacity());
    list1.trimToSize();
    assertEquals("Test if capaciity got trimmed to size", 3,
     list1.getCapacity());
    list3 = new MyArrayList(0);
    list3.trimToSize();
    assertEquals("Test what happens if capacity is 0", 0, 
    list3.getCapacity());
  }

  /**
   * This method tests whether or not the exception is thrown correctly in the
   * insert method
   */
  @Test
  public void testInsertExceptions() {
    // test exception for insert method
    list3.append(1);
    list3.append(2);
    list3.append(3);
    list3.append(4);
    list3.append(5);
    try {
      list3.insert(-1, 3);
      fail("IndexOutOfBounds expected");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }

    try {
      list3.insert(-2, 3);
      fail("IndexOutOfBounds expected");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }

    try {
      list3.insert(6, 6);
      fail("IndexOutOfBounds expected");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }

    try {
      list3.insert(1, 2);
    } catch (IndexOutOfBoundsException e) {
      fail("Shouldnt have thrown an exception");
    }
  }

  /**
   * This method tests whether or not hte exception is thrown properly in the
   * get method
   */
  @Test
  public void testGetExceptions() {
    list3.append(1);
    list3.append(2);
    list3.append(3);

    try {
      list3.get(0);
    } catch (IndexOutOfBoundsException e) {
      fail("Should not have thrown exception");
    }
    try {
      list3.get(-1);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }

    try {
      list3.get(3);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }

    try {
      list3.get(4);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }

    try {
      list3.get(5);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }
  }

  /**
   * This method tests whether or not the exceptions are thrown properly in the
   * set method
   */
  @Test
  public void testSetExceptions() {
    list3.append(1);
    list3.append(3);
    list3.append(5);
    list3.append(7);
    list3.append(null);

    try {
      list3.set(0, 1);
      list3.set(1, 3);
    } catch (IndexOutOfBoundsException e) {
      fail("Should not have thrown exception");
    }

    try {
      list3.set(-1, 2);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }

    try {
      list3.set(5, 2);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }

    try {
      list3.set(6, 2);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
      // pass
    }
  }

  /**
   * This method tests whether or not the exception is thrown properly in the
   * remove method
   */
  @Test
  public void testRemoveExceptions() {
    listzero.append(1);
    listzero.append(2);
    listzero.append(3);
    listzero.append(4);
    listzero.append(5);
    listzero.append(null);

    try {
      listzero.remove(0);
    } catch (IndexOutOfBoundsException e) {
      fail("Should not have thrown an exception");
    }

    try {
      listzero.remove(-1);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
    }

    try {
      listzero.remove(5);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
    }

    try {
      listzero.remove(6);
      fail("Should have thrown exception");
    } catch (IndexOutOfBoundsException e) {
    }
  }

  /**
   * This method tests whether or not the constructors are working properly
   */
  @Test
  public void testConstructors() {
    MyArrayList<String> alpha = new MyArrayList<String>();
    assertEquals("Testing to see if size is correct", 0,
     alpha.size());
    assertEquals("Tseting to see if capacity is correct", 
    10, alpha.getCapacity());
    MyArrayList<String> beta = new MyArrayList<String>(10);
    assertEquals("Testing to see if size is correct", 0,
     beta.size());
    assertEquals("Testing ot see if capacity is correct", 10, beta.getCapacity());
    Integer[] array = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 };
    MyArrayList<Integer> charlie = new MyArrayList<Integer>(array);
    assertEquals("Testing to see if size is correct", 8,
     charlie.size());
    assertEquals("Testing to see if capacity is correct", 8,
     charlie.getCapacity());
    Integer[] array2 = null;
    MyArrayList<Integer> delta = new MyArrayList<Integer>(array2);
    assertEquals("Testing to see if capacity is 10 with null array as input",
     10, delta.getCapacity());
  }
}
