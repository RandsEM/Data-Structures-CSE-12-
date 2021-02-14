/** 
 *  Name: Darren Yeung 
 *  PID: A15943292 
 *  Email: dyeung@ucsd.edu
 *  ACCOUNT: cs12sp20bef
 *  This file contains the generic class MyLinkedList which contains
 *  inner classes. The inner classes should be used only with
 *  this class. There are two inner classes, one of them is Node 
 *  and other one is MyListIterator
 */

import java.util.*;

/** 
 *  This is the Class MyLinkedList. It contains three instance 
 *  variables. nelems keeps track of the size and we have
 *  head tail node.
 */

public class MyLinkedList<E> extends AbstractList<E> { 

	int nelems;
	Node head;
	Node tail;

  /**
   * This is the inner class Node which contains three instance 
   * variables. Each node contains some information, a reference to the 
   * next node, and a reference to the previous node. 
   */
	protected class Node {
		E data;
		Node next;
		Node prev;

    /**
     * This is the default constructor for a Node. It sets
     * the information of the Node to the user input
     * @param element The thing that is going to be used for
     * this nodes data
     */
		public Node(E element)
		{
      this.data = element; 
    }
    
    /**
     * This sets the previous reference of this node 
     * to the input
     * @param p The node that is going to be the previous
     * of this node 
     */
		public void setPrev(Node p)
		{
			this.prev = p; 
    }
    
    /**
     * This method sets the next reference of this node 
     * to the input 
     * @param n The node that is going to be the next 
     * of this node 
     */
		public void setNext(Node n)
		{
			this.next = n; 
		}

    /**
     * This method sets the data of this node 
     * to the user input
     * @param e The element that is going to be the 
     * data of this node
     */
		public void setElement(E e)
		{
			this.data = e; 
		}

    /**
     * This method returns the reference of the next node
     * @return The reference of next node
     */
		public Node getNext()
		{
			return this.next; // XXX-CHANGE-XXX
		}

    /**
     * This method returns the reference of the previous
     * node
     * @return The reference of the previous node
     */
		public Node getPrev()
		{
			return this.prev; // XXX-CHANGE-XXX
		} 

    /**
     * This method returns the thing that is stored 
     * at data
     * @return The data that is stored
     */
		public E getElement()
		{
			return this.data; // XXX-CHANGE-XXX
		} 
	}

  /** ListIterator implementation */ 
  /**
   * This is the class MyListIterator which contains fiveinstance 
   * variables. Forward is true when we are moving forward and false
   * other wise. canremoveorset is true when we just called next or
   * previous. False otherwise. Left right node is self explanatory.
   * idx is the index is the index of the iterator as is straddles between
   * two nodes at all times.
   */
	protected class MyListIterator implements ListIterator<E> {

		boolean forward;
		boolean canRemoveOrSet;
		Node left,right;
		int idx;

    /**
     * Constructor that sets the left to head and right to the 
     * node after head. 
     */
		public MyListIterator()
		{
      this.left = MyLinkedList.this.head; 
      this.right = MyLinkedList.this.head.getNext(); 
      this.idx = 0; 
      this.forward = true; 
      this.canRemoveOrSet = false; 
		}

    /**
     * This method adds an element right where the
     * itertator is at. Or in otherwords, right before 
     * the right Node of this iterator.
     * @param e the Node containing e that is going be 
     * added 
     */
		@Override
		public void add(E e)
		{
      if(e == null){
        throw new NullPointerException();
      }
      Node newNode = new Node(e);
      this.right.setPrev(newNode);
      this.left.setNext(newNode);
      newNode.setNext(this.right);
      newNode.setPrev(this.left);
      this.left = newNode; 
      this.idx++; 
      this.canRemoveOrSet = false; 
      MyLinkedList.this.nelems++; 
		}

    /**
     * This method returns true if there is another 
     * element to the right that is not dumym node. False otherwise. 
     * @return truth value of whether iterator has next 
     * or not 
     */
		@Override
		public boolean hasNext()
		{
			if(this.right != MyLinkedList.this.tail){
        return true; 
      }else{
        return false; 
      }
		}

    /**
     * This method returns true if iterator has 
     * node to the left that is not dummy node. 
     * @return truth value of whether iterator has previous or not
     */
		@Override
		public boolean hasPrevious()
		{
      if(this.left != MyLinkedList.this.head){
        return true; 
      }else{
        return false; 
      }
		}

