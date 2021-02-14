//I turned in the mid quarter survey 
/**
 * Name: Darren Yeung 
 * PID: A15943292
 * EMAIL: dyeung@ucsd.edu 
 * LOGIN: cs12sp20bef
 * This file cotnains the class MyHashTable. MYHashTable is basically a 
 * scuffed version of the actual HashTable made by Java. It 
 * contains less methods
 * and is basically a subset of HashTable. 
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This is the class MyHashTable which implements from the interface
 * MyHashtTableInterface. It contains the back end data structure which is an
 * Array of LinkedLists. It keeps track of the number of elements, how many
 * times it has been expanded since creation, and number of 
 * collisions since the
 * last expansion. It also has a filename which is the location to print the
 * statistics and also a boolean variable to keep track of whether statistics
 * should be printed or not.
 */
public class MyHashTable implements MyHashTableInterface {

  // Constant used to double the size and do addition
  final static int CONSTANT_TWO = 2;

  LinkedList<String>[] array;// Array that stores linkedlists
  int nelems; // Number of element stored in the hash table
  int expand; // Number of times that the table has been expanded
  int collision; // Number of collisions since last expansion
  String statsFileName; // FilePath for the file to write statistics
  // upon every rehash
  boolean printStats = false; // Boolean to decide whether to write
  // stats to file or not after rehashing
  final static double loadValue = (2.0 / 3.0);

  // Feel free to add more :)
  /**
   * This method is the default constructor for MyHashTable. It
   * takes in the parameter size which is then used to make the size 
   * of the hash table. 
   * @param size The size the hashtable is going to be
   * @throws IllegalArgumentException thrown when size is less than zero 
   */
  public MyHashTable(int size) {
    if (size < 0) {
      throw new IllegalArgumentException();
    } else {
      this.array = new LinkedList[size];
      this.nelems = 0;
      this.collision = 0;
    }
  }

  /**
   * This is the 2nd constructor for MyHashTable. It takes in the 
   * size and the name of the file where we can write the statistics to.
   * @param size The size of the hashtable
   * @param fileName The name of the file where the 
   * statistics will be written to
   */
  public MyHashTable(int size, String fileName) {
    if (size < 0) {
      throw new IllegalArgumentException();
    } else if (fileName == null) {
      throw new NullPointerException();
    } else {
      this.array = new LinkedList[size];
      this.nelems = 0;
      this.collision = 0;
      this.statsFileName = fileName;
      this.printStats = true;
    }
  }

  /**
   * This method inserts a value into the hashtable 
   * @param value The String that is going to be inserted into the 
   * HashTable
   * @return Truth value denoting whether or not the value was inserting 
   * successfully
   */
  @Override
  public boolean insert(String value) {
    if (value == null) {
      throw new NullPointerException();
    }
    // references current to the array position
    if (this.array[this.hashString(value)] != null) {
      ListIterator<String> currentiterator = 
      this.array[this.hashString(value)].listIterator();
      while (currentiterator.hasNext()) {
        if (currentiterator.next().equals(value)) {
          return false;
        }
      }
      if ((((double) (this.nelems + 1)) / this.array.length) >= 
      loadValue) {
        this.rehash();
        if (this.array[this.hashString(value)] == null) {
          this.array[this.hashString(value)] = new 
          LinkedList<String>();
          this.array[this.hashString(value)].add(value);
          this.nelems++;
          return true;
        } else {
          this.array[this.hashString(value)].add(value);
          this.nelems++;
          this.collision++;
          return true;
        }
      }
      this.array[this.hashString(value)].add(value);
      this.collision++;
      this.nelems++;
      return true;
    } else {
      if ((((double) (this.nelems + 1)) / 
      this.array.length) > loadValue) {
        this.rehash();
        if (this.array[this.hashString(value)] == null) {
          this.array[this.hashString(value)] = 
          new LinkedList<String>();
          this.array[this.hashString(value)].add(value);
          this.nelems++;
          return true;
        } else {
          this.array[this.hashString(value)].add(value);
          this.nelems++;
          this.collision++;
          return true;
        }
      }
      this.array[this.hashString(value)] = new 
      LinkedList<String>();
      this.array[this.hashString(value)].add(value);
      this.nelems++;
      return true;
    }
  }

  /**
   * This method delets a string from the hashtable
   * @param value the string that the user wants deleted
   * @return Truth value of whether or not the string was deleted 
   * from the hashtable or not. Returns false if there is no element
   * in there that matches value/
   */
  @Override
  public boolean delete(String value) {

    if (value == null) {
      throw new NullPointerException();
    }
    if (this.array[this.hashString(value)] == null) {
      return false;
    } else {
      ListIterator currentIterator = 
      this.array[this.hashString(value)].listIterator();
      while (currentIterator.hasNext()) {
        if (currentIterator.next().equals(value)) {
          currentIterator.remove();
          if (this.array[this.hashString(value)].size() == 0) {
            this.array[this.hashString(value)] = null;
          }
          this.nelems--;
          return true;
        }
      }
      return false;
    }
  }

