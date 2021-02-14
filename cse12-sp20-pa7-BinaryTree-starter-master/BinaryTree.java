/**
 * NAME: Darren Yeung 
 * EMAIL: dyeung@ucsd.edu 
 * PID: A15943292 
 * This file contains BinaryTree.java which is a class. 
 * That is all 
 */

/**
 * This is the class BinaryTree which is a generic class. It's generic used 
 * should extend from the Comparable interface. It contains the innerClass 
 * which contains three instance variables: left, right, and data
 */


import java.util.List;
import java.util.LinkedList; 
import java.util.Queue;


public class BinaryTree<E extends Comparable>{

  /**
   * This is the inner class Node that contains three instance variables. 
   * It has a reference to the left node, a reference to the right node, and a thing 
   * that stores the data
   */
  protected class Node{
    Node left;
    Node right;
    E data;
    
    /**
     * Defualt constructor for Node that takes an a Data
     * and sets the left and right referneces to null 
     * @param data data of the node
     */
    public Node(E data){
      this.data = data; 
      this.left = null; 
      this.right = null; 
    }

    /**
     * This method sets the left node of this node
     * @param left Node that is going to become the left 
     * node of this node 
     */
    public void setLeft(Node left){
      this.left = left; 
    }
    
    /**
     * This method sets the right node of this node
     * @param right Node that is going to become the right 
     * node of this node
     */
    public void setRight(Node right){
      this.right = right; 
    }

    /**
     * This method sets the data of this node
     * @param data data that is going to be come the data 
     * of this node 
     */
    public void setData(E data){
      this.data = data;
    }

    /**
     * This method returns the left node of this node
     * @return the left node of this node
     */
    public Node getLeft(){
      return this.left;
    }

    /**
     * This method returns the right node of this node
     * @return the right node of this node
     */
    public Node getRight(){
      return this.right; 
    }
    
    /**
     * This method returns the data of this node
     * @return the data of this node
     */
    public E getData(){
      return this.data; 
    }
  }

  //Beginning of BinaryTree class 
  Node root; 
  int size; 

  /**
   * This is the default constructor of the BinaryTree. It sets 
   * the root of the tree to null and the size to zero 
   */
  public BinaryTree(){
    this.root = null; 
    this.size = 0; 
  }

  /**
   * This is another constructor for the Binary Tree. It makes the 
   * root node of this have the data that was passed in
   * @param data The data of the root node
   */
  public BinaryTree(E data){
    this.root = new Node(data);
    this.size = 1;
  }

  /**
   * This method takes in a list and adds all the element to 
   * the binary tree in level order fasion
   * @param list List that is going to have its elements added
   */
  public BinaryTree(List<E> list){
    for(int i = 0; i < list.size(); i++){
      this.add(list.get(i)); 
    }
  }

  /**
   * This method adds a new node to the binary tree 
   * with the element
   * @param element Element that is going to be in the new node
   * that is added
   */
  public void add(E element){
    if(element == null){
      throw new NullPointerException(); 
    }
    if(this.root == null){
      this.root = new Node(element); 
      this.size++; 
    }else{
      Queue<Node> lmao = new LinkedList<Node>();
      lmao.add(this.root);
      while(lmao.isEmpty() == false){
        if(lmao.peek().getLeft() == null){
          lmao.peek().setLeft(new Node(element)); 
          this.size++; 
          lmao.remove(); 
          break; 
        }else if(lmao.peek().getRight() == null){
          lmao.peek().setRight(new Node(element)); 
          this.size++; 
          lmao.remove(); 
          break; 
        }else{
          lmao.add(lmao.peek().getLeft()); 
          lmao.add(lmao.peek().getRight()); 
          lmao.remove(); 
        }
      }
    }
  }

