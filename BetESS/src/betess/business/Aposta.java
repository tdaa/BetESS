package betess.business;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manuel Sousa
 * @author Tiago Alves
 */
public class Aposta implements Serializable {
    
    private int idAposta;
    private Map<Integer, Evento> eventos;
    private Map<Integer, Double> oddsApostadas;
    private double valor;

    public Aposta() {
        this.eventos = new HashMap<>();
        this.oddsApostadas = new HashMap<>();
        this.valor = 0;
    }

    public Aposta(int id) {
        this.idAposta = id;
        this.eventos = new HashMap<>();
        this.oddsApostadas = new HashMap<>();
        this.valor = 0;
    }
    
    public Aposta(Aposta a) {
        this.idAposta = a.getIdAposta();
        this.valor = a.getValor();
        this.eventos = a.getEventos();
        this.oddsApostadas = a.getOdds();
    }
    
    public Aposta(int idAposta, Map<Integer, Evento> eventos, 
                  Map<Integer, Double> odds, double valor) 
    {
        this.idAposta = idAposta;
        this.setEventos(eventos);
        this.setOdds(odds);
        this.valor = valor;
    }

    /**
     * Getters e Setters
     * @return 
     */
    
    public int getIdAposta() {
        return this.idAposta;
    }

    public void setIdAposta(int idAposta) {
        this.idAposta = idAposta;
    }

    public Map<Integer, Evento> getEventos() {
        Map<Integer, Evento> evs = new HashMap<>();
        
        this.eventos.entrySet().forEach(m -> {
            evs.put(m.getKey(), m.getValue());
        });
        
        return evs;
    }
    
    public void setEventos(Map<Integer, Evento> evs) {
        evs.entrySet().forEach(m -> {
            this.eventos.put(m.getKey(), m.getValue());
        });
    }
    
    public Map<Integer, Double> getOdds() {
        Map<Integer, Double> odds = new HashMap<>();
        
        this.oddsApostadas.entrySet().forEach(m -> {
            odds.put(m.getKey(), m.getValue());
        });
        
        return odds;
    }
    
    public void setOdds(Map<Integer, Double> odds) {
        odds.entrySet().forEach(m -> {
            this.oddsApostadas.put(m.getKey(), m.getValue());
        });
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public double getTotalOdds() {
        /*
        //BUG!!!! odssApostadas está a NULL!!!!
        double total = 0;
        
        if (this.oddsApostadas == null)
            total = 2;
        
        return total; */
        
        return this.oddsApostadas.values()
                .stream()
                .mapToDouble(d -> d)
                .sum();
    }
    
    public double getGanhoTotal() {
        return (this.valor * this.getTotalOdds());
    }

    /**
     * Método toString().
     * Retorna uma representação textual do objeto.
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Aposta{" + "idAposta=" + this.idAposta + 
               ", eventos=" + this.eventos + ", valor=" + this.valor + '}';
    }
    
    /**
     * Método clone().
     * Retorna uma cópia do objeto.
     * 
     * @return 
     */
    public Aposta clone() {
        return new Aposta(this);
    }
    
    /**
     * Método addEventoToAposta(...).
     * Adiciona evento a uma aposta.
     * 
     * @param e
     * @param odd 
     */
    public void addEventoToAposta(Evento e, double odd) {
        if (!this.eventos.containsKey(e.getIdEvento())) {
            this.eventos.put(e.getIdEvento(), e);
            this.oddsApostadas.put(e.getIdEvento(), odd);
            //this.valor += odd;
        }
    }
    
    /**
     * Método remEventoFromAposta(...).
     * Remove evento de uma aposta.
     * 
     * @param e 
     */
    public void remEventoFromAposta(Evento e) {
        this.eventos.remove(e.getIdEvento(), e);
        //this.valor -= this.oddsApostadas.get(e.getIdEvento());
        this.oddsApostadas.remove(e.getIdEvento(), e);
    }
}
