import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import javax.xml.transform.SourceLocator; 

public class MyPriorityQueueTester {
  
  private MyMinHeap<Integer> integerHeap; 
  ArrayList<Integer> listIntsRegularHeap;
  private static final Integer[] arrIntsRegular = {5, 8, 10, 9, 11}; 
  MyPriorityQueue<Integer> tester;

  @Before 
  public void setUp(){
    integerHeap = new MyMinHeap<>(); 
    listIntsRegularHeap = new ArrayList<>(Arrays.asList(arrIntsRegular));
    tester = new MyPriorityQueue<>(listIntsRegularHeap);
  }

  @Test
  public void testCon(){
    assertEquals(5, tester.getLength());
    assertEquals(Integer.valueOf(5), tester.peek());
  }

  @Test
  public void testPush(){
    tester.push(20);
    tester.push(-3);
    tester.push(6);
    assertEquals(8, tester.getLength());
    assertEquals(Integer.valueOf(-3), tester.peek());
    assertEquals(Integer.valueOf(6), tester.heap.list.get(1));
    assertEquals(Integer.valueOf(20), tester.heap.list.get(5));
  }

  @Test
  public void testPeek(){
    tester.push(3);
    tester.push(1);
    tester.push(2);
    tester.push(0);
    assertEquals(Integer.valueOf(0), tester.peek());
    tester.clear();
    assertEquals(null, tester.peek());
  }

  @Test
  public void testPop(){
    assertEquals(Integer.valueOf(5), tester.pop());
    assertEquals(Integer.valueOf(8), tester.peek());
    tester.clear();
    assertEquals(null, tester.pop());
  }

  @Test
  public void testLength(){
    assertEquals(5, tester.getLength());
  }

  @Test
  public void testClear(){
    tester.clear();
    assertEquals(0, tester.getLength());
  }

  @Test
  public void testNullPointer(){
    try{
      tester.push(null);
      fail("Expected dummy");
    }catch (NullPointerException e){
      assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
    }

    try{
      MyPriorityQueue<Integer> tester2 = new MyPriorityQueue<>(null);
      fail("Expected dummyass");
    }catch(NullPointerException e){
      assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
    }

    try{
      ArrayList<Integer> alist = new ArrayList<Integer>();
      alist.add(2);
      alist.add(null);
      MyPriorityQueue<Integer> tester3 = new MyPriorityQueue<>(alist);
      fail("Expected dummy");
    }catch(NullPointerException e){
      assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
    }
  }
  
}