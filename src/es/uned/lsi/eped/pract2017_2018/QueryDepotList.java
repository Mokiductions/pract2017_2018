package es.uned.lsi.eped.pract2017_2018;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class QueryDepotList implements QueryDepotIF {

    private ListIF<Query> queryList;

    public QueryDepotList() {
        queryList = new List<Query>();
    }

    /* Devuelve el n�mero de consultas diferentes (sin contar repeticiones) */
    /* que hay almacenadas en el dep�sito */
    /* @returns el n�mero de consultas diferentes almacenadas */
    @Override
    public int numQueries() {
        //viewQueries(); // Habrá que eliminar esta línea, es sólo para pruebas ---------------------------->
        return queryList.size();               
    }

    /* Consulta la frecuencia de una consulta en el dep�sito */
    /* @returns la frecuencia de la consulta. Si no est�, devolver� 0 */
    /* @param el texto de la consulta */
    @Override
    public int getFreqQuery(String q) {
        Query query = searchQuery(q);
        if (query == null) { // No existe la consulta
            return 0;
        } else { // Sí que existe la consulta, devuelve la frecuencia
            return query.getFreq();
        }
    }
    
    private Query searchQuery(String text) {
        Query q;
        IteratorIF<Query> i = queryList.iterator();
        while(i.hasNext()) {
            q = i.getNext();
            if (q.getText().equals(text)) {
                return q;
            }
        }
        return null;
    }

    /* Dado un prefijo de consulta, devuelve una lista, ordenada por */
    /* frecuencias de mayor a menor, de todas las consultas almacenadas */
    /* en el dep�sito que comiencen por dicho prefijo */
    /* @returns la lista de consultas ordenada por frecuencias y orden */
    /* lexicogr�fico en caso de coincidencia de frecuencia */
    /* @param el prefijo */
    @Override
    public ListIF<Query> listOfQueries(String prefix) {
        ListIF<Query> queryTemp = new List();
        Query query;
        // Aquí habrá que crear un comparador para ordenarlo
        IteratorIF<Query> i = queryList.iterator();
        while(i.hasNext()) {
            query = i.getNext();
            if (query.getText().startsWith(prefix)) {
                queryTemp.insert(query, queryTemp.size() + 1);
            }
        }
        return queryTemp;
    }

    /* Incrementa en uno la frecuencia de una consulta en el dep�sito */
     /* Si la consulta no exist�a en la estructura, la deber� a�adir */
    /* @param el texto de la consulta */
    @Override
    public void incFreqQuery(String q) {
        Query query = searchQuery(q);
        if (query == null) { // No existe, crear la query
            queryList.insert(new Query(q), queryList.size() + 1);
        } else { // Sí que existe, incrementar la frecuencia
            query.setFreq(query.getFreq() + 1);
        }
    }
    
    // Método de pruebas ----------------------------------------------------------------------->
    private void viewQueries() {
        System.out.println("\nConsultas almacenadas:\n");
        IteratorIF<Query> i = queryList.iterator();
        while(i.hasNext()) {
            System.out.println(i.getNext().toString());
        }
    }

}
