/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.algorithm;

import datasort.structure.MyHeap;

/**
 *
 * @author willi
 */
public class HeapSort extends Sort {

    @Override
    public <T extends Comparable<T>> T[] execute(T[] array) {
        MyHeap myHeap = MyHeap.buildHeapFromArray(array);
        
        for(int i =0 ; i < array.length; i++){
            array[i] = (T) myHeap.min();
        }
        
        return array;
    }
    
}
