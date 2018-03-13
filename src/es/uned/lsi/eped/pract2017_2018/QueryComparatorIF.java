/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uned.lsi.eped.pract2017_2018;

/**
 *
 * @author PC_15
 */
public interface QueryComparatorIF {
    
    public final int LESS = -1, EQUAL = 0, GREATER = 1;
    
    public int compare(Query q1, Query q2);
    
    
    
}
