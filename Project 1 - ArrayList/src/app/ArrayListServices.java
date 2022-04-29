package app;
import java.util.ArrayList;

/**
 *  This class provides methods that perform operations on a list of generic objects.
 *  The Objects are assumed to have an appropriate implementation of the equals method.
 */
public class ArrayListServices <T> {

   /**
    * This method takes an ArrayList as a parameter and returns a new ArrayList that 
    * does not contain any duplicate data. For example, if this list was passed in: 
    * [A, B, D, A, A, E, B], the method would return a list containing: [A, B, D, E]. 
    * The ordering of the data does not matter. 
    * Assume that the parameter is not null, but it may be an empty ArrayList, in which case 
    * your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the 
    * same as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> deDuplicate(ArrayList<T> inputList){
      //Write your comments on how you implemented the method.
      /**
      **/
      //TODO: Implement this method.
      //Create a new arrayList in order to store the non duplicates in a new arrayList
      ArrayList<T> newList1 = new ArrayList<T>();
      //Enhanced For loop in order to interate over the arraylist and then used an if Statement to see if the new list already contains that number. 
      //If it doesn't, it adds the number to the new List
      for (T Items : inputList) {
         if (!newList1.contains(Items)) {
            newList1.add(Items);  
         }
      }
      //returns the newList1
      return newList1;
   }

   /**
    * This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the intersection of the data in the ArrayLists passed in. The intersection 
    * contains the elements that occur in both lists.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [B, E]. The ordering of the data does not matter. Note that 
    * the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList, in which 
    * case your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same as 
    * the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetIntersection(ArrayList<T> listA, ArrayList<T> listB){
      //Write your comments on how you implemented the method.
      /**
      **/
      //TODO: Implement this method.
      //Create a new ArrayList in order to store the values in there
      ArrayList<T> newList1 = new ArrayList<T>();
      //Nested for loops in order to iterate through both ArrayLists and then checking whether or not the Item in listA that its checking is equal to one (Next line)
      // of the elements in listB. If it does, it'll add the Item to the new list.
      for (T Items1 : listA) {
         for (T Items2 : listB) {
            if(Items1.equals(Items2)){
               if (!newList1.contains(Items1)) {
                  newList1.add(Items1);
               }
            }
         }
      }
      //returns the ArrayList newList1
      return newList1;
   }

   /**
    *  This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the set difference of the data in the ArrayLists passed in. The set difference 
    * contains the elements that occur only in one or the other list, but not in both.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [A, C]. The ordering of the data does not matter. Note 
    * that the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList. In the 
    * case where one list is empty, your method returns a new ArrayList that contains all of the 
    * elements on the other list- with no duplicates. In the case where both lists are empty, your 
    * method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same 
    * as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetDifference(ArrayList<T> listA, ArrayList<T> listB){
      //Write your comments on how you implemented the method.
      /**
      **/
      //TODO: Implement this method.
      //Created a new Arraylist in order to store the elements that had a set difference in there
      ArrayList<T> newList1 = new ArrayList<T>();
      //if statement to check whether or not the lists are empty and if they are, it'll return the empty list
      if (listB.isEmpty() && listA.isEmpty()) {
         return newList1;
      }
      //for loop to check whether or not listB contains Item1 and checks for duplicates.
      //If it doesn't contains Item1, it'll add it to newList1
      for (T Item1 : listA) {
         if (!listB.contains(Item1)) {
            if (!newList1.contains(Item1)) {
               newList1.add(Item1);
            }
         }
      }
      //for loop to check whether or not listA contains Item2 and checks for duplicates
      //If it doesn't contain Item2, it'll add it to newList1
      for (T Item2 : listB) {
         if (!listA.contains(Item2)) {
            if (!newList1.contains(Item2)) {
               newList1.add(Item2);
            } 
         }
      }
      //returns the ArrayList newList1
      return newList1;
   }
}


