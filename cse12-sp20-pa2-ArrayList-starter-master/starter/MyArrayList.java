
/**
 * NAME: Darren Yeung 
 * ID: A15943292 
 * EMAIL: dyeung@ucsd.edu
 * This file contains the class MyArrayList. 
 * This is kind sort of a scuffed version 
 * of the actual arraylist provided by Java.util. 
 */

/**
 * This is the class MyArrayList which is a generic class. This generic class 
 * takes in any object and stores the object in a linear fashion. 
 * It contains the array data which is the back end data structure 
 * and the size of the arrayList which is basically 
 * how many elements are in the list.
 * @param <E> The object the user wishes to store 
 */
public class MyArrayList<E> implements MyList<E> {

  Object[] data;
  int size;

  /**
   * Default constructor that makes the capacity of the ArrayList ten
   */
  public MyArrayList() {
    this.data = new Object[10];
  }

  /**
   * Constructor that takes in a capacity value that will be used 
   * to create the length of the data array 
   * @param initialCapacity length of the array data
   */
  public MyArrayList(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException();
    }
    this.data = new Object[initialCapacity];
  }

  /**
   * Constructor that takes in an array 
   * and copies those elements from the array
   * to the ArrayList. This method also sets 
   * the size of the arraylist to the number 
   * of elements in the array including the nulls. 
   * @param arr The input array the user wishes 
   * to copy its elements from into the
   * arrayList. 
   */
  public MyArrayList(E[] arr) {
    if (arr == null) {
      this.data = new Object[10];
    } else {
      size = arr.length;
      this.data = new Object[arr.length];
      for(int i = 0; i < arr.length; i++){
        this.data[i] = arr[i];
      }
    }
  }

  /**
   * This method checks if the curreny arrayList has the input
   * required capacity. If it does not, double, if current capacity is
   * zero, cannot double so make it 10, if doubling or 10 still not enough, 
   * then we just make the current capacity the input
   * @param requiredCapacity This the required 
   * capacity the user wishes to have 
   * in this arrayList
   */
  public void checkCapacity(int requiredCapacity) {
    if (this.data.length - this.size < requiredCapacity) {
      if (this.data.length == 0) {
        if (10 < requiredCapacity) {
          //Simply makes the capa the input because 10 is not enough
          this.data = new Object[requiredCapacity];
        } else {
          //If enough, capa becomes 10
          this.data = new Object[10];
        }
      } else {
        //if double is not enough, make capa equal to required 
        //and copy elements
        if (this.data.length * 2 < requiredCapacity) {
          Object[] replacer = new Object[requiredCapacity];
          for (int i = 0; i < this.data.length; i++) {
            replacer[i] = this.data[i];
          }
          this.data = replacer;
        } else {
          //if double is enough, simply double capa and copy 
          //elements
          Object[] replacer = new Object[this.data.length * 2];
          for (int i = 0; i < this.data.length; i++) {
            replacer[i] = this.data[i];
          }
          this.data = replacer;
        }
      }
    }
  }

  /**
   * This method returns the current capacity
   * @return Current capacity of the arraylist
   */
  public int getCapacity() {
    return this.data.length;
  }

  /**
   * This method inserts a particular element into the specified index 
   * all elements after the index are pushed back by one
   * @param index the index the user wants to insert the element into 
   * @param element the element the user wants to insert
   */
  public void insert(int index, E element) {
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException();
    }
    int required = this.size + 1;
    if (this.data.length < required) {
      this.checkCapacity(required);
    }
    //shifts elements to the right by one
    for (int i = this.size; i > index; i--) {
      this.data[i] = this.data[i - 1];
    }
    this.data[index] = element;
    this.size++;
  }

  /**
   * This method adds an element to the end of the arrayList 
   * @param element This is the element that is going to be added to the 
   * end of the arrayList
   */
  public void append(E element) {
    int required = this.size + 1;
    if (this.data.length < required) {
      this.checkCapacity(required);
    }
    this.data[this.size] = element;
    this.size++;
  }

  /**
   * This method adds an element to the front of the arrayList
   * @param element This is the element that is going to be added to the 
   * front of the arryList
   */
  public void prepend(E element) {
    int required = this.size + 1;
    if (this.data.length < required) {
      this.checkCapacity(required);
    }
    //shifts element to the right by one
    for (int i = size; i > 0; i--) {
      this.data[i] = this.data[i - 1];
    }
    this.data[0] = element;
    this.size++;
  }

  /**
   * This method returns the element at the specified index and throws 
   * an exception if the index does not make sense
   * @param index The position of the element the user wants to retrieve 
   * @return the Element that is at input index
   */
  public E get(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    return (E) (this.data[index]);
  }

  /**
   * This method sets the index of the arrayList to a particular element and
   * returns the old element that was there
   * @param index The place where the element is going to be changed 
   * @param element the Element that is going to replace the current element of 
   * param index
   * @return The element that was there before the new element took 
   * its place
   */
  public E set(int index, E element) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    Object current = this.data[index];
    this.data[index] = element;
    return (E) current;
  }

  /**
   * This method removes the element specified at the index and shifts 
   * every element after it downward by one
   * @param index The place where the element is going to be removed 
   * @return The element that was removed 
   */
  public E remove(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    this.size--;
    Object removed = this.data[index];
    //shifts elements to the left by one
    for (int i = index; i < this.size; i++) {
      this.data[i] = this.data[i + 1];
    }
    return (E) removed;
  }

  /**
   * This method returns the size of the arrayList or in other words, 
   * the amount of elements in the arrayList 
   * @return the amount of elements in the arrayList
   */
  public int size() {
    return this.size;
  }

  /**
   * This method makes the current capacity of the arrayList fit its size.
   * So if the amount of elements in the arrayList 
   * is 5 and the capacity is 10, 
   * the capacity gets changed to 5. 
   */
  public void trimToSize() {
    Object[] newArray = new Object[this.size];
    for (int i = 0; i < this.size; i++) {
      newArray[i] = this.data[i];
    }
    this.data = newArray;
  }
}