/**
 * NAME: Darren Yeung 
 * EMAIL: dyeung@ucsd.edu
 * PID: A15943292
 * This is the file MyPriorityQueue.java. It contains the single class 
 * MyPriorityQueue
 */

 /**
  * This is the generic class MyPriorityQueue where the generic implements the 
  * comparable interface. It contains one instance variable which is MyMinHeap
  */
import java.util.Collection; 

public class MyPriorityQueue<E extends Comparable<E>>{
  protected MyMinHeap<E> heap; 

  /**
   * Default constructor of MyPriorityQueue.
   */
  public MyPriorityQueue(){
    this.heap = new MyMinHeap<>(); 
  }

  /**
   * This is the second constructor of MyPriorityQueue that makes 
   * a MyMinHeap based on the collection given
   * @param collection the collection the user wants tranformed into a prioqueue
   * @throws NullPointerException when collection is null or there is element in the collection
   * that is null
   */
  public MyPriorityQueue(Collection<? extends E> collection){
    if(collection == null){
      throw new NullPointerException();
    }
    if(collection.contains(null)){
      throw new NullPointerException(); 
    }
    this.heap = new MyMinHeap<>(collection); 
  }
  
  /**
   * This method pushes an element into the que and is adjusted based on its priority
   * @param element the element the user wants entered into the prioqueue. 
   * @throws NullPointerExpcetion when element is null
   */
  public void push(E element){
    if(element == null){
      throw new NullPointerException(); 
    }else{
      this.heap.insert(element); 
    }
  }
  
  /**
   * This method returns the smallest or basically the first element in the prioqueue
   * @return smallest element since smallest has highest prio in this case
   */
  public E peek(){
    if(this.heap.size() == 0){
      return null; 
    }else{
      return this.heap.getMin(); 
    }
  }

  /**
   * This method removes the first element from the prioqueue which is also the
   * smallest element
   * @return the smallest element in the queue
   */
  public E pop(){
    if(this.heap.size() == 0){
      return null; 
    }else{
      E stored = this.heap.getMin(); 
      this.heap.remove();
      return stored; 
    }
  }

  /**
   * This method returns the size of the prioqueue
   * @return the number of elements in the priorityqueue
   */
  public int getLength(){
    return this.heap.size();
  }
  
  /**
   * This method removes everything from the prioqueue
   */
  public void clear(){
    this.heap.clear(); 
  }
}