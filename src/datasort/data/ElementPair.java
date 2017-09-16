/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort.data;

import datasort.Configuration;
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
        if (e1 == null || e2 == null) {
            throw new IllegalArgumentException();
        }

        Configuration config = Configuration.getInstance();
        if (config.firstElementIsInteger) {
            this.element1 = Integer.parseInt(e1);
        } else {
            this.element1 = e1;
        }
        if (config.secondElementIsInteger) {
            this.element2 = Integer.parseInt(e2);
        } else {
            this.element2 = e2;
        }
    }

    public static ElementPair[] initializeArrayElementPairFromFile(String file) throws FileNotFoundException, IOException {
        BufferedReader br = null;

        br = new BufferedReader(new FileReader(file));
        ArrayList<ElementPair> elements = new ArrayList<>();

        String line = br.readLine();
        String[] tok = line.split(DataSort.splitTag);
        Configuration config = Configuration.getInstance();
        config.setVariableType(tok[0], tok[1]);
      
        while (line != null && !line.equals("")) {
            tok = line.split(DataSort.splitTag);
            ElementPair pair = new ElementPair(tok[0], tok[1]);
            elements.add(pair);
            line = br.readLine();
        }
        
        ElementPair[] res = new ElementPair[elements.size()];
    
        return elements.toArray(res);
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
        if (DataSort.config.firstElementIsInteger) {
            return (Integer) this.element1 - (Integer) o.element1;
        } else {
            return ((String) this.element1).compareTo((String) o.getElement1());
        }
    }

    private int compareToSecond(ElementPair o) {
        if (DataSort.config.secondElementIsInteger) {
            return (Integer) this.element2 - (Integer) o.element2;
        } else {
            return ((String) this.element2).compareTo((String) o.getElement2());
        }
    }

    private int compareToBoth(ElementPair o) {
        int comp = this.compareToFirst(o);
        if (comp == 0) {
            return this.compareToSecond(o);
        }
        return comp;
    }

}
