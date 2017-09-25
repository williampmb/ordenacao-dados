/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.structure;

/**
 *
 * @author willi
 */
public class MyHeap<T extends Comparable<T>> {

    T[] heap; //array com dados do heap

    int pos; //posição vazia do array
    int dnary; //quantos filhos tem cada pai
    int order; //quão grande o heap vai crescer quando estiver cheio

    MyHeap(int size) {
        heap = (T[]) new Comparable[size];
        pos = 0; //começa com 1 para facilitar achar os filhos
        dnary = 2;
        order = 2;
    }

    MyHeap(T[] array) {
        heap = array;
        pos = 0;
        dnary = 2;
        order = 2;
    }

    public T[] getHeap() {
        return heap;
    }

    public static <T extends Comparable<T>> MyHeap buildHeapFromArray(T[] array) {
        MyHeap myHeap = new MyHeap(array);
        for (T element : array) {
            myHeap.insert(element);
        }
        return myHeap;
    }

    private void insert(T element) {
        if (pos == heap.length) {
            createSpace();
        }
        heap[pos] = element;
        checkParentPriority(pos);
        pos++;

    }

    private void createSpace() {
        T[] newHeap = (T[]) new Comparable[heap.length + ((int) Math.pow(dnary, order))];
        order++;
        for (int i = 1; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    private void checkParentPriority(int currentPos) {
        if (currentPos == 0) {
            return;
        }

        int posParent = getParentPosition(currentPos);
        if (heap[currentPos].compareTo(heap[posParent]) < 0) {
            T tmp = heap[currentPos];
            heap[currentPos] = heap[posParent];
            heap[posParent] = tmp;
            checkParentPriority(posParent);
        }
    }

    public T min() {
        if (pos == 0) {
            return null;
        } else if (pos == 1) {
            return heap[--pos];
        }

        T min = heap[0];
        heap[0] = heap[--pos];
        checkChildPriority(0);

        return min;
    }

    private void checkChildPriority(int currentPos) {
        int leftChild = getLeftChildPosition(currentPos);
        int rightChild = getRightChildPosition(currentPos);
        int smallerChild;
        
        if (hasChild(leftChild) && hasChild(rightChild)) {
           smallerChild = (heap[leftChild].compareTo(heap[rightChild])<=0)? leftChild:rightChild;
        }else if(hasChild(leftChild)){
            smallerChild = leftChild;
        }else{
            return;
        }
        
        if(heap[smallerChild].compareTo(heap[currentPos])<0){
            swap(currentPos, smallerChild);
            checkChildPriority(smallerChild);
        }
    }

    private int getParentPosition(int currentPos) {
        return (currentPos - 1) / 2;
    }

    private int getLeftChildPosition(int currentPos) {
        return 2 * currentPos + 1;
    }

    private int getRightChildPosition(int currentPos) {
        return 2 * currentPos + 2;
    }

    private void swap(int currentPos, int leftChild) {
        T tmp = heap[currentPos];
        heap[currentPos] = heap[leftChild];
        heap[leftChild] = tmp;
    }

    private boolean hasChild(int childPos) {
        return childPos < pos;
    }
}
