package structures;

import java.util.Iterator;

//This class implements a hashtable for a generic key and value type using an array backend.
public class ArrayHashTable<K, V> implements HashTable<K, V> {

  //Default constructor values- DO NOT CHANGE.
  public static final int DEFAULT_CAPACITY = 5;
  public static final double DEFAULT_LOAD_FACTOR = 0.7;

  //Provided instance variables- DO NOT CHANGE
  protected K[] keyArray;  //stores all keys 
  protected V[] valueArray;  // stores all values
  protected boolean[] isActiveArray;  // if true the cell is active, false the cell has data to be deleted.
  protected CollisionHandler <K> collisionHandler;  // collision handler can be either linear or quadratic.
  protected int count; // counts the number of key/value pairs on the hashtable
  protected int capacity;  // length of the arrays
  private double loadFactor;  // the ratio (0 < loadFactor < 1) that determines when to resize the hashtable.

  /**
   *   Initialize count to 0, capacity and loadFactor to the respective DEFAULT values,
   *   collisionHandler to the collisionHdler argument, create the three arrays
   *   for keys, values, and the isActive boolean array.
   */
  public ArrayHashTable(CollisionHandler <K> collisionHdler) {
      //TODO: Implement this method.
      int count = 0;
      capacity = DEFAULT_CAPACITY;
      loadFactor = DEFAULT_LOAD_FACTOR;
      collisionHandler = collisionHdler;
      keyArray = (K[]) new Object[capacity];
      valueArray = (V[]) new Object[capacity];
      isActiveArray = new boolean[capacity];
  }

  /**
   *   Initialize count to 0, capacity to the argument, loadFactor to the respective DEFAULT values,
   *   collisionHandler to the collisionHdler argument, create the three arrays
   *   for keys, values, and the isActive boolean array.
   */
  public ArrayHashTable(int capacity, CollisionHandler <K> collisionHdler){
      //TODO: Implement this method.
      int count = 0;
      this.capacity = capacity;
      loadFactor = DEFAULT_LOAD_FACTOR;
      collisionHandler = collisionHdler;
      keyArray = (K[]) new Object[capacity];
      valueArray = (V[]) new Object[capacity];
      isActiveArray = new boolean[capacity];


  }

  /**
   *   Initialize count to 0, capacity and loadFactor to the respective argument values,
   *   collisionHandler to the collisionHdler argument, create the three arrays
   *   for keys, values, and the isActive boolean array.
   */
  public ArrayHashTable(int capacity, double loadFactor, CollisionHandler <K> collisionHdler){
      //TODO: Implement this method.
      int count = 0;
      this.capacity = capacity;
      this.loadFactor = loadFactor;
      collisionHandler = collisionHdler;
      keyArray = (K[]) new Object[capacity];
      valueArray = (V[]) new Object[capacity];
      isActiveArray = new boolean[capacity];
  }

  /** 
   * Returns the number of elements in the hash table.
   */
  public int getSize() {
      //TODO: Implement this method.
      return count;
  }

   /**
   * One approach: Double the capacity of the hashtable. Create new, larger arrays.
   * For each key in the key array, if that cell is active (true) in
   * the activeArray, get the index by calling the hash function, use the collisionHandler
   * probe method to get the new index (it may be different from the original hash if
   * a collision occurred). Enter the key in the new keyArray, value in the new valueArray,
   * and set the new activeArray to true. Finally, assign the current keyArray, valueArray,
   * and activeArray to the new arrays.
   */
  private void resizeArray() {
   //TODO: Implement this method
    // K[] newK1 = keyArray;
    // V[] newV1 = valueArray;
    // boolean[] newActiveArray1 = isActiveArray;
   
    capacity = capacity * 2; 

    K[] newK = (K[]) new Object[capacity];
    V[] newV = (V[]) new Object[capacity];
    boolean[] newActiveArray = new boolean[capacity];

    for (int i = 0; i < keyArray.length; i++){
      if(isActiveArray[i] == true) {
      int index = getHash(keyArray[i]);
      index = collisionHandler.probe(index, newActiveArray, capacity);
      newK[index] = keyArray[i];
      newV[index] = valueArray[i];
      newActiveArray[index] = true;
      // keyArray[i] = newK[i];
      // valueArray[i] = newV[i];
      // isActiveArray[i] = newActiveArray[i];
      }
    }

    keyArray = newK;
    valueArray = newV;
    isActiveArray = newActiveArray;
  }

  /**
  * Calculates and returns the load factor of the hash table.
  * This is the ratio of the number of elements stored in the hashtable
  * divided by the total capacity of the hashtable.
  */
  private double calcLoadFactor() {
   //TODO: Implement this method
    return ((1.0 * count) / capacity);
  } 

