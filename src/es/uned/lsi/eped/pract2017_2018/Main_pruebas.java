package es.uned.lsi.eped.pract2017_2018;

import es.uned.lsi.eped.DataStructures.ListIF;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main_pruebas {

    public static void main(String[] args) throws IOException {
        //lectura de parámetros

        int rep = 50000; // Cantidad de repeticiones de cada operación
        int numConsultas = 16000; // 1000, 2000, 4000, 8000, 30776

        //estructura: L (lista) o T (árbol general)
        String estructure = "T";

        //fichero con el depósito de queries
        String pathDepot = "JdP-consultas " + numConsultas + ".txt";
        //fichero de operaciones
        String pathOperations = "JdP-operaciones " + numConsultas + ".txt";

        //creación del depósito de acuerdo a la estructura seleccionada
        QueryDepotIF QD = null;
        if (estructure.equalsIgnoreCase("L")) {
            QD = new QueryDepotList();
        }
        if (estructure.equalsIgnoreCase("T")) {
            QD = new QueryDepotTree();
        }
        //lectura de las queries del depósito
        FileReader fDepot = new FileReader(pathDepot);
        BufferedReader bDepot = new BufferedReader(fDepot);
        String lineDepot;
        while ((lineDepot = bDepot.readLine()) != null) {
            QD.incFreqQuery(lineDepot);
        }
        bDepot.close();
        System.out.println("Consultas almacenadaS: " + QD.numQueries());
        //lectura y ejecución de las operaciones 
        FileReader fOperations = new FileReader(pathOperations);
        BufferedReader bOperations = new BufferedReader(fOperations);
        String lineOperation;
        while ((lineOperation = bOperations.readLine()) != null) {
            Operation operation = new Operation(lineOperation);
            String op = operation.getOperation();
            String arg = operation.getArg();
            if (op.equalsIgnoreCase("F")) {
                long tInicial = System.currentTimeMillis();
                int f = 0;
                for (int cont = 1; cont < rep; cont++) {
                    f = QD.getFreqQuery(arg);
                }
                long tFinal = System.currentTimeMillis();
                double duracion = ((double) tFinal - (double) tInicial) / (double) rep;
                System.out.printf("%f\t", duracion);
            }
            if (op.equalsIgnoreCase("S")) {
                ListIF<Query> L = null;
                long tInicial = System.currentTimeMillis();
                int f = 0;
                for (int cont = 1; cont < rep; cont++) {
                    L = QD.listOfQueries(arg);
                }
                long tFinal = System.currentTimeMillis();
                double duracion = ((double) tFinal - (double) tInicial) / (double) rep;
                /*IteratorIF<Query> I = L.iterator();
                while (I.hasNext()) {
                    Query query = I.getNext();
                    String textQuery = query.getText();
                    int frec = query.getFreq();
                    System.out.println("\t\"" + textQuery + "\" con frecuencia " + frec + ".");
                }*/
                System.out.printf("%f\t", duracion);

            }
        }
        bOperations.close();
    }

}
