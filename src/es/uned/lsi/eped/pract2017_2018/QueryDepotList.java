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
        return queryList.size();
    }

    /* Consulta la frecuencia de una consulta en el dep�sito */
    /* @returns la frecuencia de la consulta. Si no est�, devolver� 0 */
    /* @param el texto de la consulta */
    @Override
    public int getFreqQuery(String q) {
        // Aquí habrá que implementar otro método privado para buscar una consulta en la lista
        return 1;
    }
    
    private Query searchQuery(String text) {
        Query q;
        IteratorIF<Query> i = queryList.iterator();
        while(i.hasNext()) {
            
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
        // Aquí habrá que crear un comparador para ordenarlo
        return null;
    }

    /* Incrementa en uno la frecuencia de una consulta en el dep�sito */
     /* Si la consulta no exist�a en la estructura, la deber� a�adir */
    /* @param el texto de la consulta */
    @Override
    public void incFreqQuery(String q) {
        // Aquí habrá que usar el buscador creado en getFreqQuery() para saber si ya existe
        // y saber si hay que aumentar la frecuencia o añadir de nuevo la consulta.
        // Si la lista está vacía hay que añadir directamente
    }

}
