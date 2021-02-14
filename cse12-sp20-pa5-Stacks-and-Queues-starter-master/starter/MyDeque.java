/**
 * NAME: Darren Yeung 
 * PID: A15943292
 * EMAIL: dyeung@ucsd.edu
 * This file is MyDeque.java. It contains the single class MyDeque .
 */

 /**
  * This is the class MyDeque which is a generic class. It implements the 
  * DequeInterface. It has four instance varaibles. One is the back end 
  * data structure which is the array, one size, and front and rear is used 
  * to keep track of the index of the front and the rear. 
  * @param <E>
  */
public class MyDeque<E> implements DequeInterface<E>{
  Object[] data; 
  int size; 
  int rear; 
  int front;

  /**
   * This is the constructor for MyDeque. It takes in the initialcapacity 
   * and initiates data to that number. 
   * @param initialCapacity The initialcapacity the user wants their Deque to be. 
   */
  public MyDeque(int initialCapacity){
    if(initialCapacity < 0){
      throw new IllegalArgumentException();
    }
    this.data = new Object[initialCapacity];
    this.size = 0; 
    this.rear = 0; 
    this.front = 0; 
  }

  /**
   * This method returns the size of the Deque (not the capacity, the
   * amount of elements)
   * @return the size of the Deque
   */
  public int size(){
    return this.size; 
  }

  /**
   * This method doubles the capacity of the deque, makes front start from 0, 
   * and rear start from size -1. If capacity was at 0, we make the capacity 10
   * and move on with our lives. 
   */
  public void expandCapacity(){
    if(this.data.length == 0){
      this.data = new Object[10]; 
    }else{
      Object[] newArray = new Object[this.data.length * 2];
      int newArrayCurrent = 0; //keeps track of newarray index
      if(this.front > this.rear){
        //goes from front to end
        for(int i = this.front; i < this.data.length; i++){
          newArray[newArrayCurrent] = this.data[i]; 
          newArrayCurrent++;
        }
        //then to 0 to rear 
        for(int j = 0; j <= this.rear; j++){
          newArray[newArrayCurrent] = this.data[j]; 
          newArrayCurrent++; 
        }
        this.data = newArray; 
        this.front = 0; 
        this.rear = this.size -1; 
      }else if(this.front < this.rear){
        //goes from front to rear
        for(int i = this.front; i <= this.rear; i++){
          newArray[newArrayCurrent] = this.data[i]; 
          newArrayCurrent++;
        }
        this.front = 0; 
        this.rear = this.size -1; 
        this.data = newArray; 
        // only way to fall into this else is if size is 0 
        // if size is 1
      }else{
        //makes data double its length only
        if(this.size == 0){
          this.rear = 0;
          this.front = 0; 
          this.data = newArray; 
        }else{
          // if size is 1, then just trasfer one element
          newArray[0] = this.data[this.front];
          this.rear = 0; 
          this.front = 0; 
          this.data = newArray; 
        }
      }
    }
  }

  /**
   * This method adds an element the front of the deque
   * @param the element the user wants to add to the front
   */
  public void addFirst(E element){
    if(element == null){
      throw new NullPointerException(); 
    }
    if(this.size == this.data.length){
      this.expandCapacity();
    }
    if(this.size == 0){
      this.data[0] = element; 
    }else if(this.front == 0){
      this.data[this.data.length -1] = element; 
      this.front = this.data.length -1;
    }else{
      this.data[this.front - 1] = element; 
      this.front --; 
    }
    this.size ++;
  }
  
  /**
   * This method adds an element to the back of the deque 
   * @param element the user wants to add to the back
   */
  public void addLast(E element){
    if(element == null){
      throw new NullPointerException();
    }
    if(this.size == this.data.length){
      this.expandCapacity();
    }
    if(this.size == 0){
      this.data[0] = element; 
    }else if(this.rear == this.data.length -1){
      this.data[0] = element; 
      this.rear = 0;
    }else{
      this.data[this.rear + 1] = element; 
      this.rear++;
    }
    this.size++;
  }
  
  /**
   * This element removes the element in the front of the 
   * deque
   * @return element that was removed 
   */
  public E removeFirst(){
    if(this.size == 0){
      return null;
    }
    E stored = (E)this.data[this.front];
    if(this.front == this.data.length -1){
      this.data[front] = null; 
      this.size--; 
      this.front = 0; 
    }else{
      this.data[front] = null; 
      this.size--; 
      if(this.size == 0){
        return stored;
      }else{
        this.front++; 
      }
    }
    if(this.size == 0){
      this.rear = 0; 
    }
    return stored;
  }

  /**
   * This method removes an element from behind.
   * @return element that was removed
   */
  public E removeLast(){
    if(this.size == 0){
      return null; 
    }
    E stored = (E)this.data[this.rear];
    if(this.rear == 0){
      this.data[this.rear] = null; 
      this.size--;
      if(this.size == 0){
        return stored; 
      }else{
        this.rear = this.data.length -1; 
      }
    }else{
      this.data[this.rear] = null; 
      this.rear --; 
      this.size--; 
    }
    if(this.size == 0){
      this.front = 0; 
    }
    return stored; 
  }
  
  /**
   * This method returns the first element without 
   * making changes to the deque
   * @return element in the front of the deque
   */
  public E peekFirst(){
    if(this.size == 0){
      return null; 
    }else{
      return (E)this.data[this.front];
    }
  }

  /**
   * This method returns the last element without
   * making changes to the deque
   * @return element in the back of the deque
   */
  public E peekLast(){
    if(this.size == 0){
      return null; 
    }else{
      return (E)this.data[this.rear];
    }
  }

}