/**
 * Name: Darren Yeung 
 * PID: A15943292 
 * EMAIL: dyeung@ucsd.edu 
 * This is the MYBST.java file. It actually contains two classes. 
 * The outer class is the MyBst class. The second class is a static 
 * inner class called MyBSTNode. 
 */
import java.util.ArrayList;
public class MyBST<K extends Comparable<K>,V> {
/**
 * This is the beginning of the static inner class 
 * MyBSTNode. It is a generic class that takes in two 
 * generics, one key and one value. It has a key, value, 
 * reference to parent, right, and left. A node is basically 
 * something that stores a reference to another node and its own 
 * conents. 
 * @param <K> key
 * @param <V> value
 */
  static class MyBSTNode<K,V>{
    K key; 
    V value; 
    MyBSTNode<K,V> parent; 
    MyBSTNode<K,V> left; 
    MyBSTNode<K,V> right; 

    /**
     * This is the only constructor for this Node. It takes in a key,
     * value, and parent Node
     */
    public MyBSTNode(K key, V value, MyBSTNode<K,V> parent){
      this.key = key; 
      this.value = value; 
      this.parent = parent;
      this.left = null; 
      this.right = null; 
    }

    /**
     * This method returns the key of the node
     */
    public K getKey(){
      return this.key; 
    }

    /**
     * This method returns the value of the node
     */
    public V getValue(){
      return this.value; 
    }

    /**
     * This methos return the parent node
     */
    public MyBSTNode<K,V> getParent(){
      return this.parent; 
    }

    /**
     * This method returns the left node
     */
    public MyBSTNode<K,V> getLeft(){
      return this.left; 
    }

    /**
     * This method returns the right node
     */
    public MyBSTNode<K,V> getRight(){
      return this.right; 
    }

    /**
     * This method replaces the current key with a new 
     * key
     * @param the key that is going to be set
     */
    public void setKey(K newKey){
      this.key = newKey; 
    }

    /**
     * This method replaces the value with a new value
     * @param value that is going to replace 
     */
    public void setValue(V newValue){
      this.value = newValue; 
    }

    /**
     * This method replaces the current parent with anew 
     * parent
     * @param newParent parent that is going to be set
     */
    public void setParent(MyBSTNode<K,V> newParent){
      this.parent = newParent; 
    }

    /**
     * This method replaces the current left node 
     * @param newLeft new left node that is going to be set
     */
    public void setLeft(MyBSTNode<K,V> newLeft){
      this.left = newLeft; 
    }

    /**
     * This method replaces the current right node
     * @param newRight new right node that is going to be set
     */
    public void setRight(MyBSTNode<K,V> newRight){
      this.right = newRight; 
    }

    /**
     * This method returns the successor of the current node
     * @return successor of current node 
     */
    public MyBSTNode<K,V> successor(){
      /**
       * If have right child, go to it then keep going left until 
       * can't go anymore. If don't have right child, keep going up 
       * to parent until you find one that is bigger than you
       */
      if(this.getRight() != null){
        MyBSTNode<K,V> smallestBigger = this.getRight(); 
        while(smallestBigger.getLeft() != null){
          smallestBigger = smallestBigger.getLeft(); 
        }
        return smallestBigger; 
      }else{
        MyBSTNode<K,V> smallestBigger = this.getParent(); 
        if(smallestBigger == null){
          return null;
        }
        MyBSTNode<K,V> rightChild = this; 
        while(smallestBigger.getRight() == rightChild){
          rightChild = smallestBigger; 
          smallestBigger = smallestBigger.getParent(); 
          if(smallestBigger == null){
            return null; 
          }
        }
        return smallestBigger; 
      }
    }
  }

  /**
   * The beginning of the outer class MYBST. It contains two instance
   * variables, the size, and the root node. A binarysearchtree 
   * is basically where the node to the left of the node has to be smaller 
   * than the parent and the node to the right of it must be bigger than the 
   * parent. 
   */
  int size; 
  MyBSTNode<K,V> root; 

  /**
   * Only constructor for the MyBST class. It sets root to null
   * and the size to zero
   */
  public MyBST(){
    this.root = null; 
    this.size = 0; 
  }

  /**
   * This method takes in a a node and returns the successor of that node. 
   * It doesn't do anything here. It only uses the successor method from the 
   * inner node class
   * @param node the node the user wants the successor of
   * @return the successor of the paramater
   */
  protected MyBSTNode<K,V> successor(MyBSTNode<K,V> node){
    if(node == null){
      return null; 
    }
    return node.successor(); 
  }

  /**
   * This method returns the size of the binarysearchtree
   */
  public int size(){
    return this.size; 
  }

