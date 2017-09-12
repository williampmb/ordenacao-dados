/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort;

import datasort.algorithm.SelectionSort;
import datasort.data.ElementPair;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author willi
 */
public class DataSort {

    private static final String filePath = "src/datasort/data.txt";
    private static final String orderBy = "FIRST";
    public static final String splitTag = ",";
    public static Configuration config;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        
        ElementPair[] array = ElementPair.initializeArrayElementPairFromFile(filePath);
        
        config = Configuration.getInstance();
        
        SelectionSort sort = new SelectionSort();
        sort.print(array);
        System.out.println("");
        array = sort.execute(array);
        sort.print(array);

    }


}
