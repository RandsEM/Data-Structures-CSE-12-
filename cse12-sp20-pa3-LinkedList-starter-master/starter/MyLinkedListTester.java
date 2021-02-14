/**
 * NAME: Darren Yeung 
 * PID: A15943292 
 * EMAIL: dyeung@ucsd.edu 
 * ACCOUNT: cs12sp20bef
 * This file contains the class MyLinkeList which is a class 
 * that tests the MyLinkedList class for bugs 
 */
import org.junit.*;	
import static org.junit.Assert.*;	
import java.util.LinkedList;	
import java.util.ListIterator;	


	
/**
 * This is the class MyLinkedListTester which contains a series 
 * of lists that is going to be used in testing. There is an empty list,
 * list containing one element, list containing multiple elements, and a string
 * list
 */
public class MyLinkedListTester	
{	
	private MyLinkedList<Integer> empty ;	
	private MyLinkedList<Integer> one ;	
	private MyLinkedList<Integer> several ;	
	private MyLinkedList<String>  slist ;	
	static final int DIM = 5;	
	static final int FIBMAX = 30;	
  private ListIterator<Integer> iterTest;	
  private MyLinkedList<Integer> ownList; 

	/**	
	 * Standard Test Fixture. An empty list, a list with one entry (0) and 	
	 * a list with several entries (0,1,2)	
	 */ 	
	@Before	
	public void setUp()	
	{	
		empty = new MyLinkedList<Integer>();	
		one = new MyLinkedList<Integer>();	
		one.add(0,new Integer(0));	
		several = new MyLinkedList<Integer>() ;	
		// List: 1,2,3,...,Dim	
		for (int i = DIM; i > 0; i--)	
			several.add(0,new Integer(i));	

		// List: "First","Last"	
		slist = new MyLinkedList<String>();	
		slist.add(0,"First");	
    slist.add(1,"Last");	
    ownList = new MyLinkedList();
	}	

	/** Test if first node of the lists are correct */	
	@Test	
	public void testGetFirst()	
	{	
		assertEquals("Check 0",new Integer(0),one.get(0)) ;	
    assertEquals("Check 0",new Integer(1),several.get(0)) ;
    assertEquals("check 0", "First", slist.get(0));

	}	

	/** Test if size of lists are correct */	
	@Test	
	public void testListSize()	
	{	
		assertEquals("Check Empty Size",0,empty.size()) ;	
		assertEquals("Check One Size",1,one.size()) ;	
		assertEquals("Check Several Size",DIM,several.size()) ;	
	}	

	/** Test setting a specific entry */	
	@Test	
	public void testSet()	
	{	
		slist.set(1,"Final");	
		assertEquals("Setting specific value", "Final",slist.get(1));	
	}	

	/** Test isEmpty */	
	@Test	
	public void testEmpty()	
	{	
		assertTrue("empty is empty",empty.isEmpty()) ;	
		assertTrue("one is not empty",!one.isEmpty()) ;	
		assertTrue("several is not empty",!several.isEmpty()) ;	
	}	

	/** Test out of bounds exception on get */	
	@Test	
	public void testGetException()	
	{	
		try 	
		{	
			empty.get(0);	
			// This is how you can test when an exception is supposed 	
			// to be thrown	
			fail("Should have generated an exception");  	
		}	
		catch(IndexOutOfBoundsException e)	
		{	
			//  normal	
		}	
	}	

	/** Test iterator on empty list and several list */	
	@Test	
	public void testIterator()	
	{	
		int counter = 0 ;	
		ListIterator<Integer> iter;	
		for (iter = empty.listIterator() ; iter.hasNext(); iter.next())	
		{	
			fail("Iterating empty list and found element") ;	
		}	
    counter = 0 ;	
    
    for (iter = several.listIterator() ; iter.hasNext(); iter.next()){
      counter++;	
      System.out.println(counter);
    }	
		assertEquals("Iterator several count", counter, DIM);	
	}	
	
	/* Add your own tests here */	

	//////////////////////////////////////////	
	//Begin testing on List Iterator methods//	
	/////////////////////////////////////////	


	/** Test listiterator hasnext method while it goes through the empty	
	 * and one list	
	 */	
	@Test 	
	public void testIteratorHasNext() {	

		ListIterator<Integer> iter = empty.listIterator();	
		ListIterator<Integer> iter1 = one.listIterator();	

		assertTrue(!iter.hasNext());	
		assertTrue(iter1.hasNext());							
	}	

	/** Test listiterator next method */	
	@Test	
	public void testIteratorNext() {	

		iterTest = several.listIterator();	

		assertEquals(new Integer(1),iterTest.next());	
		assertEquals(new Integer(2),iterTest.next());	
		assertEquals(new Integer(3),iterTest.next());	
		assertEquals(new Integer(4),iterTest.next());	
		assertEquals(new Integer(5),iterTest.next());						
	}	
	
	/** Test nextIndex method of list iterator */	
	@Test	
	public void testIteratorNextIndex() {	
		iterTest = several.listIterator();	

		//Test the nextIndex method at the start of and end 	
		//of the list as well as middle of the list	
		assertEquals(0, iterTest.nextIndex());	

		iterTest.next();	
		iterTest.next();	
		iterTest.next();	

		assertEquals(3, iterTest.nextIndex());			

		iterTest.next();	
		iterTest.next();	

		assertEquals(5, iterTest.nextIndex());	
	}	

	/** Test the remove method of list iterator */	
	@Test 	
	public void testIteratorRemove() {	
		iterTest = several.listIterator(); 	

		//Test whether removes method work after next method		
		iterTest.next();	
		iterTest.next();	
		iterTest.remove();	

		assertEquals(new Integer(1), iterTest.previous());	

		//Test whether remove method work after previous method	
		iterTest.next();	
		iterTest.next();	
		iterTest.previous();	
		iterTest.remove();	

		assertEquals(new Integer(4), iterTest.next());	
  } 	
  
  @Test
  public void testFirst(){
    try{
      ownList.add(null);
      fail("was supposed to throw");
    }catch(NullPointerException e){
    }

    assertEquals(new Integer(1),several.get(0));
    several.getNth(0).setElement(2);
    assertEquals(new Integer(2),several.get(0));
  }
	
	/* Add your own tests here */	

}
