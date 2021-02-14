/**
 * NAME: Darren Yeung 
 * PID: A15943292
 * EMAIL: dyeung@ucsd.edu 
 * 
 * This is the file MyStack.java. It contains the single class 
 * MyStack. 
 */

 /**
  * This is the class MyStack. It is a generic class that implements
  * the stackinterface. It has one instance variable which is MyDeque and 
  * it basically cheats the system by only using some of the methods from 
  * deque
  */
public class MyStack<E> implements StackInterface<E>{

  MyDeque<E> theStack; 

  /**
   * This is the one and only constructor for Stack. It takes in an initial 
   * capacity and makes theStack that capacity.
   * @param initialCapacity The capacity the user wants their stack to be
   */
  public MyStack(int initialCapacity){
    theStack = new MyDeque<E>(initialCapacity);
  }

  /**
   * This method checks if the stack is empty or not 
   * @return boolean value that stands for the truth value of whehter or 
   * not the stack is empty 
   */
  public boolean empty(){
    if(this.theStack.size() == 0){
      return true; 
    }else{
      return false; 
    }
  }

  /**
   * This method pushes an element onto the stack. It uses the dequeue
   * object to do that and uses its addfirst method. 
   */
  public void push(E e){
    this.theStack.addLast(e);
  }

  /**
   * This method removes the element from the top of the stack and returns
   * the element that was removed 
   * @return Element that was just removed
   */
  public E pop(){
    if(this.empty() == true){
      return null; 
    }
    E stored = this.theStack.peekLast();
    this.theStack.removeLast();
    return stored; 
  }

  /**
   * This method returns the element on the top of the stack
   * without making changes to the stack itself
   */
  public E peek(){
    return this.theStack.peekLast();
  }

}
