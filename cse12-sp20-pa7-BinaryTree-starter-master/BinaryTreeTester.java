import org.junit.*;
import static org.junit.Assert.*; 
import java.util.*; 

public class BinaryTreeTester {
  private BinaryTree<Integer> ilist;
  private BinaryTree<Integer> tlist; 
  ArrayList<Integer> intList; 
  ArrayList<Integer> smallList; 
  

  private static final Integer[] arrIntsRegular ={5,6,7,10,11,4,5,6,11,12,13}; 
  private static final Integer[] arrIntsSmall = {1,2,3}; 


  @Before
  public void setUp(){
    intList = new ArrayList<>(Arrays.asList(arrIntsRegular)); 
    smallList = new ArrayList<>(Arrays.asList(arrIntsSmall)); 
  }

  @Test
  public void testFirstConstructor(){
    ilist = new BinaryTree<Integer>(); 
    assertEquals(null, ilist.root);
    assertEquals(0, ilist.size); 
  }

  @Test
  public void testSecondConstructor(){
    ilist = new BinaryTree<Integer>(3); 
    assertEquals(Integer.valueOf(3), ilist.root.getData()); 
    assertEquals(1, ilist.size); 
  }

  @Test
  public void testThirdConstructor(){
    ilist = new BinaryTree<Integer>(intList);
    assertEquals(Integer.valueOf(5), ilist.root.getData());
    assertEquals(Integer.valueOf(6), ilist.root.getLeft().getData()); 
    assertEquals(Integer.valueOf(7), ilist.root.getRight().getData()); 
    assertEquals(Integer.valueOf(10), ilist.root.getLeft().getLeft().getData()); 
    assertEquals(Integer.valueOf(11), ilist.root.getLeft().getRight().getData()); 
    assertEquals(Integer.valueOf(4), ilist.root.getRight().getLeft().getData()); 
    assertEquals(Integer.valueOf(5), ilist.root.getRight().getRight().getData()); 
    assertEquals(Integer.valueOf(12), ilist.root.getLeft().getRight().getLeft().getData()); 
    assertEquals(Integer.valueOf(13), ilist.root.getLeft().getRight().getRight().getData()); 
    assertEquals(null, ilist.root.getLeft().getRight().getRight().getLeft()); 
  }

  @Test
  public void testAddElement(){
    tlist = new BinaryTree<Integer>(smallList); 
    tlist.add(5); 
    tlist.add(6); 
    tlist.add(7); 
    assertEquals(Integer.valueOf(5), tlist.root.getLeft().getLeft().getData()); 
    assertEquals(Integer.valueOf(6), tlist.root.getLeft().getRight().getData()); 
    assertEquals(Integer.valueOf(7), tlist.root.getRight().getLeft().getData()); 
    tlist = new BinaryTree<Integer>();
    tlist.add(23); 
    assertEquals(Integer.valueOf(23), tlist.root.getData());
    assertEquals(null, tlist.root.getRight()); 
    assertEquals(null, tlist.root.getLeft()); 
    tlist = new BinaryTree<Integer>(23);
    tlist.add(13); 
    tlist.add(14);
    assertEquals(Integer.valueOf(13), tlist.root.getLeft().getData());
    assertEquals(Integer.valueOf(14), tlist.root.getRight().getData());
    assertEquals(null, tlist.root.getRight().getLeft()); 
  }

  @Test
  public void testRemoveElement(){
    tlist = new BinaryTree<Integer>(3);
    tlist.add(5);
    tlist.add(7);
    tlist.add(10);
    tlist.add(5);
    tlist.remove(3);  
    assertEquals(Integer.valueOf(5), tlist.root.getData()); 
    assertEquals(Integer.valueOf(5), tlist.root.getLeft().getData()); 
    assertEquals(Integer.valueOf(7), tlist.root.getRight().getData()); 
    assertEquals(Integer.valueOf(10), tlist.root.getLeft().getLeft().getData());
    assertEquals(null, tlist.root.getLeft().getRight()); 
    tlist = new BinaryTree<Integer>(smallList); 
    assertEquals(false, tlist.remove(4)); 
    assertEquals(true, tlist.remove(3));
  }

  @Test
  public void testContains(){
    tlist = new BinaryTree<Integer>(intList);
    assertEquals(true, tlist.containsBFS(5));
    assertEquals(true, tlist.containsBFS(6));
    assertEquals(true, tlist.containsBFS(10));
    assertEquals(true, tlist.containsBFS(11));
    assertEquals(false, tlist.containsBFS(22));

    ilist = new BinaryTree<Integer>(smallList);
    assertEquals(true, ilist.containsBFS(1)); 
    assertEquals(false, ilist.containsBFS(10)); 
    ilist.remove(1); 
    ilist.add(1); 
    assertEquals(true, ilist.containsBFS(1)); 
  }

  @Test
  public void testHeight(){
    ilist = new BinaryTree<Integer>(intList);
    assertEquals(3, ilist.getHeight()); 
    ilist.remove(6); 
    ilist.remove(12); 
    ilist.remove(11);
    ilist.remove(13);
    assertEquals(2, ilist.getHeight());

    ilist = new BinaryTree<Integer>(); 
    assertEquals(0, ilist.getHeight()); 
    ilist = new BinaryTree<Integer>(2);
    assertEquals(0, ilist.getHeight()); 
  }

  @Test
  public void testSize(){
    ilist = new BinaryTree<Integer>(intList);
    assertEquals(11, ilist.getSize());
    tlist = new BinaryTree<Integer>(smallList);
    assertEquals(3, tlist.getSize());
    ilist = new BinaryTree<Integer>(2);
    assertEquals(1, ilist.getSize()); 
    ilist = new BinaryTree<Integer>();
    assertEquals(0, ilist.getSize()); 
    ilist.add(3); 
    ilist.add(2); 
    assertEquals(2, ilist.getSize()); 
    ilist.remove(2); 
    assertEquals(1, ilist.getSize()); 
    ilist.remove(3);
    assertEquals(0, ilist.getSize()); 
  }

  @Test
  public void testMinValue(){
    ilist = new BinaryTree<Integer>(smallList); 
    assertEquals(Integer.valueOf(1), ilist.minValue()); 
    ilist.remove(1); 
    assertEquals(Integer.valueOf(2), ilist.minValue()); 
    ilist.add(-11); 
    assertEquals(Integer.valueOf(-11), ilist.minValue()); 

  }
}