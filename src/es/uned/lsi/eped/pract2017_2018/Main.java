package es.uned.lsi.eped.pract2017_2018;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.ListIF;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Pruebas custom ------------------------------------------------------------------------->
       /* System.out.println("Arrancando como QueryDepotList");

        QueryDepotIF QD = null;
        QD = new QueryDepotList();

        String pathDepot = "JdP-consultas.txt";

        // Carga las consultas del archivo
        FileReader fDepot = new FileReader(pathDepot);
        BufferedReader bDepot = new BufferedReader(fDepot);
        String lineDepot;
        while ((lineDepot = bDepot.readLine()) != null) {
            QD.incFreqQuery(lineDepot);
        }
        bDepot.close();

        // Muestra la cantidad de consultas almacenadas
        System.out.println("Consultas almacenadas: " + QD.numQueries());
/*
        // Muestra la frecuencia de la consulta 'car tree'
        System.out.println("\nLa consulta 'car tree' ha sido realizada " + QD.getFreqQuery("car tree") + " veces.");

        // Muestra las consultas que empiezan con 'car tree'
        System.out.println("\nConsultas que empiezan por 'car tree':\n");
        ListIF<Query> queries = new List();
        queries = QD.listOfQueries("car tree");
        if (queries.isEmpty()) {
            System.out.println("Ninguna");
        } else {
            IteratorIF<Query> i = queries.iterator();
            while (i.hasNext()) {
                Query query = i.getNext();
                System.out.println(query.toString());
            }
        }
        
        // Muestra las consultas que empiezan con 'asdf'
        System.out.println("\nConsultas que empiezan por 'asdfghjkl':\n");
        queries = new List();
        queries = QD.listOfQueries("asdfghjkl");
        if (queries.isEmpty()) {
            System.out.println("Ninguna");
        } else {
            IteratorIF<Query> i = queries.iterator();
            while (i.hasNext()) {
                Query query = i.getNext();
                System.out.println(query.toString());
            }
        }
        
        // Fin de pruebas custom ------------------------------------------------------------------>*/
/* //lectura de par�metros
        //estructura: L (lista) o T (�rbol general)
        String estructure = args[0];

        //fichero con el dep�sito de queries
        String pathDepot = args[1];
        //fichero de operaciones
        String pathOperations = args[1];*/
        // Custom ---------------------------------------------------------------------------------->
        String estructure = "L";
        String pathDepot = "JdP-consultas.txt";
        //String pathDepot = "edC.txt";
        String pathOperations = "JdP-operaciones.txt";
        double t0ttl = System.nanoTime();
        // Custom ---------------------------------------------------------------------------------->

        //creaci�n del dep�sito de acuerdo a la estructura seleccionada
        QueryDepotIF QD = null;
        if (estructure.equalsIgnoreCase("L")) {
            QD = new QueryDepotList();
        }
        if (estructure.equalsIgnoreCase("T")) {
            QD = new QueryDepotTree();
        }
        //lectura de las queries del dep�sito
        FileReader fDepot = new FileReader(pathDepot);
        BufferedReader bDepot = new BufferedReader(fDepot);
        String lineDepot;
        double t0load = System.nanoTime();
        while ((lineDepot = bDepot.readLine()) != null) {
            QD.incFreqQuery(lineDepot);
        }
        double t1load = System.nanoTime() - t0load;
        t1load = t1load / 1000000.0;
        bDepot.close();
        System.out.println("-Tiempo: " + t1load);
        System.out.println("Consultas almacenadas: " + QD.numQueries());
        //lectura y ejecuci�n de las operaciones 
        FileReader fOperations = new FileReader(pathOperations);
        BufferedReader bOperations = new BufferedReader(fOperations);
        String lineOperation;
        while ((lineOperation = bOperations.readLine()) != null) {
            Operation operation = new Operation(lineOperation);
            String op = operation.getOperation();
            String arg = operation.getArg();
            if (op.equalsIgnoreCase("F")) {
                double t0 = System.nanoTime();
                int f = QD.getFreqQuery(arg);
                double t1 = System.nanoTime() - t0;
                t1 = t1 / 1000000.0;
                System.out.println("La frecuencia de \"" + arg + "\" es " + f + ".");
                System.out.println("-Tiempo: " + t1);
            }
            if (op.equalsIgnoreCase("S")) {
                double t0 = System.nanoTime();
                ListIF<Query> L = QD.listOfQueries(arg);
                double t1 = System.nanoTime() - t0;
                t1 = t1 / 1000000.0;
                System.out.println("La lista de sugerencias para \"" + arg + "\" es:");
                IteratorIF<Query> I = L.iterator();
                while (I.hasNext()) {
                    Query query = I.getNext();
                    String textQuery = query.getText();
                    int frec = query.getFreq();
                    System.out.println("\t\"" + textQuery + "\" con frecuencia " + frec + ".");
                }
                System.out.println("-Tiempo: " + t1);

            }
        }
        bOperations.close();
        double t1ttl = System.nanoTime() - t0ttl;
        t1ttl = t1ttl / 1000000.0;
        System.out.println("-Tiempo total: " + t1ttl);
    }

}
