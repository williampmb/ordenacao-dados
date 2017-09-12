/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.data;

import datasort.DataSort;
import datasort.OrderBy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class ElementPair implements Comparable<ElementPair> {

    Object element1, element2;
    boolean isInteger;

    public Object getElement1() {
        return element1;
    }

    public Object getElement2() {
        return element2;
    }

    private ElementPair(String e1, String e2) {
        if (element1 == null || element2 == null) {
            //throw execption
        }

        //element1 = e1;
        // element2 = 2;
        boolean firstElementInteger = checkIfInteger(e1);
        boolean secondElementInteger = checkIfInteger(e2);
        if (firstElementInteger && secondElementInteger) {
            this.isInteger = true;
            this.element1 = Integer.parseInt(e1);
            this.element2 = Integer.parseInt(e2);
        } else {
            this.isInteger = false;
            this.element1 = e1;
            this.element2 = e2;
        }
    }

    public static ElementPair[] initializeArrayElementPairFromFile(String file) throws FileNotFoundException, IOException {
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        ArrayList<ElementPair> elements = new ArrayList<>();

        String line = br.readLine();
        while (line != null && !line.equals("")) {
            String[] tok = line.split(DataSort.splitTag);
            ElementPair pair = new ElementPair(tok[0], tok[1]);
            elements.add(pair);
            line = br.readLine();
        }
        ElementPair[] res = new ElementPair[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            res[i] = elements.get(i);
        }
        return res;
    }

    private boolean checkIfInteger(String ele1) {
        for (char c : ele1.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "<" + this.element1 + ">,<" + this.element2 + ">";
    }

    @Override
    public int compareTo(ElementPair o) {
        if (DataSort.config.orderBy == OrderBy.FIRST) {
            return compareToFirst(o);
        } else if (DataSort.config.orderBy == OrderBy.SECOND) {
            return compareToSecond(o);
        } else {
            return compareToBoth(o);
        }
    }

    private boolean checkTypeFromObject(Object elementPair) {
        if (elementPair instanceof Integer) {
            return true;
        } else {
            return false;
        }
    }

    private int compareToFirst(ElementPair o) {
        if(DataSort.config.isInteger){
            return (Integer) this.element1 - (Integer) o.element1;
        }else{
            return ((String)this.element1).compareTo((String)o.getElement1());
        }
    }

    private int compareToSecond(ElementPair o) {
        if(DataSort.config.isInteger){
            return (Integer) this.element2 - (Integer) o.element2;
        }else{
            return ((String)this.element2).compareTo((String)o.getElement2());
        }
    }

    private int compareToBoth(ElementPair o) {
         if(DataSort.config.isInteger){
            int comp = (Integer)this.element1 - (Integer) o.element1;
            if(comp == 0){
                return (Integer) this.element2 - (Integer) o.element2; 
            }
            return comp;
        }else{
            int comp = ((String)this.element1).compareTo((String)o.getElement1());
            if(comp == 0){
                return ((String)this.element2).compareTo((String)o.getElement2()); 
            }
            return comp;
        }
    }

}
