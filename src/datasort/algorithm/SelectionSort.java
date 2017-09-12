/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.algorithm;

/**
 *
 * @author willi
 */
public class SelectionSort implements Sort{

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {
        //Sort the i..n unsorted elements
        for(int i = 0 ; i < array.length-1; i++){
            int posMin= i;
            //find the minimum element in unsorted part
            for(int j = i+1; j < array.length;j++){
                if(array[j].compareTo(array[posMin])<0){
                    posMin = j;
                }
            }
            //swap elements if the current posMin element is less than element[i]
            // and it is not the same element
            if(posMin != i){
                T tmp = array[i];
                array[i] = array[posMin];
                array[posMin] = tmp;
            }
        }
        return array;
    }

    @Override
    public <T extends Comparable<T>> void print(T[] array) {
        for(int i = 0 ; i < array.length; i++) System.out.print(array[i].toString() + " - ");
    }

  
    
}
