/**
 * NAME: Darren Yeung
 * EMAIL: dyeung@ucsd.edu
 * PID: A15943292
 * This is the file that contains the class MyHashTableTester. 
 * It used junit to test. 
 */
import org.junit.*;
import static org.junit.Assert.*;

/**
 * This is the class MyHashTableTester. It has a private instance variable MyHashTable
 * and has various methods that use that instance variable to test whether not
 * the methods in the class are working
 */
public class MyHashTableTester {

	private MyHashTable hashTable1;

	@Before
	public void setUp()
	{
		hashTable1 = new MyHashTable(1);
	}
	@Test
	public void testInsert()
	{
		assertEquals("Checking insert", true, hashTable1.insert("abc"));
		assertEquals("Checking contains after insert", true,
		hashTable1.contains("abc"));
	}

	@Test
	public void testInsertNullPointerException(){
		try{
			hashTable1.insert(null);
			fail("Expected an NullPointerException to be thrown");
		}catch(NullPointerException e){
			assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
		}
	}


	@Test
	public void testDelete()
	{
		hashTable1.insert("abc");
		assertEquals("Checking delete", true, hashTable1.delete("abc"));
		assertEquals("Checking contains after delete", false,
		hashTable1.contains("abc"));
	}
	@Test
	public void testGetSize()
	{
		hashTable1.insert("abc");
		hashTable1.insert("pqr");
		hashTable1.insert("xyz");
		assertEquals("Checking getSize", Integer.valueOf(3),
		Integer.valueOf(hashTable1.getSize()));
	}

}
