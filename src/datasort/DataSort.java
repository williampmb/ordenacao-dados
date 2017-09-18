/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort;

import datasort.algorithm.HeapSort;
import datasort.algorithm.InsertionSort;
import datasort.algorithm.MergeSort;
import datasort.algorithm.SelectionSort;
import datasort.algorithm.Sort;
import datasort.data.ElementPair;
import datasort.structure.MyHeap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author willi
 */
public class DataSort {

    public static Configuration config;
    private static ElementPair[] copy;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        config = Configuration.getInstance();

        printOptions();

        while (true) {
            String optionStr = sc.next();
            boolean checkIfInteger = config.checkIfInteger(optionStr);
            if (checkIfInteger) {
                int option = Integer.parseInt(optionStr);
                if (option > 0 && option < 5) {
                    break;
                }
            }
            System.out.println("");
            System.out.println("Opcao Invalida. Digite 1,2,3 ou 4");
            System.out.println("");
            printOptions();
        }
        
        System.out.println("");
        System.out.println("Insira o caminho do arquivo dos dados para ordenacao:");
        String file = sc.next();
        while (true) {
            File dataFile = new File(file);
            if(dataFile.isFile()){
                config.setFilePath(file);
                break;
            }
            System.out.println("");
            System.out.println("Arquivo nao existente, tente novamente: ");
            file = sc.next();
            
        }
        System.out.println("--------------------");
        System.out.println("Arquivo reconhecido");
        System.out.println("--------------------");

        ElementPair[] array = ElementPair.initializeArrayElementPairFromFile(config.getFilePath());

        config.setElementsType(array[0]);

        copy = array.clone();
        
        //TO-DO
        
        Sort sort = new HeapSort();
        //Sort sort = new MergeSort();

        //Sort sort = new SelectionSort();
        //Sort sort = new InsertionSort();
        sort.print(array);
        System.out.println("");
        ElementPair[] execute = sort.execute(array);
        sort.print(execute);

    }

    public static void printOptions() {
        System.out.println("---------------------------------------------------");
        System.out.println("Escolhe o que qual ordenação você gostaria de usar:");
        System.out.println("1 - InsertionSort");
        System.out.println("2 - SelectionSort");
        System.out.println("3 - HeapSort");
        System.out.println("4 - MergeSort");
        System.out.println("---------------------------------------------------");
    }

}