  /**
   * This method removes an element from the node if found
   * and returns true/false based on whehter remove 
   * was successful
   * @param element Element that the user wants to be removed
   * @return True or false depending on whether remove was 
   * sucessful
   */
  public boolean remove(E element){
    if(element == null){
      throw new NullPointerException();
    }
    if(this.containsBFS(element) == false){
      return false; 
    }else{
      Queue<Node> lmao = new LinkedList<Node>(); 
      lmao.add(this.root);
      while(lmao.isEmpty() == false){
        if(lmao.peek().getData().equals(element)){
          Node lastNode = this.root;
          Queue<Node> lookingForLast = new LinkedList<Node>(); 
          lookingForLast.add(this.root);
          while(lookingForLast.peek() != null){
            lastNode = lookingForLast.peek(); 
            lookingForLast.add(lookingForLast.peek().getLeft()); 
            lookingForLast.add(lookingForLast.peek().getRight()); 
            lookingForLast.remove(); 
          }
          lmao.peek().setData(lastNode.getData()); 
          //correct up until here 
          Node parentOfLast = this.root; 
          Queue<Node> lookingForParent = new LinkedList<Node>(); 
          lookingForParent.add(parentOfLast); 
          while(lookingForParent.peek() != lastNode){
            parentOfLast = lookingForParent.peek();
            if(lookingForParent.peek().getLeft() == lastNode){
              parentOfLast = lookingForParent.peek(); 
              break; 
            }else if(lookingForParent.peek().getRight() == lastNode){
              parentOfLast = lookingForParent.peek(); 
              break; 
            }
            lookingForParent.add(lookingForParent.peek().getLeft());
            lookingForParent.add(lookingForParent.peek().getRight()); 
            lookingForParent.remove(); 
          }
          if(parentOfLast.getLeft() == lastNode){
            parentOfLast.setLeft(null);
          }else if(parentOfLast.getRight() == lastNode){
            parentOfLast.setRight(null); 
          }
          this.size--; 
          return true; 
        }
        lmao.add(lmao.peek().getLeft());
        lmao.add(lmao.peek().getRight()); 
        lmao.remove(); 
      }
      return false; 
    }
  }

  /**
   * This method searches for the input and returns True or false 
   * based on whether or not the element was found
   * @param element Element the user wants to search for
   * @return True or false depending on whether or not 
   * element was found
   */
  public boolean containsBFS(E element){
    if(element == null){
      throw new NullPointerException(); 
    }
    if(this.root == null){
      return false;
    }else{
      Queue<Node> lmao = new LinkedList<Node>(); 
      lmao.add(this.root); 
      while(lmao.isEmpty() == false){
        if(lmao.peek() == null){
          return false; 
        }
        if(lmao.peek().getData().equals(element)){
          return true; 
        }
        lmao.add(lmao.peek().getLeft()); 
        lmao.add(lmao.peek().getRight()); 
        lmao.remove(); 
      }
      return false; 
    }
  }

  /**
   * Returns the height of the binary tree
   * @return Height of the binary tree
   */
  public int getHeight(){
    if(this.root == null){
      return 0; 
    }
    if(this.getSize() == 1){
      return 0;
    }else{
      Queue<Node> gettingHeight = new LinkedList<Node>(); 
      gettingHeight.add(this.root); 
      int height = 0; 
      while(true){
        int nodesCurrentLevel = gettingHeight.size();
         if(nodesCurrentLevel == 0){
           return height; 
         }else{
          if(gettingHeight.peek() != this.root){
            height++; 
          }
         }
         while(nodesCurrentLevel > 0){
           if(gettingHeight.peek().getLeft() != null){
             gettingHeight.add(gettingHeight.peek().getLeft()); 
           }
           if(gettingHeight.peek().getRight() != null){
             gettingHeight.add(gettingHeight.peek().getRight()); 
           }
           gettingHeight.remove(); 
           nodesCurrentLevel--; 
         }
      }
    }
  }

  /**
   * This method returns the size of the binary tre
   * @return The number of elements in the binary tree
   */
  public int getSize(){
    return this.size; 
  }

  /**
   * This method returns the smallest element in the binary tree 
   * @return The smallest element in the binary tree
   */
  public E minValue(){
    if (this.getSize() == 0){
      return null; 
    }else{
      Queue<Node> findingLast = new LinkedList<Node>(); 
      Node smallest = this.root;
      findingLast.add(smallest);
      while(findingLast.peek() != null){
        if(smallest.getData().compareTo(findingLast.peek().getData()) > 0){
          smallest = findingLast.peek();
        }
        findingLast.add(findingLast.peek().getLeft()); 
        findingLast.add(findingLast.peek().getRight()); 
        findingLast.remove(); 
      } 
      return (E)smallest.getData();
    }
  }
}
