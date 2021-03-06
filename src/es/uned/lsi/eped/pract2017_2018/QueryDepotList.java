package es.uned.lsi.eped.pract2017_2018;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class QueryDepotList implements QueryDepotIF {

    private ListIF<Query> queryDepot;

    public QueryDepotList() {
        queryDepot = new List<Query>();
    }

    /* Devuelve el n�mero de consultas diferentes (sin contar repeticiones) */
 /* que hay almacenadas en el dep�sito */
 /* @returns el n�mero de consultas diferentes almacenadas */
    @Override
    public int numQueries() {
        //printQueries();
        return queryDepot.size();
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
        IteratorIF<Query> i = queryDepot.iterator();
        while (i.hasNext()) {
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
        QueryComparator qc = new QueryComparator();
        ListIF<Query> queryTemp = new List();
        Query query;
        IteratorIF<Query> i = queryDepot.iterator();
        boolean stop = false;
        while (i.hasNext() && !stop) {
            query = i.getNext();
            if (query.getText().startsWith(prefix)) {
                queryTemp.insert(query, queryTemp.size() + 1);
            }
            if (!query.getText().isEmpty()) {
                if (query.getText().substring(0, 1).compareTo(prefix.substring(0, 1)) > 0) {
                    stop = true;
                }
            }
        }
        queryTemp = qc.sort(queryTemp);
        return queryTemp;
    }

    /* Incrementa en uno la frecuencia de una consulta en el dep�sito */
 /* Si la consulta no exist�a en la estructura, la deber� a�adir */
 /* @param el texto de la consulta */
    @Override
    public void incFreqQuery(String q) {
        boolean found = false;
        int c = 1;
        Query query;
        IteratorIF<Query> i = queryDepot.iterator();
        while (i.hasNext() && !found) {
            query = i.getNext();
            if (q.compareTo(query.getText()) == 0) { // Es igual
                query.setFreq(query.getFreq() + 1);
                found = true;
            } else if (q.compareTo(query.getText()) < 0) { // Es inferior
                queryDepot.insert(new Query(q), c);
                found = true;
            } else { // Es superior
                c++;
            }
        }
        if (!found) {
            queryDepot.insert(new Query(q), queryDepot.size() + 1);
        }
    }

    private void printQueries() {
        IteratorIF<Query> i = queryDepot.iterator();
        while (i.hasNext()) {
            Query query = i.getNext();
            System.out.println(query.toString());
        }
    }
}
