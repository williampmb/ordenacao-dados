/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.structure;

import datasort.data.ElementPair;

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
        pos = 1; //começa com 1 para facilitar achar os filhos
        dnary = 2;
        order = 2;
    }

    public T[] getHeap() {
        return heap;
    }

    public static <T extends Comparable<T>> MyHeap buildHeapFromArray(T[] array) {
        MyHeap myHeap = new MyHeap(array.length + 1);
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
        T[] newHeap = (T[]) new Object[heap.length + ((int) Math.pow(dnary, order))];
        order++;
        for (int i = 1; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    private void checkParentPriority(int currentPos) {
        if (currentPos == 1) {
            return;
        }

        int posParent = (currentPos + (dnary - 2)) / dnary;
        if (heap[currentPos].compareTo(heap[posParent]) < 0) {
            T tmp = heap[currentPos];
            heap[currentPos] = heap[posParent];
            heap[posParent] = tmp;
            checkParentPriority(posParent);
        }
    }

    public T min() {
        if (pos == 1) {
            return null;
        } else if (pos == 2) {
            return heap[--pos];
        }

        T min = heap[1];
        heap[1] = heap[--pos];
        checkChildPriority(1);

        return min;
    }

    private void checkChildPriority(int currentPos) {
        boolean end = false;
        T min = heap[currentPos];
        int posMin = currentPos;
        for (int i = -(dnary - 2); i < 2; i++) {
            if ((dnary * currentPos + i) == this.pos) {
                end = true;
            }
            if ((dnary * currentPos + i) > this.pos) {
                break;
            }
            if (min.compareTo(heap[dnary * currentPos + i]) > 0) {
                min = heap[dnary * currentPos + i];
                posMin = dnary * currentPos + i;
            }
        }
        if (min == heap[currentPos]) {
            return;
        }
        T tmp = heap[currentPos];
        heap[currentPos] = min;
        heap[posMin] = tmp;

        if (end) {
            return;
        }
        checkChildPriority(posMin);
    }

}
