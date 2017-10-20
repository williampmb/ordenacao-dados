/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasort;

import datasort.algorithm.Sort;
import datasort.data.ElementPair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
        System.out.println("-------------------------------");
        System.out.println("***********Bem Vindo***********");
        System.out.println("-------------------------------");

        //Configurar o caminho do arquivo de dados a ser lido
        System.out.println("Insira o caminho do arquivo dos dados para ordenacao:");
        String file = sc.next();
        System.out.println("");
        while (true) {
            File dataFile = new File(file);
            if (dataFile.isFile()) {
                config.setFilePath(file);
                break;
            }
            System.out.println("");
            System.out.println("Arquivo nao existente, tente novamente: ");
            file = sc.next();

        }
        System.out.println("");
        System.out.println("------ Arquivo reconhecido ------");
        System.out.println("");

        //Setar mode de debugar
        setDebugMode(sc);

        //Escolher qual tipo de ordenacao será executada
        setOrderType(sc);

        //Configurar por qual elemento será ordenado
        setOrderBy(sc);

        // Ler o arquivo
        ElementPair[] array = ElementPair.initializeArrayElementPairFromFile(config.getFilePath());
        config.setElementsType(array[0]);

        copy = array.clone();
        boolean exit = false;
        while (!exit) {

            Sort sort = config.getSort();
            createEmptySpaceOnConsole(3);
            System.out.println("Array Inicial:");
            sort.print(array);
            createEmptySpaceOnConsole(2);

            System.out.println("----------------------");
            if(!config.debugModeOn){
                System.out.println("Modo de Debugacao OFF");
            }
            ElementPair[] execute = sort.execute(array);
            System.out.println("----------------------");

            createEmptySpaceOnConsole(2);

            System.out.println("Array Ordenado");
            sort.print(execute);
            createEmptySpaceOnConsole(3);

            System.out.println("*****************************************************************************************");
            System.out.println("-------------------------------");
            System.out.println("O que deseja fazer: ");
            System.out.println("1 - Configurar o tipo de ordenacao");
            System.out.println("2 - Configurar por qual elemento ordenar");
            System.out.println("3 - Configurar o item 1 e 2");
            System.out.println("4 - Configurar modo de debug");
            System.out.println("5 - Sair");
            System.out.println("-------------------------------");

            String next = sc.next();
            int nextMove;
            while (true) {
                boolean checkIfInteger = config.checkIfInteger(next);
                nextMove = Integer.parseInt(next);
                if (checkIfInteger && nextMove > 0 && nextMove < 6) {
                    break;
                }
                System.out.println("Opcao invalida");
                next = sc.next();
            }

            switch (nextMove) {
                case 1:
                    setOrderType(sc);
                    break;
                case 2:
                    setOrderBy(sc);
                    break;
                case 3:
                    setOrderType(sc);
                    setOrderBy(sc);
                    break;
                case 4:
                    setDebugMode(sc);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }
            if (!exit) {
                array = copy.clone();
            }
        }

        System.out.println("-------------------------------");
        System.out.println("***********Ate mais***********");
        System.out.println("-------------------------------");

    }

    private static void setOrderBy(Scanner sc) throws NumberFormatException {
        System.out.println("Escolhe por quais elementos deseja ordenar: ");
        printOrderBy();

        while (true) {
            String orderBy = sc.next();
            boolean checkIfInteger = config.checkIfInteger(orderBy);
            if (checkIfInteger) {
                int option = Integer.parseInt(orderBy);
                if (option > 0 && option < 4) {
                    config.setOrderBy(option);
                    break;
                }
            }
            System.out.println("");
            System.out.println("Opcao Invalida. Digite 1,2 ou 3");
            System.out.println("");
            printOrderBy();
        }
    }

    public static void printOrderName() {
        System.out.println("");
        System.out.println("---------------------------------------------------");
        System.out.println("Escolhe o que qual ordenação você gostaria de usar:");
        System.out.println("1 - InsertionSort");
        System.out.println("2 - SelectionSort");
        System.out.println("3 - HeapSort");
        System.out.println("4 - MergeSort");
        System.out.println("5 - BubbleSort");
        System.out.println("---------------------------------------------------");
    }

    public static void printOrderBy() {
        System.out.println("");
        System.out.println("---------------------------------------------------");
        System.out.println("Escolhe o que qual ordenação você gostaria de usar:");
        System.out.println("1 - Primeiro Elemento");
        System.out.println("2 - Segundo Elemento");
        System.out.println("3 - Ambos Elementos");
        System.out.println("---------------------------------------------------");
    }

    private static void setOrderType(Scanner sc) {
        printOrderName();
        String orderNameStr;
        while (true) {
            orderNameStr = sc.next();
            boolean checkIfInteger = config.checkIfInteger(orderNameStr);
            if (checkIfInteger) {
                int option = Integer.parseInt(orderNameStr);
                if (option > 0 && option < 6) {
                    config.setSortType(option);
                    break;
                }
            }
            System.out.println("");
            System.out.println("Opcao Invalida. Digite 1,2,3,4 ou 5");
            System.out.println("");
            printOrderName();
        }
    }

    public static void createEmptySpaceOnConsole(int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("");
        }
    }

    private static void setDebugMode(Scanner sc) {
        printDebugModeOption();
        String next = sc.next();
        if (next.equals("1") || next.toLowerCase().equals("sim")) {
            config.debugModeOn = true;
        } else {
            config.debugModeOn = false;
        }
    }

    private static void printDebugModeOption() {
        System.out.println("");
        System.out.println("---------------------------------------------------");
        System.out.println("Deseja ativar a impressao do array a cada iteracao:");
        System.out.println("1 - Sim");
        System.out.println("2 - Nao");
        System.out.println("---------------------------------------------------");
    }

}
