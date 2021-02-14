import org.junit.*;
import static org.junit.Assert.*; 
import java.util.*; 

public class MyBSTTester {
  private MyBST<Integer,String> islist;
   

  @Before
  public void setUp(){
    islist = new MyBST<>();
    islist.insert(6, "Hello");
    islist.insert(5,"Lmao");
    islist.insert(3, "thre"); 
    islist.insert(7, "seven"); 
    islist.insert(9, "nine"); 

  }

  @Test
  public void testSuc(){
    MyBST.MyBSTNode<Integer,String> result = islist.successor(islist.root); 
    assertEquals(islist.root.getRight(), result); 
    MyBST.MyBSTNode<Integer,String> result1 = islist.root.getLeft(); 
    assertEquals(result1, islist.successor(islist.root.getLeft().getLeft())); 
    assertEquals(null, islist.successor(islist.root.getRight().getRight()));
    assertEquals(islist.root.getRight(), islist.successor(islist.root)); 
    assertEquals(islist.root, islist.successor(islist.root.getLeft())); 
    assertEquals(islist.root.getRight().getRight(), islist.successor(islist.root.getRight())); 

  }

  @Test
  public void testInsert(){
    assertEquals(Integer.valueOf(6), islist.root.getKey()); 
    assertEquals(Integer.valueOf(5), islist.root.getLeft().getKey()); 
    assertEquals(Integer.valueOf(7), islist.root.getRight().getKey());
    assertEquals(Integer.valueOf(3), islist.root.getLeft().getLeft().getKey());
    assertEquals(Integer.valueOf(9), islist.root.getRight().getRight().getKey());
    //insert with same key 
    assertEquals(islist.insert(6, "Jello"), "Hello"); 
    assertEquals("Jello", islist.root.getValue()); 
    assertEquals("nine", islist.insert(9, "Cady")); 
    assertEquals("Cady", islist.root.getRight().getRight().getValue()); 
    assertEquals(null, islist.insert(10, "ten")); 
    assertEquals(Integer.valueOf(10), islist.root.getRight().getRight().getRight().getKey()); 
  }

  @Test
  public void testSize(){
    assertEquals(5, islist.size()); 
    islist.remove(6); 
    assertEquals(4,islist.size()); 
    islist.insert(10, "ten "); 
    assertEquals(5, islist.size()); 
    islist.insert(20, "twenty"); 
    assertEquals(6, islist.size()); 
    islist.remove(10); 
    islist.remove(10); 
    islist.remove(20); 
    islist.remove(9); 
    islist.remove(7); 
    islist.remove(3); 
    islist.remove(5); 
    assertEquals(0, islist.size()); 
  }

  @Test
  public void testSearch(){
    assertEquals("Hello", islist.search(6)); 
    assertEquals("Lmao", islist.search(5)); 
    assertEquals("thre", islist.search(3)); 
    assertEquals("seven", islist.search(7)); 
    assertEquals("nine", islist.search(9)); 
    islist.insert(6, "Jello"); 
    assertEquals("Jello",islist.search(6)); 
    assertEquals(null, islist.search(null)); 
    assertEquals(null, islist.search(11)); 
    islist = new MyBST(); 
    assertEquals(null, islist.search(10)); 
  }

  @Test
  public void testRemove(){
    islist.remove(6);
    assertEquals(Integer.valueOf(7), islist.root.getKey()); 
    assertEquals(Integer.valueOf(9), islist.root.getRight().getKey()); 
    islist = new MyBST<>(); 
    islist.insert(12, "twelve"); 
    islist.insert(10, "ten"); 
    islist.insert(15, "fifteen"); 
    islist.insert(7, "seven");
    islist.insert(11, "eleven"); 
    islist.insert(16, "sixteen"); 
    assertEquals("seven", islist.remove(7)); 
    assertEquals(null, islist.root.getLeft().getLeft()); 
    assertEquals("eleven", islist.remove(11)); 
    assertEquals(null, islist.root.getLeft().getRight());
    assertEquals(null, islist.insert(7, "seven")); 
    assertEquals(null, islist.insert(11, "eleven")); 
    assertEquals("sixteen", islist.remove(16)); 
    assertEquals(null, islist.root.getRight().getRight()); 
    assertEquals(null, islist.insert(16, "sixteen")); 
    assertEquals("fifteen", islist.remove(15)); 
    assertEquals(Integer.valueOf(16), islist.root.getRight().getKey()); 
    assertEquals(null, islist.root.getRight().getRight()); 
    assertEquals(null, islist.root.getRight().getLeft()); 
    islist.insert(15, "fifteen"); 
    assertEquals("ten", islist.remove(10)); 
    assertEquals(Integer.valueOf(11), islist.root.getLeft().getKey());
    assertEquals(null, islist.root.getLeft().getRight()); 
    islist = new MyBST<>(); 
    islist.insert(25, "twentyfive"); 
    islist.insert(20, "twenty"); 
    islist.insert(32, "thirtytwo"); 
    islist.insert(19, "nineteen");
    islist.insert(23, "twentythree");
    islist.insert(22, "twentytwo"); 
    islist.insert(24, "twentyfour");
    islist.insert(21, "twentyone"); 
    assertEquals("twenty", islist.remove(20)); 
    assertEquals(Integer.valueOf(21), islist.root.getLeft().getKey()); 
    assertEquals("twentyone", islist.root.getLeft().getValue()); 
    assertEquals(null, islist.root.getLeft().getRight().getLeft().getLeft()); 
  }

  @Test
  public void testArrayList(){
    ArrayList<MyBST.MyBSTNode<Integer,String>> lmao = islist.inorder(); 
    MyBST.MyBSTNode<Integer,String> current = islist.root; 
    while(current.getLeft() != null){
      current = current.getLeft(); 
    }
    assertEquals(Integer.valueOf(3), lmao.get(0).getKey()); 
    assertEquals(Integer.valueOf(5), lmao.get(1).getKey()); 
    for(int i = 0; i < lmao.size(); i++){
      assertEquals(current.getKey(), lmao.get(i).getKey()); 
      current = current.successor(); 
    }
  }
}