    /**
     * This method moves the iterator up by one and returns the 
     * data that was stored in the node is just passed
     * @return Data in node that iteratir just passed if successful 
     * next call was made
     */
		@Override
		public E next()
		{
      if(this.hasNext() == false){
        throw new NoSuchElementException();
      }else{
        Node goingOut = this.right; 
        this.left = this.right; 
        this.right = this.right.getNext();
        this.idx++;
        this.forward = true; 
        this.canRemoveOrSet = true; 
        return goingOut.getElement();
      }
		}

    /**
     * This method returns the value of the index of the Node that is 
     * to the right of iterator
     * @return index of node that is to the right of the iterator 
     */
		@Override
		public int nextIndex()
		{
      if(this.right == MyLinkedList.this.tail){
        return MyLinkedList.this.nelems; 
      }
      return this.idx; 
		}

    /**
     * This method moves the iterator back by one and returns the 
     * data stored at the node that is just passed
     * @return data stored at node that iterator just passed
     */
		@Override
		public E previous()
		{
			if(this.hasPrevious() == false){
        throw new NoSuchElementException();
      }else{
        E goingOut = this.left.getElement();
        this.right = this.left; 
        this.left = this.left.getPrev();
        this.forward = false; 
        this.idx--;
        this.canRemoveOrSet = true; 
        return goingOut; 
      }
		}

    /**
     * This method returns the index of the node stored to the left
     * @return Index of node that is stored to the left
     */
		@Override
		public int previousIndex()
		{
      if(this.left == MyLinkedList.this.head){
        return -1; 
      }
			return this.idx - 1; 
		}

    /**
     * This method removes a node that next or previous call just passed
     * @throws IllegealStateExcetion if last call was not next or previous or 
     * iterator is at the beginning of the list
     */
		@Override
		public void remove()
		{
      if(this.forward == true && this.idx == 0){
        throw new IllegalStateException();
      }
      if(this.canRemoveOrSet == false){
        throw new IllegalStateException();
      }
      if(this.canRemoveOrSet == true){
        if(this.forward == false){
          this.right = this.right.getNext();
          this.left.setNext(this.right);
          this.right.setPrev(this.left);
        }else{
          this.right.setPrev(this.left.getPrev());
          this.left.getPrev().setNext(this.right);
          this.left = this.left.getPrev();
        }
        MyLinkedList.this.nelems--; 
        this.canRemoveOrSet = false; 
      }
		}

    /**
     * This method sets the value of the node that next or previous 
     * just passed to an user input 
     * @param e The data that is going to be set in the node
     */
		@Override
		public void set(E e) 
		{
      if(e == null){
        throw new NullPointerException();
      }
      if(this.forward == true && this.idx == 0){
        throw new IllegalStateException();
      }
      if(this.canRemoveOrSet == false){
        throw new IllegalStateException();
      }
			if(this.forward == false){
        this.right.setElement(e);
      }else{
        this.left.setElement(e);
      }
		}
	}

	//  Implementation of the MyLinkedList Class
  /** Only 0-argument constructor is define */
  /**
   * This is the constructor of MyLinkedList. Two dummy nodes
   * are created and points to each other in a double linked 
   * fashion. Number of elements is set to zero. 
   */
	public MyLinkedList()
	{
    this.nelems = 0; 
    this.head = new Node(null);
    this.head.prev = null; 
    this.tail = new Node(null);
    this.tail.next = null; 
    this.head.setNext(this.tail);
    this.tail.setPrev(this.head);
	}

  @Override
  /**
   * This method returns the number of elements in the list 
   * @return number of elements in the list
   */
	public int size()
	{
    return this.nelems; 
	}

  /**
   * This method returns the data stored at the node at the user 
   * input
   * @param index The index of the node that stores the element the 
   * user wants
   * @return data that is stored at the node 
   * @throws IndexOutOfBoundException thrown when index is out 
   * of bounds
   */
	@Override
	public E get(int index)
	{
    if(index < 0 || index >= this.nelems){
      throw new IndexOutOfBoundsException();
    }
    Node current = this.head.next; 
    //Iterates until at index
    for(int i = 0; i < index; i++){
      current = current.getNext();
    }
    return current.getElement(); 
	}