  /**
   * This method checks if a particular string
   * is in the hashtable 
   * @param value The string the users wants to check 
   * @return Truth value denoting whether or not the string was found
   * in the hashtable
   * @throws NullPointerException when input string value is null
   */
  @Override
  public boolean contains(String value) {
    if (value == null) {
      throw new NullPointerException();
    }
    
    if (this.array[this.hashString(value)] == null) {
      return false;
    }
    ListIterator<String> currentIterator = 
    this.array[this.hashString(value)].listIterator();
    while (currentIterator.hasNext()) {
      if (currentIterator.next().equals(value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method prints all the elements in the hashtable. If 
   * a slot in the hashtable has more than one element, it prints 
   * those too in the same line
   */
  @Override
  public void printTable() {
    for (int i = 0; i < this.array.length; i++) {
      String line = i + ":";
      if (this.array[i] == null) {
        if(i == this.array.length - 1){
          System.out.print(line);
        }else{
          System.out.println(line);
        }
      } else {
        line += " ";
        LinkedList<String> current = this.array[i];
        ListIterator<String> currentIterator =
         current.listIterator();
        while (currentIterator.hasNext()) {
          if (currentIterator.nextIndex() == 
          current.size() - 1) {
            line += currentIterator.next();
          } else {
            line += currentIterator.next() + ", ";
          }
        }
        if (i == this.array.length - 1) {
          System.out.print(line);
        } else {
          System.out.println(line);
        }
      }
    }
  }

  /**
   * This method returns the number of elements in the 
   * HashTable 
   * @return int value denoting the elements in the hashtable
   */
  @Override
  public int getSize() {
    return this.nelems;
  }

  /**
   * This method resizes the hashtable and rehashes everything and 
   * then inserts it back into the hashtable. This is done when the 
   * load factor is greater than 2/3. The size of the hashtable 
   * gets resized to the first prime number greater than double
   * the previous size
   */
  @Override
  @SuppressWarnings("unchecked")
  public void rehash() {
    this.nelems = 0; 
    this.collision = 0; 
    LinkedList<String>[] previousArray = this.array;
    this.array = new LinkedList[this.primeGen()];
    for (int i = 0; i < previousArray.length; i++) {
      if (previousArray[i] != null) {
        ListIterator<String> currentIterator = 
        previousArray[i].listIterator();
        while (currentIterator.hasNext()) {
          this.insert(currentIterator.next());
        }
      }
    }
    this.expand++;
  }

  /**
   * Calculate the hash value of a given string
   * 
   * @param str the string value
   * @return the hash value
   */
  public int hashString(String str) {
    char[] storage = new char[str.length()];
    for (int i = 0; i < str.length(); i++) {
      storage[i] = str.charAt(i);
    }
    int h = 0;
    int highorder;
    for (int i = 0; i < storage.length; i++) {
      highorder = h & 0xf8000000;
      h = h << 5;
      h = h ^ (highorder >>> 27);
      h = h ^ (int) storage[i];
    }
    return (Math.abs(h) % (this.array.length));
  }

  /**
   * Print statistics to the given file.
   * @return True if successfully printed statistics, false if the file could not
   * be opened/created.
   */
  @Override
  public boolean printStatistics() {
    PrintStream out;
    try {
      out = new PrintStream(new FileOutputStream(this.statsFileName, true));
    } catch (FileNotFoundException e) {
      return false;
    }
    out.print(this.expand + " resizes, ");// Print resize times
    // Calculate the load factor
    double loadFactor = ((double) nelems / array.length);
    DecimalFormat df = new DecimalFormat("#.##"); // Print the load factor
    out.print("load factor " + df.format(loadFactor) + ", ");
    out.print(this.collision + " collisions, "); // Print collision times
    int length = 0;
    for (int i = 0; i < this.array.length; i++) {
      if (this.array[i] != null && this.array[i].size() > length)
        length = this.array[i].size();
    }
    // Print the length of the longest chain
    out.println(length + " longest chain");
    out.close();
    return true;
  }

  /**
   * Generate a prime number that is close to the double of current array size
   * @return a prime number used as array size
   */
  private int primeGen() {
    boolean isPrime = false;
    int num = array.length * CONSTANT_TWO;// Double the size

    /*
     * Generate next prime number that is greater than the double of current array
     * size
     */
    while (!isPrime) {
      num++;
      /*
       * Try divides the number with all numbers greater than two and less than or
       * equal to the square root of itself
       */
      for (int divisor = CONSTANT_TWO; divisor <= Math.sqrt(num); divisor++) {
        if (num % divisor == 0)// The number is divisible
          break;// No need for further testing, break inner loop
        if (divisor == (int) Math.sqrt(num))// The number is indivisible
          isPrime = true;// Then it is a prime
      }
    }
    return num;
  }

}
