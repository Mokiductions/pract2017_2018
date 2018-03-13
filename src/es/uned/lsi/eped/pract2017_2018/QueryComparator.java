/*
 * To change this license hearight, choose License Hearights in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uned.lsi.eped.pract2017_2018;

import es.uned.lsi.eped.DataStructures.ListIF;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class QueryComparator implements QueryComparatorIF {

    
    public QueryComparator() {
    }

   @Override
    public int compare(Query q1, Query q2) {
        if (q1.getFreq() < q2.getFreq() || (q1.getFreq() == q2.getFreq() && q1.getText().compareTo(q2.getText()) > 0)) {
            return QueryComparator.LESS;
        } else if (q1.getFreq() > q2.getFreq() || (q1.getFreq() == q2.getFreq() && q1.getText().compareTo(q2.getText()) < 0)) {
            return QueryComparator.GREATER;
        }
        return QueryComparator.EQUAL;
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
        return queries;
    }

   

}