  /**
   * This method adds a node at a particular index and pushes 
   * all nodes back 
   * @param index index where the new node is going to be added 
   * @param data Data of newNode to be added 
   * @throws NullPointException when data is null 
   * @throws IndexOutofBoundsException when index is out of bounds
   */
	@Override
	public void add(int index, E data) 
	{
    if(data == null){
      throw new NullPointerException();
    }
    if(index < 0 || index > this.nelems){
      throw new IndexOutOfBoundsException();
    }
    Node newNode = new Node(data);
    //checks if user is wanting to enter at the beginning of list
    if(index == 0){
      //if there are already elements, then must do previous
      if(this.nelems != 0){
        newNode.setNext(this.head.getNext());
        this.head.getNext().setPrev(newNode);
        this.head.setNext(newNode);
        newNode.setPrev(this.head);
      }else{
        //if list is empty, then just set dummyNode next to newNode
        this.head.setNext(newNode);
        this.tail.setPrev(newNode);
        newNode.setPrev(this.head);
        newNode.setNext(this.tail);
      }
     //checks if user wants to enter at the latter part of the list
    }else if(index == this.nelems){
      this.tail.getPrev().setNext(newNode);
      newNode.setPrev(this.tail.getPrev());
      newNode.setNext(this.tail);
      this.tail.setPrev(newNode);
    }else{
      Node current = this.head.next; 
      //Iterates until at index
      for(int i = 0; i < index; i++){
        current = current.getNext();
      }
      current.getPrev().setNext(newNode);
      newNode.setPrev(current.getPrev());
      newNode.setNext(current);
      current.setPrev(newNode);
    }
    this.nelems++; 
	}

  /**
   * Method adds a newnode containing data at the end of 
   * the list 
   * @param data Data of new node to be added 
   * @return Always returns true
   */
	public boolean add(E data)
	{
    if(data == null){
      throw new NullPointerException();
    }
    Node newNode = new Node(data);
    if(this.nelems == 0){
      this.head.setNext(newNode);
      newNode.setNext(this.tail);
      newNode.setPrev(this.head);
      this.tail.setPrev(newNode);
    }else{
      this.tail.getPrev().setNext(newNode);
      newNode.setPrev(this.tail.getPrev());
      newNode.setNext(this.tail);
      this.tail.setPrev(newNode);
    }
    this.nelems++; 
		return true; 
	}

  /**
   * This method sets the node at the particular index with 
   * new data 
   * @param index index of node that is going to have its data changed 
   * @param data data that is going to be set in the node
   * @return the old data that was stored in the node
   */
	public E set(int index, E data) 
	{
    if(data == null){
      throw new NullPointerException();
    }
    if(index < 0 || index >= this.nelems){
      throw new IndexOutOfBoundsException();
    }
    Node current = this.head.getNext();
    //Iterates until at index
    for(int i = 0; i < index; i++){
      current = current.getNext(); 
    }
    E stored = current.getElement(); 
    current.setElement(data);
    return stored; 
	}

	public E remove(int index)
	{
		if(index < 0 || index >= this.nelems){
      throw new IndexOutOfBoundsException();
    }
    Node current = this.head.getNext();
    //Iterates until at index
    for(int i = 0; i < index; i++){
      current = current.getNext();
    }
    E stored = current.getElement();
    current.getPrev().setNext(current.getNext());
    current.getNext().setPrev(current.getPrev());
    this.nelems--; 
    return stored; 
	}

  /**
   * This method removes all the nodes in the list except for the
   * dummy nodes 
   */
	public void clear()
	{
    this.head.setNext(this.tail);
    this.tail.setPrev(this.head);
    this.nelems = 0; 
	}

  /**
   * This method checks whether or not the list is empty 
   * @return truth value determning whether or not list is empty 
   */
	public boolean isEmpty()
	{
    if(this.nelems == 0){
      return true; 
    }else{
      return false; 
    }
	}

  /**
   * This method returns the node at the particular index
   * @param index Index of node 
   * @return Node at the index 
   * @throws IndexOutOfBoundsException when index is out of bounds
   */
	protected Node getNth(int index)
	{
    if(index < 0 || index >= this.nelems){
      throw new IndexOutOfBoundsException();
    }
    Node current = this.head.getNext();
    //Iterates until at index
    for(int i = 0; i < index; i++){
      current = current.getNext();
    }
    return current; 
	}

  /**
   * This method returns a new MyListIterator but the reference is 
   * Iterator 
   * @return new Iterator
   */
	public Iterator<E> iterator()
	{
    MyListIterator newIterator = new MyListIterator();
    return newIterator;
		
  }

  /**
   * This method returns a new MyListIterator but the reference is 
   * ListIterator
   * @return new Iterator
   */
	public ListIterator<E> listIterator()
	{
		MyListIterator newIterator = new MyListIterator();
    return newIterator;
	}
}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4