  /**
   * This method inserts a new node in the binarysearchtree
   * @param key key of new node
   * @param value value of new node
   * @return null if no node was replaced, the value of node that 
   * was replaced if replaced
   */
  public V insert(K key, V value){
    if (key == null){
      throw new NullPointerException(); 
    }
    MyBSTNode<K,V> newNode = new MyBSTNode(key, value, null); 
    if(root == null){
      this.root = new MyBSTNode<K,V>(key, value, null); 
      this.size++;
      return null; 
    }else{
      MyBSTNode<K,V> current = this.root; 
      while(current.getKey().compareTo(key) != 0){
        if(key.compareTo(current.getKey()) > 0){
          if(current.getRight() == null){
            current.setRight(newNode); 
            newNode.setParent(current); 
            this.size++; 
            return null; 
          }
         current = current.getRight(); 
        }else if(key.compareTo(current.getKey()) < 0){
          if(current.getLeft() == null){
            current.setLeft(newNode); 
            newNode.setParent(current); 
            this.size++;
            return null; 
          }
          current = current.getLeft(); 
        }
      }
      V stored = current.getValue(); 
      current.setValue(value); 
      return stored; 
    }
  }

  /**
   * This method searches for a node using the key 
   * and returns the value of that node 
   * @param key the key of the node the user wants to search for
   * @return the value of the node that is associated with that key
   */
  public V search(K key){
    if(key == null){
      return null; 
    }
    MyBSTNode<K,V> current = this.root; 
    if(current == null){
      return null; 
    }
    if(this.root.getKey().compareTo(key) == 0){
      return this.root.getValue(); 
    }
    while(current.getKey().compareTo(key) != 0){
      if(key.compareTo(current.getKey()) > 0){
        current = current.getRight(); 
        if(current == null){
          return null; 
        }
      }else if(key.compareTo(current.getKey()) < 0){
        current = current.getLeft(); 
        if(current == null){
          return null;  
        }
      }
    }
    return current.getValue(); 
  }

  /**
   * This method removes a node from the binarysearchtree if it only
   * has one child, or is a leaf node. If it has two children, 
   * then the node's data is replaced with the data of the successor's data 
   * and the successor is removed
   * @param key the key of the node the user wants to remove
   * @return value of node that was overwritten or replaced
   */
  public V remove(K key){
    if(key == null){
      return null; 
    }
    if(this.search(key) == null){
      return null; 
    }
    MyBSTNode<K,V> current = this.root; 
    while(current.getKey().compareTo(key) != 0){
      if(key.compareTo(current.getKey()) > 0){
        current = current.getRight(); 
        if(current == null){
          return null; 
        }
      }else if(key.compareTo(current.getKey()) < 0){
        current = current.getLeft(); 
        if(current == null){
          return null; 
        }
      }
    }
    V storedValue = current.getValue();
    if(key.compareTo(this.root.getKey()) == 0){
      if(this.root.getRight() == null && this.root.getLeft() == null){
        this.root = null; 
        this.size--; 
        return storedValue; 
      }else if(this.root.getRight() == null && this.root.getLeft() != null){
        this.root = this.root.getLeft(); 
        this.size--;
        return storedValue; 
      }else if(this.root.getRight() != null && this.root.getLeft() == null){
        this.root = this.root.getRight(); 
        this.size--;
        return storedValue; 
      }
    }
    if(current.getLeft() == null && current.getRight() == null){
      if(current.getParent().getRight() == current){
        current.getParent().setRight(null); 
        this.size--; 
        return storedValue; 
      }else if(current.getParent().getLeft() == current){
        current.getParent().setLeft(null); 
        this.size--;
        return storedValue; 
      }
    }
    if(current.getLeft() == null && current.getRight() != null){
      if(current.getParent().getRight() == current){
        current.getParent().setRight(current.getRight()); 
        current.getRight().setParent(current.getParent()); 
        this.size--; 
        return storedValue; 
      } 
      if(current.getParent().getLeft() == current){
        current.getParent().setLeft(current.getRight()); 
        current.getRight().setParent(current.getParent()); 
        this.size--;  
        return storedValue; 
      }
    }
    if(current.getLeft() != null && current.getRight() == null){
      if(current.getParent().getRight() == current){
        current.getParent().setRight(current.getLeft()); 
        current.getLeft().setParent(current.getParent()); 
        this.size--; 
        return storedValue; 
      } 
      if(current.getParent().getLeft() == current){
        current.getParent().setLeft(current.getLeft()); 
        current.getLeft().setParent(current.getParent()); 
        this.size--;  
        return storedValue; 
      }
    }
    if(current.getLeft() != null && current.getRight() != null){
      V valueStored = current.successor().getValue(); 
      K keyStored = current.successor().getKey(); 
      this.remove(current.successor().getKey()); 
      current.setKey(keyStored);
      current.setValue(valueStored); 
      return storedValue; 
    }
    return null; 
  }

  /**
   * This method creates an arraylist of nodes from the
   * binarysearchtree of whose keys are arranged 
   * from smallest to biggest of 
   * @return the arraylist
   */
  public ArrayList<MyBSTNode<K,V>> inorder(){
    ArrayList<MyBSTNode<K,V>> goingOut = new ArrayList();
    if(this.size == 0){
      return goingOut; 
    }else{
      MyBSTNode<K,V> current = this.root; 
      while(current.getLeft() != null){
        current = current.getLeft(); 
      }
      while(current != null){
        goingOut.add(current); 
        current = current.successor(); 
      }
    }
    return goingOut; 
  }
}