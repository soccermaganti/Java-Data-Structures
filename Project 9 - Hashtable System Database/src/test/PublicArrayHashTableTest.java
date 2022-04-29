package test;

import structures.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PublicArrayHashTableTest {
  @Test 
  public void testConstructor1Linear() throws Exception  {
    CollisionHandler <Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (linCollisionHdler);
    assertEquals(0, q.getSize());
    assertTrue((ArrayHashTable.DEFAULT_CAPACITY - q.getCapacity()) < .001);
    assertTrue((ArrayHashTable.DEFAULT_LOAD_FACTOR - q.getLoadFactor()) < .001);
    Object[] karr = q.getKeyArray();
    assertTrue(karr.length == ArrayHashTable.DEFAULT_CAPACITY);
    Object[] varr = q.getValueArray();
    assertTrue(varr.length == ArrayHashTable.DEFAULT_CAPACITY);
    boolean[] aa = q.getIsActiveArray();
    assertTrue(aa.length == ArrayHashTable.DEFAULT_CAPACITY);
    assertTrue(q.getCollisionHandler()==linCollisionHdler);
  }

  @Test 
  public void testConstructor1Quadratic() throws Exception  {
    CollisionHandler <Integer> quadCollisionHdler = new QuadraticCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (quadCollisionHdler);
    assertEquals(0, q.getSize());
    assertTrue((ArrayHashTable.DEFAULT_CAPACITY - q.getCapacity()) < .001);
    assertTrue((ArrayHashTable.DEFAULT_LOAD_FACTOR - q.getLoadFactor()) < .001);
    Object[] karr = q.getKeyArray();
    assertTrue(karr.length == ArrayHashTable.DEFAULT_CAPACITY);
    Object[] varr = q.getValueArray();
    assertTrue(varr.length == ArrayHashTable.DEFAULT_CAPACITY);
    boolean[] aa = q.getIsActiveArray();
    assertTrue(aa.length == ArrayHashTable.DEFAULT_CAPACITY);
    assertTrue(q.getCollisionHandler()==quadCollisionHdler);
  }

  @Test 
  public void testConstructor2Linear() throws Exception  {
    CollisionHandler <Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (100, linCollisionHdler);
    assertEquals(0, q.getSize());
    assertEquals(100, q.getCapacity());
    assertTrue((ArrayHashTable.DEFAULT_LOAD_FACTOR - q.getLoadFactor()) < .001);
    Object[] karr = q.getKeyArray();
    assertTrue(karr.length == 100);
    Object[] varr = q.getValueArray();
    assertTrue(varr.length == 100);
    boolean[] aa = q.getIsActiveArray();
    assertTrue(aa.length == 100);
    assertTrue(q.getCollisionHandler()==linCollisionHdler);
  }

  @Test 
  public void testConstructor2Quadratic() throws Exception  {
    CollisionHandler <Integer> quadCollisionHdler = new QuadraticCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (100, quadCollisionHdler);
    assertEquals(0, q.getSize());
    assertEquals(100, q.getCapacity());
    assertTrue((ArrayHashTable.DEFAULT_LOAD_FACTOR - q.getLoadFactor()) < .001);
    Object[] karr = q.getKeyArray();
    assertTrue(karr.length == 100);
    Object[] varr = q.getValueArray();
    assertTrue(varr.length == 100);
    boolean[] aa = q.getIsActiveArray();
    assertTrue(aa.length == 100);
    assertTrue(q.getCollisionHandler()==quadCollisionHdler);
  }

  @Test 
  public void testConstructor3Linear() throws Exception  {
    CollisionHandler <Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (100, 0.9, linCollisionHdler);
    assertEquals(0, q.getSize());
    assertEquals(100, q.getCapacity());
    assertTrue((0.9 - q.getLoadFactor()) < .001);
    Object[] karr = q.getKeyArray();
    assertTrue(karr.length == 100);
    Object[] varr = q.getValueArray();
    assertTrue(varr.length == 100);
    boolean[] aa = q.getIsActiveArray();
    assertTrue(aa.length == 100);
    assertTrue(q.getCollisionHandler()==linCollisionHdler);
  }

  @Test
  public void testConstructor3Quadratic() throws Exception  {
    CollisionHandler <Integer> quadCollisionHdler = new QuadraticCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (100, 0.9, quadCollisionHdler);
    assertEquals(0, q.getSize());
    assertEquals(100, q.getCapacity());
    assertTrue((0.9 - q.getLoadFactor()) < .001);
    Object[] karr = q.getKeyArray();
    assertTrue(karr.length == 100);
    Object[] varr = q.getValueArray();
    assertTrue(varr.length == 100);
    boolean[] aa = q.getIsActiveArray();
    assertTrue(aa.length == 100);
    assertTrue(q.getCollisionHandler()==quadCollisionHdler);
  }

  @Test 
  public void testPutAndGetValue() throws Exception  {
    CollisionHandler <Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (linCollisionHdler);
    assertEquals(0, q.getSize());
    q.put(1, "apple");
    q.put(10, "pencil");
    assertTrue(q.getValue(1).equals("apple"));
    assertTrue(q.getValue(10).equals("pencil"));
    q.put(3, "pineapple");
    assertEquals(3, q.getSize());
    assertTrue(q.getValue(3).equals("pineapple"));
  }

  @Test 
  public void testResizeArray() throws Exception  {
    CollisionHandler <Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (3, linCollisionHdler);
    assertEquals(0, q.getSize());
    assertEquals(3, q.getCapacity());
    q.put(1, "apple");
    q.put(2, "pen");
    q.put(3, "pineapple");
    assertEquals(3, q.getSize());
    assertEquals(3, q.getCapacity());
    q.put(10, "pencil");
    assertEquals(4, q.getSize());
    assertEquals(6, q.getCapacity());
  }

  @Test 
  public void testLinearProb() throws Exception  {
    CollisionHandler <Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (200, linCollisionHdler);
    q.put(111, "Apple");
    q.put(311, "Pen");
    Object[] keyTable = q.getKeyArray();
    Object[] valueTable = q.getValueArray();
    Integer k = (Integer) keyTable[111];
    String v = (String) valueTable[112];
    assertTrue(k.equals(111));
    assertTrue(v.equals("Pen"));
    q.put(1114, "Dog");
    q.put(914, "Cat");
    q.put(713, "Bird");
    q.put(313, "Grape");
    keyTable = q.getKeyArray();
    valueTable = q.getValueArray();
    k = (Integer) keyTable[115];
    v = (String) valueTable[116];
    assertTrue(k.equals(914));
    assertTrue(v.equals("Grape"));
  }

  @Test 
  public void testQuadProbHandler1() throws Exception  {
     QuadraticCollisionHandler<Integer> hndler = new QuadraticCollisionHandler<Integer>(1,1);
     boolean[] arr = new boolean[27];
     arr[5] = true;
     arr[7] = true;
     arr[13] = true;
     arr[25] = true;
     int resIndex = hndler.probe(5, arr, 27);
     assertTrue(resIndex == 18);
  }

  @Test //(timeout = 1000)
	public void testQuadProbHandler2() throws Exception  {
		QuadraticCollisionHandler<Integer> hndler = new QuadraticCollisionHandler<Integer>(1,1);
		boolean[] arr = new boolean[207];
    arr[25]=true;
		arr[27]=true;
		arr[33]=true;
		arr[45]=true;
		arr[65]=true;
		arr[95]=true;
		arr[137]=true;
		arr[193]=true;
		int resIndex = hndler.probe(25, arr, 207);
	  assertTrue(resIndex == 58);
	}

  @Test 
  public void testResizeArray2() throws Exception  {
    CollisionHandler <Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (3, linCollisionHdler);
    assertEquals(0, q.getSize());
    assertEquals(3, q.getCapacity());
    q.put(1, "apple");
    q.put(2, "pen");
    q.put(3, "pineapple");
    q.put(4,"monkey");
    q.put(5,"dog");
    q.put(6,"cat");
    assertEquals(6, q.getSize());
    assertEquals(12, q.getCapacity());
    q.put(10, "pencil");
    q.put(50,"rat");
    q.put(100,"horse");
    assertEquals(9, q.getSize());
    assertEquals(12, q.getCapacity());
  }

  @Test 
  public void testPutAndGetValueandRemove() throws Exception  {
    CollisionHandler <Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
    ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String> (linCollisionHdler);
    assertEquals(0, q.getSize());
    q.put(1, "apple");
    q.put(3, "pineapple");
    q.remove(1);
    assertTrue(q.getValue(3).equals("pineapple"));
    //assertTrue(q.getValue(1).equals("apple"));
    q.put(5, "dog");
    q.put(10, "pencil");
    assertEquals(3, q.getSize());
    assertTrue(q.getValue(10).equals("pencil"));
    q.remove(5);
    q.remove(3);
    q.remove(10);
    //assertFalse(q.getValue(1).equals(null));
    assertEquals(0, q.getSize());
  }
@Test
public void removeLinear() throws Exception{
  CollisionHandler<Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
  ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String>(linCollisionHdler);
  assertEquals(0, q.getSize());
  q.put(1, "apple");
  q.put(10, "pencil");
  q.put(15, "pineapple");
  q.put(16, "pineapple2");
  q.put(17, "pineapple3");
  q.put(25, "pineapple4");
  q.put(40, "pineapple5");
  q.put(58, "pineapple5");
  q.put(68, "pineapple7");
  q.put(105, "pineapple7");

  assertTrue(q.getValue(1).equals("apple"));
  assertTrue(q.getValue(10).equals("pencil"));
  assertTrue(q.getValue(15).equals("pineapple"));
  assertEquals(10, q.getSize());
  assertEquals(q.remove(1), "apple");
  assertNull(q.getValue(1));
  assertTrue(q.getValue(10).equals("pencil"));
  assertEquals(9, q.getSize());
  assertEquals(q.remove(10), "pencil");
  assertNull(q.getValue(1));
  assertNull(q.getValue(10));
  assertEquals(8, q.getSize());
  assertEquals(q.remove(25), "pineapple4");
  assertNull(q.getValue(25));
  assertEquals(q.remove(58), "pineapple5");
  assertNull(q.getValue(58));
  q.remove(17);
  assertNull(q.getValue(17));
  q.remove(105);
  assertNull(q.getValue(105));
}

@Test
public void removeQuad() throws Exception{
  CollisionHandler<Integer> quadCollisionHdler = new QuadraticCollisionHandler<Integer>();
  ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String>(quadCollisionHdler);
  assertEquals(0, q.getSize());
  q.put(1, "apple");
  q.put(10, "pencil");
  q.put(15, "pineapple");
  q.put(16, "pineapple2");
  q.put(17, "pineapple3");
  q.put(25, "pineapple4");
  q.put(40, "pineapple5");
  q.put(58, "pineapple5");
  q.put(68, "pineapple7");
  q.put(105, "pineapple7");

  assertTrue(q.getValue(1).equals("apple"));
  assertTrue(q.getValue(10).equals("pencil"));
  assertTrue(q.getValue(15).equals("pineapple"));
  assertEquals(10, q.getSize());
  assertEquals(q.remove(1), "apple");
  assertNull(q.getValue(1));
  assertTrue(q.getValue(10).equals("pencil"));
  assertEquals(9, q.getSize());
  assertEquals(q.remove(10), "pencil");
  assertNull(q.getValue(1));
  assertNull(q.getValue(10));
  assertEquals(8, q.getSize());
  assertEquals(q.remove(25), "pineapple4");
  assertNull(q.getValue(25));
  assertEquals(q.remove(58), "pineapple5");
  assertNull(q.getValue(58));
  q.remove(17);
  assertNull(q.getValue(17));
  q.remove(105);
  assertNull(q.getValue(105));

}


@Test
public void testDuplicateLinear() throws Exception {
  CollisionHandler<Integer> linCollisionHdler = new LinearCollisionHandler<Integer>();
  ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String>(linCollisionHdler);
  assertEquals(0, q.getSize());
  q.put(1, "apple");
  q.put(10, "pencil");
  assertTrue(q.getValue(1).equals("apple"));
  assertTrue(q.getValue(10).equals("pencil"));
  assertEquals(2, q.getSize());
  q.put(1, "banana");
  assertTrue(q.getValue(1).equals("banana"));
  assertTrue(q.getValue(10).equals("pencil"));
  assertEquals(2, q.getSize());
  q.put(10, "pen");
  assertTrue(q.getValue(1).equals("banana"));
  assertTrue(q.getValue(10).equals("pen"));
  assertEquals(2, q.getSize());
}

@Test
public void testDuplicateQuad() throws Exception {
  CollisionHandler<Integer> quadCollisionHdler = new QuadraticCollisionHandler<Integer>();
  ArrayHashTable<Integer, String> q = new ArrayHashTable<Integer, String>(quadCollisionHdler);
  assertEquals(0, q.getSize());
  q.put(1, "apple");
  q.put(10, "pencil");
  assertTrue(q.getValue(1).equals("apple"));
  assertTrue(q.getValue(10).equals("pencil"));
  assertEquals(2, q.getSize());
  q.put(1, "banana");
  assertTrue(q.getValue(1).equals("banana"));
  assertTrue(q.getValue(10).equals("pencil"));
  assertEquals(2, q.getSize());
  q.put(10, "pen");
  assertTrue(q.getValue(1).equals("banana"));
  assertTrue(q.getValue(10).equals("pen"));
  assertEquals(2, q.getSize());
}
}


