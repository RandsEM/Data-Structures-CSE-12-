/**
 * NAME: Darren Yeung
 * PID: A15943292
 * EMAIL: dyeung@ucsd.edu
 * This file contains the class Sorting. It has two implemnneted sorting algortithms.
 * One of them is insertion sort. The other one is merge sort
 */

/**
 * This is the class Sorting which is a generic class that takes in stuff that extends
 * comparable. It has no instance varaibles and two instance methods. It has implemented
 * insertion sort and merge sort.
 */

import java.util.Arrays;

public class Sorting<E extends Comparable>{

  /**
   * This ist he method insertionsort. Ita takes an an array and sorts
   * the array by insertion sort. Basically, we start at the beginning of the array,
   * look at the next value, and put it where it needs to be in the sorted part
   * of the array.
   * @param array The array the user wants to sort
   */
  public void insertionSort(E[] array){
    if(array == null){
      throw new NullPointerException(); 
    }
    Object[] sorting = new Object[array.length]; 
    //Copies the contents of input array to new array
    int sorted = 0; 
    //beginning of alg 
    for(int i = 0; i < array.length; i++){
      if(array[i] == null){
        throw new NullPointerException(); 
      }
      //if first element, don't do much
      if(i == 0){
        System.out.println(Arrays.toString(array));
        continue; 
      }else{
        int tracker = i; 
        for(int j = sorted; j >= 0; j--){
          if(array[tracker].compareTo(array[j]) < 0){
            E stored = array[tracker]; 
            array[tracker] = array[j];
            array[j] = stored; 
            tracker--; 
          }
        }
        sorted++; 
        System.out.println(Arrays.toString(array));
      }
    }
  }

  /**
   * This method is mergesort that takes in an array. Because, it splits the
   * list with halves until it gets an array of one element, It merges the sorted
   * spitted elements back together.
   * @param array Array the user wants sorted.
   */
  public void mergeSort(E[] array){
    if(array == null){
      throw new NullPointerException(); 
    }
    for(int i = 0; i < array.length; i++){
      if(array[i] == null){
        throw new NullPointerException(); 
      }
    }
    if(array.length > 1){
      int j; 
      if(array.length < 4){
        j = (0 + array.length )/2; 
      }else{
        j = (0 + array.length)/4;
      }
      Comparable[] lefter = Arrays.copyOfRange(array,0,j ); 
      Comparable[] righter = Arrays.copyOfRange(array, j, array.length); 
      mergeSort((E[])lefter); 
      mergeSort((E[])righter); 
      merge( array, (E[])lefter, (E[])righter, lefter.length ,righter.length); 
    }
  }

  /**
   * This is a helper method for mergesort that merges two arrays
   */
  public void merge(E[] array, E[] leftArray, E[] rightArray, int left, int right){
    //left is leftarray's size lmao.. 
    //right is rightarry's size as well 
    int mergesize = left + right; 
    int tracker = 0; 
    int leftpos = 0; 
    int rightpos = 0; 
    Comparable[] mergedNumbers = new Comparable[mergesize]; 
    while(leftpos <= leftArray.length - 1 && rightpos <= rightArray.length -1){
      if(leftArray[leftpos].compareTo(rightArray[rightpos]) < 0){
        mergedNumbers[tracker] = leftArray[leftpos]; 
        leftpos++;
        tracker++; 
      }else{
        mergedNumbers[tracker] = rightArray[rightpos];
        rightpos++; 
        tracker++; 
      }
    }
    while(leftpos <= leftArray.length - 1 ){
      mergedNumbers[tracker] = leftArray[leftpos]; 
      leftpos++; 
      tracker++; 
    }
    while(rightpos<= rightArray.length -1){
      mergedNumbers[tracker] = rightArray[rightpos];
      rightpos++; 
      tracker++; 
    }
    for(int i = 0; i < mergesize; i++){
      array[i] = (E)mergedNumbers[i]; 
    }
    System.out.println(Arrays.asList(mergedNumbers));
  }
}