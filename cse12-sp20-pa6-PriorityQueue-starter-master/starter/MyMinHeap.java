/**
 * NAME: Darren Yeung 
 * EMAIL: dyeung@ucsd.edu
 * PID: A15943292
 * This is the MyMinHeap.java file. It contains the single class 
 * MyMinHeap
 */

 /**
  * This is the generic class MyMinHeap where the generic implements the 
  * comparble interface. It contains one instance variable which is an arraylist 
  */
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class MyMinHeap<E extends Comparable<E>>
{
    protected List<E> list; 
     
    /**
     * This is the default constructor for MyMinHeap 
     */
    public MyMinHeap() {
      this.list = new ArrayList<E>();
    }
    
    /**
     * This is the second constructor. It takes in a collection and organizes all those
     * elements in to a min heap structure 
     * @param collection the collection the user wants to be made into a
     * heap
     * @throws NullPointerException when collection is null or the collection
     * contains a null element
     */
    public MyMinHeap(Collection<? extends E> collection) {
      if(collection == null){
        throw new NullPointerException();
      }
      if(collection.contains(null)){
        throw new NullPointerException();
      }
      this.list = new ArrayList<>(collection);
      for(int i = 0; i < this.list.size(); i++){
        this.percolateDown(i);
      }
    }

    /**
     * This construtor swaps two elements from the list
     * @param from first location of element
     * @param to second location of element
     */
    protected void swap(int from, int to) {
      E stored = this.list.get(from);
      this.list.set(from, this.list.get(to));
      this.list.set(to, stored);
    }

    /**
     * This method returns the index of the parent given an index 
     * of a child node 
     * @param index the child node's index
     * @return the index of the parent node
     */
    protected int getParentIdx(int index) {
      return (index-1)/2;
    }

    /**
     * This method returns the index of the left child
     * @param index the node the user wants to the left child of 
     * @return the index of the nodes left child
     */
    protected int getLeftChildIdx(int index) {
      return (2*index) + 1; 
    }

    /**
     * THis method returns the index of the right child
     * @param index the node the user wants to the right child of 
     * @return the index of the nodes right child
     */
    protected int getRightChildIdx(int index) {
      return (2 * index) + 2; 
    }

    /**
     * This method returns the index of the smaller child
     * @param index the node the user wants the smallest child of 
     * @return the index of the smaller child node 
     */
    protected int getMinChildIdx(int index) {
      if(this.getLeftChildIdx(index) == this.list.size() - 1){
        return this.getLeftChildIdx((index)); 
      }else{
        E leftValue = this.list.get(this.getLeftChildIdx(index)); 
        E rightValue = this.list.get(this.getRightChildIdx(index)); 
        if(rightValue.compareTo(leftValue) > 0){
          return (this.getLeftChildIdx(index)); 
        }else if (rightValue.compareTo(leftValue) == 0){
          return this.getLeftChildIdx(index);
        }else{
          return this.getRightChildIdx(index);
        }
      }
    }

    /**
     * This method moves an element up until it doesn't violate the minheap 
     * structure
     * @param index The index of the node the user wants to move up
     */
    protected void percolateUp(int index) {
      int parentIndex = this.getParentIdx(index); 

      E parentValue = this.list.get(parentIndex); 
      E thisValue = this.list.get(index); 
      if(thisValue.compareTo(parentValue) < 0){
        this.swap(index, parentIndex); 
        this.percolateUp(parentIndex);
      }
    }

    /**
     * This method moves an element down until it doesn't violate the minheap 
     * structure 
     * @param index The index of the node the user wants to move down 
     */
    protected void percolateDown(int index) {
      E thisValue = this.list.get(index); 
      if(!(this.getLeftChildIdx(index) >= this.list.size())){
        if(this.getLeftChildIdx(index) == this.list.size() - 1){
          int leftIndex = this.getLeftChildIdx(index);
          E leftValue = this.list.get(leftIndex); 
          if(thisValue.compareTo(leftValue) > 0){
            this.swap(index, leftIndex);
          }
        }else{
          if(thisValue.compareTo(this.list.get(this.getMinChildIdx(index))) > 0){
            int stored = this.getMinChildIdx(index);
            this.swap(index, stored);
            this.percolateDown(stored);
          }
        }
      }
    } 

    /**
     * This method deletes an element at the particular index 
     * @param index the place of the element the user wants deleted
     * @return the deleted element
     */
    protected E deleteIndex(int index) {
      if(index == this.list.size() - 1){
        E stored = this.list.get(this.list.size() -1);
        this.list.remove(this.list.size() - 1);
        return stored; 
      }else{
        E stored = this.list.get(index); 
        E replacement = this.list.get(list.size() - 1); 
        this.swap(index, this.list.size() - 1); 
        this.list.remove(this.list.size() - 1); 
        if(replacement.compareTo(stored) < 0){
          this.percolateUp(index);
        }else if(replacement.compareTo(stored) > 0){
          this.percolateDown(index);
        }
        return stored; 
      }
    }

    /**
     * This method inserts an element into the minheap
     * @param element the element the user wants inserted
     * @throws NullPointerException when element is null
     */
    public void insert(E element) {
      if(element == null){
        throw new NullPointerException();
      }
      this.list.add(element); 
      this.percolateUp(this.list.size() - 1);
    }

    /**
     * This method returns the first element of the minheap, which is the 
     * smallest element 
     * @return The smallest element in the heap
     */
    public E getMin() {
      if(this.list.size() == 0){
        return null;
      }else{
        return(this.list.get(0)); 
      }
    }

    /**
     * This method removes the first or smallest element in the minheap
     * @return the first or smallest element in the minheap
     */
    public E remove() {
      if(this.list.size() == 0){
        return null; 
      }else{
        E stored = this.list.get(0); 
        this.deleteIndex(0); 
        return stored; 
      }
    }

    /**
     * This method returns the number of elements in the heap
     * @return number of elements in the minheap
     */
    public int size() {
      return this.list.size(); 
    }

    /**
     * This method deletes everything from the minheap data 
     */
    public void clear() {
      this.list.clear(); 
    }		
  }