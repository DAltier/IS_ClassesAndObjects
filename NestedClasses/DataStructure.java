package NestedClasses;

public class DataStructure {
    
  // Create an array
  private final static int SIZE = 15;
  private int[] arrayOfInts = new int[SIZE];
  

  public DataStructure() {
    // fill the array with ascending integer values
    for (int i = 0; i < SIZE; i++) {
      arrayOfInts[i] = i;
    }
  }


  public void printEven() {
    // Print out values of even indices of the array
    DataStructureIterator iterator = this.new EvenIterator();
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + " ");
    }
    System.out.println();
  }
  

  interface DataStructureIterator extends java.util.Iterator<Integer> { } 

  // Inner class implements the DataStructureIterator interface,
  // which extends the Iterator<Integer> interface
  
  private class EvenIterator implements DataStructureIterator {
    // Start stepping through the array from the beginning
    private int nextIndex = 0;

    public boolean hasNext() {
      // Check if the current element is the last in the array
      return (nextIndex <= SIZE - 1);
    }        
    
    public Integer next() {
      // Record a value of an even index of the array
      Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);
      // Get the next even element
      nextIndex += 2;
      return retValue;
    }
  }


  // Define a method named print(DataStructureIterator iterator). Invoke this method with an instance 
  // of the class EvenIterator so that it performs the same function as the method printEven.
  
  public void print(DataStructureIterator iterator) {
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + " ");
    }
    System.out.println();
  }

  public DataStructureIterator getEvenIterator() {
    return new EvenIterator();
  }
  

  // Invoke the method print(DataStructureIterator iterator) so that it prints elements 
  // that have an odd index value. Use an anonymous class as the method's argument instead 
  // of an instance of the interface DataStructureIterator.
  public int getSize() {
    return arrayOfInts.length;
  }

  public int getElement(int index) {
    return arrayOfInts[index];
  }


  // Define a method named print(java.util.function.Function<Integer, Boolean> iterator) that 
  // performs the same function as print(DataStructureIterator iterator). Invoke this method with 
  // a lambda expression to print elements that have an even index value. Invoke this method again 
  // with a lambda expression to print elements that have an odd index value.
  public void print(java.util.function.Function<Integer, Boolean> iterator) {
    for (int i=0; i<SIZE; i++) {
      if (iterator.apply(i)) {
          System.out.print(arrayOfInts[i] + " ");
      }
    }
    System.out.println();
  }


  // Define two methods so that the following two statements print elements that have an even index 
  // value and elements that have an odd index value:
  // DataStructure ds = new DataStructure()
  // ...
  // ds.print(DataStructure::isEvenIndex);
  // ds.print(DataStructure::isOddIndex);
  public static Boolean isEvenIndex(Integer index) {
    if (index % 2 == 0) return Boolean.TRUE;
    return Boolean.FALSE;
  }

  public static Boolean isOddIndex(Integer index) {
      if (index % 2 == 0) return Boolean.FALSE;
      return Boolean.TRUE;
  }

  public static void main(String s[]) {
      
    // Fill the array with integer values and print out only
    // values of even indices
    DataStructure ds = new DataStructure();
    System.out.println("printEven()");
    ds.printEven();


    // Define a method named print(DataStructureIterator iterator). Invoke this method with an instance 
    // of the class EvenIterator so that it performs the same function as the method printEven.
    System.out.println("print(DataStructureIterator) with getEvenIterator");
    ds.print(ds.getEvenIterator());

    
    // Invoke the method print(DataStructureIterator iterator) so that it prints elements 
    // that have an odd index value. Use an anonymous class as the method's argument instead 
    // of an instance of the interface DataStructureIterator.
    System.out.println("print(DataStructureIterator) with anonymous class, odd index values");
    ds.print(new DataStructureIterator () {
      // Start stepping through the array from the beginning
      int nextIndex = 1;
    
      public boolean hasNext() {             
        // Check if the current element is the last in the array
        return (nextIndex <= ds.getSize() - 1); 
      }        
    
      public Integer next() {             
        // Record a value of an even index of the array
        // ds object in scope within class declaration
        Integer retValue = Integer.valueOf(
          ds.getElement(nextIndex));
        
        // Get the next even element
        nextIndex += 2;
        return retValue;
      }
    });


    // Define a method named print(java.util.function.Function<Integer, Boolean> iterator) that 
    // performs the same function as print(DataStructureIterator iterator). Invoke this method with 
    // a lambda expression to print elements that have an even index value. Invoke this method again 
    // with a lambda expression to print elements that have an odd index value.
    System.out.println("print(Function) with lambda expressions");
    ds.print(i -> i % 2 == 0);
    ds.print(i -> i % 2 != 0);


    // Define two methods so that the following two statements print elements that have an even index 
    // value and elements that have an odd index value:
    // DataStructure ds = new DataStructure()
    // ...
    // ds.print(DataStructure::isEvenIndex);
    // ds.print(DataStructure::isOddIndex);
    System.out.println("print(Function) with method references");
    ds.print(DataStructure::isEvenIndex);
    ds.print(DataStructure::isOddIndex);
  }
}