  /** 
   * Insert the provided key value pair into the hashtable. If the key exists in 
   * the table, the stored value is overwritten with the new value passed in.
   * Please use the provided getHash method to get the hash for the array index.
   * Steps: check is current load is > loadFactor. If true, call resizeArray.
   * Then see if key is in the table by calling getIndex. If key exists, overwrite value
   * in the valueArray. If key does not exist, get the index by calling the hash function (getHash).
   * Then call resolveCollision to see if a collision occurs and if so, that method will use the 
   * collision handler to resolve it. Then enter the key in the keyArray using the index returned from 
   * resolveCollision, enter the value in the valueArray, and set the isActiveArray to true. Finally,
   * increment the count.
   */
  public void put(K key, V value) {
      //TODO: Implement this method.
      int index = getHash(key);
      if (/*getIndex(index, key) == -1 &&*/ calcLoadFactor() > loadFactor){
        resizeArray();
      }
      
      if (getIndex(index, key) != -1) {
        //index = resolveCollision(index, key);
        index = getIndex(index, key);
        valueArray[index] = value;
        //keyArray[index] = key;
        //isActiveArray[index] = true;
      } else {
        // if (calcLoadFactor() > loadFactor){
        //   resizeArray();
        // }
        //Essentially can't find an empty index so it returns -1.
        index = resolveCollision(index, key);
        keyArray[index] = key;
        valueArray[index] = value;
        isActiveArray[index] = true;
        count++;
      }
  }

  
  private int getHash(K key) {
    return Math.abs(key.hashCode()) % capacity;
  }

  private int getIndex(int index, K key){
    return collisionHandler.search(index, key, keyArray, isActiveArray, capacity);
  }

  private int resolveCollision(int index, K key){
    return collisionHandler.probe(index, isActiveArray, capacity);
  }

  /**
   * Returns the value associated with the key if it exists in the hashtable.
   * Get the index by calling the hash function, then check if key exists by calling getIndex.
   * Return the value in the valueArray if key is in the table.
   * Returns null if the key is not in the table.
   */
  public V getValue(K target) {
   //TODO: Implement this method
   V value = null;
   //int probed = 0;
   int index = getHash(target);
   if (getIndex(index, target) != -1){
    index = getIndex(index, target); 
    value = valueArray[index];
   }
   return value;
   
   
   //int index = getHash(target);
   //int probed = 0;
  //  if (getIndex(getHash(target), target) != -1){
  //    return valueArray[getHash(target)];
  //  } else {
  //    return null;
  //  }
   }


  /**
   * Removes the key-value pair specified by the targetKey from the hashtable and returns the value. 
   * The method gets the hash on the targetKey, then checks if the key is in the table by calling getIndex.
   * Then, if targetKey is in the table, get the value associated with the targetKey from the valueArray,
   * then set the activeArray to false as this cell is now open, decrement the count, and return the value.
   * 
   * @throws ElementNotFoundException if the target does not exist in the table.
   */
  public V remove(K targetKey)throws ElementNotFoundException {
   //TODO: Implement this method
    if (targetKey == null){
      return null;
    } 
    int index = getHash(targetKey);
    // if (keyArray[getIndex(index, targetKey)] == null || getIndex(index, targetKey) == -1){
    //   throw new ElementNotFoundException("so cry?");
    // }
    if ( getIndex(index,targetKey) == -1){
      throw new ElementNotFoundException("so cry?");
    }
    
    index = getIndex(index, targetKey);
    isActiveArray[index] = false;
    count--;
    return valueArray[index];
    // //if (getIndex(index, targetKey) != -1) {
    // if (getValue(targetKey) != null) {
    //   index = getIndex(index, targetKey);
    //   //V temp = valueArray[index];
    //   //valueArray[index] = null;
    //   //keyArray[index] = null;
    //   isActiveArray[index] = false;
    //   count--;
    //   return valueArray[index];
    // } else {
    //   throw new ElementNotFoundException("so cry?");
    // }
  }

    // private boolean emptyAfterRemoval(){
    //   return false;
    // }



  /**
   *  Returns a KeyIterator for the keys in this HashTable. 
   */
  public Iterator<K> keyIterator(){
       return new KeyIterator<K>(keyArray, count);
  }

    /******** methods used for testing purposes ***********/
    public int getCapacity(){
      return capacity;
    }

    public double getLoadFactor(){
      return loadFactor;
    }
  
    public K[] getKeyArray(){
       return keyArray;
    }  
  
    public V[] getValueArray(){
      return valueArray;
   }

   public boolean[] getIsActiveArray(){
     return isActiveArray;
   }

   public CollisionHandler <K>  getCollisionHandler(){
     return collisionHandler;
   }
  /************************************************************/

   /**  
    * Use this code for testing your ArrayHashTable. 
    * Comment out statements as needed. Use the debugger to see
    * what your code is doing by stepping in, out, and over methods
    * and statements, and checking the variables pane to see the state 
    * of your table and its variables.
    */
  public static void main(String[] args) throws Exception {
      // Init the type of collision handler: linear or quadratic.
    // CollisionHandler <String> collisionHdler = new LinearCollisionHandler<>();
      CollisionHandler <String> collisionHdler = new QuadraticCollisionHandler<>();
     ArrayHashTable<String, String> table = new ArrayHashTable<String, String> (collisionHdler);
     table.put("fatimeh", "ef4#B%k");
     // test the get method after the put method was called:
     System.out.println("test get after put: "+table.getValue("fatimeh"));
     table.put("matt", "A9d%&b");
     table.put("fadhil", "2h*k9s");
     table.put("rumeng", "j8*shX2");
    // this next call should cause a resize- uncomment when the table works on the above data:
     table.put("harper", "m8Ut6%#a");
    // test the keyIterator
     Iterator<String> keyIter = table.keyIterator();
     while(keyIter.hasNext()){
       String curKey = keyIter.next();
       System.out.print(curKey+ " ");
       System.out.println(table.getValue(curKey)+", ");
     }
    // test removing 
    table.remove("matt");
    System.out.println("test remove: "+table.getValue("matt"));
  }
}
