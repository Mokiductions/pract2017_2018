package es.uned.lsi.eped.pract2017_2018;

import es.uned.lsi.eped.DataStructures.ListIF;
import java.util.Comparator;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class QueryComparator implements Comparator<Query> {
        
    public QueryComparator() {
    }

   @Override
    public int compare(Query q1, Query q2) {
        /*if (q1.getFreq() < q2.getFreq() || (q1.getFreq() == q2.getFreq() && q1.getText().compareTo(q2.getText()) > 0)) {
            return -1;
        } else if (q1.getFreq() > q2.getFreq() || (q1.getFreq() == q2.getFreq() && q1.getText().compareTo(q2.getText()) < 0)) {
            return 1;
        }
        return 0;*/
        if (q1.getFreq() < q2.getFreq()) {
            return -1;
        } else if (q1.getFreq() > q2.getFreq()) {
            return 1;
        }
        return 0;
    }

    public ListIF<Query> sort(ListIF<Query> queries) {
        return quicksort(queries, 1, queries.size());
    }

    private ListIF<Query> quicksort(ListIF<Query> queries, int left, int right) {
        Query pivot = queries.get(left);
        int i = left;
        int j = right;
        Query aux;
        while (i < j) {
            while (queries.get(i).compareTo(pivot) >= 0 && i < j) {
                i++;
            }
            while (queries.get(j).compareTo(pivot) < 0) {
                j--;
            }
            if (i < j) {          
                aux = queries.get(i);
                queries.set(i, queries.get(j));
                queries.set(j, aux);
            }
        }
        queries.set(left, queries.get(j));
        queries.set(j, pivot);
        if (left < j - 1) {
            quicksort(queries, left, j - 1);
        }
        if (j + 1 < right) {
            quicksort(queries, j + 1, right);
        }
        return queries;
        
        /*Query pivot = queries.get(left);
        int i = left;
        int j = right;
        Query aux;
        while (i < j) {
            while (queries.get(i).getFreq() >= pivot.getFreq() && i < j) {
                i++;
            }
            while (queries.get(j).getFreq() < pivot.getFreq()) {
                j--;
            }
            if (i < j) {          
                aux = queries.get(i);
                queries.set(i, queries.get(j));
                queries.set(j, aux);
            }
        }
        queries.set(left, queries.get(j));
        queries.set(j, pivot);
        if (left < j - 1) {
            quicksort(queries, left, j - 1);
        }
        if (j + 1 < right) {
            quicksort(queries, j + 1, right);
        }
        return queries;*/
        
        /*Query pivot = queries.get(left);
        int i = left;
        int j = right;
        Query aux;
        while (i < j) {
            while (compare(queries.get(i), pivot) >= 0 && i < j) {
                i++;
            }
            while (compare(queries.get(j), pivot) < 0) {
                j--;
            }
            if (i < j) {          
                aux = queries.get(i);
                queries.set(i, queries.get(j));
                queries.set(j, aux);
            }
        }
        queries.set(left, queries.get(j));
        queries.set(j, pivot);
        if (left < j - 1) {
            quicksort(queries, left, j - 1);
        }
        if (j + 1 < right) {
            quicksort(queries, j + 1, right);
        }
        return queries;*/
    }

   

}
