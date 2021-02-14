/**
 * NAME: Darren Yeung
 * PID: A15943292
 * EMAIL: dyeung@ucsd.edu
 * This is the file MyQueue.java. It contains the single class 
 * MyQueue.
 */

/**
 * This is the class MyQueue. It is a generic class that implements 
 * QueueInterface. It basically cheats by using one instance object 
 * which is a deque and uses only some of its methods
 * @param <E>
 */
public class MyQueue<E> implements QueueInterface<E>{
  
  MyDeque<E> theQueue;

  /**
   * This is the constructor for MyQueue. It takes in the initial capacity 
   * of the queue and sets the capacity of theQueue to it
   * @param The capacity the user wants the queue to be
   */
  public MyQueue(int initialCapacity){
    theQueue = new MyDeque<>(initialCapacity);
  }

  /**
   * This method checks whether or not the queue is empty 
   * @return Boolean value dictating whether or not the queue is empty
   */
  public boolean empty(){
    if(theQueue.size() == 0){
      return true; 
    }else{
      return false; 
    }
  }

  /**
   * This method adds an element to the back of the queue 
   * @param element the user wants to insert into the queue
   */
  public void enqueue(E e){
    theQueue.addLast(e);
  }

  /**
   * This method removes the element in the front of the queue 
   * @return the element that was just removed from the queue 
   */
  public E dequeue(){
    if(this.empty() == true){
      return null;
    }else{
      E stored = theQueue.peekFirst();
      theQueue.removeFirst();
      return stored; 
    }
  }

  /**
   * This method returns the element in the front of the queue
   * without making any changes to the queue
   * @return the first element in the queue 
   */
  public E peek(){
    return theQueue.peekFirst();
  }
}