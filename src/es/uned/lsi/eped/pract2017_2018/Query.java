package es.uned.lsi.eped.pract2017_2018;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class Query implements Comparable<Query> {
    
    private String text;
    private int freq;
    
    /**
     * Construye una nueva query con el texto pasado como parámetro
     * @param text el texto para la query
     */
    public Query(String text) {
        this.text = text;
        this.freq = 1;
    }

    /**
     * Devuelve el texto de una query
     * @return el texto actual de la query
     */
    public String getText() {
        return text;
    }

    /**
     * Devuelve la frecuencia de una query
     * @return la frecuencia actual de la query
     */
    public int getFreq() {
        return freq;
    }

    /**
     * Modifica la frecuencia de la query
     * @param freq la nueva frecuencia de la query
     */
    public void setFreq(int freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "Consulta '" + text + "' - Frecuencia " + freq;
    }

    @Override
    public int compareTo(Query q) {
        if (this.getFreq() < q.getFreq() || (this.getFreq() == q.getFreq() && this.getText().compareTo(q.getText()) > 0)) {
            return -1;
        } else if (this.getFreq() > q.getFreq() || (this.getFreq() == q.getFreq() && this.getText().compareTo(q.getText()) < 0)) {
            return 1;
        }
        return 0;
    }
    
}
