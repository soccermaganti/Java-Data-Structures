package structures;

import java.security.Key;

import javax.naming.ldap.StartTlsRequest;

public class LinearCollisionHandler <K> implements CollisionHandler <K>{
    private int probeLength;

    /**
  * Constructors to set probeLength to 1, or a different length.
  */
    public LinearCollisionHandler(){
        this.probeLength = 1;
    }

    public LinearCollisionHandler(int probeLength){
        this.probeLength = probeLength;
    }

/**
  * Method starts at index and searches linearly until an open spot
  * is found in the array. This could include index itself.
  * index = (index + probeLength) % size
  */
   public int probe(int index, boolean[] activeArray, int M) {
      //TODO: Implement this method.
        // int numChecked = 0;
        while (/*numChecked < M && */activeArray[index] == true) {
          index = (index + probeLength) % M;
          //numChecked++;
        }

        // if (numChecked == M) {
        //   return -1;
        // }
        return index;
    }
   

  /**
* Start at index and search the array linearly until the target
* is found. Then return the array index of the target. 
* Return -1 if not found.
*/
   public int search(int startIndex, K target, K[] keyArray, boolean [] activeArray, int M){
      //TODO: Implement this method.
    int numChecked = 0;
    // int index = startIndex;
    startIndex = Math.abs(target.hashCode()) % M;
  
    while ( /*keyArray[startIndex] != null &&*/ numChecked < M ){
      //if (keyArray[startIndex].equals(target) && activeArray[startIndex]){
      if (target.equals(keyArray[startIndex]) && activeArray[startIndex]){
      //if (keyArray[startIndex].equals(target)&& activeArray[startIndex]){
        return startIndex;
      } else {
      if (keyArray[startIndex] == null){
        return -1;
      }
      startIndex = (startIndex + probeLength) % M;
      numChecked++;
      }
    }
    return -1;
   }
}