package es.uned.lsi.eped.pract2017_2018;

/**
 *
 * @author Miquel Ginés Borràs
 */
public class Query implements Comparable<Query> {
    
    private String text;
    private int freq;
    
    /**
     * Constructor vacío.
     */
    public Query() {
        
    }

    public Query(String text) {
        this.text = text;
        this.freq = 1;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFreq() {
        return freq;
    }

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
